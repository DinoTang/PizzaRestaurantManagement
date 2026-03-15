package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import Custom.MyTable;
import Custom.TransparentPanel;
import Utils.Constants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
//import static Main.Main.changLNF;
import java.time.LocalDate;

public class PnQuanLyKhachHangGUI extends JPanel {

    public PnQuanLyKhachHangGUI() {
//        changLNF("Windows");
        addControls();
        addEvents();
    }

    private KhachHangBUS khachHangBUS = new KhachHangBUS();

    JButton btnReset;
    JTextField txtMa, txtTen, txtSDT, txtTongChiTieu, txtTukhoa, txtMaxChiTieu, txtMinChiTieu;

    JButton btnThem, btnSua, btnXoa, btnTim;

    MyTable tblKhachHang;
    DefaultTableModel dtmKhachHang;

    final Color colorPanel = new Color(247,247,247);

    private void addControls() {

        Font font = new Font("Tahoma", Font.PLAIN, 20);
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        /*
        ================= TITLE =================
        */

        JPanel pnTitle = new TransparentPanel();

        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ KHÁCH HÀNG</h1></html>");

        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40,40));

        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);

        this.add(pnTitle);

        /*
        ================= FORM =================
        */

        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        JLabel lblMa = new JLabel("Mã KH");
        JLabel lblTen = new JLabel("Tên KH");
        JLabel lblSDT = new JLabel("Số điện thoại");
        JLabel lblTong = new JLabel("Tổng chi tiêu");

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblSDT.setFont(font);
        lblTong.setFont(font);

        txtMa = new JTextField(15);
        txtMa.setEditable(false);

        txtTen = new JTextField(15);
        txtSDT = new JTextField(15);

        txtTongChiTieu = new JTextField(15);
        txtTongChiTieu.setEditable(false);

        txtMa.setFont(font);
        txtTen.setFont(font);
        txtSDT.setFont(font);
        txtTongChiTieu.setFont(font);

        JPanel p1 = new TransparentPanel();
        p1.add(lblMa);
        p1.add(txtMa);

        JPanel p2 = new TransparentPanel();
        p2.add(lblTen);
        p2.add(txtTen);

        JPanel p3 = new TransparentPanel();
        p3.add(lblSDT);
        p3.add(txtSDT);

        JPanel p4 = new TransparentPanel();
        p4.add(lblTong);
        p4.add(txtTongChiTieu);

        Dimension lblSize = lblTong.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblSDT.setPreferredSize(lblSize);
        lblTong.setPreferredSize(lblSize);

        pnText.add(p1);
        pnText.add(p2);
        pnText.add(p3);
        pnText.add(p4);

        pnThongTin.add(pnText);

        this.add(pnThongTin);

        /*
        ================= SEARCH =================
        */

        JPanel pnSearch = new TransparentPanel();

        JLabel lblSearch = new JLabel("Từ khóa tìm");
        lblSearch.setFont(font);

        txtTukhoa = new JTextField(20);
        txtTukhoa.setFont(font);

        btnTim = new JButton();
        btnTim.setIcon(Constants.loadIcon("/images/Search-icon.png"));
        btnTim.setPreferredSize(new Dimension(50,30));

        pnSearch.add(lblSearch);
        pnSearch.add(txtTukhoa);
        pnSearch.add(btnTim);

        this.add(pnSearch);

        /*
        ================= RANGE SEARCH =================
        */

        JPanel pnRange = new TransparentPanel();

        JLabel lblMin = new JLabel("Chi tiêu từ");
        JLabel lblMax = new JLabel("đến");

        txtMinChiTieu = new JTextField(6);
        txtMaxChiTieu = new JTextField(6);

        pnRange.add(lblMin);
        pnRange.add(txtMinChiTieu);
        pnRange.add(lblMax);
        pnRange.add(txtMaxChiTieu);

        this.add(pnRange);

        /*
        ================= BUTTON =================
        */

        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);

        btnThem.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSua.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoa.setIcon(Constants.loadIcon("/images/delete-icon.png"));

        Dimension btnSize = new Dimension(120,40);

        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        this.add(pnButton);

        /*
        ================= TABLE =================
        */

        JPanel pnTable = new TransparentPanel(new BorderLayout());

        dtmKhachHang = new DefaultTableModel(
                new Object[]{
                        "Mã KH",
                        "Tên khách hàng",
                        "Số điện thoại",
                        "Tổng chi tiêu"
                }, 0) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tblKhachHang = new MyTable(dtmKhachHang);

        tblKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(tblKhachHang);

        pnTable.add(scroll, BorderLayout.CENTER);

        this.add(pnTable);

        loadDataLenTable();
    }

    private void addEvents() {

        btnReset.addActionListener(e -> {
            loadDataLenTable();
            txtMa.setText("");
            txtTen.setText("");
            txtSDT.setText("");
            txtTongChiTieu.setText("");
        });

        tblKhachHang.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int row = tblKhachHang.getSelectedRow();

                txtMa.setText(tblKhachHang.getValueAt(row, 0) + "");
                txtTen.setText(tblKhachHang.getValueAt(row, 1) + "");
                txtSDT.setText(tblKhachHang.getValueAt(row, 2) + "");
                txtTongChiTieu.setText(tblKhachHang.getValueAt(row, 3) + "");

            }

        });

        txtTukhoa.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) { search(); }

            public void removeUpdate(DocumentEvent e) { search(); }

            public void changedUpdate(DocumentEvent e) { search(); }

        });

        btnTim.addActionListener(e -> searchRange());

        btnThem.addActionListener(e -> xuLyThemKhachHang());

        btnSua.addActionListener(e -> xuLySuaKhachHang());

        btnXoa.addActionListener(e -> xuLyXoaKhachHang());

    }

    private void loadDataLenTable() {

        

        List<KhachHangDTO> ds = khachHangBUS.getAllCustomers();;

        loadData(ds);
    }

    private void loadData(List<KhachHangDTO> ds) {

        dtmKhachHang.setRowCount(0);

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (KhachHangDTO kh : ds) {

            Vector vec = new Vector();

            vec.add(kh.getMaKhachHang());
            vec.add(kh.getTenKhachHang());
            vec.add(kh.getSoDienThoai());
            vec.add(dcf.format(kh.getTongChiTieu()));

            dtmKhachHang.addRow(vec);

        }

    }

    private void search() {

        List<KhachHangDTO> ds =khachHangBUS.searchCustomer(txtTukhoa.getText());
        loadData(ds);
    }

    private void searchRange() {

        List<KhachHangDTO> ds = khachHangBUS.searchCustomer(txtMinChiTieu.getText(), txtMaxChiTieu.getText());
        loadData(ds);
    }
    
    private void xuLyThemKhachHang() {

    if(txtTen.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Vui lòng nhập tên khách hàng!");
        return;
    }

    if(txtSDT.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Vui lòng nhập số điện thoại!");
        return;
    }

    KhachHangDTO kh = new KhachHangDTO();

    kh.setMaKhachHang(khachHangBUS.getNextId());
    kh.setTenKhachHang(txtTen.getText());
    kh.setSoDienThoai(txtSDT.getText());
    kh.setNgayTao(LocalDate.now());
    kh.setTongChiTieu(0);

    if(khachHangBUS.addCustomer(kh)){
        JOptionPane.showMessageDialog(this,"Thêm khách hàng thành công!");
        btnReset.doClick();
    }else{
        JOptionPane.showMessageDialog(this,"Thêm khách hàng thất bại!");
    }
    }   
    
    private void xuLySuaKhachHang(){

    if(txtMa.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Vui lòng chọn khách hàng cần sửa!");
        return;
    }

    KhachHangDTO kh = khachHangBUS.getKhachHangById(txtMa.getText());

    kh.setTenKhachHang(txtTen.getText());
    kh.setSoDienThoai(txtSDT.getText());

    if(khachHangBUS.updateCustomer(kh)){
        JOptionPane.showMessageDialog(this,"Cập nhật thành công!");
        btnReset.doClick();
    }else{
        JOptionPane.showMessageDialog(this,"Cập nhật thất bại!");
    }
    }
    
    private void xuLyXoaKhachHang(){

    String ma = txtMa.getText();

    if(ma.trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Vui lòng chọn khách hàng cần xoá!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn xoá khách hàng này?",
            "Xác nhận xoá",
            JOptionPane.YES_NO_OPTION
    );

    if(confirm == JOptionPane.YES_OPTION){

        if(khachHangBUS.deleteCustomer(ma)){
            JOptionPane.showMessageDialog(this,"Xoá thành công!");
            btnReset.doClick();
        }else{
            JOptionPane.showMessageDialog(this,"Xoá thất bại!");
        }

    }
    }
}