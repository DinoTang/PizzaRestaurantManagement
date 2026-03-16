//package DAO;
//
//import java.sql.*;
//import java.util.ArrayList;
//import Utils.DBConnection;
//import DTO.ThongKeDTO;
//
//public class ThongKeDAO {
//
//    Connection conn;
//
//    public ThongKeDAO() {
//        conn = DBConnection.getConnection();
//    }
//
//    public int[] getDoanhThuTheoThang(int nam) {
//
//        int[] data = new int[12];
//
//        try {
//
//            String sql = """
//                SELECT MONTH(NgayLap) thang,
//                       SUM(TongTien) doanhthu
//                FROM hoadon
//                WHERE YEAR(NgayLap) = ?
//                GROUP BY MONTH(NgayLap)
//            """;
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, nam);
//
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()){
//                int thang = rs.getInt("thang");
//                int tien = rs.getInt("doanhthu");
//
//                data[thang-1] = tien;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//
//    public ArrayList<ThongKeDTO> getTopSanPham(int nam){
//
//        ArrayList<ThongKeDTO> list = new ArrayList<>();
//
//        try {
//
//            String sql = """
//                SELECT sp.TenSP, SUM(ct.SoLuong) soluong
//                FROM cthoadon ct
//                JOIN sanpham sp ON sp.MaSP = ct.MaSP
//                JOIN hoadon hd ON hd.MaHD = ct.MaHD
//                WHERE YEAR(hd.NgayLap) = ?
//                GROUP BY sp.MaSP
//                ORDER BY soluong DESC
//                LIMIT 5
//            """;
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, nam);
//
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()){
//                list.add(new ThongKeDTO(
//                        rs.getString("TenSP"),
//                        rs.getInt("soluong")
//                ));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//    
//    public ArrayList<ThongKeDTO> getTopNhanVien(int nam){
//
//    ArrayList<ThongKeDTO> list = new ArrayList<>();
//
//    try{
//
//        String sql = """
//            SELECT nv.HoTen, COUNT(hd.MaHD) AS soluong
//            FROM hoadon hd
//            JOIN nhanvien nv ON nv.MaNV = hd.MaNV
//            WHERE YEAR(hd.NgayLap) = ?
//            GROUP BY nv.MaNV
//            ORDER BY soluong DESC
//            LIMIT 5
//        """;
//
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, nam);
//
//        ResultSet rs = ps.executeQuery();
//
//        while(rs.next()){
//            list.add(new ThongKeDTO(
//                    rs.getString("HoTen"),
//                    rs.getInt("soluong")
//            ));
//        }
//
//    }catch(Exception e){
//        e.printStackTrace();
//    }
//
//    return list;
//    }
//    
//    public ArrayList<ThongKeDTO> getTopKhachHang(int nam){
//
//    ArrayList<ThongKeDTO> list = new ArrayList<>();
//
//    try{
//
//        String sql = """
//            SELECT kh.TenKhachHang, COUNT(hd.MaHD) AS soluong
//            FROM hoadon hd
//            JOIN khachhang kh ON kh.MaKH = hd.MaKH
//            WHERE YEAR(hd.NgayLap) = ?
//            GROUP BY kh.MaKH
//            ORDER BY soluong DESC
//            LIMIT 5
//        """;
//
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, nam);
//
//        ResultSet rs = ps.executeQuery();
//
//        while(rs.next()){
//            list.add(new ThongKeDTO(
//                    rs.getString("TenKhachHang"),
//                    rs.getInt("soluong")
//            ));
//        }
//
//    }catch(Exception e){
//        e.printStackTrace();
//    }
//
//    return list;
//    }
//
//}
package DAO;

import DTO.ThongKeDTO;
import java.sql.*;
import java.util.*;
import Utils.DBConnection;

public class ThongKeDAO {

    Connection conn;

    public ThongKeDAO() {
        conn = DBConnection.getConnection();
    }

    // Tổng doanh thu
    public double getTongDoanhThu() {

        double result = 0;

        try {

            String sql = "SELECT SUM(TongTien) tong FROM hoadon";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getDouble("tong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Tổng hóa đơn
    public int getTongHoaDon() {

        int result = 0;

        try {

            String sql = "SELECT COUNT(*) tong FROM hoadon";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getInt("tong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Doanh thu theo tháng
   public Map<Integer, Double> getDoanhThuTheoThang(int nam){

    Map<Integer, Double> map = new HashMap<>();

    try{

        Connection con = DBConnection.getConnection();

        String sql = """
        SELECT MONTH(NgayLap) thang,
        SUM(TongTien) tong
        FROM hoadon
        WHERE YEAR(NgayLap)=?
        GROUP BY MONTH(NgayLap)
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, nam);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            int thang = rs.getInt("thang");
            double tong = rs.getDouble("tong");

            map.put(thang, tong);
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return map;
}

    // Top sản phẩm
    public List<ThongKeDTO> getTopSanPham() {

        List<ThongKeDTO> list = new ArrayList<>();

        try {

            String sql = """
            SELECT sp.TenSP ten,
            SUM(ct.SoLuong) soluong
            FROM cthoadon ct
            JOIN sanpham sp ON sp.MaSP=ct.MaSP
            GROUP BY sp.MaSP
            ORDER BY soluong DESC
            LIMIT 5
            """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new ThongKeDTO(
                        rs.getString("ten"),
                        rs.getDouble("soluong")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Khách hàng mua nhiều
    public List<ThongKeDTO> getTopKhachHang() {

        List<ThongKeDTO> list = new ArrayList<>();

        try {

            String sql = """
            SELECT kh.TenKH ten,
            COUNT(hd.MaHD) tong
            FROM hoadon hd
            JOIN khachhang kh ON kh.MaKH=hd.MaKH
            GROUP BY kh.MaKH
            ORDER BY tong DESC
            LIMIT 5
            """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new ThongKeDTO(
                        rs.getString("ten"),
                        rs.getDouble("tong")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}