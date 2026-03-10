package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

import java.util.List;

public class KhachHangBUS {

    private KhachHangDAO dao = new KhachHangDAO();

    public List<KhachHangDTO> getAllCustomers(){
        return dao.getAll();
    }

    public List<KhachHangDTO> searchCustomer(String phone){
        return dao.searchByPhone(phone);
    }

}