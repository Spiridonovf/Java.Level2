package Level2.Lesson7;

import Level2.Lesson6.ChatClient;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;
    private String login;
    private String password;
    private String serverName;
    private int port;

    private boolean authok = false;
    private boolean stopped = false;

    public Client(String _serverName, int _port, String _login, String _password) {
        this.serverName = _serverName;
        this.port = _port;
        this.login = _login;
        this.password = _password;
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

    private void login() {
        sendMessage("/auth " + this.login + " " + this.password);
    }

    public void logout() {
        this.stopped = true;
        System.out.println("Вы "+nick+" вышли из чата");
        sendMessage("/end");
    }

    public void PrintMessage(String str){
        System.out.println(str);
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.login();
            while ((socket.isConnected())&&(!socket.isClosed()) &&(!this.stopped) ){
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
                        PrintMessage("Вы авторизованы под ником: "+nick);

                    } else if (msg.startsWith("/authfail")) {
                        PrintMessage("Ошибка авторизации");
                        break;
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




