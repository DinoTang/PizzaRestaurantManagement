package DAO;

import DTO.SanPhamDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    public boolean addSanPham(SanPhamDTO sp){

        String sql = "INSERT INTO sanpham (MaSP,TenSP,MaLoai,SoLuong,DonViTinh,HinhAnh,DonGia,TrangThaiXoa) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setString(3, sp.getMaLoai());
            ps.setInt(4, sp.getSoLuong());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getHinhAnh());
            ps.setInt(7, sp.getDonGia());
            ps.setBoolean(8, sp.isTrangThaiXoa());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSanPham(SanPhamDTO sp){

        String sql = "UPDATE sanpham SET TenSP=?,MaLoai=?,SoLuong=?,DonViTinh=?,HinhAnh=?,DonGia=? WHERE MaSP=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getMaLoai());
            ps.setInt(3, sp.getSoLuong());
            ps.setString(4, sp.getDonViTinh());
            ps.setString(5, sp.getHinhAnh());
            ps.setInt(6, sp.getDonGia());
            ps.setString(7, sp.getMaSP());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSanPham(String maSP){

        String sql = "UPDATE sanpham SET TrangThaiXoa = 1 WHERE MaSP=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, maSP);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<SanPhamDTO> getAllSanPham(){

        List<SanPhamDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM sanpham WHERE TrangThaiXoa = 0";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                SanPhamDTO sp = new SanPhamDTO();

                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoai(rs.getString("MaLoai"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setDonGia(rs.getInt("DonGia"));
                sp.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    public String getTenSP(String maSP) {

        String sql = "SELECT TenSP FROM sanpham WHERE MaSP = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSP);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("TenSP");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public SanPhamDTO getSanPhamById(String maSP){

        SanPhamDTO sp = null;

        String sql = "SELECT * FROM sanpham WHERE MaSP=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, maSP);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                sp = new SanPhamDTO();

                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoai(rs.getString("MaLoai"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setDonGia(rs.getInt("DonGia"));
                sp.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return sp;
    }

    public List<SanPhamDTO> searchSanPhamByName(String keyword){

        List<SanPhamDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM sanpham WHERE TenSP LIKE ? AND TrangThaiXoa = 0";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,"%"+keyword+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                SanPhamDTO sp = new SanPhamDTO();

                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoai(rs.getString("MaLoai"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setDonGia(rs.getInt("DonGia"));
                sp.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public boolean updateSoLuongTang(String maSP, int soLuong){

        String sql = "UPDATE sanpham SET SoLuong = SoLuong + ? WHERE MaSP = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, soLuong);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean updateSoLuongGiam(String maSP, int soLuong){

        String sql = "UPDATE sanpham SET SoLuong = SoLuong - ? WHERE MaSP = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, soLuong);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public String getNextSanPhamId(){

        String sql = """
                     SELECT MaSP
                     FROM sanpham
                     ORDER BY CAST(SUBSTRING(MaSP, 3) AS UNSIGNED) DESC
                     LIMIT 1
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            if(rs.next()){

                String lastId = rs.getString("MaSP");

                int number = Integer.parseInt(lastId.substring(2));
                number++;

                return String.format("SP%03d", number);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "SP001";
    }
}