
package com.company;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements  Runnable{
    DataOutputStream dos;
    String name;
    Socket s;
    boolean isloggedin;
    public ClientHandler(Socket s, String name) throws IOException {
        this.name = name;
        this.s = s;
        this.isloggedin=true;
        dos = new DataOutputStream(s.getOutputStream());
    }

     @Override
      public void run() {
        // Read
            while (true) {

                try {
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    String received = dis.readUTF();
                    System.out.println(name + "  :  " + received);
                    if (received.equals("logout")) {
                        s.close();
                        break;
                    }

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
    }
}
