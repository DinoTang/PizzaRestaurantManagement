package DAO;

import DTO.GiamGiaDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiamGiaDAO {

    public List<GiamGiaDTO> getAll(){

        List<GiamGiaDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM giamgia WHERE TrangThaiXoa = 0";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                GiamGiaDTO km = new GiamGiaDTO();

                km.setMaGiam(rs.getString("MaGiamGia"));
                km.setTenGiamGia(rs.getString("TenGiamGia"));
                km.setPhanTramGiam(rs.getInt("PhanTramGiam"));
                km.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                list.add(km);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public boolean addGiamGia(GiamGiaDTO km){

        String sql = """
                     INSERT INTO giamgia(MaGiamGia, TenGiamGia, PhanTramGiam, TrangThaiXoa)
                     VALUES(?,?,?,0)
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, km.getMaGiam());
            ps.setString(2, km.getTenGiamGia());
            ps.setInt(3, km.getPhanTramGiam());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateGiamGia(GiamGiaDTO km){

        String sql = """
                     UPDATE giamgia
                     SET TenGiamGia = ?, PhanTramGiam = ?
                     WHERE MaGiamGia = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, km.getTenGiamGia());
            ps.setInt(2, km.getPhanTramGiam());
            ps.setString(3, km.getMaGiam());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteGiamGia(String maGiamGia){

        String sql = """
                     UPDATE giamgia
                     SET TrangThaiXoa = 1
                     WHERE MaGiamGia = ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maGiamGia);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public String getNextMaGiamGia() {

        String maGG = "GG001";

        try {

            Connection conn = DBConnection.getConnection();

            String sql = """
                         SELECT MaGiamGia
                         FROM giamgia
                         ORDER BY CAST(SUBSTRING(MaGiamGia,3) AS UNSIGNED) DESC
                         LIMIT 1
                         """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String lastID = rs.getString("MaGiamGia");

                int number = Integer.parseInt(lastID.substring(2));

                number++;

                maGG = String.format("GG%03d", number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maGG;
    }
    public GiamGiaDTO getMaGiamGiaById(String maGiamGia){

        String sql = "SELECT * FROM giamgia WHERE MaGiamGia = ? AND TrangThaiXoa = 0";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maGiamGia);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                GiamGiaDTO gg = new GiamGiaDTO();

                gg.setMaGiam(rs.getString("MaGiamGia"));
                gg.setTenGiamGia(rs.getString("TenGiamGia"));
                gg.setPhanTramGiam(rs.getInt("PhanTramGiam"));
                gg.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                return gg;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}