package GUI;

import GUI.components.CartPanel;
import GUI.components.HeaderPanel;
import GUI.components.MenuFoodPanel;
import javax.swing.*;
import java.awt.*;

public class BanHangGUI extends JFrame {

    public BanHangGUI() {
        this.initComponents();
        this.addControls();
    }

    private void initComponents(){
        this.setTitle("Bán Hàng");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }
    private void addControls(){
        this.add(new HeaderPanel(),BorderLayout.NORTH);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(31,33,37));
        
        CartPanel cartPanel = new CartPanel();
        MenuFoodPanel menuPanel = new MenuFoodPanel(cartPanel);
        
        container.add(menuPanel,BorderLayout.CENTER);
        container.add(cartPanel,BorderLayout.EAST);

        this.add(container,BorderLayout.CENTER);
    }
}