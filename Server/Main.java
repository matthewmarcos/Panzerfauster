import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Main extends JFrame {
    public static ArrayList<Connection> connections = new ArrayList<Connection>();

    public static void main(String[] args) {

        int chatPort = 8000;
        int gamePortNumber = 4438;

        JFrame frame = new JFrame("Panzerfauster Server");
        JPanel panel = new JPanel();
        JLabel port = new JLabel("Port: " + chatPort);
        JButton startButton = new JButton("Start");

        frame.setSize(500, 300);
        // JLabel gamePort = new JLabel("Port: "+gamePortNumber);
        // JButton startGame = new JButton("Start");

        startButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                Thread t = new Thread(new PazerfausterServer());
                t.start();

                startButton.hide();

                JLabel info = new JLabel("Running...");
                panel.add(info);

                JTextArea connectionField = new JTextArea(50, 100);
                connectionField.setEditable(false);
                connections=getConnected();
                connectionField.append("may nakaconnect\n");

                panel.add(connectionField);

              }
        });

        // startGame.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {

        //         PanzerfausterGameServer server = new PanzerfausterGameServer(gamePortNumber);
        //         Thread t = new Thread(server);
        //         t.start();

        //         startGame.hide();

        //         JLabel info = new JLabel("Running...");
        //         panel.add(info);

        //         JTextArea connectionField = new JTextArea(50, 100);
        //         connectionField.setEditable(false);
        //         connections=getConnected();
        //         connectionField.append("may nakaconnect\n");

        //         panel.add(connectionField);

        //         System.out.println("clicked");

        //       }
        // });

        panel.add(port);
        panel.add(startButton);

        // panel.add(gamePort);
        // panel.add(startGame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        //frame.pack();
        frame.setVisible(true);

    }

    public static ArrayList<Connection> getConnected(){
       return Connection.getConnections();
    }
}
