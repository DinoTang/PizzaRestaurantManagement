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
}