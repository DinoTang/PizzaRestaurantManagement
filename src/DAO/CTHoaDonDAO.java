package DAO;

import DTO.CTHoaDonDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}