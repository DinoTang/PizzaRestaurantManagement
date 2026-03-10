package GUI.components;

import BUS.GiamGiaBUS;
import DTO.CartItemDTO;
import DTO.GiamGiaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;

public class PaymentDlg extends JDialog {

    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTempTotal;
    private JLabel lblCustomerPay;
    private JLabel lblChange;
    private JTextField txtCustomerPay;
    private JButton btnPay;
    private double totalPrice;

    // thêm biến này để lưu tiền sau khi giảm
    private double finalPrice;

    private JComboBox<GiamGiaDTO> cboKhuyenMai;
    private JComboBox<String> cboPaymentMethod;
    private JLabel lblPhanTramGiam;
    private JLabel lblTienGiam;
    private JLabel lblKhachCanTra;
    private boolean isFormatting = false;
    public PaymentDlg(Window parent, List<CartItemDTO> cartList) {

        super(parent,"Thanh toán",ModalityType.APPLICATION_MODAL);

        calculateTotal(cartList);

        // mặc định giá cuối = tổng tiền
        finalPrice = totalPrice;

        setSize(1100,600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));
        main.setBackground(new Color(245,245,245));

        add(main);

        createHeader(main);
        createCenter(main);
        createBottom(main);

        loadCartToTable(cartList);

        txtCustomerPay.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                formatMoney();
                calculateChange();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                formatMoney();
                calculateChange();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calculateChange();
            }
        });

        this.loadKhuyenMai();

        cboKhuyenMai.addActionListener(e -> {
            tinhKhuyenMai();
            calculateChange();
        });
        btnPay.addActionListener(e -> {

            String method = (String) cboPaymentMethod.getSelectedItem();

            if(method.equals("Tiền mặt")){

                String text = txtCustomerPay.getText().replace(",", "");

                if(text.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Vui lòng nhập tiền khách đưa!");
                    return;
                }

                double pay = Double.parseDouble(text);

                if(pay < finalPrice){
                    JOptionPane.showMessageDialog(this,"Khách đưa chưa đủ tiền!");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this,"Thanh toán thành công!");
            dispose();
        });
    }

    private void createHeader(JPanel main){

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header,BoxLayout.Y_AXIS));
        header.setBackground(new Color(245,245,245));

        JLabel title = new JLabel("Thông tin thanh toán");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));

        JLabel maHD = new JLabel("Mã hóa đơn: HD001");
        maHD.setFont(new Font("Segoe UI",Font.BOLD,15));
        maHD.setForeground(new Color(0,70,255));

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(maHD);

        main.add(header,BorderLayout.NORTH);
    }

    private void createCenter(JPanel main){

        JPanel center = new JPanel(new BorderLayout(20,0));
        center.setBackground(new Color(245,245,245));

        JPanel tablePanel = createTablePanel();
        JPanel payPanel = createPayPanel();

        payPanel.setPreferredSize(new Dimension(420,0));

        center.add(tablePanel, BorderLayout.CENTER);
        center.add(payPanel, BorderLayout.EAST);

        main.add(center, BorderLayout.CENTER);
    }

    private JPanel createTablePanel(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

        String[] col = {"Tên món","Số lượng","Đơn giá","Thành tiền"};

        model = new DefaultTableModel(col,0);

        table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI",Font.BOLD,14));
        header.setPreferredSize(new Dimension(0,35));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(null);

        panel.add(scroll,BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPayPanel(){

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,5,8,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        JLabel lblCustomer = new JLabel("Chưa chọn khách");
        lblCustomer.setFont(new Font("Segoe UI",Font.PLAIN,13));

        JLabel addCustomer = new JLabel("➕ Thêm thông tin khách hàng");
        addCustomer.setForeground(new Color(0,70,255));
        addCustomer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addCustomer.setHorizontalAlignment(SwingConstants.RIGHT);

        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        panel.add(lblCustomer,gbc);

        gbc.gridy = y++;
        panel.add(addCustomer,gbc);

        gbc.gridwidth = 1;

        addCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                CustomerDlg dialog = new CustomerDlg(PaymentDlg.this);
                dialog.setVisible(true);

                String name = dialog.getSelectedName();
                String phone = dialog.getSelectedPhone();

                if(name != null){
                    if(phone.equals("")) lblCustomer.setText(name);
                    else lblCustomer.setText("👤 " + name + " - " + phone);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Tạm tính"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        lblTempTotal = createRightLabel(String.format("%,.0fđ", totalPrice));
        panel.add(lblTempTotal, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Loại giảm giá"), gbc);

        gbc.gridx = 1;
        cboKhuyenMai = new JComboBox<>();
        panel.add(cboKhuyenMai, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Mức giảm giá"), gbc);

        gbc.gridx = 1;
        lblPhanTramGiam = createRightLabel("0%");
        panel.add(lblPhanTramGiam, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Số tiền giảm"), gbc);

        gbc.gridx = 1;
        lblTienGiam = createRightLabel("0đ");
        panel.add(lblTienGiam, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        panel.add(Box.createVerticalStrut(20),gbc);

        gbc.gridwidth = 1;

        JLabel khachTra = new JLabel("Khách cần trả");
        khachTra.setFont(new Font("Segoe UI",Font.BOLD,15));

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(khachTra, gbc);

        gbc.gridx = 1;
        lblKhachCanTra = createRightLabelBold(String.format("%,.0fđ", totalPrice));
        panel.add(lblKhachCanTra, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Phương thức thanh toán"), gbc);

        gbc.gridx = 1;
        this.cboPaymentMethod = new JComboBox<>(
                new String[]{"Tiền mặt", "Chuyển khoản", "Thẻ"}
        );
        panel.add(this.cboPaymentMethod, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Khách đưa"), gbc);

        gbc.gridx = 1;
        txtCustomerPay = new JTextField();
        txtCustomerPay.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txtCustomerPay, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Tiền thừa"), gbc);

        gbc.gridx = 1;
        lblChange = createRightLabel("0đ");
        panel.add(lblChange, gbc);

        return panel;
    }

    private JLabel createRightLabel(String text){

        JLabel lbl = new JLabel(text);
        lbl.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(235,235,235));
        lbl.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        return lbl;
    }

    private JLabel createRightLabelBold(String text){

        JLabel lbl = createRightLabel(text);
        lbl.setFont(new Font("Segoe UI",Font.BOLD,14));

        return lbl;
    }

    private void createBottom(JPanel main){

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,10));
        bottom.setBackground(new Color(245,245,245));

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setPreferredSize(new Dimension(110,35));

        this.btnPay = new JButton("THANH TOÁN");
        this.btnPay.setPreferredSize(new Dimension(160,40));
        this.btnPay.setBackground(new Color(255,140,0));
        this.btnPay.setForeground(Color.WHITE);
        this.btnPay.setFont(new Font("Segoe UI",Font.BOLD,14));

        bottom.add(btnCancel);
        bottom.add(this.btnPay);

        main.add(bottom,BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());

        
    }

    private void loadCartToTable(List<CartItemDTO> cart){

        this.model.setRowCount(0);

        for(CartItemDTO item : cart){

            double thanhTien = item.getGia() * item.getSoLuong();

            this.model.addRow(new Object[]{
                    item.getTenSP(),
                    item.getSoLuong(),
                    String.format("%,.0fđ", item.getGia()),
                    String.format("%,.0fđ", thanhTien)
            });
        }
    }

    private void calculateTotal(List<CartItemDTO> cart){

        this.totalPrice = cart.stream()
                .mapToDouble(i -> i.getGia() * i.getSoLuong())
                .sum();
    }

    private void calculateChange(){

        try{

            String text = txtCustomerPay.getText().replace(",", "").replace("đ", "");

            if(text.isEmpty()) return;

            double customerPay = Double.parseDouble(text);

            double change = customerPay - finalPrice;

            if(change < 0){
                lblChange.setText("0đ");
            }else{
                lblChange.setText(String.format("%,.0fđ", change));
            }

        }catch(Exception e){
            lblChange.setText("0đ");
        }
    }

    private void loadKhuyenMai(){

        GiamGiaBUS bus = new GiamGiaBUS();

        cboKhuyenMai.removeAllItems();

        // mặc định
        cboKhuyenMai.addItem(null);

        for(GiamGiaDTO km : bus.getDanhSach()){
            cboKhuyenMai.addItem(km);
        }

        cboKhuyenMai.setSelectedIndex(0);
    }

    private void tinhKhuyenMai(){

        GiamGiaDTO km = (GiamGiaDTO) cboKhuyenMai.getSelectedItem();

        if(km == null){

            lblPhanTramGiam.setText("0%");
            lblTienGiam.setText("0đ");

            finalPrice = totalPrice;

            lblKhachCanTra.setText(String.format("%,.0fđ", totalPrice));
            return;
        }

        int phanTram = km.getPhanTramGiam();

        double tienGiam = totalPrice * phanTram / 100;

        finalPrice = totalPrice - tienGiam;

        lblPhanTramGiam.setText(phanTram + "%");
        lblTienGiam.setText(String.format("%,.0fđ", tienGiam));
        lblKhachCanTra.setText(String.format("%,.0fđ", finalPrice));
    }

    // format tiền giống POS
    private void formatMoney(){

        if(isFormatting) return;

        try{

            isFormatting = true;

            String text = txtCustomerPay.getText().replace(",", "");

            if(text.isEmpty()){
                isFormatting = false;
                return;
            }

            long value = Long.parseLong(text);

            String formatted = String.format("%,d", value);

            if(!formatted.equals(txtCustomerPay.getText())){
                txtCustomerPay.setText(formatted);
            }

        }catch(Exception ignored){}

        isFormatting = false;
    }
}