package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clentSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clentSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clentSocket.getInputStream()))
        ) {
            System.out.println("Server start");
            System.out.println("New connection accepted");
            out.println("Hello. What is your name? My name is: ");
            final String name = in.readLine();
            out.println("Are you child? (yes/no): ");
            final boolean isChild = in.readLine().equalsIgnoreCase("yes");
            if (isChild) {
                out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
            } else {
                out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
            }
            int number = new Random().nextInt(10);
            System.out.println(number);
            out.println("Guess the number from 0 to 10. To complete the game, write \"end\".");
            String add = "";
            while (true) {
                out.println(add.isEmpty() ? "You choose: " : String.format("%s. You choose: ", add));
                String choose = in.readLine();
                if (choose.equalsIgnoreCase("end")) {
                    break;
                }
                System.out.println(choose);
                try {
                    int numChoose = Integer.parseInt(choose);
                    if (numChoose == number) {
                        out.println("You WIN!!!");
                    } else {
                        add = numChoose < number ? "More" : "Less";
                    }
                } catch (NumberFormatException e) {
                    out.println("You choose: ");
                }
            }
            System.out.println("Server stop");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
