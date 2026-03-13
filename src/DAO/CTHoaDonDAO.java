package DAO;

import DTO.CTHoaDonDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class CTHoaDonDAO {

    public boolean addCTHoaDon(CTHoaDonDTO ct){

        String sql = "INSERT INTO cthoadon(MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES(?,?,?,?,?)";

        try{

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ct.getMaHoaDon());
            ps.setString(2, ct.getMaSanPham());
            ps.setInt(3, ct.getSoLuong());
            ps.setDouble(4, ct.getDonGia());
            ps.setDouble(5, ct.getThanhTien());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public List<CTHoaDonDTO> getCTHoaDonByMaHD(String maHD){

        List<CTHoaDonDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM cthoadon WHERE MaHD = ?";

        try{

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maHD);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                CTHoaDonDTO ct = new CTHoaDonDTO();

                ct.setMaHoaDon(rs.getString("MaHD"));
                ct.setMaSanPham(rs.getString("MaSP"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                ct.setThanhTien(rs.getDouble("ThanhTien"));

                list.add(ct);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}