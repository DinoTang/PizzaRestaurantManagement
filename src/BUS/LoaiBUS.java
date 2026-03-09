package BUS;

import DAO.LoaiDAO;
import DTO.LoaiDTO;
import java.util.List;

public class LoaiBUS {

    private LoaiDAO loaiDAO = new LoaiDAO();

    public List<LoaiDTO> getAllLoai() {
        return loaiDAO.getAllLoai();
    }

}