package src.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class VentanaChat {
}

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

        //JTextArea in_text = new JTextArea();
        panel_text = new JPanel();
        panel_text.setBounds(80,70,500,400);
        //panel_text.add(in_text);
        //in_text.setText("Hello");
        scrollPane = new JScrollPane(panel_text);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

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
            //send_text.setBounds(70,600,100,10);
            //send_btn.setBounds(170,600,10,10);
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
            JButton in_text2 = new JButton("Hola");
            in_text2.setBackground(Color.black);
            send_text.setText("");
            //JTextArea in_text = new JTextArea();
            panel_text.add(in_text2);
            //in_text.setText("Hola");
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
            //scrollPane.setVisible(false);
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
    //public JLabel out_msj;
    //public JLabel in_msj;

    public JPanel panel_text;

}
