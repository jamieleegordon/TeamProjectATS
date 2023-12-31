package LoginPages;


import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *
 * @author Jamie-Lee
 *
 * This class is a form used to allow users to select their role and lead them to the appropriate login pages (this is the start page)
 */
public class WelcomePage extends javax.swing.JFrame {

    private int code;

    /**
     * Creates new form Login
     */
    public WelcomePage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        adminIcon = new javax.swing.JLabel();
        managerIcon = new javax.swing.JLabel();
        advisorIcon = new javax.swing.JLabel();
        adminLoginButton = new javax.swing.JButton();
        managerLoginButton = new javax.swing.JButton();
        advisorLoginButton = new javax.swing.JButton();
        userManualButton = new javax.swing.JButton();
        adminText = new javax.swing.JLabel();
        managerText = new javax.swing.JLabel();
        advisorText = new javax.swing.JLabel();
        uniJetLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(51, 51, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setForeground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 600));

        jLabel6.setIcon(new javax.swing.ImageIcon("data/AirViaLogo.png")); // NOI18N
        jLabel6.setText("jLabel6");

        adminIcon.setIcon(new javax.swing.ImageIcon("data/admin.png")); // NOI18N

        managerIcon.setIcon(new javax.swing.ImageIcon("data/manager.png")); // NOI18N

        advisorIcon.setIcon(new javax.swing.ImageIcon("data/advisor.png")); // NOI18N

        adminLoginButton.setText("LOGIN");

        adminLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLoginButtonActionPerformed(evt);
            }
        });

        managerLoginButton.setText("LOGIN");

        managerLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerLoginButtonActionPerformed(evt);
            }
        });

        advisorLoginButton.setText("LOGIN");

        advisorLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advisorLoginButtonActionPerformed(evt);
            }
        });

        adminText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        adminText.setText("System Administrator");

        managerText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        managerText.setText("Office Manager");

        advisorText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        advisorText.setText("Travel Advisor");

        uniJetLogo.setIcon(new javax.swing.ImageIcon("data/uniJetLogo.png")); // NOI18N

        userManualButton.setText("Open User Manual");

        userManualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    userManualButtonActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(uniJetLogo)
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(adminIcon)
                                                        .addComponent(adminLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(managerIcon)
                                                        .addComponent(managerLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(149, 149, 149)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(advisorIcon)
                                                        .addComponent(advisorLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(adminText)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(managerText))
                                                .addGap(144, 144, 144)
                                                .addComponent(advisorText)
                                                .addGap(14, 14, 14)))
                                .addGap(207, 207, 207))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(uniJetLogo)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(adminText)
                                                        .addComponent(managerText)
                                                        .addComponent(advisorText))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(adminLoginButton)
                                                                        .addComponent(managerLoginButton)
                                                                        .addComponent(advisorLoginButton))
                                                                .addGap(64, 64, 64))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(29, 29, 29)
                                                                .addComponent(advisorIcon)
                                                                .addContainerGap(108, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(managerIcon)
                                                        .addComponent(adminIcon))
                                                .addGap(111, 111, 111))))

        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        userManualButton.setBounds(100,100,100,100);

        pack();
    }// </editor-fold>

    // Generates random 6-digit number for two-factor authentication
    private int generateSixDigit() {
        return (int) ((Math.random() * (999999)));
    }

    /**
     * method used to navigate to the admin log in page.
     */
    private void adminLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new AdminLogin().setVisible(true);
        dispose();
    }

    /**
     * method used to navigate to the manager log in page.
     */
    private void managerLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new ManagerLogin().setVisible(true);
        dispose();
    }

    /**
     * method used to navigate to the advisor log in page.
     */
    private void advisorLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new AdvisorLogin().setVisible(true);
        dispose();
    }

    private void userManualButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            Desktop.getDesktop().open(new File("data/UserManual.pdf"));
        } catch (Exception e){
            System.out.println(e);
        }
    }

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
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton userManualButton;
    private javax.swing.JLabel adminIcon;
    private javax.swing.JButton adminLoginButton;
    private javax.swing.JLabel adminText;
    private javax.swing.JLabel advisorIcon;
    private javax.swing.JButton advisorLoginButton;
    private javax.swing.JLabel advisorText;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel managerIcon;
    private javax.swing.JButton managerLoginButton;
    private javax.swing.JLabel managerText;
    private javax.swing.JLabel uniJetLogo;
    // End of variables declaration
}


