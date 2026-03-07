package GUI;

import Custom.ImagePanel;
import java.awt.*;
import javax.swing.*;
import BUS.TaiKhoanBUS;
import Custom.RoundedButton;
import DTO.TaiKhoanDTO;

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
    private RoundedButton btnLogin;
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
        this.MainPanel = new JPanel(new GridLayout(1, 2));

       
        this.jPanelLeft = new JPanel(new BorderLayout());
        this.jPanelLeft.setBackground(Color.WHITE);
        this.jPanelLeft.setPreferredSize(new Dimension(350, 500));

        this.jPanelLogo = new JPanel(null);
        this.jPanelLogo.setPreferredSize(new Dimension(350, 120));
        this.jPanelLogo.setBackground(Color.WHITE);
        ImagePanel logoPanel = new ImagePanel("/images/logo.png", true);
        logoPanel.setBounds(50, -15, 250, 150);

        this.jPanelLogo.add(logoPanel);
        this.jPanelLeft.add(this.jPanelLogo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        this.lblTitle = new JLabel("ĐĂNG NHẬP");
        this.lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        this.lblTitle.setBounds(60, 10, 350, 50);

        formPanel.add(this.lblTitle);

        this.lblUser = new JLabel("Mã nhân viên");
        this.lblUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.lblUser.setBounds(50, 80, 200, 20);
        formPanel.add(this.lblUser);

        this.txtUser = new JTextField();
        this.txtUser.setBounds(40, 110, 270, 35);
        this.txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        this.txtUser.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        formPanel.add(this.txtUser);
        
        this.lblPass = new JLabel("Mật khẩu");
        this.lblPass.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.lblPass.setBounds(50, 160, 200, 20);
        formPanel.add(this.lblPass);
        
        this.txtPass = new JPasswordField();
        this.txtPass.setBounds(40, 190, 270, 35);
        this.txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        this.txtPass.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 2),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        this.defaultEchoChar = this.txtPass.getEchoChar();
        formPanel.add(this.txtPass);
        
        this.chkShowPass = new JCheckBox("Hiện mật khẩu");
        this.chkShowPass.setBackground(Color.WHITE);
        this.chkShowPass.setBounds(40, 230, 150, 20);
        this.chkShowPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(this.chkShowPass);
        
        this.btnLogin = new RoundedButton(20);
        this.btnLogin.setText("ĐĂNG NHẬP");
        this.btnLogin.setBounds(40, 280, 270, 40);
        this.btnLogin.setBackground(new Color(255,140,0));
        this.btnLogin.setForeground(Color.WHITE);
        this.btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.btnLogin.setFocusPainted(false);
        formPanel.add(this.btnLogin);
        
        this.jPanelLeft.add(formPanel, BorderLayout.CENTER);
        this.MainPanel.add(this.jPanelLeft);
        
        
        this.jPanelRight = new ImagePanel("/images/pizza.png", false);
        this.jPanelRight.setLayout(null); // cho phép đặt icon
        this.jPanelRight.setBackground(Color.WHITE);

        this.createCloseButton();

        MainPanel.add(this.jPanelRight);

        add(this.MainPanel, BorderLayout.CENTER);
    }
    private void addEvents() {

        this.btnLogin.addActionListener(e -> xuLyDangNhap());

        this.chkShowPass.addActionListener(e -> togglePassword());
    }
    
    private void createCloseButton() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/close.png"));
        Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        this.lblClose = new JLabel(icon);

        this.lblClose.setBounds(310, 10, 32, 32); // góc phải panel
        this.lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.exit(0);
            }
        });
        this.jPanelRight.add(lblClose);
    }
    
   
   private void togglePassword() {
    if (this.chkShowPass.isSelected()) {
      this.txtPass.setEchoChar((char) 0); // hiện mật khẩu
    } else {
      this.txtPass.setEchoChar(defaultEchoChar); // trở lại dấu chấm ban đầu
    }
   }
   private void xuLyDangNhap() {

        String username = this.txtUser.getText();
        String password = new String(this.txtPass.getPassword());

        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        TaiKhoanDTO tk = taiKhoanBUS.Login(username, password);

        if(tk != null){
            String maNV = tk.getMaNhanVien();
            String maVaiTro = tk.getMaVaiTro();
            
            JOptionPane.showMessageDialog(this,"Đăng nhập thành công!");
            Utils.WindowUtil.showWindow(new DashboardGUI(maNV,maVaiTro));
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Sai tài khoản hoặc mật khẩu!");
        }
    }
   
}