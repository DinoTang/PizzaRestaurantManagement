/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CongThucDAO;
import DTO.CongThucDTO;

import java.util.ArrayList;
import java.util.List;

public class CongThucBUS {

    private CongThucDAO ctDAO = new CongThucDAO();

    private ArrayList<CongThucDTO> listCongThuc;

    public CongThucBUS() {
        listCongThuc = new ArrayList<>();
    }

    // ===================== THÊM =====================
    public boolean addCongThuc(CongThucDTO ct) {

        boolean flag = ctDAO.addCongThuc(ct);

        if(flag){
            listCongThuc.add(ct);
        }

        return flag;
    }

    // ===================== SỬA =====================
    public boolean updateCongThuc(CongThucDTO ct){

        boolean flag = ctDAO.updateCongThuc(ct);

        if(flag){
            for(CongThucDTO c : listCongThuc){

                if(c.getMaSP().equals(ct.getMaSP()) &&
                   c.getMaNguyenLieu().equals(ct.getMaNguyenLieu())){

                    c.setSoLuong(ct.getSoLuong());
                    break;
                }
            }
        }

        return flag;
    }

    // ===================== XOÁ =====================
    public boolean deleteCongThuc(String maSP, String maNguyenLieu){

        boolean flag = ctDAO.deleteCongThuc(maSP, maNguyenLieu);

        if(flag){
            listCongThuc.removeIf(ct ->
                    ct.getMaSP().equals(maSP) &&
                    ct.getMaNguyenLieu().equals(maNguyenLieu)
            );
        }

        return flag;
    }
    
    public List<CongThucDTO> getCongThucTheoSP(String maSP) {
    return ctDAO.getCongThucTheoSP(maSP);
    }
    
    public String getMaByTen(String MaNL){
        return ctDAO.getMaByTen(MaNL);
    }

}
