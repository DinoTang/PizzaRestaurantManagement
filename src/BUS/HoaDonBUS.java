package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

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
}