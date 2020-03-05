package src.chat;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
    }
}
class Server implements Runnable{
    private String mensaje;
    private int host;

    public String getMensaje() {
        return mensaje;
    }

    public Server(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(7777); // se crea el server con X puerto para las conexiones

            String nombre, ip, mensaje;

            Info info_reciida;

            while (true){
                System.out.println("Listening..."); // empieza a recibir
                Socket entrante = serverSocket.accept(); // acepta la entrada de un cliente X
                ObjectInputStream lector = new ObjectInputStream(entrante.getInputStream());
                info_reciida = (Info) lector.readObject();

                if(Mensaje_text!=null) {
                    mensaje = Mensaje_text;
                    System.out.println(mensaje);
                }// lee lo que esta guardado
                entrante.close(); // termina la conexion con el cliente
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
    }
}