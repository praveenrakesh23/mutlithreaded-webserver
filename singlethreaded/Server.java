import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    public void run() throws IOException{

        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);

        serverSocket.setSoTimeout(10000);

        System.out.println("Single Threaded server is listening on port "+port);

        while(true){
            try{
            Socket acceptedConnection = serverSocket.accept();
            System.out.println("Connection accepted from"+acceptedConnection.getRemoteSocketAddress());

            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);

            toClient.println("Hello from the single-threaded server");

            toClient.close();
            acceptedConnection.close();
        }
    
    catch(Exception e){
        e.printStackTrace();
    }
    }
}

public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

