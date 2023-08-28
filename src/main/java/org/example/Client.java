package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        try (
                Socket clientSocket = new Socket(host, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            Scanner scanner = new Scanner(System.in);
            String input;
            System.out.print(in.readLine());
            input = scanner.nextLine();
            out.println(input);
            System.out.print(in.readLine());
            input = scanner.nextLine();
            out.println(input);
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            while (true) {
                input = in.readLine();
                if (input.equalsIgnoreCase("You WIN!!!")) {
                    System.out.println(input);
                    out.println("end");
                    break;
                } else {
                    System.out.print(input);
                    input = scanner.nextLine();
                    out.println(input);
                    if (input.equalsIgnoreCase("end")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
