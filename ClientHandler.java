package com.mycompany.mavenproject1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    // multi-threading server (can accept multiple clients). Runnable interface allows multi-threading

    private Socket client;
    private Server s;
    String clientName;

    // channel for sending & receiving data
    private DataOutputStream out; // writing. sending data
    private DataInputStream in; // reading. receiving data.

    int score = 0; // to score respective client's score

    public ClientHandler(Socket client, Server s) throws IOException {
        this.client = client;
        this.out = new DataOutputStream(client.getOutputStream());
        this.in = new DataInputStream(client.getInputStream());

        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {

                // communication between server and client.
                clientName = in.readUTF();
                System.out.println("Name received in ClientHandler");
                Server.modelForClientList.addElement(clientName);

                for (int i = 0; i < 5; i++) {
                    String msg = in.readUTF();
                    s.updateScore(clientName, Integer.parseInt(msg));
                    
                }
                /* // we create a loop that loops as many times as there are questions
                score = Integer.parseInt(in.readUTF()); // send to server to determine leaderboards !!!!!!!!!!!!!!!!!
                
                // UPDATE THE SCORES ARRAY OF SERVER (LETS MAKE IT A MAP ACTUALLY)
                // receive answerCorrectly to export student data !!!!!!!!!!!
                s.updateScore(clientName, score);
                System.out.println("Receoved score 1:" + score);
                
                score = Integer.parseInt(in.readUTF());
                s.updateScore(clientName, score);
                System.out.println("Received score 2:" + score);
                
                score = Integer.parseInt(in.readUTF());
                s.updateScore(clientName, score);
                System.out.println("Received score 3:" + score);
                
                score = Integer.parseInt(in.readUTF());
                s.updateScore(clientName, score);
                System.out.println("Received score 4:" + score);
                
                score = Integer.parseInt(in.readUTF());
                s.updateScore(clientName, score);
                System.out.println("Received score 5:" + score);*/
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendData(String data) throws IOException {
        //out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(data);
        //out.close();
        //System.out.println("Game Sequence or leaderboard sent from clientHandler.");
    }

    public void sendLeaderboardStream() throws IOException {
        while (true) {
            out.writeUTF(s.encodedLeaderboard);
        }
    }

}
