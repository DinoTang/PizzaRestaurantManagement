/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucNangDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quock
 */
public class ChucNangDAO {
    public boolean AddChucNang(ChucNangDTO chucnang){
        String sql = """
                     INSERT INTO chucnang (MACHUCNANG, TENCHUCNANG, TRANGTHAIXOA)
                     VALUES (?, ?, 0)
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, chucnang.getMaChucNang());
            ps.setString(2, chucnang.getTenChucNang());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  UpdateChucNang(ChucNangDTO chucnang){
        String sql = """
                     UPDATE chucnang
                     SET TENCHUCNANG = ?
                     WHERE MACHUCNANG = ? AND TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, chucnang.getTenChucNang());
            ps.setString(2, chucnang.getMaChucNang());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  DeleteChucNang(ChucNangDTO chucnang){
        String sql = """
                     UPDATE chucnang
                     SET TRANGTHAIXOA = 1
                     WHERE MACHUCNANG = ?
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, chucnang.getMaChucNang());
            
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
     public String GetNextChucNangId(){
        String sql = """
                     SELECT MACHUCNANG 
                     FROM vaitro
                     ORDER BY MACHUCNANG DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MACHUCNANG");
                int number = Integer.parseInt(lastId.substring(2));
                number++;
                
                return String.format("CN%02d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return "CN01";
    }
}
