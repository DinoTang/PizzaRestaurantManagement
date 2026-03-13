package BUS;

import DAO.GiamGiaDAO;
import DTO.GiamGiaDTO;
import java.util.List;

public class GiamGiaBUS {

    private GiamGiaDAO dao = new GiamGiaDAO();

    public List<GiamGiaDTO> getDanhSach(){

        return dao.getAll();

    }
    public boolean addGiamGia(GiamGiaDTO gg){
        return dao.addGiamGia(gg);
    }
    public boolean updateGiamGia(GiamGiaDTO gg){
        return dao.updateGiamGia(gg);
    }
    public boolean deleteGiamGia(String maGG){
        return dao.deleteGiamGia(maGG);
    }
    public String getNextMaGiamGia(){
        return dao.getNextMaGiamGia();
    }
    public GiamGiaDTO getMaGiamGiaById(String maGiamGia){
        return dao.getMaGiamGiaById(maGiamGia);
    }
}