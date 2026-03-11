package GUI.components;

import javax.swing.*;
import java.awt.*;

public class HoaDonCard extends JPanel {

    public HoaDonCard(String maHD, String ngay, double tong){

        setPreferredSize(new Dimension(230,70)); // ngắn hơn
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8,10,8,10));

        // hover cursor
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel lblMa = new JLabel("Hóa đơn: " + maHD);
        JLabel lblNgay = new JLabel("Ngày: " + ngay);
        JLabel lblTong = new JLabel("Tổng: " + String.format("%,.0f đ", tong));

        lblMa.setFont(new Font("Segoe UI",Font.BOLD,14));
        lblTong.setForeground(new Color(255,120,0));

        infoPanel.add(lblMa);
        infoPanel.add(lblNgay);
        infoPanel.add(lblTong);

        add(infoPanel,BorderLayout.CENTER);
    }
}