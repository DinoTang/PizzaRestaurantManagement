package GUI.components;
import java.util.List;
import BUS.LoaiBUS;
import BUS.SanPhamBUS;
import Custom.RoundedPanel;
import Custom.WrapLayout;
import DTO.LoaiDTO;
import DTO.SanPhamDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MenuFoodPanel extends RoundedPanel {
    private JComboBox<LoaiDTO> cboLoai;
    private JTextField txtSearch;
    private JPanel menuContainer;
    private CartPanel cartPanel;
    
    public MenuFoodPanel(CartPanel cartPanel){
        super(40,Color.decode("#3A3A3A"));
        this.initComponents();
        this.cartPanel = cartPanel;
        this.addControls();
        this.addEvents();
    }
     private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#1F2125"));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screen.width * 2.0/3);
        this.setPreferredSize(new Dimension(width,0));
    }
     private void addControls(){
        this.createMenuHeader();
        this.createMenuList();
    }
    
    private void addEvents(){
        this.cboLoai.addActionListener(e -> {
            loadMenuFood();
        });
        
        this.txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                loadMenuFood();
            }
        });
        
        this.txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Tìm kiếm...")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Tìm kiếm...");
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });
    }
    private void createMenuHeader(){

        JPanel menuHeader = new JPanel();
        menuHeader.setPreferredSize(new Dimension(0, 100));
        menuHeader.setBackground(Color.decode("#3A3A3A"));
        menuHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        menuHeader.setOpaque(false);
        this.add(menuHeader, BorderLayout.NORTH);

        RoundedPanel filterPanel = new RoundedPanel(10, Color.decode("#FF8C00"));
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,8));
        filterPanel.setBackground(Color.decode("#1F2125"));
        filterPanel.setPreferredSize(new Dimension(240,45));

        JLabel lblLoai = new JLabel("Loại :");
        lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblLoai.setForeground(Color.WHITE);

        // khởi tạo combobox
        cboLoai = new JComboBox<>();

        cboLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoai.setPreferredSize(new Dimension(160,28));
        cboLoai.setBackground(Color.decode("#E6E6E6"));
        cboLoai.setBorder(null);

        filterPanel.add(lblLoai);
        filterPanel.add(cboLoai);

        menuHeader.add(filterPanel);

        // load dữ liệu từ database
        loadLoaiToComboBox();

        RoundedPanel searchPanel = new RoundedPanel(10, Color.decode("#3A3A3A"));
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,8));
        searchPanel.setBackground(Color.decode("#2A2D31"));
        searchPanel.setPreferredSize(new Dimension(600,40));

        JLabel iconSearch = new JLabel(
                new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/search.png"))
                                .getImage().getScaledInstance(18,18,Image.SCALE_SMOOTH)
                )
        );

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(400,25));
        txtSearch.setBorder(null);
        txtSearch.setBackground(Color.decode("#2A2D31"));
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setCaretColor(Color.WHITE);
        txtSearch.setText("Tìm kiếm...");
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        searchPanel.add(iconSearch);
        searchPanel.add(txtSearch);

        menuHeader.add(searchPanel);
    }

    private void createMenuList(){

        this.menuContainer = new JPanel();
        this.menuContainer.setLayout(new WrapLayout(FlowLayout.LEFT,20,10));
        this.menuContainer.setBackground(Color.decode("#383C42"));
        cartPanel.setMenuPanel(menuContainer);
        
        this.loadMenuFood();

        JScrollPane scroll = new JScrollPane(this.menuContainer);

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        this.add(scroll, BorderLayout.CENTER);
    }
    private void loadLoaiToComboBox(){

        LoaiBUS loaiBUS = new LoaiBUS();

        List<LoaiDTO> dsLoai = loaiBUS.getAllLoai();

        for(LoaiDTO loai : dsLoai){
            cboLoai.addItem(loai);
        }

    }
    private void loadMenuFood(){

        this.menuContainer.removeAll();

        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        List<SanPhamDTO> dsSanPham = sanPhamBUS.getAllSanPham();

        LoaiDTO loaiChon = (LoaiDTO) this.cboLoai.getSelectedItem();
        String keyword = this.txtSearch.getText().trim().toLowerCase();

        for(SanPhamDTO sp : dsSanPham){

            // ===== lọc theo loại =====
            if(loaiChon != null 
                && !loaiChon.getMaLoai().equals("L00") 
                && !sp.getMaLoai().equals(loaiChon.getMaLoai())){
                continue;
            }

            // ===== lọc theo search =====
            if(!keyword.equals("") && !keyword.equals("tìm kiếm...")){
                if(!sp.getTenSP().toLowerCase().contains(keyword)){
                    continue;
                }
            }

            // ===== chỉ lấy ảnh trong 1 folder =====
            String imagePath = "/images/SanPham/" + sp.getHinhAnh();

            ItemCard card;

            if(sp.getMaLoai().equals("L01")){
                card = new ItemCard(
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    imagePath,
                    true,
                    sp.getDonGia(),
                    this.cartPanel
                );
            }else{
                card = new ItemCard(
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    imagePath,
                    false,
                    sp.getDonGia(),
                    this.cartPanel
                );
            }

            this.menuContainer.add(card);
        }

        this.menuContainer.revalidate();
        this.menuContainer.repaint();
    }
}