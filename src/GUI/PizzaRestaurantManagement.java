/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

/**
 *
 * @author quock
 */
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.List;
public class PizzaRestaurantManagement {

  public static void main(String[] args){
    
        NhanVienDAO nv = new NhanVienDAO();
        NhanVienDTO nhanvien = nv.GetNhanVienById("NV04");
        System.out.println(
            nhanvien.getMaNhanVien() + " - " +
            nhanvien.getHoTen() + " - " +
            nhanvien.getLuong()
        );
    }
}