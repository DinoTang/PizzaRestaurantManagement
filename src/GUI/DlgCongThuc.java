package GUI;

import BUS.CongThucBUS;
import BUS.NguyenLieuBUS;
import BUS.SanPhamBUS;

import DTO.CongThucDTO;
import DTO.NguyenLieuDTO;
import DTO.SanPhamDTO;

import Custom.MyDialog;
import Custom.MyTable;
import Custom.TransparentPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class DlgCongThuc extends JDialog {

    String maSP;

    CongThucBUS ctBUS = new CongThucBUS();
    NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    SanPhamBUS spBUS = new SanPhamBUS();

    JLabel lblTenSP;

    JComboBox<String> cmbNguyenLieu;
    JTextField txtSoLuong;

    JButton btnThem, btnSua, btnXoa;

    MyTable tblCongThuc;
    DefaultTableModel dtm;

    public DlgCongThuc(String maSP){

        this.maSP = maSP;

        setTitle("Công thức sản phẩm");
        setSize(650,500);
        setLocationRelativeTo(null);
        setModal(true);

        addControls();
        addEvents();

        loadTenSanPham();
        loadNguyenLieu();
        loadCongThuc(maSP);
    }

    // ================= GUI =================

    private void addControls(){

        setLayout(new BorderLayout());

        // ===== TOP =====

        JPanel pnTop = new TransparentPanel();

        lblTenSP = new JLabel();
        lblTenSP.setFont(new Font("Tahoma",Font.BOLD,20));

        pnTop.add(lblTenSP);

        add(pnTop,BorderLayout.NORTH);

        // ===== CENTER =====

        JPanel pnCenter = new JPanel(new BorderLayout());

        // ===== INPUT =====

        JPanel pnInput = new TransparentPanel();

        cmbNguyenLieu = new JComboBox<>();
        txtSoLuong = new JTextField(10);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        pnInput.add(new JLabel("Nguyên liệu"));
        pnInput.add(cmbNguyenLieu);

        pnInput.add(new JLabel("Số lượng"));
        pnInput.add(txtSoLuong);

        pnInput.add(btnThem);
        pnInput.add(btnSua);
        pnInput.add(btnXoa);

        pnCenter.add(pnInput,BorderLayout.NORTH);

        // ===== TABLE =====

        dtm = new DefaultTableModel(
                new Object[]{"Tên nguyên liệu","Số lượng","Đơn vị"},0
        ){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        tblCongThuc = new MyTable(dtm);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        tblCongThuc.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        tblCongThuc.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblCongThuc.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scr = new JScrollPane(tblCongThuc);

        pnCenter.add(scr,BorderLayout.CENTER);

        add(pnCenter,BorderLayout.CENTER);

    }

    // ================= EVENTS =================

    private void addEvents(){

        tblCongThuc.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e){

                int row = tblCongThuc.getSelectedRow();

                if(row < 0) return;

                txtSoLuong.setText(tblCongThuc.getValueAt(row,1).toString());

            }

        });

        btnThem.addActionListener(e -> themNguyenLieu());
        btnSua.addActionListener(e -> suaNguyenLieu());
        btnXoa.addActionListener(e -> xoaNguyenLieu());

    }

    // ================= LOAD =================

    private void loadTenSanPham(){

        SanPhamDTO sp = spBUS.getSanPhamById(maSP);

        lblTenSP.setText("Sản phẩm: " + sp.getTenSP());

    }

    private void loadNguyenLieu(){

        cmbNguyenLieu.removeAllItems();

        List<NguyenLieuDTO> list = nlBUS.getAllNguyenLieu();

        for(NguyenLieuDTO nl : list){

            cmbNguyenLieu.addItem(
                    nl.getMaNguyenLieu()+" - "+nl.getTenNguyenLieu()
            );

        }

    }

    private void loadCongThuc(String maSP){

        dtm.setRowCount(0);

        List<CongThucDTO> list = ctBUS.getCongThucTheoSP(maSP);

        for(CongThucDTO ct : list){

            dtm.addRow(new Object[]{
                    ct.getTenNguyenLieu(),
                    String.format("%.2f", ct.getSoLuong()),
                    ct.getTenDonVi()
            });

        }

    }

    // ================= THÊM =================

    private void themNguyenLieu(){

        try{

            if(cmbNguyenLieu.getSelectedItem()==null){
                new MyDialog("Chưa chọn nguyên liệu",MyDialog.ERROR_DIALOG);
                return;
            }

            String item = cmbNguyenLieu.getSelectedItem().toString();
            String maNL = item.split(" - ")[0];

            double soLuong = Double.parseDouble(txtSoLuong.getText());

            CongThucDTO ct = new CongThucDTO();

            ct.setMaSP(maSP);
            ct.setMaNguyenLieu(maNL);
            ct.setSoLuong(soLuong);

            boolean flag = ctBUS.addCongThuc(ct);

            if(flag){

                new MyDialog("Thêm thành công",MyDialog.SUCCESS_DIALOG);
                loadCongThuc(maSP);

            }else{

                new MyDialog("Thêm thất bại",MyDialog.ERROR_DIALOG);

            }

        }catch(Exception e){

            new MyDialog("Dữ liệu không hợp lệ",MyDialog.ERROR_DIALOG);

        }

    }

    // ================= SỬA =================

    private void suaNguyenLieu(){

        int row = tblCongThuc.getSelectedRow();

        if(row < 0){
            new MyDialog("Chọn nguyên liệu cần sửa",MyDialog.WARNING_DIALOG);
            return;
        }

        try{

            String item = cmbNguyenLieu.getSelectedItem().toString();
            String maNL = item.split(" - ")[0];

            double soLuong = Double.parseDouble(txtSoLuong.getText());

            CongThucDTO ct = new CongThucDTO();

            ct.setMaSP(maSP);
            ct.setMaNguyenLieu(maNL);
            ct.setSoLuong(soLuong);

            boolean flag = ctBUS.updateCongThuc(ct);

            if(flag){

                new MyDialog("Sửa thành công",MyDialog.SUCCESS_DIALOG);
                loadCongThuc(maSP);

            }else{

                new MyDialog("Sửa thất bại",MyDialog.ERROR_DIALOG);

            }

        }catch(Exception e){

            new MyDialog("Dữ liệu không hợp lệ",MyDialog.ERROR_DIALOG);

        }

    }

    // ================= XOÁ =================

    private void xoaNguyenLieu(){

        int row = tblCongThuc.getSelectedRow();

        if(row < 0){
            new MyDialog("Chọn nguyên liệu cần xóa",MyDialog.WARNING_DIALOG);
            return;
        }

        String item = cmbNguyenLieu.getSelectedItem().toString();
        String maNL = item.split(" - ")[0];

        MyDialog dlg = new MyDialog("Bạn có chắc muốn xóa?",MyDialog.WARNING_DIALOG);

        if(dlg.getAction()==MyDialog.OK_OPTION){

            boolean flag = ctBUS.deleteCongThuc(maSP,maNL);

            if(flag){

                new MyDialog("Xóa thành công",MyDialog.SUCCESS_DIALOG);
                loadCongThuc(maSP);

            }else{

                new MyDialog("Xóa thất bại",MyDialog.ERROR_DIALOG);

            }

        }

    }

}