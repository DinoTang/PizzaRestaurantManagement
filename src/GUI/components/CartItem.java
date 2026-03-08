package GUI.components;

import Custom.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class CartItem extends RoundedPanel {

    public CartItem(String name,String price,String img){

        super(15);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(450,100));
        setBackground(Color.decode("#F9F9F9"));

        JLabel lblName = new JLabel(name);
        JLabel lblPrice = new JLabel(price);

        add(lblName,BorderLayout.CENTER);
        add(lblPrice,BorderLayout.EAST);
    }
}