package com.company;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

            System.out.println("Client started..");

            Socket s = new Socket("localhost", 2322);

            System.out.println("Client Connected..");
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());


                Thread writerThread = new Thread(new Runnable()
                {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                String msg = sc.readLine();
                                dos.writeUTF(msg);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                // Read
                Thread readerThread = new Thread(new Runnable()
                {
                    @Override
                    public void run() {

                        while (true) {
                            try {

                                String msg = dis.readUTF();
                                System.out.println("Server :"+msg);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                });

        writerThread.start();
        readerThread.start();
    }
}