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
    public boolean addSanPham(SanPhamDTO sp){
        return sanPhamDAO.addSanPham(sp);
    }
    public String getNextSanPhamId(){
        return sanPhamDAO.getNextSanPhamId();
    }
    public boolean updateSanPham(SanPhamDTO sp){
        return sanPhamDAO.updateSanPham(sp);
    }
    public boolean deleteSanPham(String maSP){
        return sanPhamDAO.deleteSanPham(maSP);
    }
}