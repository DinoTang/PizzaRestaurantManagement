/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {

    private int radius;
    private Color borderColor;

    public RoundedPanel(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
        setOpaque(false);
    }
    public RoundedPanel(int radius) {
        this(radius, null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // vẽ nền
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // vẽ viền nếu có
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        }

        super.paintComponent(g);
    }
}