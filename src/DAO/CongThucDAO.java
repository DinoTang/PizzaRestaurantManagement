/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CongThucDTO;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CongThucDAO {

    Connection conn = null;
    PreparedStatement ps = null;

    // ===================== THÊM =====================
    public boolean addCongThuc(CongThucDTO ct) {

        String sql = "INSERT INTO CongThuc(MaSP, MaNguyenLieu, SoLuong) VALUES (?, ?, ?)";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, ct.getMaSP());
            ps.setString(2, ct.getMaNguyenLieu());
            ps.setDouble(3, ct.getSoLuong());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===================== SỬA =====================
    public boolean updateCongThuc(CongThucDTO ct) {

        String sql = "UPDATE CongThuc SET SoLuong = ? WHERE MaSP = ? AND MaNguyenLieu = ?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setDouble(1, ct.getSoLuong());
            ps.setString(2, ct.getMaSP());
            ps.setString(3, ct.getMaNguyenLieu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===================== XOÁ =====================
    public boolean deleteCongThuc(String maSP, String maNguyenLieu) {

        String sql = "DELETE FROM CongThuc WHERE MaSP = ? AND MaNguyenLieu = ?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, maSP);
            ps.setString(2, maNguyenLieu);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<CongThucDTO> getCongThucTheoSP(String maSP) {

    List<CongThucDTO> list = new ArrayList<>();

    String sql = """
        SELECT nl.TenNguyenLieu, ct.SoLuong, dv.TenDonVi
        FROM CongThuc ct
        JOIN NguyenLieu nl ON ct.MaNguyenLieu = nl.MaNguyenLieu
        JOIN DonVi dv ON nl.MaDonVi = dv.MaDonVi
        WHERE ct.MaSP = ?
        """;

    try {

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maSP);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            CongThucDTO ct = new CongThucDTO();

            ct.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
            ct.setSoLuong(rs.getFloat("SoLuong"));
            ct.setTenDonVi(rs.getString("TenDonVi")); // thêm dòng này

            list.add(ct);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
    }

}
