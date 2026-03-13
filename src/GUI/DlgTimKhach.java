//package GUI;
//
//import BUS.KhachHangBUS;
//import DTO.KhachHangDTO;
//import Custom.MyTable;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.Vector;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//
//public class DlgTimKhach extends JDialog {
//
//    private KhachHangBUS khachHangBUS = new KhachHangBUS();
//    public static KhachHangDTO khachHangTimDuoc = null;
//
//    private JTextField txtTuKhoa;
//    private JTable tblKhachHang;
//    private DefaultTableModel dtmKhachHang;
//    private JButton btnChon, btnThemKhach;
//
//    public DlgTimKhach() {
//        addControls();
//        addEvents();
//
//        this.setSize(500, 400);
//        this.setModal(true);
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//    }
//
//    private void addControls() {
//
//        Container con = getContentPane();
//        con.setLayout(new BorderLayout());
//
//        Font font = new Font("Tahoma", Font.PLAIN, 16);
//
//        /*
//        ================= SEARCH =================
//        */
//
//        JPanel pnTop = new JPanel();
//
//        JLabel lblTuKhoa = new JLabel("Từ khóa tìm");
//        txtTuKhoa = new JTextField(20);
//
//        lblTuKhoa.setFont(font);
//        txtTuKhoa.setFont(font);
//
//        pnTop.add(lblTuKhoa);
//        pnTop.add(txtTuKhoa);
//
//        con.add(pnTop, BorderLayout.NORTH);
//
//        /*
//        ================= TABLE =================
//        */
//
//        dtmKhachHang = new DefaultTableModel(
//                new Object[]{
//                        "Mã KH",
//                        "Tên khách hàng",
//                        "Số điện thoại",
//                        "Tổng chi tiêu"
//                }, 0) {
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//
//        };
//
//        tblKhachHang = new MyTable(dtmKhachHang);
//
//        JScrollPane scr = new JScrollPane(tblKhachHang);
//
//        con.add(scr, BorderLayout.CENTER);
//
//        /*
//        ================= BUTTON =================
//        */
//
//        JPanel pnButton = new JPanel();
//
//        btnChon = new JButton("Chọn");
//        btnThemKhach = new JButton("Thêm khách");
//
//        btnChon.setFont(font);
//        btnThemKhach.setFont(font);
//
//        btnChon.setPreferredSize(new Dimension(120, 40));
//        btnThemKhach.setPreferredSize(btnChon.getPreferredSize());
//
//        pnButton.add(btnChon);
//        pnButton.add(btnThemKhach);
//
//        con.add(pnButton, BorderLayout.SOUTH);
//
//        loadDataLenTable();
//    }
//
//    private void addEvents() {
//
//        txtTuKhoa.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                loadDataLenTable(txtTuKhoa.getText());
//
//            }
//
//        });
//
//        btnChon.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                xuLyChonKhachHang();
//
//            }
//
//        });
//
//        btnThemKhach.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                xuLyThemKhach();
//
//            }
//
//        });
//
//    }
//
//    /*
//    ================= CHỌN KHÁCH =================
//    */
//
//    private void xuLyChonKhachHang() {
//
//        int row = tblKhachHang.getSelectedRow();
//
//        if (row > -1) {
//
//            String ma = tblKhachHang.getValueAt(row, 0).toString();
//            String ten = tblKhachHang.getValueAt(row, 1).toString();
//            String sdt = tblKhachHang.getValueAt(row, 2).toString();
//
//            khachHangTimDuoc = new KhachHangDTO(ma, ten, sdt);
//
//        }
//
//        this.dispose();
//    }
//
//    /*
//    ================= THÊM KHÁCH =================
//    */
//
//    private void xuLyThemKhach() {
//
//        DlgThemKhachHang dlg = new DlgThemKhachHang();
//
//        dlg.setVisible(true);
//
//        if (dlg.checkThemKhach) {
//
//            khachHangBUS.docDanhSach();
//
//            loadDataLenTable();
//
//        }
//
//    }
//
//    /*
//    ================= LOAD TABLE =================
//    */
//
//    private void loadDataLenTable() {
//
//        dtmKhachHang.setRowCount(0);
//
//        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
//
//        if (dskh != null) {
//
//            for (KhachHang kh : dskh) {
//
//                Vector vec = new Vector();
//
//                vec.add(kh.getMaKhachHang());
//                vec.add(kh.getTenKhachHang());
//                vec.add(kh.getSoDienThoai());
//                vec.add(kh.getTongChiTieu());
//
//                dtmKhachHang.addRow(vec);
//
//            }
//
//        }
//
//    }
//
//    private void loadDataLenTable(String tuKhoa) {
//
//        dtmKhachHang.setRowCount(0);
//
//        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
//
//        for (KhachHang kh : dskh) {
//
//            Vector vec = new Vector();
//
//            vec.add(kh.getMaKhachHang());
//            vec.add(kh.getTenKhachHang());
//            vec.add(kh.getSoDienThoai());
//            vec.add(kh.getTongChiTieu());
//
//            dtmKhachHang.addRow(vec);
//
//        }
//
//    }
//
//}