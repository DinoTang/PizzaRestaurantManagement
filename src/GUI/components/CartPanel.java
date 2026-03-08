package GUI.components;

import Custom.RoundedButton;
import Custom.RoundedPanel;
import Custom.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends RoundedPanel {

    public CartPanel(){

        super(40);

        setLayout(new BorderLayout());
        setBackground(Color.decode("#2A2D31"));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screen.width * 1.0/3);
        setPreferredSize(new Dimension(width,0));

        createHeader();
        createCartList();
        createBottom();
    }

    private JPanel cartList;

    private void createHeader(){

        JLabel title = new JLabel("Thông tin hóa đơn:");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        title.setForeground(Color.WHITE);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        header.setOpaque(false);
        header.add(title);

        add(header,BorderLayout.NORTH);
    }

    private void createCartList(){

        cartList = new JPanel(new WrapLayout(FlowLayout.CENTER,10,10));
        cartList.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(cartList);
        scroll.setBorder(null);

        add(scroll,BorderLayout.CENTER);

        cartList.add(new CartItem(
                "Pizza Phô Mai",
                "155,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
    }

    private void createBottom(){

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));

        JLabel total = new JLabel("Tổng");
        total.setFont(new Font("Segoe UI",Font.BOLD,18));

        JLabel price = new JLabel("380,000đ");
        price.setFont(new Font("Segoe UI",Font.BOLD,20));

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(Color.WHITE);

        totalPanel.add(total,BorderLayout.WEST);
        totalPanel.add(price,BorderLayout.EAST);

        RoundedButton pay = new RoundedButton(10);
        pay.setText("THANH TOÁN");
        pay.setBackground(Color.decode("#FF8C00"));
        pay.setForeground(Color.WHITE);
        pay.setPreferredSize(new Dimension(500,50));

        bottom.add(totalPanel,BorderLayout.NORTH);
        bottom.add(pay,BorderLayout.CENTER);

        add(bottom,BorderLayout.SOUTH);
    }
}