/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author lakshhkhatri
 */
public class GameControlsTeacher extends javax.swing.JFrame implements Runnable {

    String gameSequence;
    Server s;
    ArrayList<ClientHandler> clients = new ArrayList();
    Leaderboard leaderboard;
    
    public GameControlsTeacher(ArrayList<ClientHandler> clients, Server s, Leaderboard leaderboard) {
        initComponents();
        jList1.setModel(Server.modelForClientList);
        startGameBtn.setEnabled(false);
        this.clients = clients;
        this.s = s;
        this.leaderboard = leaderboard;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regionFilterBox = new javax.swing.JComboBox<>();
        regionFilterBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        automaticQgen = new javax.swing.JButton();
        manualQgen = new javax.swing.JButton();
        startGameBtn = new javax.swing.JButton();
        numberOfQ = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        regionFilterBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        regionFilterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "World","Asia", "Europe", "Americas", "Africa", "Oceania" }));
        regionFilterBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionFilterBoxActionPerformed(evt);
            }
        });

        regionFilterBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "World","Asia", "Europe", "Americas", "Africa", "Oceania" }));
        regionFilterBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionFilterBox1ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("/Users/lakshhkhatri/Downloads/Untitled design (2).png")); // NOI18N
        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText(" Connected Students");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Question Generation");

        automaticQgen.setText("Automatic");
        automaticQgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticQgenActionPerformed(evt);
            }
        });

        manualQgen.setText("Manual");
        manualQgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualQgenActionPerformed(evt);
            }
        });

        startGameBtn.setText("Start Game");
        startGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameBtnActionPerformed(evt);
            }
        });

        numberOfQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20" }));
        numberOfQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberOfQActionPerformed(evt);
            }
        });

        jLabel3.setText("Server Address: localhost");

        regionFilterBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "World","Asia", "Europe", "Americas", "Africa", "Oceania" }));
        regionFilterBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionFilterBox2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon("/Users/lakshhkhatri/Downloads/Untitled design (2).png")); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(automaticQgen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(numberOfQ, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(regionFilterBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(manualQgen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(startGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(automaticQgen)
                    .addComponent(numberOfQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regionFilterBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(manualQgen)
                .addGap(6, 6, 6)
                .addComponent(startGameBtn))
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void automaticQgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticQgenActionPerformed
        // TODO add your handling code here:

        automaticQgen.setEnabled(false);
        
        String numberOfQuestionsString = (String) numberOfQ.getSelectedItem();
        int numberOfQuestions = Integer.parseInt(numberOfQuestionsString);
        s.numberOfQuestions = numberOfQuestions;
        
        //Server.scores = new int[clients.size()][numberOfQuestions];
        Server.scores = new int[clients.size()];
        Server.responseCollected = new int[clients.size()];

        GameSequenceGenerator g;

        try {
            g = new GameSequenceGenerator();
            gameSequence = g.createSequenceAutomatic(numberOfQuestions);
            System.out.println("automatic questions generated - game controls teacher class");
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GameControlsTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        startGameBtn.setEnabled(true);

    }//GEN-LAST:event_automaticQgenActionPerformed

    private void startGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameBtnActionPerformed
        // TODO add your handling code here:
        
        // we send the gameSequence to each client using the clientHandlers
        for (ClientHandler clientHandler : clients) { 
            try {
                clientHandler.sendData(Integer.toString(s.numberOfQuestions));
                clientHandler.sendData(gameSequence);
            } catch (IOException ex) {
                Logger.getLogger(GameControlsTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Game sequence sent to " + clientHandler.toString());
        }
        
        Server.responseCollected = new int[clients.size()];
        
        
        leaderboard.setVisible(true);
        
        
    }//GEN-LAST:event_startGameBtnActionPerformed

    private void numberOfQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberOfQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberOfQActionPerformed

    private void manualQgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualQgenActionPerformed
        try {
            // TODO add your handling code here:
            GameSequenceGenerator g = new GameSequenceGenerator();
            DragAndDrop myDragAndDrop = new DragAndDrop(clients);
            myDragAndDrop.setVisible(true);
            
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GameControlsTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameControlsTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        leaderboard.setVisible(true);
    }//GEN-LAST:event_manualQgenActionPerformed

    private void regionFilterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionFilterBoxActionPerformed
 
    }//GEN-LAST:event_regionFilterBoxActionPerformed

    private void regionFilterBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionFilterBox1ActionPerformed
        // TODO add your handling code here:
     

    }//GEN-LAST:event_regionFilterBox1ActionPerformed

    private void regionFilterBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionFilterBox2ActionPerformed
       
    }//GEN-LAST:event_regionFilterBox2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton automaticQgen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manualQgen;
    private javax.swing.JComboBox<String> numberOfQ;
    private javax.swing.JComboBox<String> regionFilterBox;
    private javax.swing.JComboBox<String> regionFilterBox1;
    private javax.swing.JComboBox<String> regionFilterBox2;
    private javax.swing.JButton startGameBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
