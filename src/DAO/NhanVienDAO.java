/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
/**
 *
 * @author quock
 */
public class NhanVienDAO {
    public boolean AddNhanVien(NhanVienDTO nhanVien)
    {
        String sql = "INSERT INTO nhanvien (MANHANVIEN, HOTEN, SODIENTHOAI, EMAIL, LUONG, NGAYTAO, TRANGTHAIXOA)"
                + "VALUES (?, ?, ?, ?, ?, CURRENT_DATE, 0)";
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, nhanVien.getMaNhanVien());
            ps.setString(2, nhanVien.getHoTen());
            ps.setString(3, nhanVien.getSoDienThoai());
            ps.setString(4, nhanVien.getEmail());
            ps.setDouble(5, nhanVien.getLuong());


            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean UpdateNhanVien(NhanVienDTO nhanVien) {
        String sql = """
                    UPDATE nhanvien SET "
                    HOTEN = ?, "
                    SODIENTHOAI = ?, "
                    EMAIL = ?, "
                    LUONG = ? "
                    WHERE MANHANVIEN = ? AND TRANGTHAIXOA = 0
                    """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, nhanVien.getHoTen());
            ps.setString(2, nhanVien.getSoDienThoai());
            ps.setString(3, nhanVien.getEmail());
            ps.setDouble(4, nhanVien.getLuong());
            ps.setString(5, nhanVien.getMaNhanVien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteNhanVien(NhanVienDTO nhanVien)
    {
        String sql = """
                    UPDATE nhanvien SET 
                    TRANGTHAIXOA = 1 
                    WHERE MANHANVIEN = ?
                    """;
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, nhanVien.getMaNhanVien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<NhanVienDTO> GetAllNhanVien()
    {   List<NhanVienDTO> nhanViens = new ArrayList();
        String sql = """
                     SELECT * FROM nhanvien
                     WHERE TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()){
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNhanVien(rs.getString("MANHANVIEN"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setSoDienThoai(rs.getString("SODIENTHOAI"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setLuong(rs.getDouble("LUONG"));
                nv.setNgayTao(rs.getDate("NGAYTAO").toLocalDate());
                nv.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                
                nhanViens.add(nv);
            }
        } catch (Exception e) {
             e.printStackTrace(); 
        }
        return nhanViens;
    }
    
    public NhanVienDTO GetNhanVienById(String id) {
        String sql = """
            SELECT *
            FROM nhanvien
            WHERE MANHANVIEN = ?
              AND TRANGTHAIXOA = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNhanVien(rs.getString("MANHANVIEN"));
                    nv.setHoTen(rs.getString("HOTEN"));
                    nv.setSoDienThoai(rs.getString("SODIENTHOAI"));
                    nv.setEmail(rs.getString("EMAIL"));
                    nv.setLuong(rs.getDouble("LUONG"));
                    nv.setNgayTao(rs.getDate("NGAYTAO").toLocalDate());
                    nv.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                    return nv;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String GetNextNhanVienId(){
        String sql = """
                     SELECT MANHANVIEN 
                     FROM nhanvien
                     ORDER BY MANHANVIEN DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MANHANVIEN");
                int number = Integer.parseInt(lastId.substring(2));
                number++;
                
                return String.format("NV%02d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return "NV01";
    }
    
    public List<NhanVienDTO> SearchNhanVienByName(String keyword){
        List<NhanVienDTO> list = new ArrayList();
        String sql = """
                     SELECT * 
                     FROM nhanvien
                     WHERE HOTEN LIKE ?
                     AND TRANGTHAIXOA = 0
                     """;
        try(
             Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, "%" + keyword + "%");
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNhanVien(rs.getString("MANHANVIEN"));
                    nv.setHoTen(rs.getString("HOTEN"));
                    nv.setSoDienThoai(rs.getString("SODIENTHOAI"));
                    nv.setEmail(rs.getString("EMAIL"));
                    nv.setLuong(rs.getDouble("LUONG"));
                    nv.setNgayTao(rs.getDate("NGAYTAO").toLocalDate());
                    nv.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return list;
    }
}