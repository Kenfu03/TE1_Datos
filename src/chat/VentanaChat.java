package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static chat.VentanaChat.*;


public class VentanaChat {
}

class Ventana extends JFrame {
    public Ventana() {

        setTitle("What'sApp");
        setSize(700,600);
        setLocationRelativeTo(null);
        LabelVentana label = new LabelVentana();
        add(label);
        setVisible(true);
    }
}
class LabelVentana extends JPanel{
    private int host;
    private String ip;

    public void setHost(int host) {
        this.host = host;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LabelVentana(){
        port = new JLabel("Puerto");
        add(port);
        port.setVisible(true);

        text_port = new JTextField(20);
        add(text_port);
        text_port.setVisible(true);

        label_ip = new JLabel("IP");
        add(label_ip);
        label_ip.setVisible(true);

        text_ip = new JTextField(20);
        add(text_ip);
        text_ip.setVisible(true);

        conect_btn = new JButton("Conect");
        Conect conect = new Conect();
        conect_btn.addActionListener(conect);
        add(conect_btn);
        conect_btn.setVisible(true);

        reconect_btn = new JButton("Reconect");
        Reconect reconect = new Reconect();
        reconect_btn.addActionListener(reconect);
        add(reconect_btn);
        reconect_btn.setVisible(false);

        scrollPane = new JScrollPane();
        add(scrollPane);
        scrollPane.setVisible(false);

        panel_text = new JPanel();
        //panel_text.setLayout(null);

        in_text = new JTextArea();

        send_text = new JTextField(20);
        add(send_text);
        send_text.setVisible(false);
        send_btn = new JButton("Enviar");

        Send send = new Send();
        send_btn.addActionListener(send);
        add(send_btn);
        send_btn.setVisible(false);
    }

    private class Conect implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setHost(Integer.parseInt(text_port.getText()));
            setIp(text_ip.getText());
            text_ip.setText("");
            text_port.setText("");
            port.setVisible(false);
            text_port.setVisible(false);
            label_ip.setVisible(false);
            text_ip.setVisible(false);
            conect_btn.setVisible(false);
            reconect_btn.setVisible(true);
            scrollPane.setVisible(true);
            send_text.setVisible(true);
            send_btn.setVisible(true);
        }
    }
    private class Send implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Sockets.Send_Client(ip, host, send_text.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            send_text.setText("");
            //in_text.setText(send_text.getText());
            panel_text.add(in_text);
            in_text.setText("Hola");
            //scrollPane.setViewportView(panel_text);
        }
    }
    private class Reconect implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            port.setVisible(true);
            text_port.setVisible(true);
            label_ip.setVisible(true);
            text_ip.setVisible(true);
            conect_btn.setVisible(true);
            reconect_btn.setVisible(false);
            scrollPane.setVisible(false);
            send_text.setVisible(false);
            send_btn.setVisible(false);
        }
    }
    private JTextField text_port;
    private JTextField text_ip;
    private JTextField send_text;

    private JButton conect_btn;
    private JButton send_btn;
    private JButton reconect_btn;

    private JScrollPane scrollPane;

    private JLabel port;
    private JLabel label_ip;
    private JLabel out_msj;
    private JLabel in_msj;

    private JPanel panel_text;

    private JTextArea in_text;
    private JTextArea out_text;
}
