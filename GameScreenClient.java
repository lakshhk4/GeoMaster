package com.mycompany.mavenproject1;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class GameScreenClient extends javax.swing.JFrame implements Runnable {

    Client client;
    String gameSequence;
    Leaderboard leaderboard;

    // stores wheter the user has answered the question. 
    boolean answered = false;

    // stores which option the user chose for the current question
    int optionChosen = 0;

    /// TIMER FOR GAME. THIS CODE WAS TAKEN FROM THE YOUTUBE VIDEO https://www.youtube.com/watch?v=36jbBSQd3eU&t=7s /////
    int secondsLeft = 10;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            secondsLeft--;

        }

    };
    /// TIMER DONE ///
    boolean secondsToOne;

    public GameScreenClient(Client client, Leaderboard leaderboard) {
        this.client = client;
        gameSequence = client.gameSequence;
        this.leaderboard = leaderboard;
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageField = new javax.swing.JLabel();
        optionOneButton = new javax.swing.JButton();
        optionTwoButton = new javax.swing.JButton();
        optionThreeButton = new javax.swing.JButton();
        optionFourButton = new javax.swing.JButton();
        myScoreArea = new javax.swing.JLabel();
        myScoreField = new javax.swing.JLabel();
        secondsDisplay = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        optionOneButton.setText("jButton1");
        optionOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionOneButtonActionPerformed(evt);
            }
        });

        optionTwoButton.setText("jButton2");
        optionTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionTwoButtonActionPerformed(evt);
            }
        });

        optionThreeButton.setText("jButton3");
        optionThreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionThreeButtonActionPerformed(evt);
            }
        });

        optionFourButton.setText("jButton3");
        optionFourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionFourButtonActionPerformed(evt);
            }
        });

        myScoreArea.setText("My score:");

        myScoreField.setText("0");

        secondsDisplay.setText("Time:");

        userName.setText("name");

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/lakshhkhatri/Downloads/Untitled design (1).png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(myScoreField)
                .addGap(55, 55, 55)
                .addComponent(secondsDisplay))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(imageField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(optionThreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(optionFourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(optionOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(userName))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(myScoreArea))
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(optionTwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myScoreField)
                    .addComponent(secondsDisplay))
                .addGap(9, 9, 9)
                .addComponent(imageField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionThreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optionFourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(optionOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(userName))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(myScoreArea))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(optionTwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optionOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionOneButtonActionPerformed
        // When the 1st  option button is chosen, the following code executes.
        optionChosen = 1;
        // disables all the buttons
        optionOneButton.setEnabled(false);
        optionTwoButton.setEnabled(false);
        optionThreeButton.setEnabled(false);
        optionFourButton.setEnabled(false);
        answered = true;
    }//GEN-LAST:event_optionOneButtonActionPerformed

    private void optionTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionTwoButtonActionPerformed
        // When the 2nd option button is chosen, the following code executes.
        optionChosen = 2;
        // disables all the buttons
        optionOneButton.setEnabled(false);
        optionTwoButton.setEnabled(false);
        optionThreeButton.setEnabled(false);
        optionFourButton.setEnabled(false);
        answered = true;
    }//GEN-LAST:event_optionTwoButtonActionPerformed

    private void optionThreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionThreeButtonActionPerformed
        // When the 3rd option button is chosen, the following code executes.
        optionChosen = 3;
        // disables all the buttons
        optionOneButton.setEnabled(false);
        optionTwoButton.setEnabled(false);
        optionThreeButton.setEnabled(false);
        optionFourButton.setEnabled(false);
        answered = true;
    }//GEN-LAST:event_optionThreeButtonActionPerformed

    private void optionFourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionFourButtonActionPerformed
        // When the 4th option button is chosen, the following code executes.
        optionChosen = 4;
        // disables all the buttons
        optionOneButton.setEnabled(false);
        optionTwoButton.setEnabled(false);
        optionThreeButton.setEnabled(false);
        optionFourButton.setEnabled(false);
        answered = true;
    }//GEN-LAST:event_optionFourButtonActionPerformed

    @Override
    public void run() {

        this.setResizable(false);
        System.out.println("in gameScreen thread");
        userName.setText(client.name);

        ArrayList gameQuesAndOptions = SequenceInterpretor.interpretGameSequence(gameSequence);
        // Sets timer to decrease by 1 every sec. 
        timer.scheduleAtFixedRate(task, 1000, 1000);

        for (int i = 0; i < gameQuesAndOptions.size(); i++) {
            
            client.leaderboard.setVisible(false);
            this.setVisible(true);
            
            // variable 'i' is the question that is currently taking place.

            // stores which question is currenly under way
            client.currentQuestion = i;

            // the scoreArea for  'myScore' turns green/red to indicate whether
            // the current question was answered correclty (if answered).
            // sets score areas to black as the question just popped up
            myScoreArea.setForeground(Color.black);

            // sets the answered variable to false
            answered = false;

            // enabling all the buttons
            optionOneButton.setEnabled(true);
            optionTwoButton.setEnabled(true);
            optionThreeButton.setEnabled(true);
            optionFourButton.setEnabled(true);

            // setting timer to start at 10s
            secondsLeft = 10;
            secondsToOne = false;

            // Gets the current question
            ArrayList fullQ = (ArrayList) gameQuesAndOptions.get(i);
            // variable to store the correct answer. Currently set at 100.
            int correctAnswer = 100;
            // generates random order for the options in the question
            String[] options = SequenceInterpretor.optionsPlacer(fullQ);
            // storing which button is the correctAnswer
            correctAnswer = Integer.parseInt(options[0]);
            // placing the correct image
            String imageFileName = "/Users/lakshhkhatri/NetBeansProjects/mavenproject1/src/main/java/com/mycompany/mavenproject1/flagsInFolder/" + fullQ.get(0);
            imageField.setIcon(new ImageIcon(new ImageIcon(imageFileName).getImage().getScaledInstance(imageField.getWidth(), imageField.getHeight(), Image.SCALE_SMOOTH)));

            // setting the text for the buttons
            optionOneButton.setText(options[1]);
            optionTwoButton.setText(options[2]);
            optionThreeButton.setText(options[3]);
            optionFourButton.setText(options[4]);

            // sound
            /* URL soundbyte = new File("/Users/lakshhkhatri/Desktop/music.wav").toURI().toURL();
            java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
            clip.play();*/
            // to ensure the score is added only once.
            boolean scoreAdded = false;
            while (secondsLeft > -1) { // runs for 10 seconds

                // display seconds left on the screen for the user to see
                secondsDisplay.setText(Integer.toString(secondsLeft));

                if (answered) {
                    if (optionChosen == correctAnswer) {
                        if (scoreAdded == false) {
                            client.addScore((2 * secondsLeft) + 10);
                            myScoreField.setText(Integer.toString(client.score));
                            scoreAdded = true;
                            myScoreArea.setForeground(Color.green);
                        }
                    } else {
                        myScoreArea.setForeground(Color.red);
                    }
                }
                //play sound

                /*if (answered) {
                if (optionChosen == correctAnswer) {
                // if answered correctly
                
                // stores data in client to update the server regarding game state
                client.userAnswers[i] = 2;
                
                if (scoreAdded == false) {
                // add score
                client.addScore((2 * secondsLeft) + 10);
                
                // update score.
                myScoreField.setText(Integer.toString(client.score));
                
                // send score to server
                try {
                client.sendData(Integer.toString(client.score));
                } catch (IOException ex) {
                Logger.getLogger(GameScreenClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                scoreAdded = true;
                
                }
                // sets the scoreArea color to green to indicate correct response
                myScoreArea.setForeground(Color.green);
                
                } else {
                
                try {
                // if answered incorrectly
                client.sendData(Integer.toString(client.score));
                } catch (IOException ex) {
                Logger.getLogger(GameScreenClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                // stores data in client to update the server regarding game state
                client.userAnswers[i] = 1;
                // set the scoreArea color to green to indicate correct response
                myScoreArea.setForeground(Color.red);
                
                }
                }
                 */
                // can have a if all users have answered!
                /*if (opponentAnswered == true && answered == true) {
                if (secondsToOne == false) {
                secondsLeft = 1;
                secondsToOne = true;
                }
                
                }*/
            }

            try {
                //clip.stop();

                /*if (answered == false) {
                // if unanswered
                client.userAnswers[i] = 1;
                try {
                client.sendData(Integer.toString(client.score));
                } catch (IOException ex) {
                Logger.getLogger(GameScreenClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }*/
                client.sendData(Integer.toString(client.score));
            } catch (IOException ex) {
                Logger.getLogger(GameScreenClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            // DISPLAY LEADERBOARD!
            secondsLeft = 2;
            while (secondsLeft > -1) {
            client.leaderboard.setLocation(this.getX(), this.getY());
            client.leaderboard.setVisible(true);
            this.setVisible(false);
            
            }
            
            // for now keeping this here. but need to change
            //DidYouGuess5 didYouGuessScreen = new DidYouGuess5(gameQuesAndOptions);
            

        }
        leaderboard.lastQuestion();
        
        

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel myScoreArea;
    private javax.swing.JLabel myScoreField;
    private javax.swing.JButton optionFourButton;
    private javax.swing.JButton optionOneButton;
    private javax.swing.JButton optionThreeButton;
    private javax.swing.JButton optionTwoButton;
    private javax.swing.JLabel secondsDisplay;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
