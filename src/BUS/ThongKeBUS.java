package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;
import java.util.*;

public class ThongKeBUS {

    ThongKeDAO thongKeDAO = new ThongKeDAO();

    // ================= DASHBOARD =================

    public double getTongDoanhThu() {
        return thongKeDAO.getTongDoanhThu();
    }

    public int getTongHoaDon() {
        return thongKeDAO.getTongHoaDon();
    }

    public int getTongPizza(){
        return thongKeDAO.getTongPizza();
    }

    public int getTongKhachHang(){
        return thongKeDAO.getTongKhachHang();
    }

    // ================= DOANH THU =================

    public Map<String,Double> getDoanhThuTheoNgay(Date from, Date to){
        return thongKeDAO.getDoanhThuTheoNgay(from,to);
    }

    public List<ThongKeDTO> getTopSanPham(Date from, Date to){
        return thongKeDAO.getTopSanPham(from,to);
    }

    public List<ThongKeDTO> getTopKhachHang(Date from, Date to){
        return thongKeDAO.getTopKhachHang(from,to);
    }

    public List<ThongKeDTO> getTopNhanVien(Date from, Date to){
        return thongKeDAO.getTopNhanVien(from,to);
    }
}