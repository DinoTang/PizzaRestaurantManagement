package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.List;

public class SanPhamBUS {

    private SanPhamDAO sanPhamDAO = new SanPhamDAO();

    public List<SanPhamDTO> getAllSanPham(){
        return sanPhamDAO.getAllSanPham();
    }

    public List<SanPhamDTO> searchSanPham(String keyword){
        return sanPhamDAO.searchSanPhamByName(keyword);
    }
    public boolean updateSoLuong(String maSP, int soLuong){
        return sanPhamDAO.updateSoLuong(maSP, soLuong);
    }
    public SanPhamDTO getSanPhamById(String maSP){
        return sanPhamDAO.getSanPhamById(maSP);
    }
}