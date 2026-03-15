package GUI;

//import static Main.Main.changLNF;
import Custom.MyDialog;
import Custom.MyTable;
import Custom.TransparentPanel;
import BUS.DonViBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import DTO.DonViDTO;
import DTO.NguyenLieuDTO;
import DTO.NhaCungCapDTO;
import Utils.Constants;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PnQuanLyNguyenLieuGUI extends JPanel {

    NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    DonViBUS dvBUS = new DonViBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();

    final Color colorPanel = new Color(247,247,247);

    MyTable tblNguyenLieu;
    DefaultTableModel dtm;

    JTextField txtMa, txtTen, txtTim;
    JComboBox<String> cbDonVi, cbNCC;

    JButton btnThem, btnSua, btnXoa, btnTim, btnReset;

    public PnQuanLyNguyenLieuGUI() {
//        changLNF("Windows");
        addControls();
        addEvents();
        loadTable();
    }

    private void addControls() {

        Font font = new Font("Tahoma", Font.PLAIN, 20);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        // ===== TITLE =====
        JPanel pnTitle = new TransparentPanel();

        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ NGUYÊN LIỆU</h1></html>");

        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40,40));

        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);

        this.add(pnTitle);

        // ===== PANEL THÔNG TIN =====
        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        JLabel lblMa = new JLabel("Mã NL");
        JLabel lblTen = new JLabel("Tên NL");
        JLabel lblDonVi = new JLabel("Đơn vị");
        JLabel lblNCC = new JLabel("Nhà cung cấp");

        txtMa = new JTextField(15);
        txtMa.setEditable(false);
        txtTen = new JTextField(15);

        cbDonVi = new JComboBox<>();
        cbNCC = new JComboBox<>();

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblDonVi.setFont(font);
        lblNCC.setFont(font);

        txtMa.setFont(font);
        txtTen.setFont(font);

        cbDonVi.setFont(font);
        cbNCC.setFont(font);

        Dimension lblSize = new Dimension(150,30);

        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblDonVi.setPreferredSize(lblSize);
        lblNCC.setPreferredSize(lblSize);

        Dimension inputSize = new Dimension(350,30);

        txtMa.setPreferredSize(inputSize);
        txtTen.setPreferredSize(inputSize);
        cbDonVi.setPreferredSize(inputSize);
        cbNCC.setPreferredSize(inputSize);

        JPanel pnMa = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        pnMa.add(lblMa);
        pnMa.add(txtMa);

        JPanel pnTen = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        pnTen.add(lblTen);
        pnTen.add(txtTen);

        JPanel pnDV = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        pnDV.add(lblDonVi);
        pnDV.add(cbDonVi);

        JPanel pnNCC = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        pnNCC.add(lblNCC);
        pnNCC.add(cbNCC);

        pnText.add(pnMa);
        pnText.add(pnTen);
        pnText.add(pnDV);
        pnText.add(pnNCC);

        pnThongTin.add(pnText);

        this.add(pnThongTin);

        // ===== SEARCH =====
        JPanel pnSearch = new TransparentPanel();

        JLabel lblTim = new JLabel("Từ khoá tìm");
        lblTim.setFont(font);

        txtTim = new JTextField(20);
        txtTim.setFont(font);

        btnTim = new JButton(Constants.loadIcon("/images/Search-icon.png"));

        pnSearch.add(lblTim);
        pnSearch.add(txtTim);
        pnSearch.add(btnTim);

        this.add(pnSearch);

        // ===== BUTTON =====
        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm", Constants.loadIcon("/images/add-icon.png"));
        btnSua = new JButton("Sửa", Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoa = new JButton("Xoá", Constants.loadIcon("/images/delete-icon.png"));

        Font fontBtn = new Font("Tahoma", Font.PLAIN, 16);

        btnThem.setFont(fontBtn);
        btnSua.setFont(fontBtn);
        btnXoa.setFont(fontBtn);

        Dimension btnSize = new Dimension(120,40);

        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        this.add(pnButton);

        // ===== TABLE =====
        JPanel pnTable = new TransparentPanel(new BorderLayout());

        dtm = new DefaultTableModel(
                new String[]{"Mã NL","Tên NL","Tồn kho","Đơn vị","Nhà cung cấp"},0
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblNguyenLieu = new MyTable(dtm);
        tblNguyenLieu.setRowHeight(30);

        JScrollPane scr = new JScrollPane(tblNguyenLieu);

        pnTable.add(scr,BorderLayout.CENTER);

        this.add(pnTable);

        loadTable();
        loadComboBox();
    }

    private void loadTable() {

        dtm.setRowCount(0);

        List<NguyenLieuDTO> ds = nlBUS.getAllNguyenLieu();

        for (NguyenLieuDTO nl : ds) {

            dtm.addRow(new Object[]{
                    nl.getMaNguyenLieu(),
                    nl.getTenNguyenLieu(),
                    nl.getTonKho(),
                    dvBUS.getDonViById(nl.getMaDonVi()).getTenDonVi(),
                    nccBUS.getNCCById(nl.getMaNCC()).getTenNCC()
            });
        }
    }

    private void loadComboBox() {
        cbDonVi.addItem("Chọn đơn vị");
        for (DonViDTO dv : dvBUS.getAllDonVi()) {
            cbDonVi.addItem(dv.getTenDonVi());
        }
        cbNCC.addItem("Chọn nhà cung cấp");
        for (NhaCungCapDTO ncc : nccBUS.getAllNhaCungCap()) {
            cbNCC.addItem(ncc.getTenNCC());
        }
    }

    private void addEvents() {

        tblNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = tblNguyenLieu.getSelectedRow();

                txtMa.setText(dtm.getValueAt(row, 0) + "");
                txtTen.setText(dtm.getValueAt(row, 1) + "");

                cbDonVi.setSelectedItem(dtm.getValueAt(row, 3));
                cbNCC.setSelectedItem(dtm.getValueAt(row, 4));
            }
        });

        btnThem.addActionListener(e -> themNguyenLieu());
        btnSua.addActionListener(e -> suaNguyenLieu());
        btnXoa.addActionListener(e -> xoaNguyenLieu());
        btnTim.addActionListener(e -> timNguyenLieu());
        btnReset.addActionListener(e -> resetForm());
    }

    private void themNguyenLieu() {

        NguyenLieuDTO nl = new NguyenLieuDTO();
        nl.setMaNguyenLieu(nlBUS.getNextId());
        nl.setTenNguyenLieu(txtTen.getText());
        nl.setTonKho(0);
        
        nl.setMaDonVi(dvBUS.getDonViById(cbDonVi.getSelectedItem() + "").getMaDonVi());
        nl.setMaNCC(nccBUS.getNCCById(cbNCC.getSelectedItem() + "").getMaNCC());

        boolean flag = nlBUS.addNguyenLieu(nl);

        if(flag){
            new MyDialog("Thêm nguyên liệu thành công!", MyDialog.SUCCESS_DIALOG);
            loadTable();
            resetForm();
        }else{
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    private void suaNguyenLieu() {

        if(txtMa.getText().equals("")){
            new MyDialog("Vui lòng chọn nguyên liệu cần sửa!", MyDialog.WARNING_DIALOG);
            return;
        }

        NguyenLieuDTO nl = nlBUS.getNguyenLieuById(txtMa.getText());
        
        nl.setTenNguyenLieu(txtTen.getText());

        String tenDonVi = cbDonVi.getSelectedItem().toString();
        String maDonVi = dvBUS.getMaDonVi(tenDonVi);

        nl.setMaDonVi(maDonVi);
        nl.setMaNCC(nccBUS.getMaNCC(cbNCC.getSelectedItem() + ""));

        boolean flag = nlBUS.updateNguyenLieu(nl);

        if(flag){
            new MyDialog("Sửa nguyên liệu thành công!", MyDialog.SUCCESS_DIALOG);
            loadTable();
        }else{
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    private void xoaNguyenLieu() {

        if(txtMa.getText().equals("")){
            new MyDialog("Vui lòng chọn nguyên liệu cần xoá!", MyDialog.WARNING_DIALOG);
            return;
        }

        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);

        if(dlg.getAction() == MyDialog.OK_OPTION){

            String ma = txtMa.getText();

            boolean flag = nlBUS.deleteNguyenLieu(ma);

            if(flag){
                new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
                loadTable();
                resetForm();
            }else{
                new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
            }
        }
    }

    private void timNguyenLieu() {

        String key = txtTim.getText();

        dtm.setRowCount(0);

        for (NguyenLieuDTO nl : nlBUS.tim(key)) {

            dtm.addRow(new Object[]{
                    nl.getMaNguyenLieu(),
                    nl.getTenNguyenLieu(),
                    nl.getTonKho(),
                    dvBUS.getDonViById(nl.getMaDonVi()).getTenDonVi(),
                    nccBUS.getNCCById(nl.getMaNCC()).getTenNCC()
            });
        }
    }

    private void resetForm() {

        txtMa.setText("");
        txtTen.setText("");
        txtTim.setText("");

        cbDonVi.setSelectedIndex(0);
        cbNCC.setSelectedIndex(0);

        loadTable();
    }
}