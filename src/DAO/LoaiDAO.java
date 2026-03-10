package DAO;

import DTO.LoaiDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LoaiDAO {

    public List<LoaiDTO> getAllLoai() {

        List<LoaiDTO> list = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM loai";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LoaiDTO loai = new LoaiDTO();

                loai.setMaLoai(rs.getString("maLoai"));
                loai.setTenLoai(rs.getString("tenLoai"));

                list.add(loai);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}