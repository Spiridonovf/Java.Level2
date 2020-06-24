package Level2.Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private ServerSocket serverSocket;
    private int port=8149;


    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Ожидаем подключений на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Пользователь соединился");
                UserThread user = new UserThread(socket, this);
                user.start();
            }
        } catch (IOException ex) {
            System.out.println("Ошибка ввода вывода: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.execute();
    }


    public class WriteServerThread extends Thread {
        private PrintWriter writer;
        private Socket socket;
        private String name;

        public WriteServerThread(Socket socket,String name) {
            this.socket = socket;
            this.name = name;
            try {
                OutputStream output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
            } catch (IOException ex) {
                System.out.println("Ошибка записи: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        public String getInputString(String str){
            String input = null;
            System.out.print(str + " ");
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
                input = is.readLine();
                if(input.length() == 0) return null;
            } catch(IOException e) {
                System.out.println(e);
            }
            return input;
        }
        public void run() {

            String text="";
            do {
                text = getInputString("["+name+"]: ");
                writer.println(text);
            } while (!text.equals("/end"));
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Ошибка записи: " + ex.getMessage());
            }
        }
    }
    public class ReadServerThread extends Thread {
        private BufferedReader reader;
        private Socket socket;
        private String name;
        public ReadServerThread(Socket socket,String name) {
            this.socket = socket;
            this.name = name;
            try {
                InputStream input = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
            } catch (IOException ex) {
                System.out.println("Ошибка чтения: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        public void run() {
            while (true) {
                try {
                    String response = reader.readLine();
                    if (response.length()>0){
                        System.out.println("\n"+"["+name+"]"+ response);
                    }
                } catch (IOException ex) {
                    if (socket.isClosed()){
                        break;}
                    else{
                        System.out.println("Ошибка ввода вывода при чтении: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    public class UserThread extends Thread {
        private Socket socket;
        private ChatServer server;
        private PrintWriter writer;

        public UserThread(Socket _socket,ChatServer _server) {
            //super();
            this.socket = _socket;
            this.server = _server;
        }
        void sendMessage(String message) {
            writer.println(message);
        }
        @Override
        public void run() {
            try {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
                String serverMessage = "Клиент подсоединился";
                sendMessage(serverMessage);
                WriteServerThread write = new WriteServerThread(socket,"Server");
                write.start();
                ReadServerThread read = new ReadServerThread(socket,"Client");
                read.start();
                write.join();
                read.join();
                socket.close();
                serverMessage = "Клиент отсоединился";
                sendMessage(serverMessage);

            } catch (IOException | InterruptedException ex) {
                System.out.println("Ошибка потока: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
