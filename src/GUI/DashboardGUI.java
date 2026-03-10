/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import Custom.ImagePanel;
import Custom.RoundedButton;
import Custom.RoundedPanel;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author quock
 */
public class DashboardGUI extends javax.swing.JFrame {

    /**
     * Creates new form DashboardGUI
     */
    private NhanVienBUS nhanVienBUS;
    private QuyenBUS quyenBUS;
    private String maQuyen;
    private String maNV;
    private RoundedButton btnDangXuat;
    private RoundedPanel cardSell;
    private RoundedPanel cardManage;
    private JPanel itemProfile;
    public DashboardGUI(String maNV, String maQuyen) {
        this.nhanVienBUS = new NhanVienBUS(); 
        this.quyenBUS = new QuyenBUS();
        this.maNV = maNV;
        this.maQuyen = maQuyen;
        this.btnDangXuat = new RoundedButton(20);
        initComponents();
        this.setTitle("Trang chủ");
        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        this.addControls();
        this.addEvents();
    }
    private void addControls(){
        this.avatar.setLayout(new java.awt.GridBagLayout()); // căn giữa
        
        ImagePanel avatarImage = new ImagePanel("/images/avatarAccount.png",true);
        avatarImage.setPreferredSize(new java.awt.Dimension(120, 120)); // size avatar

        this.avatar.add(avatarImage);
        
        NhanVienDTO nhanVien = this.nhanVienBUS.getNhanVienById(this.maNV);
        QuyenDTO quyen = this.quyenBUS.getQuyenById(this.maQuyen);
        
        this.lblName.setText(nhanVien.getHoTen());
        this.lblRole.setText("Vai trò: " + quyen.getTenQuyen());
        this.lblName.setForeground(Color.WHITE);
        this.lblRole.setForeground(new Color(180,180,180));
        this.lblName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        this.lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        this.lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator line = new JSeparator();
        line.setForeground(new Color(80,80,80));
        line.setMaximumSize(new Dimension(200,1));


        this.inforPanel.add(this.lblName);
        this.inforPanel.add(Box.createVerticalStrut(15));
        this.inforPanel.add(this.lblRole);
        this.inforPanel.add(Box.createVerticalStrut(15));
        this.inforPanel.add(line);
        this.inforPanel.add(Box.createVerticalStrut(15));
        this.inforPanel.add(this.panelMenu);
        
        this.itemProfile = new JPanel();
        this.itemProfile.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 10));
        this.itemProfile.setBackground(new Color(31,33,37));
        this.itemProfile.setMaximumSize(new Dimension(250,40));
        this.itemProfile.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/user.png"));
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);

        JLabel iconLabel = new JLabel(newIcon);
        JLabel text = new JLabel("Thông tin cá nhân");
        
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        itemProfile.add(iconLabel);
        itemProfile.add(text);
        itemProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.panelMenu.add(itemProfile);
        
        this.logoutBtn.setLayout(new java.awt.GridBagLayout());
        this.logoutBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(20,20,20,20));
        
        
        this.btnDangXuat.setText("Đăng xuất");
        this.btnDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.btnDangXuat.setBackground(new Color(255,59,48));
        this.btnDangXuat.setForeground(Color.WHITE);
        this.btnDangXuat.setBorderPainted(false);
        this.btnDangXuat.setFocusPainted(false);
        this.btnDangXuat.setPreferredSize(new Dimension(180,45));
        this.btnDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        this.logoutBtn.add(this.btnDangXuat);
        this.container.setBackground(Color.WHITE);
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
        
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imgLogo = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imgLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.container.add(Box.createVerticalStrut(20));
        this.container.add(logo);
        this.container.add(Box.createVerticalStrut(80));
        
        
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        
        String urlImageCardSell = "/images/POS.png";
        String titleCardSell = "BÁN HÀNG";
        String descriptionCardSell = "<html><center>Phục vụ món ăn,<br>hóa đơn & thanh toán.</center></html>";
        this.cardSell = this.createCardItem(urlImageCardSell, titleCardSell, descriptionCardSell,35);
       
        
        String urlImageCardManage = "/images/ManageIcon.png";
        String titleCardManage = "QUẢN LÝ";
        String descriptionCardManage = "<html><center>Báo cáo doanh thu,<br>kho, nhân sự & menu</center></html>";
        this.cardManage = this.createCardItem(urlImageCardManage, titleCardManage, descriptionCardManage,25);
        
        cardPanel.add(cardSell);
        cardPanel.add(cardManage);

        this.container.add(cardPanel);
    }
    private void addEvents(){
        btnDangXuat.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn đăng xuất?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if(confirm == JOptionPane.YES_OPTION){
            Utils.WindowUtil.showWindow(new DangNhapGUI());
            this.dispose();
        }
    });
        
        this.cardSell.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            Utils.WindowUtil.showWindow(new BanHangGUI(maNV, maQuyen));
            dispose();
        }
    });
        this.cardManage.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if(quyenBUS.isAdmin(maQuyen)){
                //new QuanLyGUI(maNV, maVaiTro).setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Bạn không có quyền truy cập chức năng này");
            }
        }
    });
        this.itemProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                NhanVienDTO nv = nhanVienBUS.getNhanVienById(maNV);
                QuyenDTO quyen = quyenBUS.getQuyenById(maQuyen);

                NhanVienProfileDlg dlg = new NhanVienProfileDlg(
                        DashboardGUI.this,
                        nv,
                        quyen.getTenQuyen()
                );

                dlg.setVisible(true);
            }
        });
    }
    private RoundedPanel createCardItem(String urlImage, String titleItem, String descriptionItem, int verticalStrut)
    {
        RoundedPanel card = new RoundedPanel(30);
        card.setPreferredSize(new Dimension(500, 500));
        card.setBackground(new Color(255,153,0));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel(new ImageIcon(
                new ImageIcon(getClass().getResource(urlImage))
                .getImage().getScaledInstance(300,240,Image.SCALE_SMOOTH)));

        JLabel title = new JLabel(titleItem);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JLabel descSell = new JLabel(descriptionItem);
        descSell.setHorizontalAlignment(JLabel.CENTER);
        descSell.setForeground(Color.WHITE);
        descSell.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        descSell.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(Box.createVerticalStrut(verticalStrut));
        card.add(icon);
        card.add(Box.createVerticalStrut(35));
        card.add(title);
        card.add(Box.createVerticalStrut(30));
        card.add(descSell);
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return card;
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSidebar = new javax.swing.JPanel();
        avatar = new javax.swing.JPanel();
        inforPanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelSidebar.setBackground(new java.awt.Color(31, 33, 37));
        panelSidebar.setPreferredSize(new java.awt.Dimension(250, 650));
        panelSidebar.setLayout(new java.awt.BorderLayout());

        avatar.setBackground(new java.awt.Color(31, 33, 37));
        avatar.setPreferredSize(new java.awt.Dimension(250, 200));

        javax.swing.GroupLayout avatarLayout = new javax.swing.GroupLayout(avatar);
        avatar.setLayout(avatarLayout);
        avatarLayout.setHorizontalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        avatarLayout.setVerticalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        panelSidebar.add(avatar, java.awt.BorderLayout.NORTH);

        inforPanel.setBackground(new java.awt.Color(31, 33, 37));
        inforPanel.setLayout(new javax.swing.BoxLayout(inforPanel, javax.swing.BoxLayout.Y_AXIS));

        lblName.setText("jLabel1");
        inforPanel.add(lblName);

        lblRole.setText("jLabel2");
        inforPanel.add(lblRole);

        panelMenu.setLayout(new javax.swing.BoxLayout(panelMenu, javax.swing.BoxLayout.Y_AXIS));
        inforPanel.add(panelMenu);

        panelSidebar.add(inforPanel, java.awt.BorderLayout.CENTER);

        logoutBtn.setBackground(new java.awt.Color(31, 33, 37));
        panelSidebar.add(logoutBtn, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(panelSidebar, java.awt.BorderLayout.LINE_START);

        panelMain.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        panelMain.add(container, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel avatar;
    private javax.swing.JPanel container;
    private javax.swing.JPanel inforPanel;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JPanel logoutBtn;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelSidebar;
    // End of variables declaration//GEN-END:variables
}
