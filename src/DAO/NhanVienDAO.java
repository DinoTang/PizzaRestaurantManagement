package DAO;

import DTO.NhanVienDTO;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class NhanVienDAO {

    public boolean AddNhanVien(NhanVienDTO nhanVien) {

        String sql = """
            INSERT INTO nhanvien 
            (MaNV, HoTen, SoDienThoai, Email, Luong, NgayTao, TrangThaiXoa)
            VALUES (?, ?, ?, ?, ?, CURRENT_DATE, 0)
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, nhanVien.getMaNhanVien());
            ps.setString(2, nhanVien.getHoTen());
            ps.setString(3, nhanVien.getSoDienThoai());
            ps.setString(4, nhanVien.getEmail());
            ps.setDouble(5, nhanVien.getLuong());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateNhanVien(NhanVienDTO nhanVien) {

        String sql = """
            UPDATE nhanvien 
            SET HoTen = ?, 
                SoDienThoai = ?, 
                Email = ?, 
                Luong = ?
            WHERE MaNV = ?
            AND TrangThaiXoa = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, nhanVien.getHoTen());
            ps.setString(2, nhanVien.getSoDienThoai());
            ps.setString(3, nhanVien.getEmail());
            ps.setDouble(4, nhanVien.getLuong());
            ps.setString(5, nhanVien.getMaNhanVien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteNhanVien(NhanVienDTO nhanVien) {

        String sql = """
            UPDATE nhanvien 
            SET TrangThaiXoa = 1 
            WHERE MaNV = ?
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, nhanVien.getMaNhanVien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVienDTO> GetAllNhanVien() {

        List<NhanVienDTO> nhanViens = new ArrayList<>();

        String sql = """
            SELECT * 
            FROM nhanvien
            WHERE TrangThaiXoa = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                NhanVienDTO nv = new NhanVienDTO();

                nv.setMaNhanVien(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setNgayTao(rs.getDate("NgayTao").toLocalDate());
                nv.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                nhanViens.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nhanViens;
    }

    public NhanVienDTO GetNhanVienById(String id) {

        String sql = """
            SELECT *
            FROM nhanvien
            WHERE MaNV = ?
            AND TrangThaiXoa = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    NhanVienDTO nv = new NhanVienDTO();

                    nv.setMaNhanVien(rs.getString("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setLuong(rs.getDouble("Luong"));
                    nv.setNgayTao(rs.getDate("NgayTao").toLocalDate());
                    nv.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                    return nv;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String GetNextNhanVienId() {

        String sql = """
            SELECT MaNV
            FROM nhanvien
            ORDER BY MaNV DESC
            LIMIT 1
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) {

                String lastId = rs.getString("MaNV");
                int number = Integer.parseInt(lastId.substring(2));
                number++;

                return String.format("NV%02d", number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NV01";
    }

    public List<NhanVienDTO> SearchNhanVienByName(String keyword) {

        List<NhanVienDTO> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM nhanvien
            WHERE HoTen LIKE ?
            AND TrangThaiXoa = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    NhanVienDTO nv = new NhanVienDTO();

                    nv.setMaNhanVien(rs.getString("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setLuong(rs.getDouble("Luong"));
                    nv.setNgayTao(rs.getDate("NgayTao").toLocalDate());
                    nv.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                    list.add(nv);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}