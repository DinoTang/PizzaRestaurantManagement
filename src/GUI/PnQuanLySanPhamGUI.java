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
    JTextField txtMa, txtTen, txtsoLuong, txtdonViTinh, txtdonGia, txtTimKiem;
    JComboBox<String> cmbLoai;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnNhapExcel, btnCongThuc;
    JLabel lblAnhSP;
    
    private void addControlsSanPham() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 844;

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        JLabel lblMa, lblTen, lblLoai, lblSoLuong, lblDonViTinh, lblDonGia;

        lblMa = new JLabel("Mã SP");
        lblTen = new JLabel("Tên SP");
        lblLoai = new JLabel("Loại");
        lblSoLuong = new JLabel("Số lượng");
        lblDonViTinh = new JLabel("Đơn vị tính");
        lblDonGia = new JLabel("Đơn giá");

        txtMa = new JTextField(25);
        txtMa.setEditable(false);
        txtTen = new JTextField(25);
        cmbLoai = new JComboBox<String>();
        txtsoLuong = new JTextField(25);
        txtdonViTinh = new JTextField(25);
        txtdonGia = new JTextField(25);

        JPanel pnMa = new TransparentPanel();
        lblMa.setFont(font);
        txtMa.setFont(font);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanel();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnLoai = new TransparentPanel();
        lblLoai.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtMa.getPreferredSize());
        pnLoai.add(lblLoai);
        pnLoai.add(cmbLoai);
        pnTextField.add(pnLoai);

        JPanel pnSoLuong = new TransparentPanel();
        lblSoLuong.setFont(font);
        txtsoLuong.setFont(font);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtsoLuong);
        pnTextField.add(pnSoLuong);

        JPanel pnDonViTinh = new TransparentPanel();
        lblDonViTinh.setFont(font);
        txtdonViTinh.setFont(font);
        pnDonViTinh.add(lblDonViTinh);
        pnDonViTinh.add(txtdonViTinh);
        pnTextField.add(pnDonViTinh);

        JPanel pnDonGia = new TransparentPanel();
        lblDonGia.setFont(font);
        txtdonGia.setFont(font);
        pnDonGia.add(lblDonGia);
        pnDonGia.add(txtdonGia);
        pnTextField.add(pnDonGia);

        Dimension lblSize = lblDonViTinh.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblLoai.setPreferredSize(lblSize);
        lblSoLuong.setPreferredSize(lblSize);
        lblDonViTinh.setPreferredSize(lblSize);
        lblDonGia.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);

        //=================PANEL ẢNH==========
        JPanel pnAnh = new TransparentPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnChuaAnh = new TransparentPanel();
        pnChuaAnh.setPreferredSize(new Dimension((int) pnAnh.getPreferredSize().getWidth(), 250));
        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setIcon(getAnhSP(""));
        pnChuaAnh.add(lblAnhSP);
        pnAnh.add(pnChuaAnh);

        JPanel pnButtonAnh = new TransparentPanel();
        pnButtonAnh.setPreferredSize(new Dimension(
                (int) pnChuaAnh.getPreferredSize().getHeight(), 40));
        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setIcon(Constants.loadIcon("/images/picture_icon.png"));
        btnChonAnh.setFont(font);
        pnButtonAnh.add(btnChonAnh);
        pnChuaAnh.add(pnButtonAnh);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("");
        btnXuatExcel = new JButton("Xuất");
        btnCongThuc = new JButton("Công thức");
       
   
//        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnCongThuc.setFont(fontButton);
        btnTim.setPreferredSize(new Dimension(50, 30));
        btnXuatExcel.setFont(fontButton);
//        btnNhapExcel.setFont(fontButton);

        btnThem.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSua.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoa.setIcon(Constants.loadIcon("/images/delete-icon.png"));
        btnTim.setIcon(Constants.loadIcon("/images/Search-icon.png"));
        btnXuatExcel.setIcon(Constants.loadIcon("/images/excel-icon.png"));
        btnCongThuc.setIcon(Constants.loadIcon("/images/recipe-icon.png"));

        JPanel pnTimKiem = new TransparentPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(btnTim);
        this.add(pnTimKiem);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
//        pnButton.add(btnTim);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnCongThuc);
        
//        pnButton.add(btnNhapExcel);

        Dimension btnSize = new Dimension(120, 40);
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
//        btnTim.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnCongThuc.setPreferredSize(new Dimension(150, 40));
        
//        btnNhapExcel.setPreferredSize(btnSize);

        this.add(pnButton);

        //============PANEL BẢNG===========
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        //====================Bảng hàng hoá====================
        //<editor-fold defaultstate="collapsed" desc="Bảng sản phẩm">
       
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

        TableColumnModel columnModelBanHang = tblSanPham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(77);
        columnModelBanHang.getColumn(1).setPreferredWidth(282);
        columnModelBanHang.getColumn(2).setPreferredWidth(120);
        columnModelBanHang.getColumn(3).setPreferredWidth(85);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(140);
        columnModelBanHang.getColumn(6).setPreferredWidth(0);

        JScrollPane scrTblSanPham = new JScrollPane(tblSanPham);
        //</editor-fold>
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
                txtdonViTinh.setText("");
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
            txtdonViTinh.setText(donViTinh.replace(",", ""));

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
            cmbLoai.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
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

        String tenSP = txtTen.getText();
        int soLuong = Integer.parseInt(txtsoLuong.getText());
        String anh = (fileAnhSP != null) ? fileAnhSP.getName() : "default.png";

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
            sp.setMaLoai(cmbLoai.getSelectedItem()+"");
            sp.setSoLuong(soLuong);
            sp.setDonViTinh(txtdonViTinh.getText());
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
        String anh = fileAnhSP.getName();
        SanPhamDTO sp = spBUS.getSanPhamById(txtMa.getText());
        sp.setTenSP(txtTen.getText());
        sp.setMaLoai(cmbLoai.getSelectedItem() + "");
        sp.setSoLuong(Integer.parseInt(txtsoLuong.getText()));
        sp.setDonViTinh(txtdonViTinh.getText());
        sp.setHinhAnh(anh);
        sp.setDonGia(Integer.parseInt(txtdonGia.getText()));
        boolean flag = spBUS.updateSanPham(sp);
//        spBUS.docListSanPham();
        loadDataLenBangSanPham();
        luuFileAnh();
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
