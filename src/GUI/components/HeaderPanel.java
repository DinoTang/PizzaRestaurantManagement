package GUI.components;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JButton btnBanHang;
    private JButton btnHoaDon;

    private JLabel exitIcon;

    public HeaderPanel(){
        this.initComponents();
        this.addControls();
        this.addEvents();
    }

    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#1F2125")); 
        this.setPreferredSize(new Dimension(0,60));
    }

    private void addControls(){

        // panel bên trái (menu)
        this.leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,15));
        this.leftPanel.setOpaque(false);

        this.btnBanHang = new JButton("Bán hàng");
        this.btnHoaDon = new JButton("Lịch sử");

        styleTab(this.btnBanHang,true);
        styleTab(this.btnHoaDon,false);

        this.leftPanel.add(this.btnBanHang);
        this.leftPanel.add(this.btnHoaDon);

        // panel bên phải (exit)
        this.rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,15));
        this.rightPanel.setOpaque(false);

        this.exitIcon = new JLabel(
                new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                                .getImage().getScaledInstance(42,42,Image.SCALE_SMOOTH)
                )
        );

        this.rightPanel.add(this.exitIcon);

        this.add(this.leftPanel,BorderLayout.WEST);
        this.add(this.rightPanel,BorderLayout.EAST);
    }

    private void styleTab(JButton btn, boolean selected){

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI",Font.BOLD,16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130,35));

        if(selected){
            btn.setBackground(new Color(52,152,219));
            btn.setForeground(Color.WHITE);
        }else{
            btn.setBackground(new Color(200,200,200));
            btn.setForeground(Color.BLACK);
        }
    }

    private void addEvents(){

        this.btnBanHang.addActionListener(e -> {

            styleTab(this.btnBanHang,true);
            styleTab(this.btnHoaDon,false);

            // TODO: chuyển panel bán hàng
        });

        this.btnHoaDon.addActionListener(e -> {

            styleTab(this.btnBanHang,false);
            styleTab(this.btnHoaDon,true);

            // TODO: chuyển panel hóa đơn
        });

        // exit
        this.exitIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.exitIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Window w = SwingUtilities.getWindowAncestor(HeaderPanel.this);
                w.dispose();
            }
        });
    }
}