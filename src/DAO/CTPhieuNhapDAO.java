package DAO;

import DTO.CTPhieuNhapDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTPhieuNhapDAO {

    public List<CTPhieuNhapDTO> getAllCTPhieuNhap() {

        List<CTPhieuNhapDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM ctphieunhap";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();

                ctpn.setMaPN(rs.getString("MaPN"));
                ctpn.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getInt("DonGia"));
                ctpn.setThanhTien(rs.getInt("ThanhTien"));

                list.add(ctpn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CTPhieuNhapDTO> getCTPhieuNhapTheoMaPN(String maPN) {

        List<CTPhieuNhapDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM ctphieunhap WHERE MaPN = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maPN);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();

                ctpn.setMaPN(rs.getString("MaPN"));
                ctpn.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getInt("DonGia"));
                ctpn.setThanhTien(rs.getInt("ThanhTien"));

                list.add(ctpn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CTPhieuNhapDTO> getCTPhieuNhapTheoMaNguyenLieu(String maNguyenLieu) {

        List<CTPhieuNhapDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM ctphieunhap WHERE MaNguyenLieu = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maNguyenLieu);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();

                ctpn.setMaPN(rs.getString("MaPN"));
                ctpn.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getInt("DonGia"));
                ctpn.setThanhTien(rs.getInt("ThanhTien"));

                list.add(ctpn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addCTPhieuNhap(CTPhieuNhapDTO ctpn) {

        String sqlUpdateSP = """
                             UPDATE sanpham
                             SET SoLuong = SoLuong + ?
                             WHERE MaSP = ?
                             """;

        String sqlInsert = """
                           INSERT INTO ctphieunhap
                           (MaPN, MaNguyenLieu, SoLuong, DonGia, ThanhTien)
                           VALUES (?, ?, ?, ?, ?)
                           """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateSP);
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
        ) {

            psUpdate.setInt(1, ctpn.getSoLuong());
            psUpdate.setString(2, ctpn.getMaNguyenLieu());
            psUpdate.executeUpdate();

            psInsert.setString(1, ctpn.getMaPN());
            psInsert.setString(2, ctpn.getMaNguyenLieu());
            psInsert.setInt(3, ctpn.getSoLuong());
            psInsert.setInt(4, ctpn.getDonGia());
            psInsert.setInt(5, ctpn.getThanhTien());

            return psInsert.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCTPhieuNhap(String maPN) {

        String sql = "DELETE FROM ctphieunhap WHERE MaPN = ?";

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

    public boolean deleteCTPhieuNhap(String maPN, String maNguyenLieu) {

        String sql = "DELETE FROM ctphieunhap WHERE MaPN = ? AND MaNguyenLieu = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, maPN);
            ps.setString(2, maNguyenLieu);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCTPhieuNhap(CTPhieuNhapDTO ctpn) {

        String sql = """
                     UPDATE ctphieunhap
                     SET MaNguyenLieu = ?, SoLuong = ?, DonGia = ?, ThanhTien = ?
                     WHERE MaPN = ?
                     """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, ctpn.getMaNguyenLieu());
            ps.setInt(2, ctpn.getSoLuong());
            ps.setInt(3, ctpn.getDonGia());
            ps.setInt(4, ctpn.getThanhTien());
            ps.setString(5, ctpn.getMaPN());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}