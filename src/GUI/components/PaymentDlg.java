package GUI.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PaymentDlg extends JDialog {

    private JLabel addCustomer;
    private JLabel lblCustomer;

    public PaymentDlg(Window parent) {

        super(parent,"Thanh toán",ModalityType.APPLICATION_MODAL);

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

        Object[][] data = {
                {"Pizza Phô Mai Truyền Thống","1","155,000đ","155,000đ"},
                {"Pizza Tôm & Bò Xốt Parmesan","1","225,000đ","225,000đ"}
        };

        JTable table = new JTable(new DefaultTableModel(data,col));
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

        lblCustomer = new JLabel("Chưa chọn khách");
        lblCustomer.setFont(new Font("Segoe UI",Font.PLAIN,13));

        addCustomer = new JLabel("➕ Thêm thông tin khách hàng");
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
                    lblCustomer.setText("👤 " + name + " - " + phone);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Tạm tính"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(createRightLabel("380,000đ"), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Loại giảm giá"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(new JComboBox<>(new String[]{"Không chọn"}), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Mức giảm giá"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(createRightLabel("0%"), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Số tiền giảm"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(createRightLabel("0đ"), gbc);
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
        gbc.weightx = 0;
        panel.add(khachTra, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(createRightLabelBold("380,000đ"), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Phương thức thanh toán"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(new JComboBox<>(new String[]{"Tiền mặt"}), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Khách đưa"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField khachDua = new JTextField("380,000đ");
        khachDua.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(khachDua, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel("Tiền thừa"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(createRightLabel("0đ"), gbc);

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

        JButton btnPay = new JButton("THANH TOÁN");
        btnPay.setPreferredSize(new Dimension(160,40));
        btnPay.setBackground(new Color(255,140,0));
        btnPay.setForeground(Color.WHITE);
        btnPay.setFont(new Font("Segoe UI",Font.BOLD,14));

        bottom.add(btnCancel);
        bottom.add(btnPay);

        main.add(bottom,BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());

        btnPay.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"Thanh toán thành công!");
            dispose();
        });
    }
}