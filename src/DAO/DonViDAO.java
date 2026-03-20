package DAO;

import DTO.DonViDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
public class DonViDAO {

    public DonViDTO getDonViById(String maDonVi){

        String sql = "SELECT * FROM donvi WHERE MaDonVi = ? AND TrangThaiXoa = 0";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maDonVi);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                DonViDTO dv = new DonViDTO();

                dv.setMaDonVi(rs.getString("MaDonVi"));
                dv.setTenDonVi(rs.getString("TenDonVi"));
                dv.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                return dv;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public List<DonViDTO> getAllDonVi(){

        List<DonViDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM donvi
                     WHERE TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            while(rs.next()){

                DonViDTO dv = new DonViDTO();

                dv.setMaDonVi(rs.getString("MaDonVi"));
                dv.setTenDonVi(rs.getString("TenDonVi"));
                dv.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                list.add(dv);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    public String getMaDonVi(String tenDonVi) {

        String sql = "SELECT MaDonVi FROM donvi WHERE TenDonVi = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, tenDonVi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("MaDonVi");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}