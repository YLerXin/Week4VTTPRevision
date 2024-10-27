package task8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("invalid format need port");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number.");
            return;
        }

        try{
        Socket sock = new Socket("localhost",port);
        InputStreamReader isr = new InputStreamReader(sock.getInputStream());
        BufferedReader br = new BufferedReader(isr);

        PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Enter command: ");
            String userCommand = scanner.nextLine();
            if(userCommand.equals("exit")){
                out.println(userCommand);
                System.out.println("exiting..");
                break;
            }
            out.println(userCommand);
            
            String response = br.readLine();
            System.out.println("Server response: " + response);
        }

        }catch(IOException e){
            e.printStackTrace();
        }



        
    }
    
}
