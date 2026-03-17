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
    
    public boolean kiemTraMaNV(String maNV){
        boolean result = false;
        try{
            String sql = "SELECT * FROM taikhoan WHERE MaNV = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, maNV);

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                result = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public String getTenDangNhapTheoMaNV(String maNV) {

        String sql = "SELECT TenDangNhap FROM TaiKhoan WHERE MaNV=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, maNV);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getString("TenDangNhap");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    // reset mật khẩu
    public boolean datLaiMatKhau(String maNV, String matKhauMoi) {

        String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, matKhauMoi);
            pre.setString(2, maNV);

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // cập nhật quyền
    public boolean datLaiQuyen(String maNV, String maQuyen) {

        String sql = "UPDATE TaiKhoan SET MaQuyen=? WHERE MaNV=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, maQuyen);
            pre.setString(2, maNV);

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public String getQuyenTheoMaNV(String maNV) {

        String sql = "SELECT MaQuyen FROM TaiKhoan WHERE MaNV=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, maNV);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getString("MaQuyen");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}