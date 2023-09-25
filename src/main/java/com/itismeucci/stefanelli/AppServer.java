package com.itismeucci.stefanelli;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppServer 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        int port;

        System.out.println("Inserisci la porta su cui aprire, 0 per generarla");
        port = scanner.nextInt();
        server.start(port, System.out);
        
        server.send("Connessione riuscita");
        System.out.println(server.receive());

        Thread receive = new ReceivingThread(server);
        receive.run();

        String input = "";

        while (true) {

            input = scanner.nextLine();

            System.out.println("\t a " + input.toUpperCase());

            if (input != "/exit")
                server.send(input);
            else break;
        }

        receive.interrupt();
        scanner.close();
    }


    private static class ReceivingThread extends Thread {

        protected Server server;

        public ReceivingThread(Server server) {

            this.server = server;
        } 

        @Override
        public void run() {
            
            String in;

            while (true) {

                in = server.receive();
                if (in != null)
                    System.out.println(in);

                try {

                    wait(5);

                } catch (InterruptedException e) {
                    
                }
            }
        }
    }
}
