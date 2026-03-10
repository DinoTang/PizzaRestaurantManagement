package DAO;

import DTO.KhachHangDTO;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public List<KhachHangDTO> getAll(){

        List<KhachHangDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM khachhang WHERE TrangThaiXoa = 0";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                Date date = rs.getDate("NgayTao");
                
                if(date != null){
                    kh.setNgayTao(date.toLocalDate());
                }
                kh.setTongChiTieu(rs.getInt("TongChiTieu"));
                kh.setTrangThaiXoa(rs.getInt("TrangThaiXoa") == 1);

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public List<KhachHangDTO> searchByPhone(String phone){

        List<KhachHangDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM khachhang WHERE TrangThaiXoa = 0 AND SoDienThoai LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,"%"+phone+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setNgayTao(rs.getDate("NgayTao").toLocalDate());
                kh.setTongChiTieu(rs.getInt("TongChiTieu"));

                list.add(kh);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}