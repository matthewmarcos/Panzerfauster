import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Main extends JFrame {
    public static ArrayList<Connection> connections = new ArrayList<Connection>();

    public static void main(String[] args) {
        int portNumber = 8000;

        JFrame frame = new JFrame("Panzerfauster Server");
        frame.setSize(500, 300);
        JPanel panel = new JPanel();

        JLabel port = new JLabel("Port: " +portNumber);
        JButton startButton = new JButton("Start");

        startButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) { 

                PazerfausterServer server = new PazerfausterServer(portNumber);
                Thread t = new Thread(server);
                t.start();

                startButton.hide();

                JLabel info = new JLabel("Running...");
                panel.add(info);

                JTextArea connectionField = new JTextArea(50, 100);
                connectionField.setEditable(false);
                connections=getConnected();
                connectionField.append("may nakaconnect\n");
                
                panel.add(connectionField);

                System.out.println("clicked");

              } 
        });
        
        panel.add(port);
        panel.add(startButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        //frame.pack();
        frame.setVisible(true);

    }

    public static ArrayList<Connection> getConnected(){
       return Connection.getConnections();
    }
}