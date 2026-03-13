package Utils;

import javax.swing.ImageIcon;

public class Constants {
    
    public static ImageIcon loadIcon(String name){
        return new ImageIcon(Constants.class.getResource(name));
    }
}