package GUI;

import DTO.HoaDonDTO;
import DTO.CTHoaDonDTO;
import BUS.HoaDonBUS;
import BUS.CTHoaDonBUS;
import BUS.SanPhamBUS;

import Custom.TransparentPanel;
import Custom.MyTable;
import BUS.NhanVienBUS;
import DTO.NhanVienDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import java.util.List;
public class PnQuanLyHoaDonGUI extends JPanel {

    HoaDonBUS hoaDonBUS = new HoaDonBUS();
    CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    SanPhamBUS spBUS = new SanPhamBUS();

    MyTable tblHoaDon;
    MyTable tblChiTietHoaDon;

    DefaultTableModel dtmHoaDon;
    DefaultTableModel dtmChiTiet;

    final Color colorPanel = new Color(247,247,247);

    public PnQuanLyHoaDonGUI() {
        addControls();
        addEvents();
        loadDataHoaDon();
    }

    private void addControls() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        // ===== TITLE =====
        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ HÓA ĐƠN</h1></html>");
        pnTitle.add(lblTitle);
        this.add(pnTitle);

        // ===== BẢNG HÓA ĐƠN =====
        JPanel pnTableHD = new TransparentPanel(new BorderLayout());

        dtmHoaDon = new DefaultTableModel(
                new Object[]{"Mã HD","Ngày lập","Nhân viên","Tổng tiền"},0
        ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tblHoaDon = new MyTable(dtmHoaDon);

        JScrollPane scrHD = new JScrollPane(tblHoaDon);
        pnTableHD.add(scrHD,BorderLayout.CENTER);

        this.add(pnTableHD);

        // ===== BẢNG CHI TIẾT HÓA ĐƠN =====
        JPanel pnTableCT = new TransparentPanel(new BorderLayout());

        JLabel lblCT = new JLabel("CHI TIẾT HÓA ĐƠN");
        lblCT.setFont(new Font("Tahoma",Font.BOLD,18));

        pnTableCT.add(lblCT,BorderLayout.NORTH);

        dtmChiTiet = new DefaultTableModel(
                new Object[]{"Mã SP","Tên SP","Số lượng","Đơn giá","Thành tiền"},0
        ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tblChiTietHoaDon = new MyTable(dtmChiTiet);
        tblChiTietHoaDon.setRowSelectionAllowed(false);
        tblChiTietHoaDon.setCellSelectionEnabled(false);
        tblChiTietHoaDon.setFocusable(false);

        JScrollPane scrCT = new JScrollPane(tblChiTietHoaDon);
        pnTableCT.add(scrCT,BorderLayout.CENTER);

        this.add(pnTableCT);
    }

    private void addEvents() {

        tblHoaDon.getSelectionModel().addListSelectionListener(e -> {

            int row = tblHoaDon.getSelectedRow();

            if(row >= 0){

                String maHD = tblHoaDon.getValueAt(row,0).toString();
                loadChiTietHoaDon(maHD);
            }
        });
    }
    NhanVienBUS nvBUS = new NhanVienBUS();
    
    private void loadDataHoaDon() {

        dtmHoaDon.setRowCount(0);

        List<HoaDonDTO> list = hoaDonBUS.getAllHoaDon();

        for(HoaDonDTO hd : list){
            
            Vector row = new Vector();

            row.add(hd.getMaHD());
            row.add(hd.getNgayLap());
            NhanVienDTO nv = nvBUS.getNhanVienById(hd.getMaNV());
            String tenNV = nv.getHoTen();
            row.add(tenNV);
            row.add(hd.getTongTien());

            dtmHoaDon.addRow(row);
        }
    }

    private void loadChiTietHoaDon(String maHD){

        dtmChiTiet.setRowCount(0);

        List<CTHoaDonDTO> list = ctHoaDonBUS.getCTHoaDonByMaHD(maHD);

        for(CTHoaDonDTO ct : list){

            String tenSP = spBUS.getSanPhamById(ct.getMaSanPham()).getTenSP();

            Vector row = new Vector();

            row.add(ct.getMaSanPham());
            row.add(tenSP);
            row.add(ct.getSoLuong());
            row.add(ct.getDonGia());
            row.add(ct.getThanhTien());

            dtmChiTiet.addRow(row);
        }
    }
}