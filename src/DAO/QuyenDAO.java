package DAO;

import DTO.QuyenDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuyenDAO {

    public QuyenDTO getQuyenById(String maQuyen) {

        String sql = "SELECT * FROM phanquyen WHERE MaQuyen = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maQuyen);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                QuyenDTO q = new QuyenDTO();

                q.setMaQuyen(rs.getString("MaQuyen"));
                q.setTenQuyen(rs.getString("TenQuyen"));

                q.setNhapHang(rs.getInt("NhapHang"));
                q.setQlSanPham(rs.getInt("QLSanPham"));
                q.setQlNhanVien(rs.getInt("QLNhanVien"));
                q.setQlKhachHang(rs.getInt("QLKhachHang"));
                q.setThongKe(rs.getInt("ThongKe"));

                return q;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<QuyenDTO> getAllQuyen(){

        List<QuyenDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM phanquyen";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            while(rs.next()){

                QuyenDTO q = new QuyenDTO();

                q.setMaQuyen(rs.getString("MaQuyen"));
                q.setTenQuyen(rs.getString("TenQuyen"));

                q.setNhapHang(rs.getInt("NhapHang"));
                q.setQlSanPham(rs.getInt("QLSanPham"));
                q.setQlNhanVien(rs.getInt("QLNhanVien"));
                q.setQlKhachHang(rs.getInt("QLKhachHang"));
                q.setThongKe(rs.getInt("ThongKe"));

                list.add(q);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean addQuyen(QuyenDTO q){

        String sql = "INSERT INTO phanquyen VALUES (?,?,?,?,?,?,?)";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, q.getMaQuyen());
            ps.setString(2, q.getTenQuyen());

            ps.setInt(3, q.getNhapHang());
            ps.setInt(4, q.getQlSanPham());
            ps.setInt(5, q.getQlNhanVien());
            ps.setInt(6, q.getQlKhachHang());
            ps.setInt(7, q.getThongKe());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateQuyen(QuyenDTO q){

        String sql = "UPDATE phanquyen SET TenQuyen=?, NhapHang=?, QLSanPham=?, QLNhanVien=?, QLKhachHang=?, ThongKe=? WHERE MaQuyen=?";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, q.getTenQuyen());

            ps.setInt(2, q.getNhapHang());
            ps.setInt(3, q.getQlSanPham());
            ps.setInt(4, q.getQlNhanVien());
            ps.setInt(5, q.getQlKhachHang());
            ps.setInt(6, q.getThongKe());

            ps.setString(7, q.getMaQuyen());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteQuyen(String maQuyen){

        String sql = "DELETE FROM phanquyen WHERE MaQuyen=?";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maQuyen);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean isAdmin(String maQuyen) {
        return "Q01".equals(maQuyen);
    }
    public QuyenDTO getQuyenBangTenQuyen(String tenQuyen) {

        String sql = "SELECT * FROM phanquyen WHERE TenQuyen = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, tenQuyen);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                QuyenDTO q = new QuyenDTO();

                q.setMaQuyen(rs.getString("MaQuyen"));
                q.setTenQuyen(rs.getString("TenQuyen"));

                q.setNhapHang(rs.getInt("NhapHang"));
                q.setQlSanPham(rs.getInt("QLSanPham"));
                q.setQlNhanVien(rs.getInt("QLNhanVien"));
                q.setQlKhachHang(rs.getInt("QLKhachHang"));
                q.setThongKe(rs.getInt("ThongKe"));

                return q;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}