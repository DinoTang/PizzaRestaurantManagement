//package BUS;
//import java.util.ArrayList;
//import DAO.ThongKeDAO;
//import DTO.ThongKeDTO;
//
//public class ThongKeBUS {
//
//    ThongKeDAO thongKeDAO = new ThongKeDAO();
//
//    public int[] getDoanhThuTheoThang(int nam){
//        return thongKeDAO.getDoanhThuTheoThang(nam);
//    }
//
//    public ArrayList<ThongKeDTO> getTopSanPham(int nam){
//        return thongKeDAO.getTopSanPham(nam);
//    }
//    
//    public ArrayList<ThongKeDTO> getTopNhanVien(int nam){
//        return thongKeDAO.getTopNhanVien(nam);
//    }
//
//    public ArrayList<ThongKeDTO> getTopKhachHang(int nam){
//        return thongKeDAO.getTopKhachHang(nam);
//    }
//
//}
package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;
import java.util.*;

public class ThongKeBUS {

    ThongKeDAO thongKeDAO = new ThongKeDAO();

    public double getTongDoanhThu() {
        return thongKeDAO.getTongDoanhThu();
    }

    public int getTongHoaDon() {
        return thongKeDAO.getTongHoaDon();
    }

    public Map<String, Double> getDoanhThuTheoThang(int nam) {
        return thongKeDAO.getDoanhThuTheoThang(nam);
    }

    public List<ThongKeDTO> getTopSanPham() {
        return thongKeDAO.getTopSanPham();
    }

    public List<ThongKeDTO> getTopKhachHang() {
        return thongKeDAO.getTopKhachHang();
    }

}