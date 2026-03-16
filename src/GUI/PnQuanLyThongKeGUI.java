//package GUI;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//import BUS.ThongKeBUS;
//import DTO.ThongKeDTO;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
//public class PnQuanLyThongKeGUI extends JPanel {
//
//    JComboBox<Integer> cmbNam;
//
//    JButton btnDoanhThu;
//    JButton btnSanPham;
//    JButton btnNhanVien;
//    JButton btnKhachHang;
//
//    JPanel chartPanel;
//
//    ThongKeBUS thongKeBUS = new ThongKeBUS();
//
//    public PnQuanLyThongKeGUI(){
//
//        setLayout(new BorderLayout());
//
//        JPanel top = new JPanel();
//
//        cmbNam = new JComboBox<>();
//
//        for(int i=2020;i<=2030;i++){
//            cmbNam.addItem(i);
//        }
//
//        btnDoanhThu = new JButton("Doanh thu");
//        btnSanPham = new JButton("Sản phẩm bán chạy");
//        btnNhanVien = new JButton("Nhân viên năng suất");
//        btnKhachHang = new JButton("Khách hàng mua nhiều");
//
//        top.add(new JLabel("Năm:"));
//        top.add(cmbNam);
//
//        top.add(btnDoanhThu);
//        top.add(btnSanPham);
//        top.add(btnNhanVien);
//        top.add(btnKhachHang);
//
//        add(top,BorderLayout.NORTH);
//
//        chartPanel = new JPanel(new BorderLayout());
//        add(chartPanel,BorderLayout.CENTER);
//
//        btnDoanhThu.addActionListener(e -> loadDoanhThu());
//        btnSanPham.addActionListener(e -> loadSanPham());
//        btnNhanVien.addActionListener(e -> loadNhanVien());
//        btnKhachHang.addActionListener(e -> loadKhachHang());
//    }
//
//    private int getNam(){
//        return Integer.parseInt(cmbNam.getSelectedItem()+"");
//    }
//
//    private void showChart(JFreeChart chart){
//
//        chartPanel.removeAll();
//
//        ChartPanel panel = new ChartPanel(chart);
//
//        chartPanel.add(panel,BorderLayout.CENTER);
//
//        chartPanel.revalidate();
//        chartPanel.repaint();
//    }
//
//    private void loadDoanhThu(){
//
//        int nam = getNam();
//
//        int[] data = thongKeBUS.getDoanhThuTheoThang(nam);
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for(int i=0;i<data.length;i++){
//            dataset.addValue(data[i],"Doanh thu","Tháng "+(i+1));
//        }
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Doanh thu năm "+nam,
//                "Tháng",
//                "Tiền",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        showChart(chart);
//    }
//
//    private void loadSanPham(){
//
//        int nam = getNam();
//
//        ArrayList<ThongKeDTO> list = thongKeBUS.getTopSanPham(nam);
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for(ThongKeDTO i : list){
//            dataset.addValue(i.getSoLuong(),"Số lượng",i.getTen());
//        }
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Top sản phẩm bán chạy",
//                "Sản phẩm",
//                "Số lượng",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        showChart(chart);
//    }
//    
//    private void loadNhanVien(){
//
//        int nam = getNam();
//
//        ArrayList<ThongKeDTO> list = thongKeBUS.getTopNhanVien(nam);
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for(ThongKeDTO i : list){
//            dataset.addValue(i.getSoLuong(),"Số hóa đơn",i.getTen());
//        }
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Nhân viên năng suất",
//                "Nhân viên",
//                "Số hóa đơn",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        showChart(chart);
//    }
//    
//    private void loadKhachHang(){
//
//        int nam = getNam();
//
//        ArrayList<ThongKeDTO> list = thongKeBUS.getTopKhachHang(nam);
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        for(ThongKeDTO i : list){
//            dataset.addValue(i.getSoLuong(),"Số hóa đơn",i.getTen());
//        }
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Khách hàng mua nhiều",
//                "Khách hàng",
//                "Số lần mua",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        showChart(chart);
//    }
//}

package GUI;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PnQuanLyThongKeGUI extends JPanel {

    JLabel lblDoanhThu = new JLabel();
    JLabel lblHoaDon = new JLabel();

    JPanel chartDoanhThu = new JPanel(new BorderLayout());
    JPanel chartSanPham = new JPanel(new BorderLayout());

    JComboBox<Integer> cmbNam = new JComboBox<>();

    ThongKeBUS thongKeBUS = new ThongKeBUS();

    public PnQuanLyThongKeGUI() {

        setLayout(new BorderLayout());

        for (int i = 2020; i <= 2030; i++) {
            cmbNam.addItem(i);
        }

        add(createTopPanel(), BorderLayout.NORTH);

        add(createDashboard(), BorderLayout.CENTER);

        add(createCharts(), BorderLayout.SOUTH);

        loadDashboard();
        loadDoanhThu();
        loadTopSanPham();
    }

    JPanel createTopPanel() {

        JPanel p = new JPanel();

        p.add(new JLabel("Năm"));

        p.add(cmbNam);

        JButton btnLoad = new JButton("Xem");

        btnLoad.addActionListener(e -> {

            loadDoanhThu();
            loadTopSanPham();

        });

        p.add(btnLoad);

        return p;
    }

    JPanel createDashboard() {

        JPanel p = new JPanel(new GridLayout(1,2,20,20));

        p.add(createCard("Tổng doanh thu", lblDoanhThu));

        p.add(createCard("Tổng hóa đơn", lblHoaDon));

        return p;
    }

    JPanel createCard(String title, JLabel value) {

        JPanel card = new JPanel(new BorderLayout());

        JLabel lblTitle = new JLabel(title);

        lblTitle.setFont(new Font("Arial",Font.BOLD,16));

        value.setFont(new Font("Arial",Font.BOLD,22));

        card.add(lblTitle,BorderLayout.NORTH);

        card.add(value,BorderLayout.CENTER);

        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return card;
    }

    JPanel createCharts() {

        JPanel p = new JPanel(new GridLayout(1,2));

        p.add(chartDoanhThu);

        p.add(chartSanPham);

        return p;
    }

    void loadDashboard() {

        lblDoanhThu.setText(String.valueOf(thongKeBUS.getTongDoanhThu()));

        lblHoaDon.setText(String.valueOf(thongKeBUS.getTongHoaDon()));

    }

    void loadDoanhThu() {

        int nam = (int) cmbNam.getSelectedItem();

        Map<String, Double> data = thongKeBUS.getDoanhThuTheoThang(nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i=0;i<12;i++){
            dataset.addValue(data.get(i), "Doanh thu", "Tháng " + (i+1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Doanh thu theo tháng",
                "Thời gian",
                "VNĐ",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        chartDoanhThu.removeAll();

        chartDoanhThu.add(new ChartPanel(chart));

        chartDoanhThu.revalidate();
    }

    void loadTopSanPham() {

        List<ThongKeDTO> list = thongKeBUS.getTopSanPham();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (ThongKeDTO sp : list) {

            dataset.setValue(sp.getTen(), sp.getGiaTri());

        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Top sản phẩm bán",
                dataset,
                true,true,false
        );

        chartSanPham.removeAll();

        chartSanPham.add(new ChartPanel(chart));

        chartSanPham.revalidate();
    }

}