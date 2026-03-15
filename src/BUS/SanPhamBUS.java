package BUS;

import DAO.CongThucDAO;
import DAO.NguyenLieuDAO;
import DAO.SanPhamDAO;
import DTO.CongThucDTO;
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
    public boolean updateSoLuongTang(String maSP, int soLuong){
        return sanPhamDAO.updateSoLuongTang(maSP, soLuong);
    }
    public boolean updateSoLuongGiam(String maSP, int soLuong){
        return sanPhamDAO.updateSoLuongGiam(maSP, soLuong);
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
    
    public void giamNguyenLieuTheoCongThuc(String maSP, int soLuongSanPham){

        CongThucDAO ctDAO = new CongThucDAO();
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();

        List<CongThucDTO> list = ctDAO.getCongThucBySanPham(maSP);

        for(CongThucDTO ct : list){

            double soLuongCanTru = ct.getSoLuong() * soLuongSanPham;

            nlDAO.giamTonKho(ct.getMaNguyenLieu(), soLuongCanTru);
        }
    }
}