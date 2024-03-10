package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Server implements Runnable {

    // for starting local server
    private static ServerSocket serverSocket;

    // List with client connected to the game
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    // to start threads when multiple clients connect
    private static ExecutorService pool = Executors.newFixedThreadPool(20);

    // port number
    public static final int PORT = 7777;

    // store all user's names
    static ArrayList<String> names;
    public static DefaultListModel<String> modelForClientList = new DefaultListModel<>();

    // store all scores 2d array
    //static int[][] scores; // maybe i dont need a 2d array of scores. just a cumulative score
    static int[] scores;
    static int[] responseCollected; // used to determine when all client's scores are received so that leaderboard is updated, and sent.
    String encodedLeaderboard = "!";

    // store number of questions -- updated from GameControlsTeacher
    public int numberOfQuestions = 5; // default in the 

    Leaderboard leaderboard;

    public void start() throws IOException {
        // create local server
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server initiated. Waiting for clients");
        // continuously listen for clients

        /*int numberOfQuestions = 10;
        GameSequenceGenerator g = new GameSequenceGenerator();
        g.generateQuestion(numberOfQuestions);
        System.out.println("generated");*/
        leaderboard = new Leaderboard();
        pool.execute(leaderboard);
        boolean acceptConnections = true;
        while (acceptConnections) {
            // waiting for client connection
            Socket client = serverSocket.accept();

            System.out.println("Client connected.");
            // once client has connected, make & execute a thread to establish communication with them.
            ClientHandler clientThread = new ClientHandler(client, this);
            clients.add(clientThread);

            // retreive client name, and add to names array from CLIENTHANDLER !!!!!!!!!!!!!!!!!!!!!!!
            pool.execute(clientThread);
        }

        // once all connections have been established
        //names = new String[clients.size()];
        //scores = new int[clients.size()][numberOfQuestions];
        // each row of scores will be for one client. parallel to names array.
    }

    /*    public int[] scoreForQuestion(int questionNumber) {
    // esentially retreives a column from the 2d scores array
    int[] scoresForQ = new int[clients.size()];
    
    int i = 0;
    for (int[] clientScore : scores) {
    scoresForQ[i] = clientScore[questionNumber];
    i++;
    }
    
    return scoresForQ;
    
    }*/
 /*public static void main(String[] args) throws IOException {
    start();
    }*/
    public void updateScore(String clientName, int score) throws IOException {

        int clientIndex = modelForClientList.indexOf(clientName);
        scores[clientIndex] = score;
        responseCollected[clientIndex] = 1;

        encodedLeaderboard += clientName + "," + Integer.toString(score) + "!";

        int sum = 0;
        for (int i : responseCollected) {
            sum += i;
        }

        if (sum == clients.size()) {
            for (ClientHandler client : clients) {
                client.sendData(encodedLeaderboard);
                System.out.println("Sent leaderboard");
            }

            // update teacher side leaderboard
            
            updateTeacherLeaderboard(encodedLeaderboard);
            
            responseCollected = new int[clients.size()];
            encodedLeaderboard = "!";

        }

        //leaderboard
        /*        boolean notLast = false;
        // check until responseCollected
        for (int number : responseCollected) {
        if (number == 0) {
        notLast = true;
        }
        }
        
        if (!notLast) {
        // now all client's scores have been updated. hence, calculate leaderboards, and send scores to all clients.
        // encode scores and clients in a string.
        
        for (ClientHandler client : clients) {
        client.sendData(encodedLeaderboard);
        }
        
        responseCollected = null;
        responseCollected = new int[clients.size()];
        encodedLeaderboard = "!";
        
        }
        
         */
        // reset responseCollected to 0
        // reset encoded leaderboard
        //updateTeacherLeaderboard(encodedLeaderboard);
    }

    public void updateTeacherLeaderboard(String encodedLeaderboard) {
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

    @Override
    public void run() {
        try {

            // Jform for teacher to view all connections
            GameControlsTeacher myGameControlsTeacher = new GameControlsTeacher(clients, this, leaderboard);
            myGameControlsTeacher.setVisible(true);
            pool.execute(myGameControlsTeacher);
            start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
