package Custom;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class TopRoundedImageLabel extends JLabel {

    private int radius;

    public TopRoundedImageLabel(ImageIcon icon, int radius) {
        super(icon);
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int r = radius;

        Path2D path = new Path2D.Double();
        path.moveTo(0, h);              // góc dưới trái
        path.lineTo(0, r);
        path.quadTo(0, 0, r, 0);        // bo góc trên trái
        path.lineTo(w - r, 0);
        path.quadTo(w, 0, w, r);        // bo góc trên phải
        path.lineTo(w, h);              // góc dưới phải
        path.closePath();

        g2.setClip(path);

        super.paintComponent(g2);
        g2.dispose();
    }
}