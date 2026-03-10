package DAO;

import DTO.TaiKhoanDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public boolean AddTaiKhoan(TaiKhoanDTO taiKhoan)
    {
        String sql = "INSERT INTO taikhoan (MaTK, MaQuyen, MaNV, TenDangNhap, MatKhau, TrangThaiXoa) VALUES (?, ?, ?, ?, ?, 0)";
        
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, taiKhoan.getMaTaiKhoan());
            ps.setString(2, taiKhoan.getMaQuyen());
            ps.setString(3, taiKhoan.getMaNhanVien());
            ps.setString(4, taiKhoan.getTenDangNhap());
            ps.setString(5, taiKhoan.getMatKhau());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateTaiKhoan(TaiKhoanDTO taiKhoan) {

        String sql = """
                    UPDATE taikhoan 
                    SET MaQuyen = ?, 
                        MaNV = ?, 
                        TenDangNhap = ?, 
                        MatKhau = ?
                    WHERE MaTK = ?
                    AND TrangThaiXoa = 0
                    """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, taiKhoan.getMaQuyen());
            ps.setString(2, taiKhoan.getMaNhanVien());
            ps.setString(3, taiKhoan.getTenDangNhap());
            ps.setString(4, taiKhoan.getMatKhau());
            ps.setString(5, taiKhoan.getMaTaiKhoan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteTaiKhoan(TaiKhoanDTO taiKhoan)
    {
        String sql = """
                    UPDATE taikhoan 
                    SET TrangThaiXoa = 1 
                    WHERE MaTK = ?
                    """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, taiKhoan.getMaTaiKhoan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TaiKhoanDTO> GetAllTaiKhoan()
    {
        List<TaiKhoanDTO> list = new ArrayList<>();

        String sql = """
                     SELECT * FROM taikhoan
                     WHERE TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            while(rs.next())
            {
                TaiKhoanDTO tk = new TaiKhoanDTO();

                tk.setMaTaiKhoan(rs.getString("MaTK"));
                tk.setMaQuyen(rs.getString("MaQuyen"));
                tk.setMaNhanVien(rs.getString("MaNV"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                list.add(tk);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public TaiKhoanDTO Login(String username, String password)
    {
        String sql = """
                     SELECT *
                     FROM taikhoan
                     WHERE TenDangNhap = ?
                     AND MatKhau = ?
                     AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                TaiKhoanDTO tk = new TaiKhoanDTO();

                tk.setMaTaiKhoan(rs.getString("MaTK"));
                tk.setMaQuyen(rs.getString("MaQuyen"));
                tk.setMaNhanVien(rs.getString("MaNV"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setTrangThaiXoa(rs.getBoolean("TrangThaiXoa"));

                return tk;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String GetNextTaiKhoanId(){

        String sql = """
                     SELECT MaTK 
                     FROM taikhoan
                     ORDER BY MaTK DESC
                     LIMIT 1
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            if(rs.next())
            {
                String lastId = rs.getString("MaTK");
                int number = Integer.parseInt(lastId.substring(2));
                number++;

                return String.format("TK%02d", number);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "TK01";
    }
}