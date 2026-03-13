package GUI;

import GUI.components.*;
import javax.swing.*;
import java.awt.*;

public class BanHangGUI extends JFrame {

    private String maNV;
    private String maQuyen;

    private JPanel mainContainer;
    private CardLayout cardLayout;
    private LichSuPanel lichSuPanel;
    public BanHangGUI(String maNV, String maQuyen) {

        this.maNV = maNV;
        this.maQuyen = maQuyen;
        this.lichSuPanel = new LichSuPanel();
        initComponents();
        addControls();
    }

    private void initComponents(){
        setTitle("Bán Hàng");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void addControls(){

        // header
        add(new HeaderPanel(this, maNV, maQuyen), BorderLayout.NORTH);

        // container chính
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        // ===== PANEL BÁN HÀNG =====
        CartPanel cartPanel = new CartPanel(maNV);
        MenuFoodPanel menuPanel = new MenuFoodPanel(cartPanel);

        JPanel banHangPanel = new JPanel(new BorderLayout());
        banHangPanel.setBackground(new Color(31,33,37));
        banHangPanel.add(menuPanel, BorderLayout.CENTER);
        banHangPanel.add(cartPanel, BorderLayout.EAST);

        // ===== PANEL LỊCH SỬ =====
        // thêm vào card
        mainContainer.add(banHangPanel, "BANHANG");
        mainContainer.add(lichSuPanel, "LICHSU");

        add(mainContainer, BorderLayout.CENTER);
    }

    // hàm chuyển panel
    public void showBanHang(){
        cardLayout.show(mainContainer, "BANHANG");
    }

    public void showLichSu(){
        lichSuPanel.refreshData();
        cardLayout.show(mainContainer, "LICHSU");
    }
}