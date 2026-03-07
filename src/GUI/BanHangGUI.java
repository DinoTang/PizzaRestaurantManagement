/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Custom.RoundedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author quock
 */
public class BanHangGUI extends javax.swing.JFrame {
    
    private JLabel exitIcon;

    /**
     * Creates new form BanHangGUI
     */
    public BanHangGUI() {
        initComponents();
        this.setTitle("Bán Hàng");
        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.addControls();
        this.addEvents();
    }

    private void addControls(){
        this.headerPanel.setPreferredSize(new Dimension(0,100));
        
        this.exitPanel.setLayout(new FlowLayout(java.awt.FlowLayout.LEFT, 20, 15));

        this.exitIcon = new JLabel(
            new ImageIcon(
                new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                .getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)
            )
        );
        exitIcon.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        
        this.exitPanel.add(exitIcon);
       
        RoundedPanel menuFoodPanel = new RoundedPanel(40, Color.decode("#3A3A3A"));
        menuFoodPanel.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 2.0 / 3);
        menuFoodPanel.setPreferredSize(new Dimension(width, 0));
        menuFoodPanel.setBackground(Color.decode("#1F2125"));
        container.add(menuFoodPanel, BorderLayout.LINE_START);
        
        RoundedPanel cartPanel = new RoundedPanel(40);
        width = (int)(screenSize.width * 1.0 / 3);
        cartPanel.setPreferredSize(new Dimension(width, 0));
        container.add(cartPanel, BorderLayout.LINE_END);
        
        JPanel menuHeader = new JPanel();
        menuHeader.setPreferredSize(new Dimension(0, 120));
        menuHeader.setBackground(Color.decode("#3A3A3A"));
        menuHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        menuHeader.setOpaque(false);
        menuFoodPanel.add(menuHeader, BorderLayout.NORTH);
        
        JPanel menuContainer = new JPanel();
        menuContainer.setBackground(Color.decode("#383C42"));
        menuContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#3A3A3A"), 3));
        menuFoodPanel.add(menuContainer, BorderLayout.CENTER);
    }
    
    private void addEvents(){
        this.exitIcon.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
//            Utils.WindowUtil.showWindow(new DashboardGUI());
            dispose();
        }
    });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        exitPanel = new javax.swing.JPanel();
        container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerPanel.setBackground(new java.awt.Color(31, 33, 37));
        headerPanel.setPreferredSize(new java.awt.Dimension(950, 50));
        headerPanel.setLayout(new java.awt.BorderLayout());

        exitPanel.setBackground(new java.awt.Color(31, 33, 37));

        javax.swing.GroupLayout exitPanelLayout = new javax.swing.GroupLayout(exitPanel);
        exitPanel.setLayout(exitPanelLayout);
        exitPanelLayout.setHorizontalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        exitPanelLayout.setVerticalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        headerPanel.add(exitPanel, java.awt.BorderLayout.LINE_END);

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        container.setBackground(new java.awt.Color(31, 33, 37));
        container.setLayout(new java.awt.BorderLayout());
        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BanHangGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JPanel exitPanel;
    private javax.swing.JPanel headerPanel;
    // End of variables declaration//GEN-END:variables
}
