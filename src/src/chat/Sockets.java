package src.chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sockets{

    public static void Send_Client(String ip, int host, String mensaje)throws IOException {
        Socket client = new Socket(ip, host); // se conecta al puerto y no estoy seguro de lo del host xd
        OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream()); // crea un escritor para los mensajes
        writer.write(mensaje);
        writer.flush();
        client.close();
    }

}
