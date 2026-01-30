/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LichLamViecDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
/**
 *
 * @author quock
 */
public class LichLamViecDAO {
    public boolean AddLichLamViec(LichLamViecDTO lichLamViec)
    {
        String sql = "INSERT INTO lichlamviec (MALICH, MANHANVIEN, MACA, NGAYLAM, TRANGTHAIXOA)"
                + "VALUES (?, ?, ?, ?, 0)";
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, lichLamViec.getMaLich());
            ps.setString(2, lichLamViec.getMaNhanVien());
            ps.setString(3, lichLamViec.getMaCa());
            ps.setDate(4, Date.valueOf(lichLamViec.getNgayLam()));


            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean UpdateLichLamViec(LichLamViecDTO lichLamViec) {
        String sql = """
                    UPDATE lichlamviec 
                    SET MANHANVIEN = ?, 
                        MACA = ?, 
                        NGAYLAM = ? 
                    WHERE MALICH = ? AND TRANGTHAIXOA = 0
                    """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, lichLamViec.getMaNhanVien());
            ps.setString(2, lichLamViec.getMaCa());
            ps.setDate(3, Date.valueOf(lichLamViec.getNgayLam()));
            ps.setString(4, lichLamViec.getMaLich());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteLichLamViec(LichLamViecDTO lichLamViec)
    {
        String sql = """
                    UPDATE lichlamviec SET 
                    TRANGTHAIXOA = 1 
                    WHERE MALICH = ?
                    """;
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, lichLamViec.getMaLich());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<LichLamViecDTO> GetAllLichLamViec()
    {   List<LichLamViecDTO> list = new ArrayList();
        String sql = """
                     SELECT * FROM lichlamviec
                     WHERE TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()){
                LichLamViecDTO llv = new LichLamViecDTO();
                llv.setMaLich(rs.getString("MALICH"));
                llv.setMaNhanVien(rs.getString("MANHANVIEN"));
                llv.setMaCa(rs.getString("MACA"));
                llv.setNgayLam(rs.getDate("NGAYLAM").toLocalDate());
                llv.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                
                list.add(llv);
            }
        } catch (Exception e) {
             e.printStackTrace(); 
        }
        return list;
    }
    
    public LichLamViecDTO GetLichLamViecById(String id) {
        String sql = """
            SELECT *
            FROM lichlamviec
            WHERE MALICH = ?
              AND TRANGTHAIXOA = 0
            """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LichLamViecDTO llv = new LichLamViecDTO();
                    llv.setMaLich(rs.getString("MALICH"));
                    llv.setMaNhanVien(rs.getString("MANHANVIEN"));
                    llv.setMaCa(rs.getString("MACA"));
                    llv.setNgayLam(rs.getDate("NGAYLAM").toLocalDate());
                    llv.setTrangThaiXoa(rs.getBoolean("TRANGTHAIXOA"));
                    return llv;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String GetNextLichLamViecId(){
        String sql = """
                     SELECT MALICH 
                     FROM lichlamviec
                     ORDER BY MALICH DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MALICH");
                int number = Integer.parseInt(lastId.substring(1));
                number++;
                
                return String.format("L%02d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "L01";
    }
    
//    public List<LichLamViecDTO> SearchLichLamViecByName(String keyword){
//       return null;
//    }
}
