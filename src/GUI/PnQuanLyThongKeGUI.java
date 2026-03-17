package GUI;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;

import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PnQuanLyThongKeGUI extends JPanel {

    private ThongKeBUS thongKeBUS = new ThongKeBUS();

    private JDateChooser dateFrom;
    private JDateChooser dateTo;

    private JPanel chartDoanhThu;
    private JPanel chartSanPham;
    private JPanel chartKhachHang;
    private JPanel chartNhanVien;

    private JLabel lbDoanhThu;
    private JLabel lbHoaDon;
    private JLabel lbPizza;
    private JLabel lbKhachHang;

    public PnQuanLyThongKeGUI() {

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);

        loadDashboard();
    }

    // ====================== TOP PANEL ======================

    private JPanel createTopPanel(){

        JPanel panel = new JPanel();

        panel.add(new JLabel("Từ ngày"));

        dateFrom = new JDateChooser();
        dateFrom.setDateFormatString("yyyy-MM-dd");
        panel.add(dateFrom);

        panel.add(new JLabel("Đến ngày"));

        dateTo = new JDateChooser();
        dateTo.setDateFormatString("yyyy-MM-dd");
        panel.add(dateTo);

        JButton btnThongKe = new JButton("Thống kê");

        btnThongKe.addActionListener(e -> {

            loadDoanhThu();
            loadTopSanPham();
            loadTopKhachHang();
            loadTopNhanVien();
        });

        panel.add(btnThongKe);

        return panel;
    }

    // ====================== CENTER PANEL ======================

    private JPanel createCenterPanel(){

        JPanel main = new JPanel(new BorderLayout());

        main.add(createDashboard(), BorderLayout.NORTH);
        main.add(createCharts(), BorderLayout.CENTER);

        return main;
    }

    // ====================== DASHBOARD ======================

    private JPanel createDashboard(){

        JPanel panel = new JPanel(new GridLayout(1,4,20,20));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        lbDoanhThu = new JLabel("0",SwingConstants.CENTER);
        lbHoaDon = new JLabel("0",SwingConstants.CENTER);
        lbPizza = new JLabel("0",SwingConstants.CENTER);
        lbKhachHang = new JLabel("0",SwingConstants.CENTER);

        panel.add(createCard("Tổng doanh thu", lbDoanhThu));
        panel.add(createCard("Tổng hóa đơn", lbHoaDon));
        panel.add(createCard("Tổng pizza", lbPizza));
        panel.add(createCard("Tổng khách hàng", lbKhachHang));

        return panel;
    }

    private JPanel createCard(String title, JLabel value){

        JPanel card = new JPanel(new BorderLayout());

        JLabel lbTitle = new JLabel(title,SwingConstants.CENTER);

        value.setFont(new Font("Arial",Font.BOLD,22));

        card.add(lbTitle,BorderLayout.NORTH);
        card.add(value,BorderLayout.CENTER);

        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        return card;
    }

    // ====================== CHART AREA ======================

    private JPanel createCharts(){

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        chartDoanhThu = new JPanel(new BorderLayout());
        chartSanPham = new JPanel(new BorderLayout());
        chartKhachHang = new JPanel(new BorderLayout());
        chartNhanVien = new JPanel(new BorderLayout());

        panel.add(chartDoanhThu);
        panel.add(chartSanPham);
        panel.add(chartKhachHang);
        panel.add(chartNhanVien);

        return panel;
    }

    // ====================== DASHBOARD LOAD ======================

    private void loadDashboard(){

        double doanhThu = thongKeBUS.getTongDoanhThu();
        int hoaDon = thongKeBUS.getTongHoaDon();
        int pizza = thongKeBUS.getTongPizza();
        int khachHang = thongKeBUS.getTongKhachHang();

        lbDoanhThu.setText(String.format("%,.0f VNĐ",doanhThu));
        lbHoaDon.setText(String.valueOf(hoaDon));
        lbPizza.setText(String.valueOf(pizza));
        lbKhachHang.setText(String.valueOf(khachHang));
    }

    // ====================== DOANH THU ======================

    private void loadDoanhThu(){

        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();

        if(from == null || to == null){

            JOptionPane.showMessageDialog(this,"Vui lòng chọn ngày");
            return;
        }

        Map<String,Double> data = thongKeBUS.getDoanhThuTheoNgay(from,to);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(String ngay : data.keySet()){

            dataset.addValue(data.get(ngay),"Doanh thu",ngay);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Doanh thu theo ngày",
                "Ngày",
                "VNĐ",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );

        chartDoanhThu.removeAll();
        chartDoanhThu.add(new ChartPanel(chart));
        chartDoanhThu.revalidate();
    }

    // ====================== TOP SAN PHAM ======================

    private void loadTopSanPham(){

        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();

        if(from == null || to == null){
            return;
        }

        List<ThongKeDTO> list = thongKeBUS.getTopSanPham(from,to);

        DefaultPieDataset dataset = new DefaultPieDataset();

        for(ThongKeDTO sp : list){

            dataset.setValue(sp.getTen(), sp.getSoLuong());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Top 5 sản phẩm bán chạy",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setCircular(true);
        plot.setLabelGap(0.02);

        chartSanPham.removeAll();
        chartSanPham.add(new ChartPanel(chart));
        chartSanPham.revalidate();
    }

    // ====================== TOP KHACH HANG ======================

    private void loadTopKhachHang(){

        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();

        List<ThongKeDTO> list = thongKeBUS.getTopKhachHang(from,to);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(ThongKeDTO kh : list){

            dataset.addValue(kh.getSoLuong(),"Lượt mua",kh.getTen());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Top khách hàng",
                "Khách hàng",
                "Số lần mua",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );

        chartKhachHang.removeAll();
        chartKhachHang.add(new ChartPanel(chart));
        chartKhachHang.revalidate();
    }

    // ====================== TOP NHAN VIEN ======================

    private void loadTopNhanVien(){

        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();

        List<ThongKeDTO> list = thongKeBUS.getTopNhanVien(from,to);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(ThongKeDTO nv : list){

            dataset.addValue(nv.getSoLuong(),"Hóa đơn",nv.getTen());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Top nhân viên",
                "Nhân viên",
                "Số hóa đơn",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );

        chartNhanVien.removeAll();
        chartNhanVien.add(new ChartPanel(chart));
        chartNhanVien.revalidate();
    }
}