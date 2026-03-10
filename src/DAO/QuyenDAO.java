package DAO;

import DTO.QuyenDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuyenDAO {

    public QuyenDTO getQuyenById(String maQuyen){
        String sql = "SELECT * FROM phanquyen WHERE MaQuyen = ?";

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, maQuyen);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                QuyenDTO q = new QuyenDTO();

                q.setMaQuyen(rs.getString("MaQuyen"));
                q.setTenQuyen(rs.getString("TenQuyen"));

                return q;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean isAdmin(String maQuyen){
        return maQuyen.equals("Q01");
    }

}