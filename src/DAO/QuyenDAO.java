/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.QuyenDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quock
 */
public class QuyenDAO {
    public boolean AddQuyen(QuyenDTO quyen){
        String sql = """
                     INSERT INTO quyen (MAQUYEN, TENQUYEN, TRANGTHAIXOA)
                     VALUES (?, ?, 0)
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, quyen.getMaQuyen());
            ps.setString(2, quyen.getTenQuyen());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  UpdateQuyen(QuyenDTO quyen){
        String sql = """
                     UPDATE quyen
                     SET TENQUEYN = ?
                     WHERE MAQUYEN = ? AND TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, quyen.getTenQuyen());
            ps.setString(2, quyen.getMaQuyen());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  DeleteQuyen(QuyenDTO quyen){
        String sql = """
                     UPDATE quyen
                     SET TRANGTHAIXOA = 1
                     WHERE MAQUYEN = ?
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, quyen.getMaQuyen());
            
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String GetNextQuyenId(){
        String sql = """
                     SELECT MAQUYEN 
                     FROM vaitro
                     ORDER BY MAQUYEN DESC
                     LIMIT 1
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ){
            if(rs.next()){
                String lastId = rs.getString("MAQUYEN");
                int number = Integer.parseInt(lastId.substring(2));
                number++;
                
                return String.format("Q%01d", number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return "Q01";
    }
}
