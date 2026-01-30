/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.VaiTroDTO;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author quock
 */
public class VaiTroDAO {
    public boolean AddVaiTro(VaiTroDTO vaitro){
        String sql = """
                     INSERT INTO vaitro (MAVAITRO, MAQUYEN, TENVAITRO, TRANGTHAIXOA)
                     VALUES (? , ?, ?, 0)
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, vaitro.getMaVaiTro());
            ps.setString(2, vaitro.getMaQuyen());
            ps.setString(3, vaitro.getTenVaiTro());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  UpdateVaiTro(VaiTroDTO vaitro){
        String sql = """
                     UPDATE vaitro
                     SET MAQUYEN = ?,
                         TENVAITRO = ?
                     WHERE MAVAITRO = ? AND TRANGTHAIXOA = 0
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, vaitro.getMaQuyen());
            ps.setString(2, vaitro.getTenVaiTro());
            ps.setString(3, vaitro.getMaVaiTro());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace(); // log để debug
            return false;
        }
    }
    
    public boolean  DeleteVaiTro(VaiTroDTO vaitro){
        String sql = """
                     UPDATE vaitro
                     SET TRANGTHAIXOA = 1
                     WHERE MAVAITRO = ?
                     """;
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, vaitro.getMaVaiTro());
            
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
