/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.NguyenLieuDTO;
import Utils.DBConnection;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author quock
 */
public class NguyenLieuDAO {
    public List<NguyenLieuDTO> getAllNguyenLieu(){

        List<NguyenLieuDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM nguyenlieu WHERE TrangThaiXoa = 0";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            while(rs.next()){

                NguyenLieuDTO nl = new NguyenLieuDTO();

                nl.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nl.setMaDonVi(rs.getString("MaDonVi"));
                nl.setTonKho(rs.getDouble("TonKho"));
                nl.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);
                nl.setMaNCC(rs.getString("MaNCC"));

                list.add(nl);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    // thêm nguyên liệu
    public boolean addNguyenLieu(NguyenLieuDTO nl){

        String sql = """
                     INSERT INTO nguyenlieu(MaNguyenLieu, TenNguyenLieu, MaDonVi, TonKho, MaNCC, TrangThaiXoa)
                     VALUES(?,?,?,?,?,0)
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, nl.getMaNguyenLieu());
            ps.setString(2, nl.getTenNguyenLieu());
            ps.setString(3, nl.getMaDonVi());
            ps.setDouble(4, nl.getTonKho());
            ps.setString(5, nl.getMaNCC());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    // sửa nguyên liệu
    public boolean updateNguyenLieu(NguyenLieuDTO nl){

        String sql = """
                     UPDATE nguyenlieu
                     SET TenNguyenLieu = ?, MaDonVi = ?, TonKho = ?, MaNCC = ?
                     WHERE MaNguyenLieu = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, nl.getTenNguyenLieu());
            ps.setString(2, nl.getMaDonVi());
            ps.setDouble(3, nl.getTonKho());
            ps.setString(4, nl.getMaNCC());
            ps.setString(5, nl.getMaNguyenLieu());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    // xóa mềm
    public boolean deleteNguyenLieu(String maNL){

        String sql = """
                     UPDATE nguyenlieu
                     SET TrangThaiXoa = 1
                     WHERE MaNguyenLieu = ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maNL);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    // tìm kiếm theo tên
    public List<NguyenLieuDTO> searchNguyenLieu(String keyword){

        List<NguyenLieuDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM nguyenlieu
                     WHERE TrangThaiXoa = 0
                     AND TenNguyenLieu LIKE ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                NguyenLieuDTO nl = new NguyenLieuDTO();

                nl.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nl.setMaDonVi(rs.getString("MaDonVi"));
                nl.setTonKho(rs.getDouble("TonKho"));
                nl.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));
                nl.setMaNCC(rs.getString("MaNCC"));

                list.add(nl);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public String getNextId(){

        String sql = """
                     SELECT MaNguyenLieu
                     FROM nguyenlieu
                     ORDER BY MaNguyenLieu DESC
                     LIMIT 1
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            if(rs.next()){

                String lastId = rs.getString("MaNguyenLieu");
                int number = Integer.parseInt(lastId.substring(2));
                number++;

                return String.format("NL%03d", number);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "NL001";
    }
    public NguyenLieuDTO getNguyenLieuById(String maNL){

        String sql = """
                     SELECT *
                     FROM nguyenlieu
                     WHERE MaNguyenLieu = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maNL);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                NguyenLieuDTO nl = new NguyenLieuDTO();

                nl.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                nl.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nl.setMaDonVi(rs.getString("MaDonVi"));
                nl.setTonKho(rs.getDouble("TonKho"));
                nl.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));
                nl.setMaNCC(rs.getString("MaNCC"));

                return nl;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean giamTonKho(String maNguyenLieu, double soLuong){

        String sql = "UPDATE nguyenlieu SET TonKho = TonKho - ? WHERE MaNguyenLieu = ?";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setDouble(1, soLuong);
            ps.setString(2, maNguyenLieu);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
