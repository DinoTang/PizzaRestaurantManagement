/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTHoaDonDAO;
import DTO.CTHoaDonDTO;
import java.util.List;

/**
 *
 * @author quock
 */
public class CTHoaDonBUS {

    private CTHoaDonDAO dao = new CTHoaDonDAO();
    
    public boolean addCTHoaDon(CTHoaDonDTO ct){

        
        ct.setThanhTien(ct.getDonGia() * ct.getSoLuong());

        return dao.addCTHoaDon(ct);
    }
    public List<CTHoaDonDTO> getCTHoaDonByMaHD(String maHD){
        return dao.getCTHoaDonByMaHD(maHD);
    }
}