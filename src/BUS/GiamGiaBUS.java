package BUS;

import DAO.GiamGiaDAO;
import DTO.GiamGiaDTO;
import java.util.List;

public class GiamGiaBUS {

    private GiamGiaDAO khuyenMaiDAO = new GiamGiaDAO();

    public List<GiamGiaDTO> getDanhSach(){

        return khuyenMaiDAO.getAll();

    }

}