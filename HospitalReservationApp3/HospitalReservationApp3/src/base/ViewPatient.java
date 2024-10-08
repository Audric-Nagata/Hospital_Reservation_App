/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS TUF
 */
public class ViewPatient extends javax.swing.JFrame {
    private final DefaultTableModel tableModel;
    /**
     * Creates new form ViewPatient
     */
    public ViewPatient() {
        initComponents();
        tableModel = new DefaultTableModel();
        Table.setModel(tableModel);
        
        loadDataFromDatabase();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        KEMBALI = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KEMBALI.setText("Kembali");
        KEMBALI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KEMBALIActionPerformed(evt);
            }
        });
        getContentPane().add(KEMBALI, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 660, 110, 40));

        jScrollPane2.setViewportView(Table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 790, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uiuxfix/viewpatient.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, -1, 770));

        jList1.setBorder(null);
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 790, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void loadDataFromDatabase() {
        try {
            // Establish a database connection (replace with your database details)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hospitalreservationapp", "root", "");

            // Execute a query to retrieve data (replace with your SQL query)
            String sql = "SELECT p.name, r.session_start_time FROM patients p JOIN reservations r ON p.username = r.patient_username";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            
            int columnCount = resultSet.getMetaData().getColumnCount();
            Object[] columns = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columns[i - 1] = resultSet.getMetaData().getColumnName(i);
            }

            // Set column names in the table model
            tableModel.setColumnIdentifiers(columns);

            // Populate the DefaultTableModel with data from the ResultSet
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }

            // Close resources
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
        }
    }
    
    private doctorpage Doc;
    private void KEMBALIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KEMBALIActionPerformed
        if (Doc == null || !Doc.isVisible()) {
        // If secondFrame is not instantiated or not visible, create and show it
        Doc = new doctorpage();
        Doc.setVisible(true);
    } else {
        // If secondFrame is already visible, bring it to the front
        Doc.toFront();
    }
    }//GEN-LAST:event_KEMBALIActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KEMBALI;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}