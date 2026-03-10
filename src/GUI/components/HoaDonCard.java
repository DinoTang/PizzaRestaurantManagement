package GUI.components;

import javax.swing.*;
import java.awt.*;

public class HoaDonCard extends JPanel {

    private JButton btnHuy;
    private JButton btnXacNhan;
    private JLabel lblTrangThai;

    public HoaDonCard(String maHD,String ngay,double tong){

        setPreferredSize(new Dimension(230,110));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // ===== THÔNG TIN =====
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel lblMa = new JLabel("Hóa đơn: "+maHD);
        JLabel lblNgay = new JLabel("Ngày: "+ngay);
        JLabel lblTong = new JLabel("Tổng: "+String.format("%,.0f đ",tong));

        lblMa.setFont(new Font("Segoe UI",Font.BOLD,14));
        lblTong.setForeground(new Color(255,120,0));

        infoPanel.add(lblMa);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(lblNgay);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(lblTong);

        add(infoPanel,BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new GridLayout(1,2,5,0));
        buttonPanel.setOpaque(false);

        btnHuy = new JButton("Hủy");
        btnXacNhan = new JButton("Xác nhận");

        btnHuy.setBackground(new Color(231,76,60));
        btnHuy.setForeground(Color.WHITE);

        btnXacNhan.setBackground(new Color(46,204,113));
        btnXacNhan.setForeground(Color.WHITE);

        buttonPanel.add(btnHuy);
        buttonPanel.add(btnXacNhan);

        add(buttonPanel,BorderLayout.SOUTH);

        // ===== STATUS LABEL =====
        lblTrangThai = new JLabel("",SwingConstants.CENTER);
        lblTrangThai.setFont(new Font("Segoe UI",Font.BOLD,13));

        // ===== EVENTS =====
        btnXacNhan.addActionListener(e -> {

            remove(buttonPanel);

            lblTrangThai.setText("Đã hoàn thành");
            lblTrangThai.setForeground(new Color(0,120,0));

            add(lblTrangThai,BorderLayout.SOUTH);

            setBackground(new Color(220,255,220));

            revalidate();
            repaint();
        });

        btnHuy.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn hủy hóa đơn?",
                    "Xác nhận hủy",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION){

                remove(buttonPanel);

                lblTrangThai.setText("Đã hủy");
                lblTrangThai.setForeground(Color.RED);

                add(lblTrangThai,BorderLayout.SOUTH);

                setBackground(new Color(255,220,220));

                revalidate();
                repaint();
            }
        });
    }
}