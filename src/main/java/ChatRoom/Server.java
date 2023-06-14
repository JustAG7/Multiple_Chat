package ChatRoom;
import ConnectDB.*;

import java.net.ServerSocket;
import java.net.*;

public class Server {
    private ServerSocket server;

    public Server(ServerSocket server) {
        this.server = server;
    }
    public void startServer(){
        try{
            while(!server.isClosed()){
                Socket socket = server.accept();
                System.out.println("Client connected");
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
        Connect con = new Connect();
        ServerSocket server = new ServerSocket(1337);
        Server server1 = new Server(server);
        server1.startServer();


    }
}
