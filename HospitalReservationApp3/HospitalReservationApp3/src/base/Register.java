/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS TUF
 */
public class Register extends javax.swing.JFrame {
    private static Connection conn;
    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
        conn = SQLConnector.getConnection();
        showDoctor();
        showPatient();
    }
    
     public void showDoctor() {
        try {
            String sql = "SELECT username, name FROM doctors WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "Input your name here");
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                USERNAME.setText(name);
                NAME.setText(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HospitalReservationApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void showPatient() {
        try {
            String sql = "SELECT username, name FROM patients WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "Input your name here");
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                USERNAME.setText(name);
                NAME.setText(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HospitalReservationApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        USERNAME = new javax.swing.JTextField();
        NAME = new javax.swing.JTextField();
        PATIENT = new javax.swing.JButton();
        DOCTOR = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        USERNAME.setText("Username");
        USERNAME.setBorder(null);

        NAME.setText("Name");
        NAME.setBorder(null);
        NAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAMEActionPerformed(evt);
            }
        });

        PATIENT.setBackground(new java.awt.Color(80, 39, 121));
        PATIENT.setForeground(new java.awt.Color(255, 255, 255));
        PATIENT.setText("Patient");
        PATIENT.setBorder(null);
        PATIENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PATIENTActionPerformed(evt);
            }
        });

        DOCTOR.setBackground(new java.awt.Color(80, 39, 121));
        DOCTOR.setForeground(new java.awt.Color(255, 255, 255));
        DOCTOR.setText("Doctor");
        DOCTOR.setBorder(null);
        DOCTOR.setBorderPainted(false);
        DOCTOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOCTORActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiuxfix/1.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(USERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(PATIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(DOCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1406, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(USERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250)
                .addComponent(PATIENT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(740, 740, 740)
                .addComponent(DOCTOR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NAMEActionPerformed

    private void PATIENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PATIENTActionPerformed
        try {
            String sql = "INSERT INTO patients(name, username) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            String name = NAME.getText();
            String username = USERNAME.getText();
            st.setString(1, name);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HospitalReservationApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        login Log = new login();
        Log.setVisible(true);
    }//GEN-LAST:event_PATIENTActionPerformed

    private void DOCTORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOCTORActionPerformed
        try {
            String sql = "INSERT INTO doctors(name, username) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            String name = NAME.getText();
            String username = USERNAME.getText();
            st.setString(1, name);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HospitalReservationApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        login Log = new login();
        Log.setVisible(true);
    }//GEN-LAST:event_DOCTORActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DOCTOR;
    private javax.swing.JTextField NAME;
    private javax.swing.JButton PATIENT;
    private javax.swing.JTextField USERNAME;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}