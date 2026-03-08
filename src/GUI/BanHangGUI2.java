package GUI;

import GUI.components.CartPanel;
import GUI.components.HeaderPanel;
import GUI.components.MenuFoodPanel;
import javax.swing.*;
import java.awt.*;

public class BanHangGUI2 extends JFrame {

    public BanHangGUI2() {
        initUI();
    }

    private void initUI(){

        setTitle("Bán Hàng");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new HeaderPanel(),BorderLayout.NORTH);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(31,33,37));

        container.add(new MenuFoodPanel(),BorderLayout.CENTER);
        container.add(new CartPanel(),BorderLayout.EAST);

        add(container,BorderLayout.CENTER);
    }
  
}