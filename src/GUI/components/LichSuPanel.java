package GUI.components;

import Custom.WrapLayout;
import DTO.HoaDonDTO;
import DTO.CTHoaDonDTO;
import BUS.CTHoaDonBUS;
import BUS.GiamGiaBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;

public class LichSuPanel extends JPanel {

    private JPanel cardsPanel;
    private JTable tableChiTiet;

    private JLabel lblMaHD;
    private JLabel lblGiamGia;
    private JLabel lblMaKH;
    private JLabel lblNhanVien;
    private JLabel lblNgayLap;
    private JLabel lblTongTien;

    private JTextField txtGiaTu;
    private JTextField txtGiaDen;

    private JDateChooser dateTu;
    private JDateChooser dateDen;
    private HoaDonCard selectedCard = null;
    private HoaDonBUS hoaDonBUS = new HoaDonBUS();
    private CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    private KhachHangBUS khachHangBUS = new KhachHangBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private GiamGiaBUS giamGiaBUS = new GiamGiaBUS();
    public LichSuPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(31,33,37));

        add(createFilterPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        
        loadData();
        btnSearch.addActionListener(e -> filterHoaDon());
    }
    private void loadData(){

        List<HoaDonDTO> list = hoaDonBUS.getAllHoaDon();

        loadHoaDon(list);
    }
    // =====================================================
    // FILTER PANEL
    // =====================================================
    private JPanel createFilterPanel(){

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(31,33,37));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,10));
        panel.setBackground(new Color(31,33,37));

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255,140,0),2),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        panel.add(createPriceRow());
        panel.add(createDateRow());

        btnSearch = new JButton("Tìm");
        btnSearch.setBackground(new Color(255,140,0));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);

        panel.add(btnSearch);

        wrapper.add(panel,BorderLayout.WEST);

        return wrapper;
    }

    private JPanel createPriceRow(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        panel.setBackground(new Color(31,33,37));

        txtGiaTu = new JTextField(8);
        txtGiaDen = new JTextField(8);

        panel.add(createLabel("Giá từ"));
        panel.add(txtGiaTu);
        panel.add(createLabel("đến"));   // sửa
        panel.add(txtGiaDen);

        return panel;
    }

    private JPanel createDateRow(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        panel.setBackground(new Color(31,33,37));

        dateTu = new JDateChooser();
        dateDen = new JDateChooser();

        dateTu.setPreferredSize(new Dimension(120,25));
        dateDen.setPreferredSize(new Dimension(120,25));

        panel.add(createLabel("Ngày từ"));
        panel.add(dateTu);
        panel.add(createLabel("đến"));  // sửa
        panel.add(dateDen);

        return panel;
    }

    // =====================================================
    // CONTENT
    // =====================================================
    private JPanel createContentPanel(){

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(31,33,37));

        container.add(createListPanel(),BorderLayout.CENTER);
        container.add(createDetailPanel(),BorderLayout.EAST);

        return container;
    }

    // =====================================================
    // LIST PANEL
    // =====================================================
    private JPanel createListPanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(31,33,37));

        cardsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT,20,20));
        cardsPanel.setBackground(new Color(31,33,37));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroll,BorderLayout.CENTER);
        
        return panel;
    }

    // =====================================================
    // DETAIL PANEL
    // =====================================================
    private JPanel createDetailPanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(420,0));
        panel.setBackground(new Color(45,47,52));

        panel.add(createThongTinPanel(),BorderLayout.NORTH);
        panel.add(createTablePanel(),BorderLayout.CENTER);
        panel.add(createTongPanel(),BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createThongTinPanel(){

        JPanel panel = new JPanel(new GridLayout(5,2,10,10));
        panel.setBackground(new Color(45,47,52));

        javax.swing.border.TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Thông tin hóa đơn"
        );

        border.setTitleColor(Color.WHITE);

        panel.setBorder(border);

        panel.add(createLabel("Hóa Đơn"));
        lblMaHD = createValue("");
        
        panel.add(lblMaHD);
        
        
        panel.add(createLabel("Khách hàng"));
        lblMaKH = createValue("");

        panel.add(lblMaKH);

        panel.add(createLabel("Nhân viên lập"));
        lblNhanVien = createValue("");

        panel.add(lblNhanVien);

        panel.add(createLabel("Ngày lập"));
        lblNgayLap = createValue("");

        panel.add(lblNgayLap);
        
        
        panel.add(createLabel("Giảm giá"));
        lblGiamGia = createValue("");

        panel.add(lblGiamGia);
        
        return panel;
    }

    // =====================================================
    // TABLE
    // =====================================================
    private JPanel createTablePanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(45,47,52));

        String[] cols = {
                "Mã SP","Tên sản phẩm","Số lượng","Đơn giá","Thành tiền"
        };

        tableChiTiet = new JTable(new DefaultTableModel(cols,0));
        tableChiTiet.setRowHeight(25);
        tableChiTiet.setEnabled(false);
        panel.add(new JScrollPane(tableChiTiet),BorderLayout.CENTER);

        return panel;
    }

    // =====================================================
    // TOTAL
    // =====================================================
    private JPanel createTongPanel(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(new Color(45,47,52));

        panel.add(createLabel("Tổng tiền"));
        
        lblTongTien = new JLabel("0 đ");
        lblTongTien.setForeground(Color.ORANGE);
        lblTongTien.setFont(new Font("Segoe UI",Font.BOLD,16));

        panel.add(lblTongTien);

        return panel;
    }

    // =====================================================
    // LOAD DATA
    // =====================================================
    public void loadHoaDon(List<HoaDonDTO> list){

        cardsPanel.removeAll();

        for(HoaDonDTO hd : list){
            addCard(hd);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void addCard(HoaDonDTO hd){

        HoaDonCard card = new HoaDonCard(
                hd.getMaHD(),
                hd.getNgayLap().toString(),
                hd.getTongTien()
        );

        card.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){

                if(selectedCard != null){
                    selectedCard.setSelected(false);
                }

                selectedCard = card;
                card.setSelected(true);

                setHoaDonDetail(hd);
            }
        });

        cardsPanel.add(card);
    }

    private void setHoaDonDetail(HoaDonDTO hd){


        lblMaHD.setText(hd.getMaHD());

        if(hd.getMaKH() != null){
            var kh = khachHangBUS.getKhachHangById(hd.getMaKH());
            lblMaKH.setText(kh != null ? kh.getTenKhachHang() : "Khách lẻ");
        }else{
            lblMaKH.setText("Khách lẻ");
        }

        var nv = nhanVienBUS.getNhanVienById(hd.getMaNV());
        lblNhanVien.setText(nv != null ? nv.getHoTen() : "");

        lblNgayLap.setText(hd.getNgayLap().toString());
        lblTongTien.setText(hd.getTongTien() + " đ");
        if (hd.getMaGiamGia() == null) {
            lblGiamGia.setText("Không");
        } else {
            var gg = giamGiaBUS.getMaGiamGiaById(hd.getMaGiamGia());

            lblGiamGia.setText(
                gg.getTenGiamGia() + " (" + gg.getPhanTramGiam() + "%)"
            );
        }
        loadChiTietHoaDon(hd.getMaHD());
    }

    private void loadChiTietHoaDon(String maHD){

        DefaultTableModel model = (DefaultTableModel) tableChiTiet.getModel();
        model.setRowCount(0);

        List<CTHoaDonDTO> list = ctHoaDonBUS.getCTHoaDonByMaHD(maHD);
        for(CTHoaDonDTO ct : list){

            model.addRow(new Object[]{
                    ct.getMaSanPham(),
                    sanPhamBUS.getSanPhamById(ct.getMaSanPham()).getTenSP(),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getThanhTien()
            });
        }
    }

    // =====================================================
    // UI HELPERS
    // =====================================================
    private JLabel createLabel(String text){

        JLabel lbl = new JLabel(text+":");
        lbl.setForeground(Color.WHITE);

        return lbl;
    }

    private JLabel createValue(String text){

        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.ORANGE);

        return lbl;
    }
    public void refreshData(){
        List<HoaDonDTO> list = hoaDonBUS.getAllHoaDon();
        loadHoaDon(list);
    }
    private JButton btnSearch;


    private void filterHoaDon(){
        List<HoaDonDTO> list = hoaDonBUS.getAllHoaDon();

        Double giaTu = txtGiaTu.getText().isEmpty() ? null : Double.parseDouble(txtGiaTu.getText());
        Double giaDen = txtGiaDen.getText().isEmpty() ? null : Double.parseDouble(txtGiaDen.getText());
        
        List<HoaDonDTO> result = new java.util.ArrayList<>();
        java.util.Date dTu = dateTu.getDate();
        java.util.Date dDen = dateDen.getDate();

        LocalDate ldTu = dTu == null ? null : dTu.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate ldDen = dDen == null ? null : dDen.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        for(HoaDonDTO hd : list){

            if(giaTu != null && hd.getTongTien() < giaTu) continue;
            if(giaDen != null && hd.getTongTien() > giaDen) continue;

            if(ldTu != null && hd.getNgayLap().isBefore(ldTu)) continue;
            if(ldDen != null && hd.getNgayLap().isAfter(ldDen)) continue;

            result.add(hd);
        }

        loadHoaDon(result);
    }
}