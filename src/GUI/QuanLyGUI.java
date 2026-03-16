package GUI;

import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.QuyenDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class QuanLyGUI extends JFrame {
    private String maNV;
    private String maQuyen;

    public QuanLyGUI(String maNV, String maQuyen) {
        this.maNV = maNV;
        this.maQuyen = maQuyen;
        this.setTitle("Quản trị");
        this.setSize(1280, 900);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("/images/ManagerUI/icon-app.png");
        this.setIconImage(icon);
        addControls();
        addEvents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JLabel btnDoiMatKhau;
    JPanel pnTitle, pnMenuLeft, pnCard, pnHoaDon, pnKhuyenMai, pnNhapHang, pnSanPham, pnNhanVien, pnKhachHang,
            pnThongKe, pnNguyenLieu;
    PnQuanLyHoaDonGUI hoaDonPanel;
    // PnQuanLyKhuyenMaiGUI khuyenMaiPanel;
    PnQuanLyNhapHangGUI nhapHangPanel;
    PnQuanLySanPhamGUI sanPhamPanel;
    PnQuanLyNhanVienGUI nhanVienPanel;
    PnQuanLyKhachHangGUI khachHangPanel;
    PnQuanLyThongKeGUI thongKePanel;
    PnQuanLyNguyenLieuGUI nguyenLieuPanel;

    JLabel btnClose, btnMinimize, btnMinisize, lblHoaDon, lblKhuyenMai, lblNhapHang, lblSanPham, lblNhanVien,
            lblKhachHang, lblThongKe, lblNguyenLieu;
    final Color clLeftItem = new Color(165, 165, 165);
    final Color clLeftItemHover = new Color(72, 88, 107);
    final Color clLeftItemSelected = new Color(58, 58, 58);
    ArrayList<JLabel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();
    
    private ImageIcon loadIcon(String name){
        return new ImageIcon(getClass().getResource("/images/ManagerUI/" + name));
    }
    
    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(80,80,80)));

        /*
         * ============================================================
         * TITLE BAR
         * ============================================================
         */
        /*
         * ============================================================
         * TITLE BAR
         * ============================================================
         */

        pnTitle = new JPanel(new BorderLayout());
        pnTitle.setPreferredSize(new Dimension(0, 46));
        pnTitle.setBackground(new Color(43, 45, 49));

        // ===== Nút đổi mật khẩu bên trái =====
        btnDoiMatKhau = new JLabel(this.loadIcon("icons8_gear_46px.png"));
        btnDoiMatKhau.setToolTipText("Đổi mật khẩu");
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // pnTitle.add(btnDoiMatKhau, BorderLayout.WEST);

        // ===== Title ở giữa =====
//        JLabel lblTitleText = new JLabel(this.loadIcon("title-text.png"));
//        lblTitleText.setHorizontalAlignment(JLabel.CENTER);
//        pnTitle.add(lblTitleText, BorderLayout.CENTER);

        // ===== Panel chứa nút bên phải =====
        JPanel pnRightButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        pnRightButton.setOpaque(false);

        btnMinimize = new JLabel(this.loadIcon("btn-minimize.png"));
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnMinisize = new JLabel(this.loadIcon("btn-minisize.png"));
        btnMinisize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnClose = new JLabel(this.loadIcon("btn-close.png"));
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnRightButton.add(btnMinimize);
        pnRightButton.add(btnMinisize);
        pnRightButton.add(btnClose);

        pnTitle.add(pnRightButton, BorderLayout.EAST);

       
        /*
         * ============================================================
         * SIDE BAR MENU
         * ============================================================
         */
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        JLabel lblAvatar = new JLabel(this.loadIcon("avt.png"), JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250, 210));
        pnMenuLeft.add(lblAvatar);

        lblKhuyenMai = new JLabel(this.loadIcon("lblKhuyenMai.png"));
        lblNhapHang = new JLabel(this.loadIcon("lblNhapHang.png"));
        lblSanPham = new JLabel(this.loadIcon("lblSanPham.png"));
        lblNhanVien = new JLabel(this.loadIcon("lblNhanVien.png"));
        lblKhachHang = new JLabel(this.loadIcon("lblKhachHang.png"));
        lblThongKe = new JLabel(this.loadIcon("lblThongKe.png"));
        lblHoaDon = new JLabel(this.loadIcon("lblHoaDon.png"));
        lblNguyenLieu = new JLabel(this.loadIcon("lblNguyenLieu.png"));

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lblSanPham);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblKhachHang);
        listMenuLeft.add(lblNhapHang);
        listMenuLeft.add(lblHoaDon);
        listMenuLeft.add(lblThongKe);
        listMenuLeft.add(lblKhuyenMai);
        listMenuLeft.add(lblNguyenLieu);

        for (JLabel lbl : listMenuLeft) {
            lbl.setVisible(true);
            lbl.setPreferredSize(new Dimension(250, 65));
            lbl.setOpaque(true);
            lbl.setBackground(clLeftItem);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
        }

        lblSanPham.setVisible(true);
        lblSanPham.setBackground(clLeftItemSelected);

        pnMain.add(pnMenuLeft, BorderLayout.WEST);

        /*
         * ============================================================
         * CARD PANEL
         * ============================================================
         */
        pnCard = new JPanel(cardMenuLeftGroup);

        //
        pnKhuyenMai = new JPanel();
        pnNhapHang = new JPanel();
        pnSanPham = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnThongKe = new JPanel();
        pnHoaDon = new JPanel();
        pnNguyenLieu = new JPanel();

        pnCard.add(pnSanPham, "2");
        pnCard.add(pnNhapHang, "3");
        pnCard.add(pnKhuyenMai, "4");
        pnCard.add(pnNhanVien, "5");
        pnCard.add(pnKhachHang, "6");
        pnCard.add(pnThongKe, "8");
        pnCard.add(pnHoaDon, "7");
        pnCard.add(pnNguyenLieu, "9");

        // ==========ADD PANEL BÁN HÀNG + KHUYẾN MÃI (Ko phân quyền)==========
        hoaDonPanel = new PnQuanLyHoaDonGUI();
        pnHoaDon.setLayout(new BorderLayout());
        pnHoaDon.add(hoaDonPanel, BorderLayout.CENTER);

        // khuyenMaiPanel = new PnQuanLyKhuyenMaiGUI();
        // pnKhuyenMai.setLayout(new BorderLayout());
        // pnKhuyenMai.add(khuyenMaiPanel, BorderLayout.CENTER);

         nguyenLieuPanel = new PnQuanLyNguyenLieuGUI();
         pnNguyenLieu.setLayout(new BorderLayout());
         pnNguyenLieu.add(nguyenLieuPanel, BorderLayout.CENTER);

        // ======XỬ LÝ PHÂN QUYỀN=======

        QuyenBUS phanQuyenBUS = new QuyenBUS();
        QuyenDTO quyen = phanQuyenBUS.getQuyenById(maQuyen);

        if (quyen.getNhapHang() == 1) {
            nhapHangPanel = new PnQuanLyNhapHangGUI(maNV);
            pnNhapHang.setLayout(new BorderLayout());
            pnNhapHang.add(nhapHangPanel, BorderLayout.CENTER);
            lblNhapHang.setVisible(true);
        }

        if (quyen.getQlSanPham() == 1) {
            sanPhamPanel = new PnQuanLySanPhamGUI();
            pnSanPham.setLayout(new BorderLayout());
            pnSanPham.add(sanPhamPanel, BorderLayout.CENTER);
            lblSanPham.setVisible(true);
        }

        if (quyen.getQlNhanVien() == 1) {
             nhanVienPanel = new PnQuanLyNhanVienGUI();
             pnNhanVien.setLayout(new BorderLayout());
             pnNhanVien.add(nhanVienPanel, BorderLayout.CENTER);
             lblNhanVien.setVisible(true);
        }

        if (quyen.getQlKhachHang() == 1) {
            khachHangPanel = new PnQuanLyKhachHangGUI();
            pnKhachHang.setLayout(new BorderLayout());
            pnKhachHang.add(khachHangPanel, BorderLayout.CENTER);
            lblKhachHang.setVisible(true);
        }

         if (quyen.getThongKe() == 1) {
            thongKePanel = new PnQuanLyThongKeGUI();
            pnThongKe.setLayout(new BorderLayout());
            pnThongKe.add(thongKePanel, BorderLayout.CENTER);
            lblThongKe.setVisible(true);
         }
        pnMain.add(pnCard);
        /*
         * ============================================================
         * CARD PANEL
         * ============================================================
         */
        con.add(pnMain);
    }

    int xMouse, yMouse;

    private void addEvents() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // new DlgDoiMatKhau().setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDoiMatKhau.setOpaque(true);
                btnDoiMatKhau.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDoiMatKhau.setOpaque(false);
                btnDoiMatKhau.setBackground(new Color(0, 0, 0, 0));
            }
        });

        btnMinimize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thuNhoFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setIcon(loadIcon("btn-minimize--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setIcon(loadIcon("btn-minimize.png"));
            }
        });

        btnMinisize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMiniSize();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinisize.setIcon(loadIcon("btn-minisize--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinisize.setIcon(loadIcon("btn-minisize.png"));
            }
        });

        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thoatChuongTrinh();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setIcon(loadIcon("btn-close--hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setIcon(loadIcon("btn-close.png"));
            }

        });

        for (JLabel lbl : listMenuLeft) {
            lbl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lblDisable : listMenuLeft) {
                        lblDisable.setBackground(clLeftItem);
                    }
                    lbl.setBackground(clLeftItemSelected);

                    // Xử lý lật trang theo menu
                    String cardName = "";
                    // if (lbl == lblBanHang) {
                    // cardName = "1";
                    if (lbl == lblSanPham) {
                        cardName = "2";
                    } else if (lbl == lblNhapHang) {
                        cardName = "3";
                    } else if (lbl == lblKhuyenMai) {
                        cardName = "4";
                    } else if (lbl == lblNhanVien) {
                        cardName = "5";
                    } else if (lbl == lblKhachHang) {
                        cardName = "6";
                    } else if (lbl == lblHoaDon) {
                        cardName = "7";
                    } else if (lbl == lblNguyenLieu) {
                        cardName = "9";
                    } else {
                        cardName = "8";
                    }
                    cardMenuLeftGroup.show(pnCard, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (lbl.getBackground().equals(clLeftItem)) {
                        lbl.setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (lbl.getBackground().equals(clLeftItemHover)) {
                        lbl.setBackground(clLeftItem);
                    }
                }
            });
        }

    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

   

    private void thoatChuongTrinh() {
        System.exit(0);
    }

    private boolean isMinisized = false;
    private Dimension originalSize;

    private void toggleMiniSize() {
        if (!isMinisized) {
            // Lưu kích thước ban đầu
            originalSize = getSize();

            // Thu nhỏ lại
            setSize(originalSize.width - 640, originalSize.height - 450);

            // Luôn ra giữa màn hình
            setLocationRelativeTo(null);

            isMinisized = true;
        } else {
            // Trả về kích thước ban đầu
            setSize(originalSize);

            // Luôn ra giữa màn hình
            setLocationRelativeTo(null);

            isMinisized = false;
        }
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
