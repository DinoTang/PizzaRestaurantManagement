package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.List;

public class HoaDonBUS {

    private HoaDonDAO dao = new HoaDonDAO();

    public boolean taoHoaDon(HoaDonDTO hd){

        if(hd == null) return false;

        return dao.addHoaDon(hd);
    }
    
    public String getNextMaHoaDon()
    {
        return dao.getNextMaHoaDon();
    }
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();

    public List<HoaDonDTO> getAllHoaDon(){
        return hoaDonDAO.getAllHoaDon();
    }
}