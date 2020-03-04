package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private String mensaje;
    private int host;

    public SocketServer(int host) {
        this.host = host;
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(host); // se crea el server con X puerto para las conexiones
            while (true){
                System.out.println("Listening..."); // empieza a recibir
                Socket entrante = serverSocket.accept(); // acepta la entrada de un cliente X
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(entrante.getInputStream()));
                String Mensaje_text = lector.readLine();
                if(Mensaje_text!=null) {
                    mensaje = Mensaje_text;

                }// lee lo que esta guardado
                System.out.println("Mensaje recibido:" + mensaje); // imprime lo que dice el mensaje
                entrante.close(); // termina la conexion con el cliente
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}