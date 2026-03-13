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

                loai.setMaLoai(rs.getString("MaLoai"));
                loai.setTenLoai(rs.getString("TenLoai"));

                list.add(loai);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean addLoai(LoaiDTO loai) {

        String sql = """
                     INSERT INTO loai (MaLoai, TenLoai)
                     VALUES (?, ?)
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, loai.getMaLoai());
            ps.setString(2, loai.getTenLoai());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getNextLoaiId() {

        String sql = """
                     SELECT MaLoai
                     FROM loai
                     ORDER BY MaLoai DESC
                     LIMIT 1
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {

                String lastId = rs.getString("MaLoai");

                int number = Integer.parseInt(lastId.substring(1));
                number++;

                return String.format("L%02d", number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "L01";
    }
    public LoaiDTO getLoaiTheoId(String maLoai) {

        LoaiDTO loai = null;

        String sql = "SELECT * FROM loai WHERE MaLoai = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maLoai);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                loai = new LoaiDTO();

                loai.setMaLoai(rs.getString("MaLoai"));
                loai.setTenLoai(rs.getString("TenLoai"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loai;
    }
}