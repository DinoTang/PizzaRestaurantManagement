/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quock
 */
public class TaiKhoanDAO {
    public boolean AddTaiKhoan(TaiKhoanDTO taiKhoan)
    {
        String sql = "INSERT INTO taikhoan (MATAIKHOAN, MAVAITRO, MANHANVIEN, TENDANGNHAP, MATKHAU, NGAYTAO, TRANGTHAIXOA)"
                + "VALUES (?, ?, ?, ?, ?, ?, 0)";
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, taiKhoan.getMaTaiKhoan());
            ps.setString(2, taiKhoan.getMaVaiTro());
            ps.setString(3, taiKhoan.getMaNhanVien());
            ps.setString(4, taiKhoan.getTenDangNhap());
            ps.setString(5, taiKhoan.getMatKhau());
            ps.setDate(6, Date.valueOf(taiKhoan.getNgayTao()));
            
            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean UpdateTaiKhoan(TaiKhoanDTO taiKhoan) {
        String sql = """
                    UPDATE taikhoan 
                    SET MAVAITRO = ?, 
                        MANHANVIEN = ?, 
                        TENDANGNHAP = ?, 
                        MATKHAU = ?,
                        NGAYTAO = ?
                    WHERE MATAIKHOAN = ? AND TRANGTHAIXOA = 0
                    """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, taiKhoan.getMaVaiTro());
            ps.setString(2, taiKhoan.getMaNhanVien());
            ps.setString(3, taiKhoan.getTenDangNhap());
            ps.setString(4, taiKhoan.getMatKhau());
            ps.setDate(5, Date.valueOf(taiKhoan.getNgayTao()));
            ps.setString(6, taiKhoan.getMaTaiKhoan());
            
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteTaiKhoan(TaiKhoanDTO taiKhoan)
    {
        String sql = """
                    UPDATE taikhoan SET 
                    TRANGTHAIXOA = 1 
                    WHERE MATAIKHOAN = ?
                    """;
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, taiKhoan.getMaTaiKhoan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<TaiKhoanDTO> GetAllTaiKhoan()
    {   List<TaiKhoanDTO> list = new ArrayList();
        String sql = """
                     SELECT * FROM taikhoan
                     WHERE TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()){
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setMaTaiKhoan(rs.getString("MATAIKHOAN"));
                tk.setMaVaiTro(rs.getString("MAVAITRO"));
                tk.setMaNhanVien(rs.getString("MANHANVIEN"));
                tk.setTenDangNhap(rs.getString("TENDANGNHAP"));
                tk.setMatKhau(rs.getString("MATKHAU"));
                tk.setNgayTao(rs.getDate("NGAYTAO").toLocalDate());
                tk.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                
                list.add(tk);
            }
        } catch (Exception e) {
             e.printStackTrace(); 
        }
        return list;
    }
    
    public TaiKhoanDTO GetTaiKhoanById(String id) {
        String sql = """
            SELECT *
            FROM taikhoan
            WHERE MATAIKHOAN = ?
              AND TRANGTHAIXOA = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoanDTO tk = new TaiKhoanDTO();
                    tk.setMaTaiKhoan(rs.getString("MATAIKHOAN"));
                    tk.setMaVaiTro(rs.getString("MAVAITRO"));
                    tk.setMaNhanVien(rs.getString("MANHANVIEN"));
                    tk.setTenDangNhap(rs.getString("TENDANGNHAP"));
                    tk.setMatKhau(rs.getString("MATKHAU"));
                    tk.setNgayTao(rs.getDate("NGAYTAO").toLocalDate());
                    tk.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                    
                    return tk;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String GetNextTaiKhoanId(){
        String sql = """
                     SELECT MATAIKHOAN 
                     FROM taikhoan
                     ORDER BY MATAIKHOAN DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MATAIKHOAN");
                int number = Integer.parseInt(lastId.substring(2));
                number++;
                
                return String.format("TK%02d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return "TK01";
    }
    
    public boolean Login(String username, String password) {
        String sql = """
                     SELECT 1
                     FROM taikhoan
                     WHERE TENDANGNHAP = ?
                     AND MATKHAU = ?
                     AND TRANGTHAIXOA = 0
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // nếu có dữ liệu -> true, không có -> false

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
