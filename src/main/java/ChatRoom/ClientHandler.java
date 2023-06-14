package ChatRoom;

import java.io.*;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientUserName;

    public ClientHandler(Socket socket) {
        try{
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUserName = reader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUserName + " has joined the chatroom");
        } catch (Exception e){
            closeAll(socket,reader,writer);
        }


    }

    @Override
    public void run(){
        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient = reader.readLine();
                broadcastMessage(messageFromClient);
            } catch (Exception e){
                closeAll(socket,reader,writer);
                break;
            }
        }
    }
    public void broadcastMessage(String message){
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientUserName.equals(this.clientUserName)) {
                    clientHandler.writer.write(message);
                    clientHandler.writer.newLine();
                    clientHandler.writer.flush();
                }
            } catch (Exception e){
                closeAll(socket,reader,writer);
            }
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER" + this.clientUserName + " has left the chatroom");
    }

    public void closeAll(Socket socket, BufferedReader reader, BufferedWriter writer){
        removeClientHandler();
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
}
