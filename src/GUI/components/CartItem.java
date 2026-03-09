package GUI.components;

import BUS.CartBUS;
import Custom.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class CartItem extends RoundedPanel {
    private String maSP;
    private String size;
    private CartPanel cartPanel;
    public CartItem(String maSP, String tenSP, String size, double gia, String img, int soLuong, CartPanel cartPanel) {

        super(15);
        
        this.maSP = maSP;
        this.size = size;
        this.cartPanel = cartPanel;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(450,100));
        setBackground(Color.decode("#F9F9F9"));
        setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        
        JPanel info = new JPanel(new GridLayout(4,1));
        info.setOpaque(false);
        
        JLabel lblName = new JLabel(tenSP);
        lblName.setFont(new Font("Segoe UI",Font.BOLD,14));
        
        JLabel lblSize = new JLabel("");
        if(!size.equals("")) lblSize.setText("Size: " + size);
        // panel chỉnh sửa
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        actionPanel.setOpaque(false);

        JLabel lblEdit = new JLabel("Điều chỉnh");
        lblEdit.setForeground(new Color(0,120,215));

        JLabel lblDelete = new JLabel("Xóa");
        lblDelete.setForeground(Color.RED);

        actionPanel.add(lblEdit);
        actionPanel.add(lblDelete);

        // quantity
        JPanel quantity = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        quantity.setOpaque(false);

        JButton btnMinus = new JButton("-");
        btnMinus.setPreferredSize(new Dimension(35,25));
        btnMinus.setMargin(new Insets(0,0,0,0));
        btnMinus.setFocusPainted(false);

        JButton btnPlus = new JButton("+");
        btnPlus.setPreferredSize(new Dimension(35,25));
        btnPlus.setMargin(new Insets(0,0,0,0));
        btnPlus.setFocusPainted(false);

        JLabel lblQty = new JLabel(String.valueOf(soLuong));
        lblQty.setPreferredSize(new Dimension(20,25));
        lblQty.setHorizontalAlignment(SwingConstants.CENTER);

        quantity.add(btnMinus);
        quantity.add(lblQty);
        quantity.add(btnPlus);

        info.add(lblName);
        info.add(lblSize);
        info.add(actionPanel);
        info.add(quantity);

        JLabel lblPrice = new JLabel(String.format("%,.0fđ", gia));
        lblPrice.setFont(new Font("Segoe UI",Font.BOLD,14));

        ImageIcon icon = new ImageIcon(
                new ImageIcon(getClass().getResource(img))
                        .getImage().getScaledInstance(80,60,Image.SCALE_SMOOTH)
        );

        JLabel imgLabel = new JLabel(icon);

        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(false);
        right.add(lblPrice,BorderLayout.NORTH);
        right.add(imgLabel,BorderLayout.CENTER);

        add(info,BorderLayout.CENTER);
        add(right,BorderLayout.EAST);

        // sự kiện xóa
        lblDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                lblDelete.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {

                    CartBUS.removeItem(maSP, size);

                    cartPanel.reloadCart();
                }

            });
            }
        });
        
        btnPlus.addActionListener(e -> {

            CartBUS.addQuantity(maSP, size);

            cartPanel.reloadCart();

        });
        btnMinus.addActionListener(e -> {

            CartBUS.deductQuantity(maSP, size);

            cartPanel.reloadCart();

        });
    }

}