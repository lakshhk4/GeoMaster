/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Leaderboard extends javax.swing.JFrame implements Runnable {

    String gameSequence;
    Client client;

    DefaultTableModel tableModel;
    public Leaderboard(String gameSequence, Client client) {
        initComponents();
        this.gameSequence = gameSequence;
        this.client = client;
        /*DefaultTableModel model = new DefaultTableModel();
        jTable1.setModel(model);
        
        List<String> list = new ArrayList<String>();
        list.add("Lakshh");
        list.add("150");
        model.addRow(list.toArray());
        jTable1.setVisible(true);
         */

        jButton1.setVisible(false);

    }

    // polymorphism --> another constructor for Server side leaderboard
    public Leaderboard() {
        initComponents();
        this.setVisible(true);
    }

    public void updateLeaderboard(ArrayList<String> playerNames, ArrayList<Integer> scores) {
        String col[] = {"PLayer", "Score"};
        tableModel = new DefaultTableModel(col, 0);

        //jTable1 = null;
        //jTable1 = new JTable(tableModel);
        jTable1.setModel(tableModel);

        for (int i = 0; i < playerNames.size(); i++) {
            String[] row = new String[2];
            row[0] = playerNames.get(i);
            row[1] = Integer.toString(scores.get(i));
            tableModel.addRow(row);

        }

        /*        String[] row = {"Lakshh", "100"};
        String[] row2 = {"Lakshh", "300"};
        tableModel.addRow(row);
        tableModel.removeRow(0);
        tableModel.addRow(row2);*/
    }

    public void lastQuestion() {
        jLabel1.setText("GAME OVER");
        

        ArrayList gameQuesAndOptions = SequenceInterpretor.interpretGameSequence(gameSequence);
        DidYouGuess5 didYouGuess = new DidYouGuess5(gameQuesAndOptions, client);
        
        // podium 
        
        String firstPlace = (String) tableModel.getValueAt(0, 0);
        String secondPlace = (String) tableModel.getValueAt(1, 0);
        //String thirdPlace = (String) tableModel.getValueAt(2, 0);
        
        System.out.println(firstPlace);
        System.out.println(secondPlace);
        this.setVisible(false);
        Podium podium = new Podium(firstPlace, secondPlace);
        podium.setLocation(this.getX(), this.getY());
        
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Leaderboard");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Player Name", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Download Leaderboards");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("/Users/lakshhkhatri/Downloads/Untitled design.png")); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // download table

        JFileChooser fchoose = new JFileChooser();
        int option = fchoose.showSaveDialog(Leaderboard.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String name = fchoose.getSelectedFile().getName();
            String path = fchoose.getSelectedFile().getParentFile().getPath();
            String file = path + "\\" + name + ".xls";
            export(jTable1, new File(file));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void export(JTable table, File file) {
        try {
            TableModel model = table.getModel();
            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < model.getColumnCount(); i++) {
                fw.write(model.getColumnName(i) + "\t");
            }

            fw.write("\n");

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    fw.write(model.getValueAt(row, col).toString() + "\t");
                }
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
    }
}
