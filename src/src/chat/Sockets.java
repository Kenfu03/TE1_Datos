/*package src.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sockets{

    public static void Send_Client()throws IOException {
        Socket client = new Socket("127.0.0.1", 7777); // se conecta al puerto y no estoy seguro de lo del host
        Info info = new Info();
        info.setIp();
        ObjectOutputStream datos_salida = new ObjectOutputStream(client.getOutputStream());
        datos_salida.writeObject(info);


        client.close();
    }

}
class Info {
    private String Ip, nombre, mensaje;

    public String getIp() {
        return Ip;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}*/