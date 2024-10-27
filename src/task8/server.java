package task8;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class server {

    private static final AtomicBoolean shouldTerminate = new AtomicBoolean(false);

    public static void main(String[] args) throws IOException{
        int port = 2000;
        ServerSocket sock = null;
        if(args.length < 1){
            System.err.println("please Input port");
        }
        port = Integer.parseInt(args[0]);

        ExecutorService es = Executors.newFixedThreadPool(10);

        try{sock = new ServerSocket(port);

        while(!shouldTerminate.get()){
            System.out.printf("Waiting for connection on port %d\n", port);
            Socket clientSocket = sock.accept();

            System.out.println("Got a new connection");

            es.execute(new handler(clientSocket, shouldTerminate));

            // // Create a worker to handle the work (one way)
            // ClientHandler handler = new ClientHandler(sock);
            // //pss the work to the worker
            // thrPool.submit(handler);        
            }
        }catch(IOException e){
            System.out.println("Error starting server: " + e.getMessage());
        }finally{
            es.shutdownNow(); // Interrupts running tasks
            try {
                if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                }
            } catch (InterruptedException ie) {
                System.out.println("Interrupted while waiting for executor shutdown.");
                Thread.currentThread().interrupt();
            }

            if (sock != null && !sock.isClosed()) {
                sock.close();
            }

            System.out.println("Server is fully shut down.");
        }
    }
    
}
