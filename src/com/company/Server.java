package com.company;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Server {

    static Vector<ClientHandler> a = new Vector<>();
    static int i = 1;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2322);
        System.out.println("Server Started..");
        new Thread(() -> {
         try{
             while (true) {
                 Socket s = ss.accept();
                 System.out.println("New client connected : " + s);

                 System.out.println("Creating a new handler for this client...");
                 ClientHandler ch = new ClientHandler(s, "Client " + i);

                 // new Thread with this object.
                 Thread t = new Thread(ch);

                 System.out.println("Adding this client to active client list");
                 a.add(ch);
                 t.start();
                 i++;
             }
         }catch (IOException e){
             e.printStackTrace();
         }
        }).start();

        while(true){
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            String msz = sc.readLine();
            sendmsz(msz);
        }
    }

    private static void sendmsz(String msz) throws IOException {
        String[] str = msz.split("#", 2);
        System.out.println(str[0] + " sent to " + str[1]);
        for (ClientHandler mc : Server.a) {
            if (mc.name.equals(str[1]) && mc.isloggedin == true) {
                mc.dos.writeUTF(" " + str[0]);
                mc.dos.flush();
            }
        }
    }
}


