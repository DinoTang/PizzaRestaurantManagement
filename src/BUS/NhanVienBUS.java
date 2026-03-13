/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.List;

public class NhanVienBUS {

    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public List<NhanVienDTO> getAllNhanVien(){
        return nhanVienDAO.GetAllNhanVien();
    }

    public NhanVienDTO getNhanVienById(String id){
        return nhanVienDAO.GetNhanVienById(id);
    }

    public boolean addNhanVien(NhanVienDTO nv){
        return nhanVienDAO.AddNhanVien(nv);
    }

    public boolean updateNhanVien(NhanVienDTO nv){
        return nhanVienDAO.UpdateNhanVien(nv);
    }

    public boolean deleteNhanVien(NhanVienDTO nv){
        return nhanVienDAO.DeleteNhanVien(nv);
    }

    public List<NhanVienDTO> searchNhanVienByName(String name){
    return nhanVienDAO.SearchNhanVienByName(name);
    }

    public String getNextId(){
        return nhanVienDAO.GetNextNhanVienId();
    }
}
