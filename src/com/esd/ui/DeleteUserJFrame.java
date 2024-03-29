/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DeleteUserJFrame.java
 *
 * Created on Oct 10, 2009, 5:08:30 PM
 */
package com.esd.ui;

import com.esd.control.AuthenticationManager;
import com.esd.control.AuthenticationManagerImpl;
import com.esd.pojo.Login;
import com.esd.util.Constants;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Duminda
 */
public class DeleteUserJFrame extends javax.swing.JFrame {

    private AuthenticationManager authenticationManager;
    private List<Login> userList;

    public DeleteUserJFrame() {
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        setLocation(width / 4, height / 4);
        authenticationManager = new AuthenticationManagerImpl();

        loadUsers();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userNameCb = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("User Name");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Menu");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(userNameCb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userNameCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int status = JOptionPane.showConfirmDialog(this, "Are you sure want to delete User", "Delete User", JOptionPane.YES_NO_OPTION);

        if (status == 0) {

            String username = (String) userNameCb.getSelectedItem();

            String response = authenticationManager.deleteUser(username);

            if (response.equals(Constants.USER_DELETED_SUCCESSFULLY)) {

                userNameCb.removeItem(username);

                JOptionPane.showMessageDialog(this, "User Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (response.equals(Constants.ERROR)) {
                JOptionPane.showMessageDialog(this, "User did not Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DeleteUserJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JComboBox userNameCb;
    // End of variables declaration//GEN-END:variables

    public void loadUsers() {

        userList = (Vector<Login>) authenticationManager.getusers();

        if (userList != null && userList.size() > 0) {

            Iterator it = userList.iterator();

            while (it.hasNext()) {

                Login login = (Login) it.next();

                userNameCb.addItem(login.getUsername());

            }
        } else {

            userNameCb.setEnabled(false);

        }

    }
}
