package GUI.components;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(){

        setLayout(new BorderLayout());
        setBackground(new Color(31,33,37));
        setPreferredSize(new Dimension(0,100));

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,15));
        exitPanel.setOpaque(false);

        JLabel exitIcon = new JLabel(
                new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                                .getImage().getScaledInstance(64,64,Image.SCALE_SMOOTH)
                )
        );

        exitIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        exitIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Window w = SwingUtilities.getWindowAncestor(HeaderPanel.this);
                w.dispose();
            }
        });

        exitPanel.add(exitIcon);
        add(exitPanel,BorderLayout.WEST);
    }
}