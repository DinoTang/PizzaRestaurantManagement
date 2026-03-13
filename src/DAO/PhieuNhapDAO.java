package DAO;

import DTO.PhieuNhapDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {

    public List<PhieuNhapDTO> getAllPhieuNhap() {

        List<PhieuNhapDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM phieunhap";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                PhieuNhapDTO pn = new PhieuNhapDTO();

                pn.setMaPN(rs.getString("MaPN"));
                pn.setMaNCC(rs.getString("MaNCC"));
                pn.setMaNV(rs.getString("MaNV"));
                pn.setNgayLap(rs.getDate("NgayLap"));
                pn.setTongTien(rs.getInt("TongTien"));

                list.add(pn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addPhieuNhap(PhieuNhapDTO pn) {

        String sql = """
                     INSERT INTO phieunhap (MaPN, MaNCC, MaNV, NgayLap, TongTien)
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, pn.getMaPN());
            ps.setString(2, pn.getMaNCC());
            ps.setString(3, pn.getMaNV());
            ps.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
            ps.setInt(5, pn.getTongTien());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public PhieuNhapDTO getPhieuNhapById(String maPN) {

        PhieuNhapDTO pn = null;

        String sql = "SELECT * FROM phieunhap WHERE MaPN = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maPN);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                pn = new PhieuNhapDTO();

                pn.setMaPN(rs.getString("MaPN"));
                pn.setMaNCC(rs.getString("MaNCC"));
                pn.setMaNV(rs.getString("MaNV"));
                pn.setNgayLap(rs.getDate("NgayLap"));
                pn.setTongTien(rs.getInt("TongTien"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pn;
    }

    public boolean deletePhieuNhap(String maPN) {

        String sql = "DELETE FROM phieunhap WHERE MaPN = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maPN);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePhieuNhap(PhieuNhapDTO pn) {

        String sql = """
                     UPDATE phieunhap
                     SET MaNCC = ?, MaNV = ?, NgayLap = ?, TongTien = ?
                     WHERE MaPN = ?
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, pn.getMaNCC());
            ps.setString(2, pn.getMaNV());
            ps.setDate(3, new java.sql.Date(pn.getNgayLap().getTime()));
            ps.setInt(4, pn.getTongTien());
            ps.setString(5, pn.getMaPN());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getLastID() {

        String sql = "SELECT MaPN FROM phieunhap ORDER BY MaPN DESC LIMIT 1";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {
                return rs.getString("MaPN");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}