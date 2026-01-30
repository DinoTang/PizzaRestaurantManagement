/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

/**
 *
 * @author quock
 */
import DAO.CaLamDAO;
import DAO.NhanVienDAO;
import DTO.CaLamDTO;
import DTO.NhanVienDTO;
import java.util.List;
public class PizzaRestaurantManagement {

  public static void main(String[] args){
    
         CaLamDAO dao = new CaLamDAO();

        // 1. Test GetNextId
        System.out.println("Next ID: " + dao.GetNextCaLamId());

        // 2. Test Add
        CaLamDTO ca = new CaLamDTO();
        ca.setMaCa(dao.GetNextCaLamId());
        ca.setTenCa("Ca Sáng");
        ca.setThoiGianBD("08:00");
        ca.setThoiGianKT("12:00");
        System.out.println("Add: " + dao.AddCaLam(ca));

        // 3. Test GetAll
        System.out.println("=== GET ALL ===");
        List<CaLamDTO> list = dao.GetAllCaLam();
        list.forEach(c -> 
            System.out.println(c.getMaCa() + " - " + c.getTenCa())
        );

        // 4. Test GetById
        System.out.println("=== GET BY ID ===");
        CaLamDTO cl = dao.GetCaLamById(ca.getMaCa());
        System.out.println(cl != null ? cl.getTenCa() : "Không tìm thấy");

        // 5. Test Update
        ca.setTenCa("Ca Sáng Update");
        System.out.println("Update: " + dao.UpdateCaLam(ca));

        // 6. Test Search
        System.out.println("=== SEARCH ===");
        dao.SearchCaLamByName("Sáng")
            .forEach(c -> System.out.println(c.getTenCa()));

        // 7. Test Delete
        System.out.println("Delete: " + dao.DeleteCaLam(ca));
}}