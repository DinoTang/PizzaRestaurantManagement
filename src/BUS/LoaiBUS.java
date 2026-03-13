package BUS;

import DAO.LoaiDAO;
import DTO.LoaiDTO;
import java.util.List;

public class LoaiBUS {

    private LoaiDAO loaiDAO = new LoaiDAO();

    public List<LoaiDTO> getAllLoai() {
        return loaiDAO.getAllLoai();
    }
     public boolean addLoai(LoaiDTO loai){

        if(loai.getTenLoai() == null || loai.getTenLoai().trim().isEmpty())
            return false;

        return loaiDAO.addLoai(loai);
    } 
    public String getNextLoaiId(){
        return loaiDAO.getNextLoaiId();
    }
    public LoaiDTO getLoaiTheoId(String maLoai){
        return loaiDAO.getLoaiTheoId(maLoai);
    }
}