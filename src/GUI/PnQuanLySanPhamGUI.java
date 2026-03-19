package GUI;

import BUS.LoaiBUS;
import BUS.SanPhamBUS;
import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import Utils.Constants;
//import static Main.Main.changLNF;

//import Custom.XuLyFileExcel;
import Custom.MyDialog;
import Custom.MyFileChooser;
import Custom.MyTable;
import Custom.TransparentPanel;
import Custom.XuLyFileExcel;
import Utils.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLySanPhamGUI extends JPanel {

    public PnQuanLySanPhamGUI() {
//        changLNF("Windows");
        addControlsSanPham();
        addEventsSanPham();
    }

    SanPhamBUS spBUS = new SanPhamBUS();
    LoaiBUS loaiBUS = new LoaiBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSanPham;
    DefaultTableModel dtmSanPham;
    JTextField txtMa, txtTen, txtsoLuong, txtdonGia, txtTimKiem;
    JComboBox<String> cmbLoai;
    JComboBox<String> cmbDonVi;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnCongThuc;
    JLabel lblAnhSP;
    
    private void addControlsSanPham() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        // ===== TITLE =====
        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        // ===== PANEL THÔNG TIN =====
        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        // ===== PANEL INPUT =====
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        Dimension lblSize = new Dimension(150, 30);

        // Components
        JLabel lblMa = new JLabel("Mã SP");
        JLabel lblTen = new JLabel("Tên SP");
        JLabel lblLoai = new JLabel("Loại");
        JLabel lblSoLuong = new JLabel("Số lượng");
        JLabel lblDonViTinh = new JLabel("Đơn vị tính");
        JLabel lblDonGia = new JLabel("Đơn giá");

        txtMa = new JTextField(25);
        txtMa.setEditable(false);
        txtTen = new JTextField(25);
        txtsoLuong = new JTextField(25);
        txtdonGia = new JTextField(25);

        cmbLoai = new JComboBox<>();
        String[] donViTinhList = {"Chọn đơn vị tính","Cái","Chai","Ly","Lon","Phần"};
        cmbDonVi = new JComboBox<>(donViTinhList);

        // ===== SET FONT =====
        Component[] inputs = {txtMa, txtTen, txtsoLuong, txtdonGia, cmbLoai, cmbDonVi};
        for (Component c : inputs) c.setFont(font);

        JLabel[] labels = {lblMa, lblTen, lblLoai, lblSoLuong, lblDonViTinh, lblDonGia};
        for (JLabel l : labels) {
            l.setFont(font);
            l.setPreferredSize(lblSize);
        }

        // ===== FIX SIZE (QUAN TRỌNG) =====
        Dimension fieldSize = txtMa.getPreferredSize();

        cmbLoai.setPreferredSize(fieldSize);
        cmbLoai.setMinimumSize(fieldSize);
        cmbLoai.setMaximumSize(fieldSize);

        cmbDonVi.setPreferredSize(fieldSize);
        cmbDonVi.setMinimumSize(fieldSize);
        cmbDonVi.setMaximumSize(fieldSize);

        // ===== HÀM TẠO ROW (GỌN CODE) =====
        java.util.function.BiFunction<JLabel, Component, JPanel> createRow = (lbl, comp) -> {
            JPanel pn = new TransparentPanel();
            pn.setAlignmentX(Component.LEFT_ALIGNMENT);
            pn.add(lbl);
            pn.add(comp);
            return pn;
        };

        pnTextField.add(createRow.apply(lblMa, txtMa));
        pnTextField.add(createRow.apply(lblTen, txtTen));
        pnTextField.add(createRow.apply(lblLoai, cmbLoai));
        pnTextField.add(createRow.apply(lblSoLuong, txtsoLuong));
        pnTextField.add(createRow.apply(lblDonViTinh, cmbDonVi)); // <-- FIX LỆCH
        pnTextField.add(createRow.apply(lblDonGia, txtdonGia));

        pnThongTin.add(pnTextField);

        // ===== PANEL ẢNH =====
        JPanel pnAnh = new TransparentPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setIcon(getAnhSP(""));

        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(font);
        btnChonAnh.setIcon(Constants.loadIcon("/images/picture_icon.png"));

        JPanel pnChuaAnh = new TransparentPanel();
        pnChuaAnh.add(lblAnhSP);

        JPanel pnButtonAnh = new TransparentPanel();
        pnButtonAnh.add(btnChonAnh);

        pnAnh.add(pnChuaAnh);
        pnAnh.add(pnButtonAnh);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        // ===== BUTTON =====
        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("");
        btnXuatExcel = new JButton("Xuất");
        btnCongThuc = new JButton("Công thức");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        JButton[] btns = {btnThem, btnSua, btnXoa, btnTim, btnXuatExcel, btnCongThuc};
        for (JButton b : btns) b.setFont(fontButton);

        btnThem.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSua.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoa.setIcon(Constants.loadIcon("/images/delete-icon.png"));
        btnTim.setIcon(Constants.loadIcon("/images/Search-icon.png"));
        btnXuatExcel.setIcon(Constants.loadIcon("/images/excel-icon.png"));
        btnCongThuc.setIcon(Constants.loadIcon("/images/recipe-icon.png"));

        Dimension btnSize = new Dimension(120, 40);
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnCongThuc.setPreferredSize(new Dimension(150, 40));

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnCongThuc);

        // ===== SEARCH =====
        JPanel pnTimKiem = new TransparentPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);

        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(btnTim);

        this.add(pnTimKiem);
        this.add(pnButton);

        // ===== TABLE =====
        dtmSanPham = new DefaultTableModel(
            new Object[]{"Mã SP","Tên SP","Loại SP","Đơn giá","Số lượng","Đơn vị tính","Ảnh"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSanPham = new MyTable(dtmSanPham);

        tblSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JScrollPane scrTblSanPham = new JScrollPane(tblSanPham);
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        pnTable.add(scrTblSanPham, BorderLayout.CENTER);

        this.add(pnTable);

        loadDataCmbLoai();
        loadDataLenBangSanPham();
    }

    private void addEventsSanPham() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnh("");
                loadDataLenBangSanPham();
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                cmbDonVi.setSelectedIndex(0);
                txtsoLuong.setText("");
                cmbLoai.setSelectedIndex(0);
            }
        });

        tblSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSanPham();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSanPham();
            }
        });

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });

        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
        btnCongThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyMoCongThuc();
            }
        });
//        btnNhapExcel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                xuLyNhapFileExcel();
//            }
//        });
    }

//    private void xuLyNhapFileExcel() {
//        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
//        if (dlg.getAction() != MyDialog.OK_OPTION) {
//            return;
//        }
//
//        XuLyFileExcel nhapFile = new XuLyFileExcel();
//        nhapFile.nhapExcel(tblSanPham);
//
//        int row = tblSanPham.getRowCount();
//        for (int i = 0; i < row; i++) {
//            String ten = tblSanPham.getValueAt(i, 1) + "";
//            String loai = tblSanPham.getValueAt(i, 2) + "";
//            String donGia = tblSanPham.getValueAt(i, 3) + "";
//            String soLuong = tblSanPham.getValueAt(i, 4) + "";
//            String donViTinh = tblSanPham.getValueAt(i, 5) + "";
//            String anh = tblSanPham.getValueAt(i, 6) + "";
//
//            spBUS.nhapSanPhamTuExcel(ten, loai, soLuong, donViTinh, anh, donGia);
//        }
//    }

    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSanPham);
    }

    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }

    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            String donGia = tblSanPham.getValueAt(row, 3) + "";
            String soLuong = tblSanPham.getValueAt(row, 4) + "";
            String donViTinh = tblSanPham.getValueAt(row, 5) + "";
            String anh = tblSanPham.getValueAt(row, 6) + "";

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtdonGia.setText(donGia);
            txtsoLuong.setText(soLuong);
            cmbDonVi.setSelectedItem(donViTinh);

            int flag = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flag = i;
                    break;
                }
            }
            cmbLoai.setSelectedIndex(flag);
            loadAnh(anh);
        }
    }

    private void loadDataLenBangSanPham() {
        dtmSanPham.setRowCount(0);

        List<SanPhamDTO> dssp = spBUS.getAllSanPham();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPhamDTO sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getLoaiTheoId(sp.getMaLoai()).getTenLoai();
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPham.addRow(vec);
        }
    }

    private void loadDataCmbLoai() {
        cmbLoai.removeAllItems();
        List<LoaiDTO> dsl = loaiBUS.getAllLoai();
        cmbLoai.addItem("Chọn loại");
        for (LoaiDTO loai : dsl) {
            cmbLoai.addItem(loai.getTenLoai());
        }
        cmbLoai.addItem("Khác...");
    }

    private void xuLyThemLoai() {
        int x = cmbLoai.getSelectedIndex();
        if (x == cmbLoai.getItemCount() - 1) {
            DlgQuanLyLoai loaiGUI = new DlgQuanLyLoai();
            loaiGUI.setVisible(true);
            loadDataCmbLoai();
        }
    }

    private void xuLyThemSanPham() {

        // kiểm tra dữ liệu
        if(txtTen.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập tên sản phẩm!", MyDialog.ERROR_DIALOG);
            return;
        }

        if(txtsoLuong.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập số lượng!", MyDialog.ERROR_DIALOG);
            return;
        }

        if(txtdonGia.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá!", MyDialog.ERROR_DIALOG);
            return;
        }
        
        if (cmbDonVi.getSelectedIndex() == 0) {
            new MyDialog("Vui lòng chọn đơn vị tính!", MyDialog.ERROR_DIALOG);
            return;
        }

        String tenSP = txtTen.getText();
        int soLuong = Integer.parseInt(txtsoLuong.getText());
        String anh = (fileAnhSP != null) ? fileAnhSP.getName() : "default.png";
        String tenLoai = cmbLoai.getSelectedItem().toString();
        String maLoai = loaiBUS.getMaLoaiByTen(tenLoai);

        // tìm sản phẩm theo tên
        List<SanPhamDTO> list = spBUS.searchSanPham(tenSP);
        SanPhamDTO spTonTai = null;

        if(!list.isEmpty()){
            spTonTai = list.get(0);
        }

        // nếu sản phẩm đã tồn tại
        if(spTonTai != null){

            boolean flag = spBUS.updateSoLuongTang(spTonTai.getMaSP(), soLuong);

            if(flag){

                // giảm nguyên liệu theo công thức
                spBUS.giamNguyenLieuTheoCongThuc(spTonTai.getMaSP(), soLuong);

                new MyDialog("Sản phẩm đã tồn tại, đã tăng số lượng!", MyDialog.SUCCESS_DIALOG);

            }else{
                new MyDialog("Cập nhật số lượng thất bại!", MyDialog.ERROR_DIALOG);
            }

        }
        else{

            // tạo sản phẩm mới
            String maSP = spBUS.getNextSanPhamId();

            SanPhamDTO sp = new SanPhamDTO();

            sp.setMaSP(maSP);
            sp.setTenSP(tenSP);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuong);
            sp.setDonViTinh(cmbDonVi.getSelectedItem().toString());
            sp.setDonGia(Integer.parseInt(txtdonGia.getText()));
            sp.setHinhAnh(anh);
            sp.setTrangThaiXoa(false);

            boolean flag = spBUS.addSanPham(sp);

            if(flag){

                // giảm nguyên liệu theo công thức
                spBUS.giamNguyenLieuTheoCongThuc(maSP, soLuong);

                new MyDialog("Thêm sản phẩm thành công!", MyDialog.SUCCESS_DIALOG);

            }else{
                new MyDialog("Thêm sản phẩm thất bại!", MyDialog.ERROR_DIALOG);
            }
        }

        // lưu ảnh nếu có
        if(fileAnhSP != null){
            luuFileAnh();
        }

        // reload bảng
        loadDataLenBangSanPham();
    }

    File fileAnhSP;

    private void xuLySuaSanPham() {
        
        // kiểm tra dữ liệu
        if(txtTen.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập tên sản phẩm!", MyDialog.ERROR_DIALOG);
            return;
        }

        if(txtsoLuong.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập số lượng!", MyDialog.ERROR_DIALOG);
            return;
        }

        if(txtdonGia.getText().trim().isEmpty()){
            new MyDialog("Vui lòng nhập đơn giá!", MyDialog.ERROR_DIALOG);
            return;
        }
        
        if (cmbDonVi.getSelectedIndex() == 0) {
            new MyDialog("Vui lòng chọn đơn vị tính!", MyDialog.ERROR_DIALOG);
            return;
        }
        String tenLoai = cmbLoai.getSelectedItem().toString();
        String maLoai = loaiBUS.getMaLoaiByTen(tenLoai);
        
        SanPhamDTO sp = spBUS.getSanPhamById(txtMa.getText());
        String anh = (fileAnhSP != null) ? fileAnhSP.getName() : sp.getHinhAnh();
        sp.setTenSP(txtTen.getText());
        sp.setMaLoai(maLoai);
        sp.setSoLuong(Integer.parseInt(txtsoLuong.getText().replace(",", "")));
        sp.setDonViTinh(cmbDonVi.getSelectedItem().toString());
        sp.setHinhAnh(anh);
        sp.setDonGia(Integer.parseInt(txtdonGia.getText().replace(",", "")));
        boolean flag = spBUS.updateSanPham(sp);
//        spBUS.docListSanPham();
        
        if(flag){
        new MyDialog("Cập nhật sản phẩm thành công!", MyDialog.SUCCESS_DIALOG);

        // chỉ lưu ảnh khi có chọn ảnh mới
        if (fileAnhSP != null) {
            luuFileAnh();
        }

        loadDataLenBangSanPham();

        // reset ảnh tránh bug
        fileAnhSP = null;

        } else {
            new MyDialog("Cập nhật sản phẩm thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    private void xuLyXoaSanPham() {
        String maSP = txtMa.getText();

        if(maSP == null || maSP.trim().isEmpty()){
            new MyDialog("Vui lòng chọn sản phẩm muốn xóa!", MyDialog.ERROR_DIALOG);
            return;
        }
        
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = spBUS.deleteSanPham(txtMa.getText());
            if (flag) {
                loadDataLenBangSanPham();
            }
        }
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        if (fileAnhSP == null) return;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("src/images/SanPham/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("/images/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(getAnhSP(fileAnhSP.getName()));
        }
    }

   private ImageIcon getAnhSP(String src) {

    if(src == null || src.trim().isEmpty()){
        src = "default.png";
    }

    String path = "/images/SanPham/" + src;
    java.net.URL url = getClass().getResource(path);

    if(url == null){
        System.out.println("Khong tim thay anh: " + path);
        return new ImageIcon(); // trả icon rỗng, không crash
    }

    ImageIcon icon = new ImageIcon(url);
    Image img = icon.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);

    return new ImageIcon(img);
    }

    private void xuLyTimKiem() {
        String ten = txtTimKiem.getText().toLowerCase();
        dtmSanPham.setRowCount(0);
        List<SanPhamDTO> dssp = null;
        dssp = spBUS.searchSanPham(ten);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (SanPhamDTO sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getLoaiTheoId(sp.getMaLoai()).getTenLoai();
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPham.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dssp.size(), MyDialog.INFO_DIALOG);
    }
    
    private void xuLyMoCongThuc() {

    String maSP = txtMa.getText();

    if(maSP == null || maSP.trim().isEmpty()){
        new MyDialog("Vui lòng chọn sản phẩm trước!", MyDialog.ERROR_DIALOG);
        return;
    }

    DlgCongThuc dlg = new DlgCongThuc(maSP);
    dlg.setLocationRelativeTo(null);
    dlg.setVisible(true);
    }
}
