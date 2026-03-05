package GUI;

import Custom.ImagePanel;
import java.awt.*;
import javax.swing.*;
import BUS.TaiKhoanBUS;

public class DangNhapGUI extends JFrame {

    private JPanel MainPanel;
    private JPanel jPanelLeft;
    private JPanel jPanelRight;
    private JPanel jPanelLogo;
    
    private JLabel lblTitle;
    private JLabel lblUser;
    private JLabel lblPass;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JCheckBox chkShowPass;
    private JButton btnLogin;
    private char defaultEchoChar;
    
    private JLabel lblClose;
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    public DangNhapGUI() {
        this.initComponents();
        this.addControls();
        this.addEvents(); 
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Đăng nhập");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(
                0, 0,
                getWidth(),
                getHeight(),
                40, 40
        ));
    }
    
    private void addControls() {
        MainPanel = new JPanel(new GridLayout(1, 2));

       
        jPanelLeft = new JPanel(new BorderLayout());
        jPanelLeft.setBackground(Color.WHITE);
        jPanelLeft.setPreferredSize(new Dimension(350, 500));

        jPanelLogo = new JPanel(null);
        jPanelLogo.setPreferredSize(new Dimension(350, 120));
        jPanelLogo.setBackground(Color.WHITE);
        ImagePanel logoPanel = new ImagePanel("/images/logo.png", true);
        logoPanel.setBounds(50, -15, 250, 150);

        jPanelLogo.add(logoPanel);
        jPanelLeft.add(jPanelLogo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setBounds(60, 10, 350, 50);

        formPanel.add(lblTitle);

        lblUser = new JLabel("Mã nhân viên");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblUser.setBounds(50, 80, 200, 20);
        formPanel.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(40, 110, 270, 35);
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUser.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        formPanel.add(txtUser);
        
        lblPass = new JLabel("Mật khẩu");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPass.setBounds(50, 160, 200, 20);
        formPanel.add(lblPass);
        
        txtPass = new JPasswordField();
        txtPass.setBounds(40, 190, 270, 35);
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        defaultEchoChar = txtPass.getEchoChar();
        formPanel.add(txtPass);
        
        chkShowPass = new JCheckBox("Hiện mật khẩu");
        chkShowPass.setBackground(Color.WHITE);
        chkShowPass.setBounds(40, 230, 150, 20);
        chkShowPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(chkShowPass);
        
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setBounds(40, 280, 270, 40);
        btnLogin.setBackground(new Color(255,140,0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        formPanel.add(btnLogin);
        
        jPanelLeft.add(formPanel, BorderLayout.CENTER);
        MainPanel.add(jPanelLeft);
        
        
        jPanelRight = new ImagePanel("/images/pizza.png", false);
        jPanelRight.setLayout(null); // cho phép đặt icon
        jPanelRight.setBackground(Color.WHITE);

        this.createCloseButton();

        MainPanel.add(jPanelRight);

        add(MainPanel, BorderLayout.CENTER);
    }
    private void addEvents() {

        btnLogin.addActionListener(e -> xuLyDangNhap());

        chkShowPass.addActionListener(e -> togglePassword());
    }
    
    private void createCloseButton() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/close.png"));
        Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        lblClose = new JLabel(icon);

        lblClose.setBounds(310, 10, 32, 32); // góc phải panel
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.exit(0);
            }
        });
        jPanelRight.add(lblClose);
    }
    
   
   private void togglePassword() {
    if (chkShowPass.isSelected()) {
      txtPass.setEchoChar((char) 0); // hiện mật khẩu
    } else {
      txtPass.setEchoChar(defaultEchoChar); // trở lại dấu chấm ban đầu
    }
   }
   private void xuLyDangNhap() {

        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        TaiKhoanBUS bus = new TaiKhoanBUS();

        boolean result = bus.Login(username, password);

        if(result){
            JOptionPane.showMessageDialog(this,"Đăng nhập thành công!");

            // mở form chính
            // new MainGUI().setVisible(true);
            Utils.WindowUtil.showWindow(new DashboardGUI());
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Sai tài khoản hoặc mật khẩu!");
        }
    }
   
}