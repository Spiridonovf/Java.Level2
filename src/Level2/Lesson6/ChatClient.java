package Level2.Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    private String hostname = "localhost";
    private int port=8149;

    public static void main(String[] args) {


        ChatClient client = new ChatClient();
        client.execute();

    }
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Соединение с сервером установлено");
            //создаем 2 потока один на чтение, другой на запись
            ReadThread rth = new ReadThread(socket);
            rth.start();
            WriteThread wth  = new WriteThread(socket);
            wth.start();
            rth.join();
            wth.join();

        } catch (UnknownHostException ex) {
            System.out.println("Сервер не найден: " + ex.getMessage());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Ошибка ввода вывода: " + ex.getMessage());
        }
    }

    public class WriteThread extends Thread {
        private PrintWriter writer;
        private Socket socket;

        public WriteThread(Socket socket) {
            this.socket = socket;
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
                text = getInputString("[Вы]: ");
                writer.println(text);
            } while (!text.equals("/end"));
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Ошибка записи: " + ex.getMessage());
            }
        }
    }

    public class ReadThread extends Thread {
        private BufferedReader reader;
        private Socket socket;
        public ReadThread(Socket socket) {
            this.socket = socket;
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
                    //System.out.println("\n" + response);
                    if (response.length()>0){
                        System.out.println("\n"+"[Сервер]: "+ response);
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
}
