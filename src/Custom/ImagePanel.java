/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author quock
 */
 public class ImagePanel extends JPanel {

        private Image image;
        private boolean leftBoolean;
        public ImagePanel(String path,  boolean leftBoolean) {
            java.net.URL url = getClass().getResource(path);

            if (url == null) {
                System.out.println("Không tìm thấy ảnh: " + path);
            } else {
                image = new ImageIcon(url).getImage();
            }

            this.leftBoolean = leftBoolean;
            
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imgWidth = image.getWidth(this);
            int imgHeight = image.getHeight(this);
            
            if(!leftBoolean) {
            

            double scale = Math.max(
                    (double) panelWidth / imgWidth,
                    (double) panelHeight / imgHeight
            );

            int newW = (int) (imgWidth * scale);
            int newH = (int) (imgHeight * scale);

            int x = (panelWidth - newW) / 2;
            int y = (panelHeight - newH) / 2;

            g2.drawImage(image, x, y, newW, newH, this);
            
            } else {
                double scale = Math.min(
                        (double) panelWidth / imgWidth,
                        (double) panelHeight / imgHeight
                );

                int newW = (int) (imgWidth * scale);
                int newH = (int) (imgHeight * scale);

                int x = (panelWidth - newW) / 2;
                int y = (panelHeight - newH) / 2;

                g2.drawImage(image, x, y, newW, newH, this);
            }
           
        }
    }