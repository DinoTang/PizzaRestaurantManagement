/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NguyenLieuDAO;
import DTO.NguyenLieuDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quock
 */
public class NguyenLieuBUS {
    private NguyenLieuDAO dao = new NguyenLieuDAO();
    private List<NguyenLieuDTO> listNguyenLieu;
    public List<NguyenLieuDTO> getAllNguyenLieu(){
        return dao.getAllNguyenLieu();
    }
    public boolean addNguyenLieu(NguyenLieuDTO nl){
        return dao.addNguyenLieu(nl);
    }

    public boolean updateNguyenLieu(NguyenLieuDTO nl){
        return dao.updateNguyenLieu(nl);
    }

    public boolean deleteNguyenLieu(String maNL){
        return dao.deleteNguyenLieu(maNL);
    }

    public List<NguyenLieuDTO> searchNguyenLieu(String keyword){
        return dao.searchNguyenLieu(keyword);
    }
    public String getNextId(){
        return dao.getNextId();
    }
    public NguyenLieuDTO getNguyenLieuById(String maNL){
        return dao.getNguyenLieuById(maNL);
    }
     public List<NguyenLieuDTO> tim(String key) {

        if (listNguyenLieu == null) {
            listNguyenLieu = getAllNguyenLieu();
        }

        List<NguyenLieuDTO> result = new ArrayList<>();

        key = key.toLowerCase();

        for (NguyenLieuDTO nl : listNguyenLieu) {

            if (String.valueOf(nl.getMaNguyenLieu()).contains(key)
                    || nl.getTenNguyenLieu().toLowerCase().contains(key)) {

                result.add(nl);
            }
        }

        return result;
    }
      public List<NguyenLieuDTO> getNguyenLieuTheoTen(String tuKhoa) {

        if (listNguyenLieu == null) {
            listNguyenLieu = getAllNguyenLieu();
        }

        List<NguyenLieuDTO> result = new ArrayList<>();

        tuKhoa = tuKhoa.toLowerCase();

        for (NguyenLieuDTO nl : listNguyenLieu) {

            if (nl.getTenNguyenLieu().toLowerCase().contains(tuKhoa)) {

                result.add(nl);
            }
        }

        return result;
    }
    
}
