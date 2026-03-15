package GUI;

//import Custom.XuLyFileExcel;
import Custom.MyDialog;
import Custom.TransparentPanel;
import Custom.MyTable;
import Custom.ImagePanel;
//import static Main.Main.changLNF;

import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import Custom.XuLyFileExcel;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import Utils.Constants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PnQuanLyNhanVienGUI extends JPanel {

    public PnQuanLyNhanVienGUI() {
//        changLNF("Windows");
        addControlsNhanVien();
        addEventsNhanVien();
        addEventsPhanQuyen();
    }

    private QuyenBUS quyenBUS = new QuyenBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();

    JLabel lblTabbedNhanVien, lblTabbedQuyen;
    final ImageIcon tabbedSelected = Constants.loadIcon("/images/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = Constants.loadIcon("/images/ManagerUI/tabbed-btn.png");
    final Color colorPanel = new Color(247, 247, 247);
    CardLayout cardNhanVienGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    JTextField txtMaNV, txtHoten, txtSoDienThoai, txtEmail, txtLuong, txtTimNV;
    MyTable tblNhanVien;
    DefaultTableModel dtmNhanVien;
    JButton btnReset, btnThemNV, btnSuaNV, btnXoaNV, btnTimNV, btnCapTaiKhoan, btnResetMatKhau, btnXoaTaiKhoan, btnXuatExcel, btnNhapExcel;

    private void addControlsNhanVien() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;
        int h = 844;
        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Nhân viên & Quyền">
        Font font = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedNhanVien = new JLabel("Nhân viên");
        lblTabbedNhanVien.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setIcon(tabbedSelected);
        lblTabbedNhanVien.setBounds(2, 2, 140, 37);
        lblTabbedNhanVien.setFont(font);
        lblTabbedNhanVien.setForeground(Color.white);
        lblTabbedNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuyen = new JLabel("Quyền");
        lblTabbedQuyen.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setIcon(tabbedDefault);
        lblTabbedQuyen.setBounds(143, 2, 140, 37);
        lblTabbedQuyen.setFont(font);
        lblTabbedQuyen.setForeground(Color.white);
        lblTabbedQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedNhanVien);
        pnTop.add(lblTabbedQuyen);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

        /*
        =========================================================================
                                    PANEL NHÂN VIÊN
        =========================================================================
         */
        JPanel pnNhanVien = new TransparentPanel();
        pnNhanVien.setLayout(new BoxLayout(pnNhanVien, BoxLayout.Y_AXIS));

        JPanel pnTopNV = new TransparentPanel();
        pnTopNV.setLayout(new BoxLayout(pnTopNV, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ NHÂN VIÊN</h1></html>");
        btnReset = new JButton(Constants.loadIcon("/images/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnTopNV.add(pnTitle);
        pnTopNV.setBackground(Color.DARK_GRAY);
        //==========
        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        txtMaNV = new JTextField(25);
        txtMaNV.setEditable(false);
        txtHoten = new JTextField(25);
        txtSoDienThoai = new JTextField(25);
        txtEmail = new JTextField(25);
        txtLuong = new JTextField(25);
        

        txtMaNV.setFont(font);
        txtHoten.setFont(font);
        txtSoDienThoai.setFont(font);
        txtEmail.setFont(font);
        txtLuong.setFont(font);
 
        JLabel lblMa, lblHoTen, lblSDT, lblEmail, lblLuong;

        lblMa = new JLabel("Mã NV");
        lblHoTen = new JLabel("Họ tên");
        lblSDT = new JLabel("Số điện thoại");
        lblEmail = new JLabel("Email");
        lblLuong = new JLabel("Lương");

        lblMa.setFont(font);
        lblHoTen.setFont(font);
        lblSDT.setFont(font);
        lblEmail.setFont(font);
        lblLuong.setFont(font);
        
      

        JPanel pnMa = new TransparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtMaNV);
        pnText.add(pnMa);

        JPanel pnHoTen = new TransparentPanel();
        pnHoTen.add(lblHoTen);
        pnHoTen.add(txtHoten);
        pnText.add(pnHoTen);

        JPanel pnSDT = new TransparentPanel();
        pnSDT.add(lblSDT);
        pnSDT.add(txtSoDienThoai);
        pnText.add(pnSDT);

        JPanel pnEmail = new TransparentPanel();
        pnEmail.add(lblEmail);
        pnEmail.add(txtEmail);
        pnText.add(pnEmail);
        
        JPanel pnLuong = new TransparentPanel();
        pnLuong.add(lblLuong);
        pnLuong.add(txtLuong);
        pnText.add(pnLuong);

        Dimension lblSize = new Dimension(130, lblMa.getPreferredSize().height);
        lblMa.setPreferredSize(lblSize);
        lblHoTen.setPreferredSize(lblSize);
        lblSDT.setPreferredSize(lblSize);
        lblEmail.setPreferredSize(lblSize);
        lblLuong.setPreferredSize(lblSize);

        pnTopNV.add(pnText);

        //==========
        JPanel pnTimNV = new TransparentPanel();
        JLabel lblTim = new JLabel("Từ khoá tìm");
        lblTim.setFont(font);
        txtTimNV = new JTextField(25);
        txtTimNV.setFont(font);
        pnTimNV.add(lblTim);
        pnTimNV.add(txtTimNV);
        pnTopNV.add(pnTimNV);
        lblTim.setPreferredSize(lblSize);
        //==========
        JPanel pnButton = new TransparentPanel();

        btnThemNV = new JButton("Thêm");
        btnSuaNV = new JButton("Lưu");
        btnXoaNV = new JButton("Xoá");
        btnTimNV = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
//        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemNV.setFont(fontButton);
        btnSuaNV.setFont(fontButton);
        btnXoaNV.setFont(fontButton);
        btnTimNV.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
//        btnNhapExcel.setFont(fontButton);

        btnThemNV.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSuaNV.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoaNV.setIcon(Constants.loadIcon("/images/delete-icon.png"));
        btnTimNV.setIcon(Constants.loadIcon("/images/Search-icon.png"));
        btnXuatExcel.setIcon(Constants.loadIcon("/images/excel-icon.png"));
//        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        pnButton.add(btnThemNV);
        pnButton.add(btnSuaNV);
        pnButton.add(btnXoaNV);
        pnButton.add(btnTimNV);
        pnButton.add(btnXuatExcel);
//        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTimNV.getPreferredSize();
        btnThemNV.setPreferredSize(btnSize);
        btnSuaNV.setPreferredSize(btnSize);
        btnXoaNV.setPreferredSize(btnSize);
        btnTimNV.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
//        btnNhapExcel.setPreferredSize(btnSize);

        JPanel pnButton2 = new TransparentPanel();
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhau = new JButton("Mật khẩu/Quyền");
        btnXoaTaiKhoan = new JButton("Khoá tài khoản");
        btnCapTaiKhoan.setIcon(Constants.loadIcon("/images/icons8_man_with_key_32px.png"));
        btnResetMatKhau.setIcon(Constants.loadIcon("/images/icons8_password_reset_32px.png"));
        btnXoaTaiKhoan.setIcon(Constants.loadIcon("/images/icons8_denied_32px.png"));
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhau.setFont(fontButton);
        btnXoaTaiKhoan.setFont(fontButton);
        pnButton2.add(btnCapTaiKhoan);
        pnButton2.add(btnResetMatKhau);
        pnButton2.add(btnXoaTaiKhoan);

        pnNhanVien.add(pnTopNV);
        pnNhanVien.add(pnButton);
        pnNhanVien.add(pnButton2);
        //===================TABLE NHÂN VIÊN=====================
        JPanel pnTableNhanVien = new TransparentPanel();
        pnTableNhanVien.setLayout(new BorderLayout());
        
        dtmNhanVien = new DefaultTableModel(
            new Object[]{"Mã NV","Họ tên","SĐT","Email","Lương","Ngày tạo"},0
        ){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
       
        tblNhanVien = new MyTable(dtmNhanVien);
        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTableNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);
        pnNhanVien.add(scrTblNhanVien);
        /*
        =========================================================================
                                    PANEL QUYỀN
        =========================================================================
         */
        JPanel pnPhanQuyen = new TransparentPanel();
        pnPhanQuyen.setLayout(new BoxLayout(pnPhanQuyen, BoxLayout.Y_AXIS));

        JPanel pnTitlePhanQuyen = new TransparentPanel();
        JLabel lblTitlePhanQuyen = new JLabel("<html><h1>Quản lý phân quyền</h1></html>");
        pnTitlePhanQuyen.add(lblTitlePhanQuyen);
        pnPhanQuyen.add(pnTitlePhanQuyen);

        JPanel pnCmbQuyen = new TransparentPanel();
        JLabel lblCmbQuyen = new JLabel("<html><b>Nhóm quyền:</b></html>");
        lblCmbQuyen.setFont(font);
        cmbQuyen = new JComboBox<String>();
        cmbQuyen.setFont(font);
        pnCmbQuyen.add(lblCmbQuyen);
        pnCmbQuyen.add(cmbQuyen);
        pnPhanQuyen.add(pnCmbQuyen);

        JPanel pnCheckNhapHang = new TransparentPanel();
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng.");
        ckbNhapHang.setFont(font);
        pnCheckNhapHang.add(ckbNhapHang);
        pnPhanQuyen.add(pnCheckNhapHang);

        JPanel pnCheckQLSanPham = new TransparentPanel();
        ckbQLSanPham = new JCheckBox("Quản lý sản phẩm.");
        ckbQLSanPham.setFont(font);
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnPhanQuyen.add(pnCheckQLSanPham);

        JPanel pnCheckQLNhanVien = new TransparentPanel();
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên.");
        ckbQLNhanVien.setFont(font);
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnPhanQuyen.add(pnCheckQLNhanVien);

        JPanel pnCheckQLKhachHang = new TransparentPanel();
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng.");
        ckbQLKhachHang.setFont(font);
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnPhanQuyen.add(pnCheckQLKhachHang);

        JPanel pnCheckQLThongKe = new TransparentPanel();
        ckbThongKe = new JCheckBox("Quản lý thống kê.");
        ckbThongKe.setFont(font);
        pnCheckQLThongKe.add(ckbThongKe);
        pnPhanQuyen.add(pnCheckQLThongKe);

        Dimension ckbSize = ckbQLKhachHang.getPreferredSize();
        cmbQuyen.setPreferredSize(ckbSize);
        ckbNhapHang.setPreferredSize(ckbSize);
        ckbQLSanPham.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLKhachHang.setPreferredSize(ckbSize);
        ckbThongKe.setPreferredSize(ckbSize);

        JPanel pnButtonQuyen = new TransparentPanel();
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xoá quyền");
        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);
        btnThemQuyen.setIcon(Constants.loadIcon("/images/add-icon.png"));
        btnSuaQuyen.setIcon(Constants.loadIcon("/images/Pencil-icon.png"));
        btnXoaQuyen.setIcon(Constants.loadIcon("/images/delete-icon.png"));
        pnButtonQuyen.add(btnThemQuyen);
        pnButtonQuyen.add(btnSuaQuyen);
        pnButtonQuyen.add(btnXoaQuyen);
        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        pnPhanQuyen.add(pnButtonQuyen);

       
        //========================
        pnCardTabNhanVien = new JPanel(cardNhanVienGroup);
        pnCardTabNhanVien.add(pnNhanVien, "1");
        pnCardTabNhanVien.add(pnPhanQuyen, "2");
        this.add(pnCardTabNhanVien);

        loadDataTblNhanVien();
        loadDataCmbQuyen();
    }

    JComboBox<String> cmbQuyen;
    JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe;
    JButton btnSuaQuyen, btnThemQuyen, btnXoaQuyen;

    private void addEventsNhanVien() {
        lblTabbedNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedSelected);
                lblTabbedQuyen.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "1");
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

        lblTabbedQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedQuyen.setIcon(tabbedSelected);
                lblTabbedNhanVien.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "2");
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

        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
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

        txtTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });

        btnSuaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });

        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatExcel();
            }
        });

//        btnNhapExcel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                xuLyNhapExcel();
//            }
//        });

        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });

        btnResetMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });

        btnXoaTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblNhanVien();
                txtMaNV.setText("");
                txtHoten.setText("");
                txtSoDienThoai.setText("");
                txtEmail.setText("");
                txtLuong.setText("");
                txtTimNV.setText("");
            }
        });
    }

    private void addEventsPhanQuyen() {
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
    }

    private void xuLyXoaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để xoá!", MyDialog.ERROR_DIALOG);
            return;
        }
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() == MyDialog.CANCEL_OPTION) {
            return;
        }
        String maQuyen = quyenBUS.getQuyenBangTenQuyen(cmbQuyen.getSelectedItem() + "").getMaQuyen();
        boolean flag = quyenBUS.xoaQuyen(maQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");
        QuyenDTO quyen = quyenBUS.getQuyenBangTenQuyen(tenQuyen);
        boolean flag = quyenBUS.themQuyen(quyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để sửa!", MyDialog.ERROR_DIALOG);
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;
        QuyenDTO quyen = quyenBUS.getQuyenBangTenQuyen(tenQuyen);
        quyen.setNhapHang(nhapHang);
        quyen.setQlSanPham(sanPham);
        quyen.setQlNhanVien(nhanVien);
        quyen.setQlKhachHang(khachHang);
        quyen.setThongKe(thongKe);
        boolean flag = quyenBUS.suaQuyen(quyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyHienThiChiTietQuyen() {
        List<QuyenDTO> dsq = quyenBUS.getAllQuyen();
        QuyenDTO phanQuyen = new QuyenDTO();
        for (QuyenDTO pq : dsq) {
            if (pq.getMaQuyen().equals(cmbQuyen.getSelectedItem())) {
                phanQuyen.setMaQuyen(pq.getMaQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        if (phanQuyen.getNhapHang() == 1) {
            ckbNhapHang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLSanPham.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLNhanVien.setSelected(true);
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLKhachHang.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbThongKe.setSelected(true);
        }
    }

    private void loadDataCmbQuyen() {
        List<QuyenDTO> dsq = quyenBUS.getAllQuyen();
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        for (QuyenDTO pq : dsq) {
            cmbQuyen.addItem(pq.getMaQuyen());
        }
    }

    private void xuLyResetMatKhau() {
//        String maNV = txtMaNV.getText();
//        if (maNV.trim().equals("")) {
//            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
//            return;
//        }
//        DlgQuyen_MatKhau dialog = new DlgQuyen_MatKhau(maNV);
//        dialog.setVisible(true);
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().equals("")) {
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgCapTaiKhoan dialog = new DlgCapTaiKhoan(txtMaNV.getText());
        dialog.setVisible(true);
        loadDataTblNhanVien();
    }

    private void xuLyKhoaTaiKhoan() {
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        loadDataTblNhanVien();
    }

//    private void xuLyNhapExcel() {
//        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
//        if (dlg.getAction() != MyDialog.OK_OPTION) {
//            return;
//        }
//
//        XuLyFileExcel nhapExcel = new XuLyFileExcel();
//        nhapExcel.nhapExcel(tblNhanVien);
//
//        int row = tblNhanVien.getRowCount();
//        for (int i = 0; i < row; i++) {
//            String ho = tblNhanVien.getValueAt(i, 1) + "";
//            String ten = tblNhanVien.getValueAt(i, 2) + "";
//            String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
//            String chucVu = tblNhanVien.getValueAt(i, 4) + "";
//
//            nhanVienBUS.nhapExcel(ho, ten, gioiTinh, chucVu);
//
//        }
//    }

        private void xuLyXuatExcel() {
        XuLyFileExcel xuatExcel = new XuLyFileExcel();
        xuatExcel.xuatExcel(tblNhanVien);
    }

    private void xuLyXoaNhanVien(){

        String ma = txtMaNV.getText();

        if(ma == null || ma.trim().isEmpty()){
            new MyDialog("Vui lòng chọn nhân viên muốn xóa!", MyDialog.ERROR_DIALOG);
            return;
        }

        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);

        if(dlg.getAction() == MyDialog.OK_OPTION){

            NhanVienDTO nv = nhanVienBUS.getNhanVienById(ma);

            if(nhanVienBUS.deleteNhanVien(nv)){
                loadDataTblNhanVien();
            }

        }
    }

    private void xuLySuaNhanVien(){

        String ma = txtMaNV.getText();

        NhanVienDTO nv = nhanVienBUS.getNhanVienById(ma);

        nv.setHoTen(txtHoten.getText());

        if(nhanVienBUS.updateNhanVien(nv)){
            loadDataTblNhanVien();
        }
    }

    private void xuLyThemNhanVien(){

        String hoten = txtHoten.getText();

        NhanVienDTO nv = new NhanVienDTO();

        nv.setMaNhanVien(nhanVienBUS.getNextId());
        nv.setHoTen(hoten);
        nv.setSoDienThoai("");
        nv.setEmail("");
        nv.setLuong(0);
        nv.setNgayTao(LocalDate.now());
        nv.setTrangThaiXoa(false);

        if(nhanVienBUS.addNhanVien(nv)){
            loadDataTblNhanVien();
        }
    }

    private void xuLyTimKiemNhanVien(){

    String keyword = txtTimNV.getText().trim();

    dtmNhanVien.setRowCount(0);

    List<NhanVienDTO> list = nhanVienBUS.searchNhanVienByName(keyword);

    for(NhanVienDTO nv : list){

        Vector row = new Vector();

        row.add(nv.getMaNhanVien());
        row.add(nv.getHoTen());
        row.add(nv.getSoDienThoai());
        row.add(nv.getEmail());
        row.add(nv.getLuong());
        row.add(nv.getNgayTao());

        dtmNhanVien.addRow(row);
    }
    }

    private void xuLyClickTblNhanVien(){

    int row = tblNhanVien.getSelectedRow();

    if(row > -1){

        txtMaNV.setText(tblNhanVien.getValueAt(row,0)+"");
        txtHoten.setText(tblNhanVien.getValueAt(row,1)+"");
        txtSoDienThoai.setText(tblNhanVien.getValueAt(row,2)+"");
        txtEmail.setText(tblNhanVien.getValueAt(row,3)+"");
        txtLuong.setText(tblNhanVien.getValueAt(row,4)+"");

    }
    }

    private void loadDataTblNhanVien(){

    dtmNhanVien.setRowCount(0);

    List<NhanVienDTO> list = nhanVienBUS.getAllNhanVien();

    for(NhanVienDTO nv : list){

        Vector row = new Vector();

        row.add(nv.getMaNhanVien());
        row.add(nv.getHoTen());
        row.add(nv.getSoDienThoai());
        row.add(nv.getEmail());
        row.add(nv.getLuong());
        row.add(nv.getNgayTao());

        dtmNhanVien.addRow(row);
    }
    }
    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

}
