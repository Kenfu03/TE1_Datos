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

    /**
     *
     */
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(7777); // se crea el server con X puerto para las conexiones

            String nombre, ip, mensaje;

            Info info_recibida;

            while (true){
                System.out.println("Listening..."); // empieza a recibir
                Socket entrante = serverSocket.accept(); // acepta la entrada de un cliente X
                ObjectInputStream lector = new ObjectInputStream(entrante.getInputStream());
                info_recibida = (Info) lector.readObject();

                nombre = info_recibida.getNombre();
                ip = info_recibida.getIp();
                mensaje = info_recibida.getMensaje();

                System.out.println(nombre + ip + mensaje);

                Socket newclient = new Socket(ip,7777);
                ObjectOutputStream reInfo = new ObjectOutputStream(newclient.getOutputStream());

                reInfo.writeObject(info_recibida);

                newclient.close();

                entrante.close(); // termina la conexion con el cliente
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
    }
}