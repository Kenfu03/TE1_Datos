package chat;

import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Ventana windows = new Ventana();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SocketServer Server = new SocketServer(40000);
        Thread Server_Thread = new Thread(Server);
        Server_Thread.start();
        //sleep(5000);
        //Sockets.Socket_Client(40000);
    }

}