package GUI.components;

import BUS.KhachHangBUS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerDlg extends JDialog {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtPhone;
    
    private String selectedMaKH;
    private String selectedName;
    private String selectedPhone;
    
    public CustomerDlg(Window parent) {

        super(parent,"Tìm khách hàng",ModalityType.APPLICATION_MODAL);

        setSize(400,350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        createTop();
        createTable();
        createBottom();
        searchCustomer();
    }

    private void createTop(){

        JPanel top = new JPanel(new BorderLayout(10,0));

        txtPhone = new JTextField();

        JButton btnSearch = new JButton("Tìm");

        top.add(new JLabel("Số điện thoại:"),BorderLayout.WEST);
        top.add(txtPhone,BorderLayout.CENTER);
        top.add(btnSearch,BorderLayout.EAST);

        add(top,BorderLayout.NORTH);

        btnSearch.addActionListener(e -> searchCustomer());
    }

    private void createTable(){

        String[] col = {"Mã KH","Tên khách","SĐT","Ngày tạo","Tổng chi tiêu"};

        model = new DefaultTableModel(col,0);

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add(scroll,BorderLayout.CENTER);
    }

    private void createBottom(){

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnSelect = new JButton("Chọn");

        bottom.add(btnSelect);

        add(bottom,BorderLayout.SOUTH);

        btnSelect.addActionListener(e -> selectCustomer());
    }

    private void searchCustomer(){

        model.setRowCount(0);

        String phone = txtPhone.getText();

        KhachHangBUS bus = new KhachHangBUS();

        var list = phone.isEmpty()
                ? bus.getAllCustomers()
                : bus.searchCustomer(phone);

        for(var kh : list){

           model.addRow(new Object[]{
                    kh.getMaKhachHang(),
                    kh.getTenKhachHang(),
                    kh.getSoDienThoai(),
                    kh.getNgayTao(),
                    String.format("%,d", kh.getTongChiTieu())
            });

        }

    }

    private void selectCustomer(){

        int row = table.getSelectedRow();

        if(row == -1) return;
        
        selectedMaKH = model.getValueAt(row,0).toString();
        selectedName = model.getValueAt(row,0).toString();
        selectedPhone = model.getValueAt(row,1).toString();
        
        dispose();
    }

    public String getSelectedName(){
        return selectedName;
    }

    public String getSelectedPhone(){
        return selectedPhone;
    }

    public String getSelectedMaKH(){
        return selectedMaKH;
    }
}