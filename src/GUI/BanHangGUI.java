package GUI;

import Custom.RoundedButton;
import Custom.RoundedPanel;
import Custom.TopRoundedImageLabel;
import Custom.WrapLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author quock
 */
public class BanHangGUI extends javax.swing.JFrame {
    
    private JLabel exitIcon;
    private JTextField txtSearch;
    private RoundedButton btnPay;
    /**
     * Creates new form BanHangGUI
     */
    public BanHangGUI() {
        initComponents();
        this.setTitle("Bán Hàng");
        this.setLocationRelativeTo(null);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.addControls();
        this.addEvents();
    }

    private void addControls(){
        this.headerPanel.setPreferredSize(new Dimension(0,100));
        
        this.exitPanel.setLayout(new FlowLayout(java.awt.FlowLayout.LEFT, 20, 15));

        this.exitIcon = new JLabel(
            new ImageIcon(
                new ImageIcon(getClass().getResource("/images/ExitIcon.png"))
                .getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)
            )
        );
        exitIcon.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        
        this.exitPanel.add(exitIcon);
       
        RoundedPanel menuFoodPanel = new RoundedPanel(40, Color.decode("#3A3A3A"));
        menuFoodPanel.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 2.0 / 3);
        menuFoodPanel.setPreferredSize(new Dimension(width, 0));
        menuFoodPanel.setBackground(Color.decode("#1F2125"));
        container.add(menuFoodPanel, BorderLayout.LINE_START);
        
        
        
        JPanel menuHeader = new JPanel();
        menuHeader.setPreferredSize(new Dimension(0, 100));
        menuHeader.setBackground(Color.decode("#3A3A3A"));
        menuHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        menuHeader.setOpaque(false);
        menuFoodPanel.add(menuHeader, BorderLayout.NORTH);
        
        RoundedPanel filterPanel = new RoundedPanel(10, Color.decode("#FF8C00"));
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,8));
        filterPanel.setBackground(Color.decode("#1F2125"));
        filterPanel.setPreferredSize(new Dimension(240,45));
        
        JLabel lblLoai = new JLabel("Loại :");
        lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblLoai.setForeground(Color.WHITE);
        String[] loai = {"Pizza","Mì Ý","Đồ uống"};

        JComboBox<String> cboLoai = new JComboBox<>(loai);
        cboLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoai.setPreferredSize(new Dimension(160,28));
        cboLoai.setBackground(Color.decode("#E6E6E6"));
        cboLoai.setBorder(null);
        filterPanel.add(lblLoai);
        filterPanel.add(cboLoai);
        menuHeader.add(filterPanel);

        RoundedPanel searchPanel = new RoundedPanel(10, Color.decode("#3A3A3A"));
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,8));
        searchPanel.setBackground(Color.decode("#2A2D31"));
        searchPanel.setPreferredSize(new Dimension(600,40));
        JLabel iconSearch = new JLabel(
                new ImageIcon(
                        new ImageIcon(getClass().getResource("/images/search.png"))
                                .getImage().getScaledInstance(18,18,java.awt.Image.SCALE_SMOOTH)
                )
        );
        this.txtSearch = new JTextField();
        this.txtSearch.setPreferredSize(new Dimension(400,25));

        this.txtSearch.setBorder(null);
        this.txtSearch.setBackground(Color.decode("#2A2D31"));
        this.txtSearch.setForeground(Color.GRAY);
        this.txtSearch.setCaretColor(Color.WHITE);
        this.txtSearch.setText("Tìm kiếm...");
        this.txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        searchPanel.add(iconSearch);
        searchPanel.add(txtSearch);
        menuHeader.add(searchPanel);
        

        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new WrapLayout(FlowLayout.LEFT,20,10));
//        menuContainer.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        menuContainer.setBackground(Color.decode("#383C42"));
        menuContainer.add(createItemCard(
                "P001",
                "Pizza Rau Củ Thập Cẩm",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));

        menuContainer.add(createItemCard(
                "P002",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));

        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        menuContainer.add(createItemCard(
                "P003",
                "Pizza Hải Sản Xốt Mayonnaise",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        JScrollPane scroll = new JScrollPane(menuContainer);

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        menuFoodPanel.add(scroll, BorderLayout.CENTER);
        
        RoundedPanel cartPanel = new RoundedPanel(40);
        width = (int)(screenSize.width * 1.0 / 3);
        cartPanel.setPreferredSize(new Dimension(width, 0));
        container.add(cartPanel, BorderLayout.LINE_END);
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBackground(Color.decode("#2A2D31"));


        // ===== Header =====
        JLabel lblCartTitle = new JLabel("Thông tin hóa đơn:");
        lblCartTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblCartTitle.setForeground(Color.WHITE);

        JPanel cartHeader = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        cartHeader.setOpaque(false);
        cartHeader.add(lblCartTitle);

        cartPanel.add(cartHeader, BorderLayout.NORTH);


        // ===== Container =====
        JPanel cartContainer = new JPanel();
        cartContainer.setLayout(new BorderLayout());
        cartContainer.setBackground(Color.WHITE);
        cartContainer.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        cartPanel.add(cartContainer, BorderLayout.CENTER);


        // ===== List item =====
        JPanel cartList = new JPanel();
        cartList.setLayout(new WrapLayout(FlowLayout.CENTER,10,10));
        cartList.setBackground(Color.WHITE);

        JScrollPane cartScroll = new JScrollPane(cartList);
        cartScroll.setBorder(null);
        cartScroll.getVerticalScrollBar().setUnitIncrement(16);

        cartContainer.add(cartScroll, BorderLayout.CENTER);


        // ===== Test item =====
        cartList.add(createCartItem(
                "Pizza Phô Mai Truyền Thống",
                "155,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));

        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        cartList.add(createCartItem(
                "Pizza Tôm & Bò Xốt Parmesan",
                "225,000đ",
                "/images/menu/pizza/Pizza Hải Sản Xốt Mayonnaise.jpg"
        ));
        JPanel cartBottom = new JPanel(new BorderLayout());
        cartBottom.setBackground(Color.WHITE);
        cartBottom.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));

        JLabel lblTotal = new JLabel("Tổng");
        lblTotal.setFont(new Font("Segoe UI",Font.BOLD,18));

        JLabel lblPriceTotal = new JLabel("380,000đ");
        lblPriceTotal.setFont(new Font("Segoe UI",Font.BOLD,20));

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(Color.WHITE);
        totalPanel.add(lblTotal,BorderLayout.WEST);
        totalPanel.add(lblPriceTotal,BorderLayout.EAST);

        this.btnPay = new RoundedButton(10);
        this.btnPay.setText("THANH TOÁN");
        this.btnPay.setBackground(Color.decode("#FF8C00"));
        this.btnPay.setForeground(Color.WHITE);
        this.btnPay.setFont(new Font("Segoe UI",Font.BOLD,18));
        this.btnPay.setPreferredSize(new Dimension(500,50));

        JPanel payPanel = new JPanel();
        payPanel.setBackground(Color.WHITE);
        payPanel.setLayout(new BorderLayout());
        payPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        payPanel.add(btnPay, BorderLayout.CENTER);

        cartBottom.add(totalPanel,BorderLayout.NORTH);
        cartBottom.add(payPanel,BorderLayout.CENTER);

        cartContainer.add(cartBottom,BorderLayout.SOUTH);
    }
    
    private void addEvents(){
        this.exitIcon.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
//            Utils.WindowUtil.showWindow(new DashboardGUI());
            dispose();
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
        
        
        Color payNormal = Color.decode("#FF8C00");
        Color payClick = payNormal.darker();

        btnPay.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPay.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPay.setBackground(payClick);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPay.setBackground(payNormal);
            }
        });
    }
    
    private JPanel createItemCard(String code,String name,String img){
        RoundedPanel card = new RoundedPanel(20);
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(300,400));
        card.setBackground(Color.WHITE);
        
        ImageIcon icon = new ImageIcon(
            new ImageIcon(getClass().getResource(img))
            .getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH)
        );

        RoundedPanel imgPanel = new RoundedPanel(20);
        imgPanel.setLayout(null);
        imgPanel.setPreferredSize(new Dimension(300,200));
        imgPanel.setBackground(Color.WHITE);

        TopRoundedImageLabel lblImg = new TopRoundedImageLabel(icon,8);
        lblImg.setBounds(0,0,300,200);
        
        
        RoundedPanel codePanel = new RoundedPanel(10);
        codePanel.setLayout(new BorderLayout());
        codePanel.setBackground(Color.WHITE);
        codePanel.setBounds(8,8,65,30); // xích trái + lên

        JLabel lblCode = new JLabel(code);
        lblCode.setFont(new Font("Segoe UI", Font.BOLD,16));
        lblCode.setForeground(Color.decode("#D32F2F"));
        lblCode.setHorizontalAlignment(JLabel.CENTER);

        codePanel.add(lblCode, BorderLayout.CENTER);
        imgPanel.add(codePanel);
        imgPanel.add(lblImg);

        JLabel lblName = new JLabel(
        "<html><div style='text-align:center;width:240;'>" + name + "</div></html>"
);
        lblName.setFont(new Font("Segoe UI",Font.BOLD,28));
        lblName.setForeground(Color.decode("#0078AE"));
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setBackground(Color.WHITE);
        lblName.setOpaque(true);
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new GridLayout(3,1));
        sizePanel.setBackground(Color.WHITE);

        JRadioButton sizeS = new JRadioButton("Size S - 85,000đ");
        JRadioButton sizeM = new JRadioButton("Size M - 155,000đ");
        JRadioButton sizeL = new JRadioButton("Size L - 225,000đ");
        
        sizeS.setBackground(Color.WHITE);
        sizeM.setBackground(Color.WHITE);
        sizeL.setBackground(Color.WHITE);

        sizeS.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sizeM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sizeL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sizeS.setHorizontalAlignment(JRadioButton.CENTER);
        sizeM.setHorizontalAlignment(JRadioButton.CENTER);
        sizeL.setHorizontalAlignment(JRadioButton.CENTER);
        ButtonGroup group = new ButtonGroup();
        group.add(sizeS);
        group.add(sizeM);
        group.add(sizeL);

        sizeM.setSelected(true); // mặc định chọn size M

        sizePanel.add(sizeS);
        sizePanel.add(sizeM);
        sizePanel.add(sizeL);
        
        JPanel infoItem = new JPanel();
        infoItem.setLayout(new BorderLayout());
        infoItem.add(lblName,BorderLayout.NORTH);
        infoItem.add(sizePanel,BorderLayout.CENTER);
        
        RoundedButton btnAdd = new RoundedButton(5); 
        btnAdd.setText("THÊM");
        btnAdd.setBackground(Color.decode("#E31837"));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI",Font.BOLD,20));
        btnAdd.setPreferredSize(new Dimension(145,30));
        
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.add(btnAdd);
        
        card.add(imgPanel,BorderLayout.NORTH);
        card.add(infoItem,BorderLayout.CENTER);
        card.add(bottom,BorderLayout.SOUTH);
        
        Color normal = Color.decode("#E31837");
        Color pressed = normal.darker();

        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                btnAdd.setBackground(pressed);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                btnAdd.setBackground(normal);
            }

        });
        return card;
    }
    private JPanel createCartItem(String name,String price,String img){

        RoundedPanel item = new RoundedPanel(15);
        item.setLayout(new BorderLayout());
        item.setPreferredSize(new Dimension(450,100));
        item.setBackground(Color.decode("#F9F9F9"));
        item.setBorder(BorderFactory.createEmptyBorder(0,15,0,15)); // padding trong item
        
        JPanel info = new JPanel(new GridLayout(4,1));
        info.setOpaque(false);

        JLabel lblName = new JLabel(name);
        lblName.setFont(new Font("Segoe UI",Font.BOLD,14));

        JLabel lblSize = new JLabel("Size M");

        // panel chỉnh sửa
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        actionPanel.setOpaque(false);

        JLabel lblEdit = new JLabel("Điều chỉnh");
        lblEdit.setForeground(new Color(0,120,215));

        JLabel lblDelete = new JLabel("Xóa");
        lblDelete.setForeground(Color.RED);

        actionPanel.add(lblEdit);
        actionPanel.add(lblDelete);

        // quantity
        JPanel quantity = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        quantity.setOpaque(false);

       JButton btnMinus = new JButton("-");
        btnMinus.setPreferredSize(new Dimension(35,25));
        btnMinus.setMargin(new Insets(0,0,0,0));
        btnMinus.setFocusPainted(false);

        JButton btnPlus = new JButton("+");
        btnPlus.setPreferredSize(new Dimension(35,25));
        btnPlus.setMargin(new Insets(0,0,0,0));
        btnPlus.setFocusPainted(false);

        JLabel lblQty = new JLabel("1");
        lblQty.setPreferredSize(new Dimension(20,25));
        lblQty.setHorizontalAlignment(SwingConstants.CENTER);

        quantity.add(btnMinus);
        quantity.add(lblQty);
        quantity.add(btnPlus);

        info.add(lblName);
        info.add(lblSize);
        info.add(actionPanel);
        info.add(quantity);

        JLabel lblPrice = new JLabel(price);
        lblPrice.setFont(new Font("Segoe UI",Font.BOLD,14));

        ImageIcon icon = new ImageIcon(
            new ImageIcon(getClass().getResource(img))
            .getImage().getScaledInstance(80,60,Image.SCALE_SMOOTH)
        );

        JLabel imgLabel = new JLabel(icon);

        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(false);
        right.add(lblPrice,BorderLayout.NORTH);
        right.add(imgLabel,BorderLayout.CENTER);

        item.add(info,BorderLayout.CENTER);
        item.add(right,BorderLayout.EAST);

        // sự kiện xóa
        lblDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Container parent = item.getParent();
                parent.remove(item);
                parent.revalidate();
                parent.repaint();
            }
        });

        return item;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        exitPanel = new javax.swing.JPanel();
        container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerPanel.setBackground(new java.awt.Color(31, 33, 37));
        headerPanel.setPreferredSize(new java.awt.Dimension(950, 50));
        headerPanel.setLayout(new java.awt.BorderLayout());

        exitPanel.setBackground(new java.awt.Color(31, 33, 37));

        javax.swing.GroupLayout exitPanelLayout = new javax.swing.GroupLayout(exitPanel);
        exitPanel.setLayout(exitPanelLayout);
        exitPanelLayout.setHorizontalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        exitPanelLayout.setVerticalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        headerPanel.add(exitPanel, java.awt.BorderLayout.LINE_END);

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        container.setBackground(new java.awt.Color(31, 33, 37));
        container.setLayout(new java.awt.BorderLayout());
        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BanHangGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JPanel exitPanel;
    private javax.swing.JPanel headerPanel;
    // End of variables declaration//GEN-END:variables
}
