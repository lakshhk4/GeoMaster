package com.mycompany.mavenproject1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    //private static final String SERVER_IP_ADDRESS = "localhost";
    private static final int PORT = 7777;
    private DataInputStream in;
    private DataOutputStream out;
    private String serverIP;
    private Socket socket;
    // to execute the Game thread
    static ExecutorService pool = Executors.newFixedThreadPool(2);
    public String name = "null";

    String gameSequence;
    public boolean startGame;
    ClientSideEnterName myClientSideEnterName;

    //////
    // to know which question is currently going on
    int currentQuestion = 0;

    // Stores whether the answer was correct for both the user and opponent. 0 = not answered yet; 1 = incorrect; 2 = correct
    int[] userAnswers = new int[15];

    // the data that needs to be sent to the server
    int score = 0;

    int numberOfQuestions = 5; // default

    Leaderboard leaderboard;

    int xPos;
    int yPos;

    public Client(String serverIP, int xPos, int yPos) throws IOException {
        this.serverIP = serverIP;
        this.xPos = xPos;
        this.yPos = yPos;
        socket = new Socket(serverIP, PORT); // connecting to correct port.
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connected to server.");
        startGame = false;
    }

    public void start() throws IOException {
        while (true) {

            if (name != null) {
                out.writeUTF(name);

            }
        }
    }

    // method that will be called in the gameScreen when the player answeres correctly
    public void addScore(int addedAmount) {
        score = score + addedAmount;
    }

    @Override
    public void run() {
        myClientSideEnterName = new ClientSideEnterName(this);
        myClientSideEnterName.setVisible(true);
        myClientSideEnterName.setLocation(xPos, yPos);
        pool.execute(myClientSideEnterName);

        while (true) {
            try {
                //System.out.println(name);
                /*if (!name.equals("null")) {
                out.writeUTF(name);
                out.close();
                System.out.println("Name sent from client.");
                }*/

                numberOfQuestions = Integer.parseInt(in.readUTF());
                System.out.println("Recived number of questions: " + numberOfQuestions);

                gameSequence = in.readUTF();
                System.out.println("Game Sequence Received " + gameSequence);
                startGame = true;
                createGameScreen();

                /*while (true) {
                
                
                }*/
                String encodedLeaderboard = in.readUTF();
                System.out.println("Received leaderboard 1" + encodedLeaderboard);
                decodeAndSendLeaderboard(encodedLeaderboard);

                String encodedLeaderboard2 = in.readUTF();
                System.out.println("Received leaderboard 2: " + encodedLeaderboard2);
                decodeAndSendLeaderboard(encodedLeaderboard2);

                String encodedLeaderboard3 = in.readUTF();
                System.out.println("Received leaderboard 3" + encodedLeaderboard3);
                decodeAndSendLeaderboard(encodedLeaderboard3);

                String encodedLeaderboard4 = in.readUTF();
                System.out.println("Received leaderboard 4" + encodedLeaderboard4);
                decodeAndSendLeaderboard(encodedLeaderboard4);

                String encodedLeaderboard5 = in.readUTF();
                System.out.println("Received leaderboard 5" + encodedLeaderboard5);
                decodeAndSendLeaderboard(encodedLeaderboard5);
                //leaderboard.lastQuestion();

                /*for (int i = 0; i < numberOfQuestions; i++) {
                
                
                
                }*/
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void createGameScreen() {
        if (startGame) {

            leaderboard = new Leaderboard(gameSequence, this);
            pool.execute(leaderboard);
            GameScreenClient myGameScreen = new GameScreenClient(this, leaderboard);
            pool.execute(myGameScreen);
            myGameScreen.setVisible(true);
            myClientSideEnterName.setVisible(false);
            myGameScreen.setLocation(xPos, yPos);
        }
    }

    // all data that needs to be sent is from clientName and gameScreen, by calling this method (send in client)
    public void sendData(String data) throws IOException {
        //out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(data);
        //out.close();
        System.out.println("Name sent from client.");
    }

    public void decodeAndSendLeaderboard(String encodedLeaderboard) {
        ArrayList<String> nameAndScores = SequenceInterpretor.dividor(encodedLeaderboard, "!");

        ArrayList<String> playerNames = new ArrayList();
        ArrayList<Integer> scores = new ArrayList();

        for (String nameAndScore : nameAndScores) {
            String[] splitted = nameAndScore.split(",");
            playerNames.add(splitted[0]);
            scores.add(Integer.parseInt(splitted[1]));
        }

        ArrayList<Integer> toSortScores = new ArrayList();

        for (String nameAndScore : nameAndScores) {
            String[] splitted = nameAndScore.split(",");

            toSortScores.add(Integer.parseInt(splitted[1]));
        }

        Collections.sort(toSortScores);
        Collections.reverse(toSortScores);

        ArrayList<String> sortedPlayerNames = new ArrayList();

        for (int i = 0; i < toSortScores.size(); i++) {
            sortedPlayerNames.add("null");
        }
        for (int i = 0; i < toSortScores.size(); i++) {
            int scoreIndexUnSorted = scores.indexOf(toSortScores.get(i));
            sortedPlayerNames.set(i, playerNames.get(scoreIndexUnSorted));
        }

        /*  ////
        for (int i = 0; i < toSortScores.size(); i++) {
        int oldIndex = scores.indexOf(toSortScores.get(i));
        // i = new index
        // sort the playerNamesArray
        
        String placeHolder = playerNames.get(i); // get the current name at the new index
        playerNames.set(i, playerNames.get(oldIndex));
        playerNames.set(oldIndex, placeHolder);
        }*/
        leaderboard.updateLeaderboard(sortedPlayerNames, toSortScores);

        // sort !
    }

}
