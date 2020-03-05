package src.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;


class Ventana extends JFrame {
    public Ventana() {

        setTitle("What'sApp");
        setSize(700,600);
        setLocationRelativeTo(null);
        LabelVentana label = new LabelVentana();
        //Frame frame = new Frame();
        //add (frame);
        add(label);
        setVisible(true);
    }
}

class LabelVentana extends JPanel implements Runnable{
    private int host;
    private String ip;

    public void setHost(int host) {
        this.host = host;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *
     */
    public LabelVentana(){
        port = new JLabel("Nombre");
        add(port);
        port.setVisible(true);

        text_port = new JTextField(20);
        add(text_port);

        label_ip = new JLabel("IP");
        add(label_ip);

        text_ip = new JTextField(20);
        add(text_ip);


        textArea = new JTextArea(29,45);
        add(textArea);

        send_text = new JTextField(20);
        add(send_text);
        send_btn = new JButton("Enviar");

        Send send = new Send();
        send_btn.addActionListener(send);
        add(send_btn);

        Thread hilo2  = new Thread(this);
        hilo2.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket S_Cliente = new ServerSocket(9090);
            Socket Cliente;
            Info infoRecibida;

            while (true){
                Cliente = S_Cliente.accept();
                ObjectInputStream entrada = new ObjectInputStream(Cliente.getInputStream());

                infoRecibida = (Info) entrada.readObject();

                textArea.append(infoRecibida.getNombre() + ":" + infoRecibida.getMensaje() + "\n");

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class Send implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket client = new Socket("192.168.100.164", 7777); // se conecta al puerto y no estoy seguro de lo del host
                Info info = new Info();
                info.setIp(text_ip.getText());
                info.setNombre(text_port.getText());
                info.setMensaje(send_text.getText());
                ObjectOutputStream datos_salida = new ObjectOutputStream(client.getOutputStream());
                datos_salida.writeObject(info);
                send_text.setText("");
                client.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private JTextField text_port, text_ip, send_text;

    private JButton conect_btn, send_btn, reconect_btn;

    private JLabel port, label_ip;

    private JTextArea textArea;

}
class Info implements Serializable {
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
}
