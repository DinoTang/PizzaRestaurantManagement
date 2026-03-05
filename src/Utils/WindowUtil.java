package Utils;

import javax.swing.*;
import java.awt.*;

public class WindowUtil {

    public static void showWindow(JFrame frame) {
        Image icon = Toolkit.getDefaultToolkit().getImage(
                WindowUtil.class.getResource("/images/logo.png")
        );

        frame.setIconImage(icon);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}