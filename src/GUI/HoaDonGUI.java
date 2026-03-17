package GUI;

import BUS.GiamGiaBUS;
import BUS.SanPhamBUS;
import DTO.HoaDonDTO;
import DTO.CTHoaDonDTO;
import DTO.GiamGiaDTO;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class HoaDonGUI extends JFrame {

    private JTextArea txtHoaDon;
    private JButton btnIn, btnDong;

    public HoaDonGUI(HoaDonDTO hd, ArrayList<CTHoaDonDTO> dsCT) {
        setTitle("HÓA ĐƠN THANH TOÁN");
        setSize(420, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== TEXT =====
        txtHoaDon = new JTextArea();
        txtHoaDon.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtHoaDon.setEditable(false);
        txtHoaDon.setBackground(Color.WHITE);

        add(new JScrollPane(txtHoaDon), BorderLayout.CENTER);

        // ===== BUTTON =====
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnIn = new JButton("🖨 In");
        btnDong = new JButton("❌ Đóng");

        panelBtn.add(btnIn);
        panelBtn.add(btnDong);

        add(panelBtn, BorderLayout.SOUTH);

        // ===== DATA =====
        hienThiHoaDon(hd, dsCT);

        // ===== EVENT =====
        btnIn.addActionListener(e -> inHoaDon());
        btnDong.addActionListener(e -> dispose());
    }

    private void hienThiHoaDon(HoaDonDTO hd, ArrayList<CTHoaDonDTO> dsCT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        txtHoaDon.setText("");
        txtHoaDon.append("        🍕 PIZZA STORE 🍕\n");
        txtHoaDon.append("====================================\n");
        txtHoaDon.append("Mã HĐ: " + hd.getMaHD() + "\n");
        txtHoaDon.append("Ngày: " + hd.getNgayLap().format(formatter) + "\n");
        txtHoaDon.append("------------------------------------\n");

        txtHoaDon.append(String.format("%-15s %-5s %-10s\n", "SP", "SL", "Giá"));

        double tong = 0;
        SanPhamBUS spbus = new SanPhamBUS();
        for (CTHoaDonDTO ct : dsCT) {
            tong += ct.getSoLuong() * ct.getDonGia();
            
            txtHoaDon.append(String.format("%-15s %-5d %-10.0f\n",
                    spbus.getSanPhamById(ct.getMaSanPham()).getTenSP(),
                    ct.getSoLuong(),
                    ct.getDonGia()));
        }

        txtHoaDon.append("------------------------------------\n");
        GiamGiaBUS giamGiaBUS = new GiamGiaBUS();
        GiamGiaDTO gg = giamGiaBUS.getMaGiamGiaById(hd.getMaGiamGia());

        String tengiamgia = "Không có";
        int giam = 0;

        if (gg != null) {
            tengiamgia = gg.getTenGiamGia();
            giam = gg.getPhanTramGiam();
        }

        double tienGiam = tong * giam / 100;

        txtHoaDon.append("Tổng:        " + tong + "\n");
        txtHoaDon.append("Giảm:        " + tengiamgia + " (" + giam + "%)\n");
        txtHoaDon.append("Thanh toán:  " + (tong - tienGiam) + "\n");

        txtHoaDon.append("====================================\n");
        txtHoaDon.append("      Cảm ơn quý khách! ❤️\n");
    }

    private void inHoaDon() {
        try {
            boolean done = txtHoaDon.print();
            if (done) {
                JOptionPane.showMessageDialog(this, "In thành công!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi in!");
        }
    }

}

