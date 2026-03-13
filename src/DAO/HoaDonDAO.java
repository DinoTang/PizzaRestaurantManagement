package DAO;

import DTO.HoaDonDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
public class HoaDonDAO {

    public boolean addHoaDon(HoaDonDTO hd){

        try{

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO hoadon(MaHD,MaKH,MaNV,MaGiamGia,NgayLap,TongTien) VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getMaKH());
            ps.setString(3, hd.getMaNV());
            ps.setString(4, hd.getMaGiamGia());
            ps.setObject(5, hd.getNgayLap());
            ps.setDouble(6, hd.getTongTien());

            ps.executeUpdate();

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public String getNextMaHoaDon() {

        String maHD = "HD001";

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT MaHD FROM hoadon ORDER BY CAST(SUBSTRING(MaHD,3) AS UNSIGNED) DESC LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String lastID = rs.getString("MaHD");

                int number = Integer.parseInt(lastID.substring(2));

                number++;

                maHD = String.format("HD%03d", number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return maHD;
    }
    public List<HoaDonDTO> getAllHoaDon(){

        List<HoaDonDTO> list = new ArrayList<>();

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM hoadon";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                HoaDonDTO hd = new HoaDonDTO();

                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaGiamGia(rs.getString("MaGiamGia"));

                Date date = rs.getDate("NgayLap");

                if(date != null){
                    hd.setNgayLap(date.toLocalDate());
                }

                hd.setTongTien(rs.getDouble("TongTien"));

                list.add(hd);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public HoaDonDTO getHoaDonById(String maHD){

        HoaDonDTO hd = null;

        try{

            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM hoadon WHERE MaHD = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maHD);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                hd = new HoaDonDTO();

                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaGiamGia(rs.getString("MaGiamGia"));

                Date date = rs.getDate("NgayLap");

                if(date != null){
                    hd.setNgayLap(date.toLocalDate());
                }

                hd.setTongTien(rs.getDouble("TongTien"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return hd;
    }
}