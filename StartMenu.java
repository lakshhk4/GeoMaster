package com.mycompany.mavenproject1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartMenu extends javax.swing.JFrame {

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public StartMenu() throws IOException {
        initComponents();
        this.setResizable(false);
    }

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        titleField = new javax.swing.JLabel();
        startServerButton = new javax.swing.JButton();
        joinServerInputField = new javax.swing.JTextField();
        joinServerButton = new javax.swing.JButton();
        serverAddressDisplay = new javax.swing.JLabel();
        connectionSuccessfulDisplay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleField.setText("                     GUESS THE COUNTRY BY ITS FLAG!");

        startServerButton.setText("Start Server");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        joinServerInputField.setText("Enter Server Address");
        joinServerInputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinServerInputFieldActionPerformed(evt);
            }
        });

        joinServerButton.setText("Join Server");
        joinServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinServerButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("/Users/lakshhkhatri/Downloads/Untitled design.png")); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(serverAddressDisplay))
            .addGroup(layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(connectionSuccessfulDisplay))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(startServerButton))
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(joinServerInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(joinServerButton))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(serverAddressDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(connectionSuccessfulDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(startServerButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(joinServerInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(joinServerButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
        // creates a server if user clicks the "Start server button"
        Server s = new Server();
        // executes the server thread
        pool.execute(s);

        this.setVisible(false);

        // displays the server ip on the JFrame GUI screen
        serverAddressDisplay.setText("localhost");

        // disable joinServer button
        joinServerButton.setEnabled(false);

    }//GEN-LAST:event_startServerButtonActionPerformed

    private void joinServerInputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinServerInputFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_joinServerInputFieldActionPerformed

    private void joinServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinServerButtonActionPerformed
        try {
            // this code is executed when the user clicks the joinServer button. It will connect the
            // user to the server ID provided.

            // reads the server ID the user has given
            String serverToJoin = joinServerInputField.getText();
            Client client = new Client(serverToJoin, this.getX(), this.getY());
            pool.execute(client);
            this.setVisible(false);

        } catch (IOException ex) {
            String infoMessage = "Input a Valid Server address";
            String titleBar = "Error: Invalid server address";
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_joinServerButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StartMenu().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connectionSuccessfulDisplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton joinServerButton;
    private javax.swing.JTextField joinServerInputField;
    private javax.swing.JLabel serverAddressDisplay;
    private javax.swing.JButton startServerButton;
    private javax.swing.JLabel titleField;
    // End of variables declaration//GEN-END:variables
}
