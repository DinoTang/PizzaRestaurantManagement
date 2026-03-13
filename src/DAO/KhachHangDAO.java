package DAO;

import DTO.KhachHangDTO;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public List<KhachHangDTO> getAll(){

        List<KhachHangDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM khachhang WHERE TrangThaiXoa = 0";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                Date date = rs.getDate("NgayTao");
                
                if(date != null){
                    kh.setNgayTao(date.toLocalDate());
                }
                kh.setTongChiTieu(rs.getInt("TongChiTieu"));
                kh.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public List<KhachHangDTO> searchByPhone(String phone){

        List<KhachHangDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM khachhang WHERE TrangThaiXoa = 0 AND SoDienThoai LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,"%"+phone+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setNgayTao(rs.getDate("NgayTao").toLocalDate());
                kh.setTongChiTieu(rs.getInt("TongChiTieu"));

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public boolean addKhachHang(KhachHangDTO kh){

        String sql = """
                     INSERT INTO khachhang(MaKH, TenKhachHang, SoDienThoai, NgayTao, TongChiTieu, TrangThaiXoa)
                     VALUES(?,?,?,?,?,0)
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getTenKhachHang());
            ps.setString(3, kh.getSoDienThoai());
            ps.setDate(4, Date.valueOf(kh.getNgayTao()));
            ps.setInt(5, kh.getTongChiTieu());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateKhachHang(KhachHangDTO kh){

        String sql = """
                     UPDATE khachhang
                     SET TenKhachHang = ?, SoDienThoai = ?
                     WHERE MaKH = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getMaKhachHang());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteKhachHang(String maKH){

        String sql = """
                     UPDATE khachhang
                     SET TrangThaiXoa = 1
                     WHERE MaKH = ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maKH);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public String getNextId(){

        String sql = """
                     SELECT MaKH
                     FROM khachhang
                     ORDER BY MaKH DESC
                     LIMIT 1
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            if(rs.next()){

                String lastId = rs.getString("MaKH");
                int number = Integer.parseInt(lastId.substring(2));
                number++;

                return String.format("KH%03d", number);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "KH001";
    }
    public KhachHangDTO getKhachHangById(String maKH){

        String sql = "SELECT * FROM khachhang WHERE MaKH = ? AND TrangThaiXoa = 0";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maKH);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));

                Date date = rs.getDate("NgayTao");
                if(date != null){
                    kh.setNgayTao(date.toLocalDate());
                }

                kh.setTongChiTieu(rs.getInt("TongChiTieu"));
                kh.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                return kh;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public List<KhachHangDTO> searchKhachHang(String keyword){

        List<KhachHangDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM khachhang
                     WHERE TrangThaiXoa = 0
                     AND (
                         MaKH LIKE ?
                         OR TenKhachHang LIKE ?
                         OR SoDienThoai LIKE ?
                     )
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));

                Date date = rs.getDate("NgayTao");
                if(date != null){
                    kh.setNgayTao(date.toLocalDate());
                }

                kh.setTongChiTieu(rs.getInt("TongChiTieu"));
                kh.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public List<KhachHangDTO> searchCustomer(String min, String max){

        List<KhachHangDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM khachhang
                     WHERE TrangThaiXoa = 0
                     AND TongChiTieu BETWEEN ? AND ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            int minValue = min.isEmpty() ? 0 : Integer.parseInt(min);
            int maxValue = max.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(max);

            ps.setInt(1, minValue);
            ps.setInt(2, maxValue);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));

                Date date = rs.getDate("NgayTao");
                if(date != null){
                    kh.setNgayTao(date.toLocalDate());
                }

                kh.setTongChiTieu(rs.getInt("TongChiTieu"));
                kh.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}