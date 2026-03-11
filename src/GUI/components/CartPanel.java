package GUI.components;

import BUS.CartBUS;
import DTO.CartItemDTO;
import Custom.RoundedButton;
import Custom.RoundedPanel;
import Custom.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends RoundedPanel {
    private JPanel menuPanel;
    private RoundedButton btnPay;
    private JPanel cartList;
    private JLabel lblPriceTotal;
    private String maNV;
    public CartPanel(String maNV){

        super(40);
        this.maNV = maNV;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#2A2D31"));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screen.width * 1.0/3);
        setPreferredSize(new Dimension(width,0));

        this.addControls();
        this.addEvents();
    }
    public void setMenuPanel(JPanel menuPanel){
        this.menuPanel = menuPanel;
    }
    private void addControls(){
        this.createHeader();
        this.createCartList();
        this.createBottom();
    }
    private void addEvents(){
        Color payNormal = Color.decode("#FF8C00");
        Color payClick = payNormal.darker();

        this.btnPay.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.btnPay.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPay.setBackground(payClick);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPay.setBackground(payNormal);
            }
        });
        this.btnPay.addActionListener(e -> {

            if(CartBUS.getCart().isEmpty()){
                JOptionPane.showMessageDialog(
                        CartPanel.this,
                        "Vui lòng thêm món trước khi thanh toán!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Window parent = SwingUtilities.getWindowAncestor(CartPanel.this);
            PaymentDlg dialog = new PaymentDlg(parent, CartBUS.getCart(), maNV);

            dialog.setVisible(true);
            
            reloadCart();
            updateTotal();
            updateAllStocks();
        });
    }
    private void createHeader(){

        JLabel title = new JLabel("Thông tin hóa đơn:");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        title.setForeground(Color.WHITE);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        header.setOpaque(false);
        header.add(title);

        this.add(header, BorderLayout.NORTH);
    }

    private void createCartList(){

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        this.add(container, BorderLayout.CENTER);

        cartList = new JPanel();
        cartList.setLayout(new WrapLayout(FlowLayout.CENTER,10,10));
        cartList.setBackground(Color.WHITE);

        JScrollPane cartScroll = new JScrollPane(cartList);
        cartScroll.setBorder(null);
        cartScroll.getVerticalScrollBar().setUnitIncrement(16);

        container.add(cartScroll, BorderLayout.CENTER);
    }

    private void createBottom(){

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));

        JLabel lblTotal = new JLabel("Tổng");
        lblTotal.setFont(new Font("Segoe UI",Font.BOLD,18));

        lblPriceTotal = new JLabel("0đ");
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
        payPanel.add(this.btnPay, BorderLayout.CENTER);

        bottom.add(totalPanel,BorderLayout.NORTH);
        bottom.add(payPanel,BorderLayout.CENTER);

        this.add(bottom,BorderLayout.SOUTH);
    }
    public void addItemToCart(CartItem item){

        cartList.add(item);
        
        cartList.revalidate();
        cartList.repaint();
    }
    public void reloadCart(){

        cartList.removeAll();

        for(CartItemDTO item : CartBUS.getCart()){

            CartItem guiItem = new CartItem(
                    item.getMaSP(),
                    item.getTenSP(),
                    item.getSize(),
                    item.getGia(),
                    item.getImg(),
                    item.getSoLuong(),
                    this
            );

            cartList.add(guiItem);
        }
        this.updateTotal();
        cartList.revalidate();
        cartList.repaint();
    }
    public void updateTotal(){

        double total = CartBUS.getTotalPrice();

        lblPriceTotal.setText(String.format("%,.0fđ", total));
    }
    public void updateAllStocks(){

        for(Component c : menuPanel.getComponents()){
            if(c instanceof ItemCard){
                ((ItemCard)c).updateStock();
            }
        }

    }
}