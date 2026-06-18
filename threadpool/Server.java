import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {
    private final ExecutorService threadpool;
    public Server(int poolSize) {
        this.threadpool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() throws IOException{
        int port = 8010;

        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(700000);

        System.out.println("Server is running on port " + port);

        while(true){
            try{
                Socket clientSocket = serverSocket.accept();
                threadpool.execute(() -> handleClient(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //logic to be executed for each client connection
    private void handleClient(Socket clientSocket){
        try(PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true)){
            toClient.println("Hello, client! You are connected to the server.");
            clientSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server(10);
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
