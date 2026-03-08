package GUI.components;

import Custom.RoundedButton;
import Custom.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class ItemCard extends RoundedPanel {

    public ItemCard(String code,String name,String img){

        super(20);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,400));
        setBackground(Color.WHITE);

        JLabel lblName = new JLabel(name);
        lblName.setHorizontalAlignment(JLabel.CENTER);

        add(lblName,BorderLayout.CENTER);
    }
}