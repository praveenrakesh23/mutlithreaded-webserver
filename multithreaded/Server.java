import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public void run() throws IOException{
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

        System.out.println("Server started on port " + port);
        while (true) {
            try{
                Socket acceptedSocket = serverSocket.accept();
                System.out.println("Client connect from: "+ acceptedSocket.getRemoteSocketAddress());
                
                //creating a thread to handle the client connection
                Thread thread = new Thread(()->{
                    try{
                        getConsumer().accept(acceptedSocket);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    
}

//logic to be executed on each client connection
public Consumer<Socket> getConsumer(){
    return (clientSocket)->{
        try{
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            toClient.println("Hello from the server!");
            toClient.close();
            clientSocket.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    };
}
public static void main(String[] args){
    Server server = new Server();
    try{
        server.run();
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}




