package GUI.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

public class LichSuPanel extends JPanel {

    private JPanel listPanel;
    private JTable tableChiTiet;

    private JLabel lblMaHD;
    private JLabel lblMaKH;
    private JLabel lblNhanVien;
    private JLabel lblNgayLap;
    private JLabel lblTongTien;

    private JTextField txtSearch;
    private JTextField txtGiaTu;
    private JTextField txtGiaDen;

    private JDateChooser dateTu;
    private JDateChooser dateDen;

    public LichSuPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(31,33,37));

        add(createFilterPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    // =========================================================
    // FILTER PANEL
    // =========================================================
    private JPanel createFilterPanel(){

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(31,33,37));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(new Color(31,33,37));

        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,120)); 
        // tạo khoảng cách bên phải để không dính panel chi tiết

        panel.add(createSearchRow());
        panel.add(Box.createVerticalStrut(10));
        panel.add(createPriceRow());
        panel.add(Box.createVerticalStrut(10));
        panel.add(createDateRow());

        wrapper.add(panel,BorderLayout.WEST);

        return wrapper;
    }

    // =========================================================
    // SEARCH ROW
    // =========================================================
    private JPanel createSearchRow(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        panel.setBackground(new Color(31,33,37));

        JLabel lblSearch = new JLabel("Tìm:");
        lblSearch.setForeground(Color.WHITE);

        txtSearch = new JTextField(25);

        panel.add(lblSearch);
        panel.add(txtSearch);

        return panel;
    }

    // =========================================================
    // PRICE ROW
    // =========================================================
    private JPanel createPriceRow(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        panel.setBackground(new Color(31,33,37));

        JLabel lblGiaTu = new JLabel("Giá từ:");
        lblGiaTu.setForeground(Color.WHITE);

        JLabel lblDen = new JLabel("đến");
        lblDen.setForeground(Color.WHITE);

        txtGiaTu = new JTextField(8);
        txtGiaDen = new JTextField(8);

        panel.add(lblGiaTu);
        panel.add(txtGiaTu);
        panel.add(lblDen);
        panel.add(txtGiaDen);

        return panel;
    }

    // =========================================================
    // DATE ROW
    // =========================================================
    private JPanel createDateRow(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        panel.setBackground(new Color(31,33,37));

        JLabel lblNgayTu = new JLabel("Ngày từ:");
        lblNgayTu.setForeground(Color.WHITE);

        JLabel lblDen = new JLabel("đến");
        lblDen.setForeground(Color.WHITE);

        dateTu = new JDateChooser();
        dateDen = new JDateChooser();

        dateTu.setPreferredSize(new Dimension(120,25));
        dateDen.setPreferredSize(new Dimension(120,25));

        panel.add(lblNgayTu);
        panel.add(dateTu);
        panel.add(lblDen);
        panel.add(dateDen);

        return panel;
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================
    private JPanel createContentPanel(){

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(31,33,37));

        listPanel = createListPanel();
        JPanel detailPanel = createDetailPanel();

        container.add(listPanel,BorderLayout.CENTER);
        container.add(detailPanel,BorderLayout.EAST);

        return container;
    }

    // =========================================================
    // LIST PANEL (4 CARD / ROW)
    // =========================================================
    private JPanel createListPanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(31,33,37));

        JPanel cards = new JPanel(new GridLayout(0,4,20,20));
        cards.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        cards.setBackground(new Color(31,33,37));

        JScrollPane scroll = new JScrollPane(cards);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(31,33,37));

        panel.add(scroll,BorderLayout.CENTER);

        // DEMO
        cards.add(new HoaDonCard("HD001","10/03/2026",450000));
        cards.add(new HoaDonCard("HD002","10/03/2026",320000));
        cards.add(new HoaDonCard("HD003","09/03/2026",780000));
        cards.add(new HoaDonCard("HD004","08/03/2026",210000));
        cards.add(new HoaDonCard("HD005","07/03/2026",150000));
        cards.add(new HoaDonCard("HD006","06/03/2026",560000));
        cards.add(new HoaDonCard("HD007","05/03/2026",920000));
        cards.add(new HoaDonCard("HD008","04/03/2026",110000));

        return panel;
    }

    // =========================================================
    // DETAIL PANEL
    // =========================================================
    private JPanel createDetailPanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(420,0));
        panel.setBackground(new Color(45,47,52));

        panel.add(createThongTinPanel(),BorderLayout.NORTH);
        panel.add(createTablePanel(),BorderLayout.CENTER);
        panel.add(createTongPanel(),BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================
    // THÔNG TIN HÓA ĐƠN
    // =========================================================
    private JPanel createThongTinPanel(){

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        javax.swing.border.TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Thông tin hóa đơn"
        );

        border.setTitleColor(Color.WHITE); // đổi màu chữ title

        panel.setBorder(border);

        panel.setBackground(new Color(45,47,52));

        panel.add(createLabel("Mã HD"));
        lblMaHD = createValue("HD001");
        panel.add(lblMaHD);

        panel.add(createLabel("Mã KH"));
        lblMaKH = createValue("KH01");
        panel.add(lblMaKH);

        panel.add(createLabel("NV lập"));
        lblNhanVien = createValue("NV01");
        panel.add(lblNhanVien);

        panel.add(createLabel("Ngày lập"));
        lblNgayLap = createValue("10/03/2026");
        panel.add(lblNgayLap);

        return panel;
    }

    // =========================================================
    // TABLE
    // =========================================================
    private JPanel createTablePanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(45,47,52));

        String[] cols = {
                "Mã SP",
                "Tên sản phẩm",
                "Số lượng",
                "Đơn giá",
                "Thành tiền"
        };

        DefaultTableModel model = new DefaultTableModel(cols,0);

        tableChiTiet = new JTable(model);
        tableChiTiet.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tableChiTiet);

        panel.add(scroll,BorderLayout.CENTER);

        model.addRow(new Object[]{"SP01","Pizza bò nướng tiêu đen",2,119000,238000});
        model.addRow(new Object[]{"SP02","Pizza dăm bông",1,239000,239000});
        model.addRow(new Object[]{"SP03","Pizza xúc xích",4,19000,76000});

        return panel;
    }

    // =========================================================
    // TOTAL PANEL
    // =========================================================
    private JPanel createTongPanel(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(new Color(45,47,52));

        JLabel lbl = new JLabel("Tổng tiền: ");
        lbl.setForeground(Color.WHITE);

        lblTongTien = new JLabel("482,400 đ");
        lblTongTien.setForeground(Color.ORANGE);
        lblTongTien.setFont(new Font("Segoe UI",Font.BOLD,16));

        panel.add(lbl);
        panel.add(lblTongTien);

        return panel;
    }

    // =========================================================
    // HELPER
    // =========================================================
    private JLabel createLabel(String text){

        JLabel lbl = new JLabel(text+":");
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JLabel createValue(String text){

        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.ORANGE);
        return lbl;
    }
}