package GUI.components;

import BUS.CartBUS;
import BUS.SanPhamBUS;
import Custom.RoundedButton;
import Custom.RoundedPanel;
import Custom.TopRoundedImageLabel;
import DTO.CartItemDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ItemCard extends RoundedPanel {
    double priceS,priceM,priceL;
    JRadioButton sizeS,sizeM,sizeL;
    private CartPanel cartPanel;
    private String code;
    private int stock;
    private JLabel lblStock;
    public ItemCard(String code, String name, int stock, String img, boolean hasSize, double basePrice, CartPanel cartPanel) {

        super(20);
        this.code = code;
        this.stock = stock;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,400));
        setBackground(Color.WHITE);
        
        this.cartPanel = cartPanel;
        // ===== FIX LOAD IMAGE =====
        String path = img;
        URL location = getClass().getResource(path);

        ImageIcon icon;

        if(location == null){
            System.out.println("Khong tim thay anh: " + path);

            // fallback icon (không crash)
            icon = new ImageIcon(
                    new BufferedImage(300,200,BufferedImage.TYPE_INT_ARGB)
            );

        }else{
            icon = new ImageIcon(
                    new ImageIcon(location)
                            .getImage()
                            .getScaledInstance(300,200,Image.SCALE_SMOOTH)
            );
        }

        RoundedPanel imgPanel = new RoundedPanel(20);
        imgPanel.setLayout(null);
        imgPanel.setPreferredSize(new Dimension(300,200));
        imgPanel.setBackground(Color.WHITE);

        TopRoundedImageLabel lblImg = new TopRoundedImageLabel(icon,8);
        lblImg.setBounds(0,0,300,200);

        RoundedPanel codePanel = new RoundedPanel(10);
        codePanel.setLayout(new BorderLayout());
        codePanel.setBackground(Color.decode("#E8E8E8"));
        codePanel.setBounds(8,8,65,30);
        
        RoundedPanel stockPanel = new RoundedPanel(10);
        stockPanel.setLayout(new BorderLayout());
        stockPanel.setBackground(Color.decode("#4CAF50"));
        stockPanel.setBounds(227,8,65,30); // góc phải

        this.lblStock = new JLabel("SL: " + this.stock); // số lượng tồn
        lblStock.setFont(new Font("Segoe UI",Font.BOLD,16));
        lblStock.setForeground(Color.WHITE);
        lblStock.setHorizontalAlignment(JLabel.CENTER);

        stockPanel.add(lblStock,BorderLayout.CENTER);
        
        JLabel lblCode = new JLabel(code);
        lblCode.setFont(new Font("Segoe UI", Font.BOLD,16));
        lblCode.setForeground(Color.decode("#D32F2F"));
        lblCode.setHorizontalAlignment(JLabel.CENTER);

        codePanel.add(lblCode,BorderLayout.CENTER);

        imgPanel.add(codePanel);
        imgPanel.add(stockPanel);
        imgPanel.add(lblImg);

        JLabel lblName = new JLabel(
                "<html><div style='text-align:center;width:240;'>" + name + "</div></html>"
        );

        lblName.setFont(new Font("Segoe UI",Font.BOLD,24));
        lblName.setForeground(Color.decode("#0078AE"));
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setOpaque(true);
        lblName.setBackground(Color.WHITE);

        JPanel infoItem = new JPanel(new BorderLayout());
        infoItem.setBackground(Color.WHITE);
        infoItem.add(lblName,BorderLayout.NORTH);

        // ===== chỉ pizza mới có size =====
        
        
        if(hasSize){

            JPanel sizePanel = new JPanel(new GridLayout(3,1));
            sizePanel.setBackground(Color.WHITE);

            priceS = basePrice;
            priceM = basePrice + 30000;
            priceL = basePrice + 50000;

            sizePanel.setBackground(Color.WHITE);

            sizeS = new JRadioButton("Size S - " + format(priceS));
            sizeM = new JRadioButton("Size M - " + format(priceM));
            sizeL = new JRadioButton("Size L - " + format(priceL));

            sizeS.setBackground(Color.WHITE);
            sizeM.setBackground(Color.WHITE);
            sizeL.setBackground(Color.WHITE);

            sizeS.setHorizontalAlignment(JRadioButton.CENTER);
            sizeM.setHorizontalAlignment(JRadioButton.CENTER);
            sizeL.setHorizontalAlignment(JRadioButton.CENTER);

            ButtonGroup group = new ButtonGroup();
            group.add(sizeS);
            group.add(sizeM);
            group.add(sizeL);

            sizeM.setSelected(true);

            sizePanel.add(sizeS);
            sizePanel.add(sizeM);
            sizePanel.add(sizeL);

            infoItem.add(sizePanel,BorderLayout.CENTER);
        }

        RoundedButton btnAdd = new RoundedButton(5);
        btnAdd.setText("THÊM");
        btnAdd.setBackground(Color.decode("#E31837"));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI",Font.BOLD,20));
        btnAdd.setPreferredSize(new Dimension(145,30));

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.add(btnAdd);

        add(imgPanel,BorderLayout.NORTH);
        add(infoItem,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);

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
        
        
        
        btnAdd.addActionListener(e -> {

            int inCart = CartBUS.getQuantityByMaSP(code);

            if(inCart >= stock){
                JOptionPane.showMessageDialog(null,"Sản phẩm đã hết!");
                return;
            }

            String size="";
            double price=basePrice;

            if(sizeM != null){
                if(sizeS.isSelected()){
                    size="S"; price=priceS;
                }else if(sizeM.isSelected()){
                    size="M"; price=priceM;
                }else{
                    size="L"; price=priceL;
                }
            }

            CartItemDTO dto = new CartItemDTO(code,name,size,price,img);

            CartBUS.addToCart(dto);

            cartPanel.reloadCart();
            cartPanel.updateAllStocks();
        });
    }
    private String format(double price){
        return String.format("%,.0fđ", price);
    }
    public void updateStock(){

        // lấy lại số lượng mới từ database
        SanPhamBUS bus = new BUS.SanPhamBUS();
        DTO.SanPhamDTO sp = bus.getSanPhamById(this.code);

        if(sp != null){
            this.stock = sp.getSoLuong();
        }

        int inCart = CartBUS.getQuantityByMaSP(this.code);

        int remain = this.stock - inCart;

        if(remain < 0) remain = 0;

        this.lblStock.setText("SL: " + remain);
    }
}