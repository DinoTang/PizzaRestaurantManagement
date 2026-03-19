package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.List;

public class KhachHangBUS {

    private KhachHangDAO dao = new KhachHangDAO();

    public List<KhachHangDTO> getAllCustomers(){
        return dao.getAll();
    }

    public List<KhachHangDTO> searchCustomer(String key){
        return dao.searchKhachHang(key);
    }

    public boolean addCustomer(KhachHangDTO kh){

        if(kh.getTenKhachHang() == null || kh.getTenKhachHang().isEmpty())
            return false;

        return dao.addKhachHang(kh);
    }

    public boolean updateCustomer(KhachHangDTO kh){
        return dao.updateKhachHang(kh);
    }

    public boolean deleteCustomer(String maKH){
        return dao.deleteKhachHang(maKH);
    }

    public String getNextId(){
        return dao.getNextId();
    }
    public KhachHangDTO getKhachHangById(String maKH){
        return dao.getKhachHangById(maKH);
    }
    public List<KhachHangDTO> searchKhachHang(String keyword){
        return dao.searchKhachHang(keyword);
    }
    public List<KhachHangDTO> searchCustomer(String min, String max){
        return dao.searchCustomer(min, max);
    }
}