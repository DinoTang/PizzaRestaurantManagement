package DAO;

import DTO.SanPhamDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    public boolean addSanPham(SanPhamDTO sp){
        try{
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO sanpham VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getMaLoai());
            ps.setDouble(4, sp.getGia());
            ps.setString(5, sp.getHinh());
            ps.setBoolean(6, sp.isTrangThaiXoa());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSanPham(SanPhamDTO sp){
        try{
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE sanpham SET TENSANPHAM=?, MALOAI=?, GIA=?, HINH=? WHERE MASANPHAM=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getTenSanPham());
            ps.setString(2, sp.getMaLoai());
            ps.setDouble(3, sp.getGia());
            ps.setString(4, sp.getHinh());
            ps.setString(5, sp.getMaSanPham());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSanPham(String maSanPham){
        try{
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE sanpham SET TRANGTHAIXOA = 1 WHERE MASANPHAM=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maSanPham);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<SanPhamDTO> getAllSanPham(){

        List<SanPhamDTO> list = new ArrayList<>();

        try{
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM sanpham WHERE TRANGTHAIXOA = 0";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                SanPhamDTO sp = new SanPhamDTO();

                sp.setMaSanPham(rs.getString("MASANPHAM"));
                sp.setTenSanPham(rs.getString("TENSANPHAM"));
                sp.setMaLoai(rs.getString("MALOAI"));
                sp.setGia(rs.getDouble("GIA"));
                sp.setHinh(rs.getString("HINH"));
                sp.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public SanPhamDTO getSanPhamById(String maSanPham){

        SanPhamDTO sp = null;

        try{
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM sanpham WHERE MASANPHAM=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maSanPham);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                sp = new SanPhamDTO();

                sp.setMaSanPham(rs.getString("MASANPHAM"));
                sp.setTenSanPham(rs.getString("TENSANPHAM"));
                sp.setMaLoai(rs.getString("MALOAI"));
                sp.setGia(rs.getDouble("GIA"));
                sp.setHinh(rs.getString("HINH"));
                sp.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return sp;
    }

    public String getNextSanPhamId(){

        String nextId = "SP001";

        try{
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT MASANPHAM FROM sanpham ORDER BY MASANPHAM DESC LIMIT 1";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){

                String lastId = rs.getString("MASANPHAM");

                int num = Integer.parseInt(lastId.substring(2)) + 1;

                nextId = "SP" + String.format("%03d", num);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return nextId;
    }

    public List<SanPhamDTO> searchSanPhamByName(String keyword){

        List<SanPhamDTO> list = new ArrayList<>();

        try{
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM sanpham WHERE TENSANPHAM LIKE ? AND TRANGTHAIXOA = 0";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                SanPhamDTO sp = new SanPhamDTO();

                sp.setMaSanPham(rs.getString("MASANPHAM"));
                sp.setTenSanPham(rs.getString("TENSANPHAM"));
                sp.setMaLoai(rs.getString("MALOAI"));
                sp.setGia(rs.getDouble("GIA"));
                sp.setHinh(rs.getString("HINH"));
                sp.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}