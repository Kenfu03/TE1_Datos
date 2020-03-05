package src.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
    }
}

/**
 *
 */
class Server implements Runnable{ //Abstraccion

    public Server(){
        Thread thread = new Thread(this); //Se crea el hilo para el Socket Server
        thread.start(); //Se inicia el hilo
    }

    /**
     *
     */
    @Override //Sobreescritura
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(9999); // se crea el server con X puerto para las conexiones

            String nombre, ip, mensaje;

            Info info_recibida;

            while (true){
                System.out.println("Listening...");
                Socket entrante = serverSocket.accept(); //Acepta la entrada de un cliente X
                ObjectInputStream lector = new ObjectInputStream(entrante.getInputStream()); //Abre a conexion de emisor
                info_recibida = (Info) lector.readObject(); //Lee los datos recibidos y los guarda en una variable

                nombre = info_recibida.getNombre(); //Guarda el nombre recibido en la variable nombre
                ip = info_recibida.getIp(); //Guarda el ip recibido en la variable ip
                mensaje = info_recibida.getMensaje(); //Guarda el mensaje recibido en la variable mensaje

                System.out.println(nombre + ip + mensaje);

                Socket newclient = new Socket(ip,9090); //Se crea el socket cliente, para reeniar la informacion
                ObjectOutputStream reInfo = new ObjectOutputStream(newclient.getOutputStream()); //Abre la conexion de receptor

                reInfo.writeObject(info_recibida); //Guarda los datos recibidos para luego reenviarlos

                newclient.close(); //termina la conexion con el cliente emisor

                entrante.close(); // termina la conexion con el cliente receptor
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
    }
}