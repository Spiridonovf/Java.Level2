package Level2.Lesson8;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client implements Runnable {
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;
    private String login;
    private String password;
    private String serverName;
    private int port;

    private boolean connected = false;
    private boolean authok = false;
    private boolean stopped = false;

    public Client(String _serverName, int _port) {
        try {
            this.serverName = _serverName;
            this.port = _port;
            this.socket = new Socket(_serverName, _port);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.connected  = true;
            PrintMessage("соединение установлено");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        if (message.isEmpty()) {
            return;
        }
        try {
            this.out.writeUTF(message);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(String _login,String _password) {
        this.login = _login;
        this.password = _password;
        sendMessage("/auth " + this.login + " " + this.password);
    }

    public void logout() {
        this.stopped = true;
        PrintMessage("Вы " + nick + " вышли из чата");
        sendMessage("/end");
        //in.close();
        //out.close();
        //socket.close();
    }

    public void PrintMessage(String str){
        System.out.println( new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime())+" "+str);
    }


    @Override
    public void run() {
        try {
            while ((socket.isConnected())&(!socket.isClosed()) &(!this.stopped) ){
                if (in.available()>0) {
                    String msg = in.readUTF();
                    if (nick != null) {
                        if (msg.startsWith("/")) {
                            if (msg.equalsIgnoreCase("/end")) {
                                PrintMessage("сервер завершил работу");
                            }
                        } else {
                            if (!msg.isEmpty()) {
                                PrintMessage(msg);
                            }
                        }
                    } else {
                        if (msg.startsWith("/authok ")) {
                            String[] elements = msg.split(" ");
                            nick = elements[1];
                            authok = true;
                            PrintMessage("Вы авторизованы под ником: " + nick);

                        } else if (msg.startsWith("/authfail")) {
                            PrintMessage("Ошибка авторизации");
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }


    }
}




