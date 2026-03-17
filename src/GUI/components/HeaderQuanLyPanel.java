package GUI.components;

import GUI.DashboardGUI;
import Utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class HeaderQuanLyPanel extends JPanel {

    private String maNV;
    private String maQuyen;

    private JLabel btnBack;

    public HeaderQuanLyPanel(String maNV, String maQuyen) {
        this.maNV = maNV;
        this.maQuyen = maQuyen;

        initComponents();
        addControls();
        addEvents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(43, 45, 49));
        setPreferredSize(new Dimension(0, 60));
    }

    private void addControls() {

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 8));
        left.setOpaque(false);

        btnBack = new JLabel(
                new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                                .getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH)
                )
        );

        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        left.add(btnBack);

        add(left, BorderLayout.EAST);
    }

    private void addEvents() {

        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                Window w = SwingUtilities.getWindowAncestor(HeaderQuanLyPanel.this);
                w.dispose();

                WindowUtil.showWindow(new DashboardGUI(maNV, maQuyen));
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnBack.setIcon(new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                                .getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH)
                ));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnBack.setIcon(new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                                .getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH)
                ));
            }
        });
    }
}