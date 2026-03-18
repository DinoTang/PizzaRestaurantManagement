package GUI;

import BUS.GiamGiaBUS;
import DTO.GiamGiaDTO;
import Custom.MyDialog;
import Custom.MyTable;
import Custom.TransparentPanel;
import Utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class PnQuanLyKhuyenMaiGUI extends JPanel {

    private GiamGiaBUS giamGiaBUS = new GiamGiaBUS();

    private JTextField txtMa, txtTen, txtPhanTram;
    private JButton btnThem, btnSua, btnXoa, btnReset;

    private MyTable tblKhuyenMai;
    private DefaultTableModel dtm;

    final Color colorPanel = new Color(247,247,247);

    public PnQuanLyKhuyenMaiGUI() {
        addControls();
        addEvents();
        loadDataTable();
    }

    private void addControls(){

        Font font = new Font("Tahoma", Font.PLAIN, 20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        // TITLE
        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ MÃ KHUYẾN MÃI</h1></html>");

        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40,40));

        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);

        this.add(pnTitle);

        // PANEL THÔNG TIN
        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMa = new JLabel("Mã KM");
        JLabel lblTen = new JLabel("Tên chương trình");
        JLabel lblPhanTram = new JLabel("Phần trăm giảm");

        txtMa = new JTextField(25);
        txtTen = new JTextField(25);
        txtPhanTram = new JTextField(25);

        txtMa.setEditable(false);

        // MÃ
        JPanel pnMa = new TransparentPanel();
        lblMa.setFont(font);
        txtMa.setFont(font);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        // TÊN
        JPanel pnTen = new TransparentPanel();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        // PHẦN TRĂM
        JPanel pnPhanTram = new TransparentPanel();
        lblPhanTram.setFont(font);
        txtPhanTram.setFont(font);
        pnPhanTram.add(lblPhanTram);
        pnPhanTram.add(txtPhanTram);
        pnTextField.add(pnPhanTram);

        // CĂN LABEL
        Dimension lblSize = new Dimension(170, 40);
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblPhanTram.setPreferredSize(lblSize);

        txtTen.setPreferredSize(txtMa.getPreferredSize());
        txtPhanTram.setPreferredSize(txtMa.getPreferredSize());

        pnThongTin.add(pnTextField);

        this.add(pnThongTin);

        // BUTTON
        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        btnThem.setFont(font);
        btnSua.setFont(font);
        btnXoa.setFont(font);

        btnThem.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSua.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoa.setIcon(Constants.loadIcon("/images/delete-icon.png"));

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        this.add(pnButton);

        // TABLE
        dtm = new DefaultTableModel();

        dtm.addColumn("Mã KM");
        dtm.addColumn("Tên chương trình");
        dtm.addColumn("Phần trăm giảm");

        tblKhuyenMai = new MyTable(dtm);

        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scr = new JScrollPane(tblKhuyenMai);

        this.add(scr);
    }

    private void addEvents(){

        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                clickTable();
            }
        });

        btnThem.addActionListener(e -> themKhuyenMai());
        btnSua.addActionListener(e -> suaKhuyenMai());
        btnXoa.addActionListener(e -> xoaKhuyenMai());
        btnReset.addActionListener(e -> resetForm());
    }

    private void loadDataTable(){

        dtm.setRowCount(0);

        List<GiamGiaDTO> list = giamGiaBUS.getDanhSach();

        for(GiamGiaDTO gg : list){

            Vector row = new Vector();

            row.add(gg.getMaGiam());
            row.add(gg.getTenGiamGia());
            row.add(gg.getPhanTramGiam() + "%");

            dtm.addRow(row);
        }
    }

    private void clickTable(){

        int row = tblKhuyenMai.getSelectedRow();

        if(row >= 0){

            txtMa.setText(tblKhuyenMai.getValueAt(row,0).toString());
            txtTen.setText(tblKhuyenMai.getValueAt(row,1).toString());

            String pt = tblKhuyenMai.getValueAt(row,2).toString();
            pt = pt.replace("%","");

            txtPhanTram.setText(pt);
        }
    }

    private void themKhuyenMai(){

        try{

            GiamGiaDTO gg = new GiamGiaDTO();

            gg.setMaGiam(giamGiaBUS.getNextMaGiamGia());
            gg.setTenGiamGia(txtTen.getText());
            gg.setPhanTramGiam(Integer.parseInt(txtPhanTram.getText()));

            if(giamGiaBUS.addGiamGia(gg)){
                new MyDialog("Thêm thành công", MyDialog.SUCCESS_DIALOG);
                loadDataTable();
                resetForm();
            }

        }catch(Exception e){
            new MyDialog("Dữ liệu không hợp lệ", MyDialog.ERROR_DIALOG);
        }
    }

    private void suaKhuyenMai(){

        try{

            GiamGiaDTO gg = new GiamGiaDTO();

            gg.setMaGiam(txtMa.getText());
            gg.setTenGiamGia(txtTen.getText());
            gg.setPhanTramGiam(Integer.parseInt(txtPhanTram.getText()));

            if(giamGiaBUS.updateGiamGia(gg)){
                new MyDialog("Cập nhật thành công", MyDialog.SUCCESS_DIALOG);
                loadDataTable();
            }

        }catch(Exception e){
            new MyDialog("Dữ liệu không hợp lệ", MyDialog.ERROR_DIALOG);
        }
    }

    private void xoaKhuyenMai(){

        String ma = txtMa.getText();

        if(ma.equals("")){
            new MyDialog("Chọn mã khuyến mãi cần xóa", MyDialog.ERROR_DIALOG);
            return;
        }

        if(giamGiaBUS.deleteGiamGia(ma)){
            new MyDialog("Xóa thành công", MyDialog.SUCCESS_DIALOG);
            loadDataTable();
            resetForm();
        }
    }

    private void resetForm(){

        txtMa.setText("");
        txtTen.setText("");
        txtPhanTram.setText("");
    }
}