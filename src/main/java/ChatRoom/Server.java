package ChatRoom;
import ConnectDB.*;

import java.net.ServerSocket;
import java.net.*;
import java.util.ArrayList;
import java.util.Set;

public class Server {
    private ServerSocket server;
    private ArrayList<ServerSocket> listClient ;
    public Server(){
        Connect con = new Connect();
        listClient = con.getServerList();
        for(ServerSocket serverSocket : listClient){
            Thread serverThread = new Thread(() -> {
                try {
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
            System.out.println("Server is running on port " + serverSocket.getLocalPort());
        }
    }
    public Server(ServerSocket server) {
        this.server = server;
    }
    public void startServer(){
        try{
            while(!server.isClosed()){
                Socket socket = server.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeServerSocket(){
        try{
            if(server != null){
                server.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{

        ServerSocket server = new ServerSocket(1337);
        Server server1 = new Server(server);
        server1.startServer();


    }
}
