package GUI.components;

import Custom.RoundedPanel;
import Custom.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class MenuFoodPanel extends RoundedPanel {

    public MenuFoodPanel(){

        super(40,Color.decode("#3A3A3A"));

        setLayout(new BorderLayout());
        setBackground(Color.decode("#1F2125"));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screen.width * 2.0/3);
        setPreferredSize(new Dimension(width,0));

        createMenuHeader();
        createMenuList();
    }

    private void createMenuHeader(){

        JPanel menuHeader = new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
        menuHeader.setOpaque(false);
        menuHeader.setPreferredSize(new Dimension(0,100));

        add(menuHeader,BorderLayout.NORTH);

        String[] loai = {"Pizza","Mì Ý","Đồ uống"};

        JComboBox<String> cboLoai = new JComboBox<>(loai);
        cboLoai.setPreferredSize(new Dimension(160,28));

        menuHeader.add(cboLoai);
    }

    private void createMenuList(){

        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new WrapLayout(FlowLayout.LEFT,20,10));
        menuContainer.setBackground(Color.decode("#383C42"));

        menuContainer.add(new ItemCard(
                "P001",
                "Pizza Rau Củ Thập Cẩm",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));

        JScrollPane scroll = new JScrollPane(menuContainer);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll,BorderLayout.CENTER);
    }
}