package DAO;

import DTO.NhaCungCapDTO;
import Utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public List<NhaCungCapDTO> getAllNhaCungCap(){

        List<NhaCungCapDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM nhacungcap
                     WHERE TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            while(rs.next()){

                NhaCungCapDTO ncc = new NhaCungCapDTO(
                        rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DienThoai"),
                        rs.getString("DiaChi"),
                        rs.getBoolean("TrangThaiXoa")
                );

                list.add(ncc);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    // thêm nhà cung cấp
    public boolean addNhaCungCap(NhaCungCapDTO ncc){

        String sql = """
                     INSERT INTO nhacungcap(MaNCC, TenNCC, DienThoai, DiaChi, TrangThaiXoa)
                     VALUES(?,?,?,?,0)
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDienThoai());
            ps.setString(4, ncc.getDiaChi());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // sửa nhà cung cấp
    public boolean updateNhaCungCap(NhaCungCapDTO ncc){

        String sql = """
                     UPDATE nhacungcap
                     SET TenNCC = ?, SoDienThoai = ?, DiaChi = ?
                     WHERE MaNCC = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getDienThoai());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getMaNCC());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // xóa mềm
    public boolean deleteNhaCungCap(String maNCC){

        String sql = """
                     UPDATE nhacungcap
                     SET TrangThaiXoa = 1
                     WHERE MaNCC = ?
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maNCC);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // auto id NCC01 NCC02
    public String getNextId(){

        String sql = """
                     SELECT MaNCC
                     FROM nhacungcap
                     ORDER BY MaNCC DESC
                     LIMIT 1
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){

            if(rs.next()){

                String lastId = rs.getString("MaNCC");
                int number = Integer.parseInt(lastId.substring(3));
                number++;

                return String.format("NCC%02d", number);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "NCC01";
    }
    public NhaCungCapDTO getNCCById(String maNCC){

        String sql = """
                     SELECT *
                     FROM nhacungcap
                     WHERE MaNCC = ? AND TrangThaiXoa = 0
                     """;

        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){

            ps.setString(1, maNCC);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return new NhaCungCapDTO(
                        rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DienThoai"),
                        rs.getString("DiaChi"),
                        rs.getBoolean("TrangThaiXoa")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}