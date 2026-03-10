package GUI;

import DTO.NhanVienDTO;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class NhanVienProfileDlg extends JDialog {

    public NhanVienProfileDlg(Frame parent, NhanVienDTO nv, String tenQuyen) {

        super(parent, "Thông tin nhân viên", true);

        setSize(420,520);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(255,153,0));
        header.setPreferredSize(new Dimension(420,190));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel avatar = new JLabel(new ImageIcon(
                new ImageIcon(getClass().getResource("/images/avatarAccount.png"))
                        .getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH)
        ));

        JLabel name = new JLabel(nv.getHoTen());
        name.setFont(new Font("Segoe UI",Font.BOLD,24));
        name.setForeground(Color.WHITE);

        // 👇 hiển thị đúng vai trò
        JLabel role = new JLabel(tenQuyen);
        role.setFont(new Font("Segoe UI",Font.PLAIN,17));
        role.setForeground(new Color(255,240,220));

        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        role.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalStrut(20));
        header.add(avatar);
        header.add(Box.createVerticalStrut(12));
        header.add(name);
        header.add(Box.createVerticalStrut(5));
        header.add(role);

        add(header, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5,1,15,15));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(25,40,20,40));
        infoPanel.setBackground(Color.WHITE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        infoPanel.add(createItem("Mã nhân viên", nv.getMaNhanVien()));
        infoPanel.add(createItem("Số điện thoại", nv.getSoDienThoai()));
        infoPanel.add(createItem("Email", nv.getEmail()));
        infoPanel.add(createItem("Lương", String.format("%,.0f VNĐ", nv.getLuong())));
        infoPanel.add(createItem("Ngày tạo", nv.getNgayTao().format(formatter)));

        add(infoPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);

        JButton btnClose = new JButton("Đóng");
        btnClose.setBackground(new Color(255,153,0));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI",Font.BOLD,14));
        btnClose.setPreferredSize(new Dimension(120,38));
        btnClose.addActionListener(e -> dispose());

        bottom.add(btnClose);

        add(bottom, BorderLayout.SOUTH);
    }

    private JLabel createItem(String title, String value){
        JLabel label = new JLabel("<html><b>"+title+":</b> "+value+"</html>");
        label.setFont(new Font("Segoe UI",Font.PLAIN,16));
        return label;
    }
}