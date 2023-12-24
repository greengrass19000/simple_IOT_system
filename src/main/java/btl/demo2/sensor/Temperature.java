package btl.demo2.sensor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Temperature implements Runnable {

    private static Socket clientSocket = null;
    private static ObjectInputStream is = null;
    private static ObjectOutputStream os = null;
    private static BufferedReader input = null;
    private static boolean logout = false;

    public static void main(String[] args) {

        int portNumber = 1234;
        String host = "127.0.0.1";
        if (args.length > 2) {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }

        try {
            clientSocket = new Socket(host, portNumber);
            input = new BufferedReader(new InputStreamReader(System.in));
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("ERROR: Unknown host");
        } catch (IOException e) {
            System.err.println("ERROR: The server is full.");
        }

        if (clientSocket != null && os != null && is != null)
            try {
                new Thread(new Temperature()).start();
                while (!logout) {
                    String msg = (String) input.readLine().trim();

                    if ((msg.split(":").length > 1)) {
                        os.writeObject(msg);
                        os.flush();
                    } else {
                        os.writeObject(msg);
                        os.flush();
                    }
                }

                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("ERROR: IOException - " + e);
            }
    }

    public void run() {
        String response;

        try {
            while ((response = (String) is.readObject()) != null) {

                System.out.println(response);

                if (response.indexOf("See you again") != -1)
                    break;
            }
            if (response.indexOf("See you again") == -1)
                System.out.println("See you again!");
            logout = true;
            System.exit(0);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Server Stopped");
        }
    }
}