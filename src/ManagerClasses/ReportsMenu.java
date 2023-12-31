package ManagerClasses;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.ResultSet;

/**
 *
 * @author Jamie-Lee
 *
 * This class is a form which allows office managers to select what type of report that
 * they wish to generate through selecting a type of report using the listed buttons on the frame.
 */
public class ReportsMenu extends javax.swing.JFrame {

    private String id;
    private String name;

    SQLReports r = new SQLReports();
    /**
     * Creates new form ReportsMenu
     */
    public ReportsMenu(String i, String n) {
        id = i;
        name = n;
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
        jLabel1 = new javax.swing.JLabel();
        individualDomesticButton = new javax.swing.JButton();
        globalInterlineButton = new javax.swing.JButton();
        globalDomesticButton = new javax.swing.JButton();
        ticketTurnoverButton = new javax.swing.JButton();
        individualInterlineButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reports Menu");
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Generate Report");

        individualDomesticButton.setText("Inidvidual Domestic Report");
        individualDomesticButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualDomesticButtonActionPerformed(evt);
            }
        });

        globalInterlineButton.setText("Global Interline Report");
        globalInterlineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalInterlineButtonActionPerformed(evt);
            }
        });

        globalDomesticButton.setText("Global Domestic Report");
        globalDomesticButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalDomesticButtonActionPerformed(evt);
            }
        });

        ticketTurnoverButton.setText("Ticket Turnover Report");
        ticketTurnoverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketTurnoverButtonActionPerformed(evt);
            }
        });

        individualInterlineButton.setText("Inidvidual Interline Report");
        individualInterlineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualInterlineButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Select the report to be generated:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(133, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(individualInterlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ticketTurnoverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(globalDomesticButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(globalInterlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(individualDomesticButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(126, 126, 126))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(individualInterlineButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(individualDomesticButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(globalInterlineButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(globalDomesticButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ticketTurnoverButton)
                                .addContainerGap(204, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    /**
     * Opens the form asking the office manager to enter what advisor's sales they wish to generate in the
     * individual interline sales report.
     */
    private void individualInterlineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new EnterInterline().setVisible(true);
        dispose();
    }

    /**
     * Opens the form asking the office manager to enter what advisor's sales they wish to generate in the
     * individual domestic sales report.
     */
    private void individualDomesticButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new EnterDomestic().setVisible(true);
        dispose();
    }

    /**
     * Generates the global interline sales report.
     */
    private void globalInterlineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ResultSet rs = r.ResultSetForGIR();
        new GlobalInterline(this,rs, id, name).setVisible(true);
    }

    /**
     * Generates the global domestic sales report.
     */
    private void globalDomesticButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ResultSet rs = r.ResultSetForGDR();
        new GlobalDomestic(this, rs, id, name).setVisible(true);
    }

    /**
     * Generates the ticket stock turnover report.
     */
    private void ticketTurnoverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new TurnoverReport(this, id , name).setVisible(true);
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
            java.util.logging.Logger.getLogger(ReportsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ReportsMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton individualDomesticButton;
    private javax.swing.JButton globalInterlineButton;
    private javax.swing.JButton globalDomesticButton;
    private javax.swing.JButton ticketTurnoverButton;
    private javax.swing.JButton individualInterlineButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration


    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}

