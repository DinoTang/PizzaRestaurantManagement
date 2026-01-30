/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CaLamDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quock
 */
public class CaLamDAO {
    public boolean AddCaLam(CaLamDTO caLam)
    {
        String sql = "INSERT INTO calam (MACA, TENCA, THOIGIANBD, THOIGIANKT, TRANGTHAIXOA)"
                + "VALUES (?, ?, ?, ?, 0)";
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, caLam.getMaCa());
            ps.setString(2, caLam.getTenCa());
            ps.setString(3, caLam.getThoiGianBD());
            ps.setString(4, caLam.getThoiGianKT());


            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean UpdateCaLam(CaLamDTO caLam) {
        String sql = """
                    UPDATE calam 
                    SET TENCA = ?, "
                        THOIGIANBD = ?, 
                        THOIGIANKT = ? 
                    WHERE MACA = ? AND TRANGTHAIXOA = 0
                    """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, caLam.getTenCa());
            ps.setString(2, caLam.getThoiGianBD());
            ps.setString(3, caLam.getThoiGianKT());
            ps.setString(4, caLam.getMaCa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteCaLam(CaLamDTO caLam)
    {
        String sql = """
                    UPDATE calam SET 
                    TRANGTHAIXOA = 1 
                    WHERE MACA = ?
                    """;
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, caLam.getMaCa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<CaLamDTO> GetAllCaLam()
    {   List<CaLamDTO> list = new ArrayList();
        String sql = """
                     SELECT * FROM calam
                     WHERE TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()){
                CaLamDTO cl = new CaLamDTO();
                cl.setMaCa(rs.getString("MACA"));
                cl.setTenCa(rs.getString("TENCA"));
                cl.setThoiGianBD(rs.getString("THOIGIANBD"));
                cl.setThoiGianKT(rs.getString("THOIGIANKT"));
                cl.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                
                list.add(cl);
            }
        } catch (Exception e) {
             e.printStackTrace(); 
        }
        return list;
    }
    
    public CaLamDTO GetCaLamById(String id) {
        String sql = """
            SELECT *
            FROM calam
            WHERE MACA = ?
              AND TRANGTHAIXOA = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CaLamDTO cl = new CaLamDTO();
                    cl.setMaCa(rs.getString("MACA"));
                    cl.setTenCa(rs.getString("TENCA"));
                    cl.setThoiGianBD(rs.getString("THOIGIANBD"));
                    cl.setThoiGianKT(rs.getString("THOIGIANKT"));
                    cl.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                    return cl;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String GetNextCaLamId(){
        String sql = """
                     SELECT MACA 
                     FROM calam
                     ORDER BY MACA DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MACA");
                int number = Integer.parseInt(lastId.substring(2));
                number++;
                
                return String.format("CA%02d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return "CA01";
    }
    
    public List<CaLamDTO> SearchCaLamByName(String keyword){
        List<CaLamDTO> list = new ArrayList();
        String sql = """
                     SELECT * 
                     FROM calam
                     WHERE TENCA LIKE ?
                     AND TRANGTHAIXOA = 0
                     """;
        try(
             Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, "%" + keyword + "%");
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    CaLamDTO cl = new CaLamDTO();
                    cl.setMaCa(rs.getString("MACA"));
                    cl.setTenCa(rs.getString("TENCA"));
                    cl.setThoiGianBD(rs.getString("THOIGIANBD"));
                    cl.setThoiGianKT(rs.getString("THOIGIANKT"));
                    cl.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                    
                    list.add(cl);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return list;
    }
}
