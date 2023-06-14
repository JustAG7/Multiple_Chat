package ChatRoom;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String userName;

    public Client(Socket socket,String userName){
        try{
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = userName;
        } catch (Exception e){
            closeAll(socket,reader,writer);
        }
    }

    public void sendMessage(){
        try{
            writer.write(userName);
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String message = scanner.nextLine();
                writer.write(userName + ": " + message);
                writer.newLine();
                writer.flush();
            }
        } catch (Exception e){
            closeAll(socket,reader,writer);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromServer;
                while(socket.isConnected()){
                    try{
                        messageFromServer = reader.readLine();
                        System.out.println(messageFromServer);
                    } catch (Exception e){
                        closeAll(socket,reader,writer);
                    }
                }
            }
        }).start();
    }

    public void closeAll(Socket socket, BufferedReader reader, BufferedWriter writer){
        try{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String userName = scanner.nextLine();
        Socket socket = null;
        try {
            socket = new Socket("localhost",1337);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client client = new Client(socket,userName);
        client.listenForMessage();
        client.sendMessage();
    }
}
