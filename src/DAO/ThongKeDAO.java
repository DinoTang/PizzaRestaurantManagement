package DAO;

import DTO.ThongKeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
   public Map<String, Double> getDoanhThuTheoNgay(Date from, Date to){

        Map<String, Double> map = new LinkedHashMap<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = """
            SELECT DATE(NgayLap) ngay,
            SUM(TongTien) tong
            FROM hoadon
            WHERE NgayLap BETWEEN ? AND ?
            GROUP BY DATE(NgayLap)
            ORDER BY ngay
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, new java.sql.Date(from.getTime()));
            ps.setDate(2, new java.sql.Date(to.getTime()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                String ngay = rs.getString("ngay");
                double tong = rs.getDouble("tong");

                map.put(ngay, tong);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }

    // Top sản phẩm
    public ArrayList<ThongKeDTO> getTopSanPham(Date from, Date to){

        ArrayList<ThongKeDTO> list = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = """
            SELECT sp.TenSP ten,
            SUM(ct.SoLuong) soluong
            FROM cthoadon ct
            JOIN sanpham sp ON sp.MaSP = ct.MaSP
            JOIN hoadon hd ON hd.MaHD = ct.MaHD
            WHERE hd.NgayLap BETWEEN ? AND ?
            GROUP BY sp.MaSP
            ORDER BY soluong DESC
            LIMIT 5
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1,new java.sql.Date(from.getTime()));
            ps.setDate(2,new java.sql.Date(to.getTime()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                String ten = rs.getString("ten");
                Double sl = rs.getDouble("soluong");

                list.add(new ThongKeDTO(ten,sl));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    // Khách hàng mua nhiều
    public ArrayList<ThongKeDTO> getTopKhachHang(Date from, Date to){

        ArrayList<ThongKeDTO> list = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = """
            SELECT kh.TenKhachHang ten,
            SUM(hd.TongTien) tongtien
            FROM hoadon hd
            JOIN khachhang kh ON kh.MaKH = hd.MaKH
            WHERE hd.NgayLap BETWEEN ? AND ?
            GROUP BY kh.MaKH, kh.TenKhachHang
            ORDER BY tongtien DESC
            LIMIT 5
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, new java.sql.Date(from.getTime()));
            ps.setDate(2, new java.sql.Date(to.getTime()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                String ten = rs.getString("ten");
                double tongTien = rs.getDouble("tongtien");

                list.add(new ThongKeDTO(ten, tongTien));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
   public ArrayList<ThongKeDTO> getTopNhanVien(Date from, Date to){

        ArrayList<ThongKeDTO> list = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = """
            SELECT nv.HoTen ten,
            COUNT(hd.MaHD) soluong
            FROM hoadon hd
            JOIN nhanvien nv ON nv.MaNV = hd.MaNV
            WHERE hd.NgayLap BETWEEN ? AND ?
            GROUP BY nv.MaNV
            ORDER BY soluong DESC
            LIMIT 5
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1,new java.sql.Date(from.getTime()));
            ps.setDate(2,new java.sql.Date(to.getTime()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new ThongKeDTO(
                        rs.getString("ten"),
                        rs.getDouble("soluong")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    public int getTongPizza(){

    int tong = 0;

    try{

        Connection con = DBConnection.getConnection();

        String sql = """
        SELECT SUM(SoLuong) tong
        FROM cthoadon
        """;

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            tong = rs.getInt("tong");
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return tong;
    }
    
    public int getTongKhachHang(){

    int tong = 0;

    try{

        Connection con = DBConnection.getConnection();

        String sql = """
        SELECT COUNT(*) tong
        FROM khachhang
        """;

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            tong = rs.getInt("tong");
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return tong;
    }   
    
    

}