package Custom;

import javax.swing.*;
import java.awt.*;

public class ImagePanel_1 extends JPanel {

    private Image img;

    public ImagePanel_1(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel_1(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
