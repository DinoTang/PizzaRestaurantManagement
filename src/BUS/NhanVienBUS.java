/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.List;

/**
 *
 * @author quock
 */
public class NhanVienBUS {
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public NhanVienDTO getNhanVienById(String id) {
        return nhanVienDAO.GetNhanVienById(id);
    }
    public List<NhanVienDTO> GetAllNhanVien(){
        return nhanVienDAO.GetAllNhanVien();
    }
}
