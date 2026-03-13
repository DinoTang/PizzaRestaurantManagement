/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import java.util.List;
import DAO.DonViDAO;
import DTO.DonViDTO;

/**
 *
 * @author quock
 */
public class DonViBUS {
    private DonViDAO dao = new DonViDAO();

    public DonViDTO getDonViById(String maDonVi){
        return dao.getDonViById(maDonVi);
    }
    public List<DonViDTO> getAllDonVi(){
        return dao.getAllDonVi();
    }
    public String getMaDonVi(String tenDonVi) {

        for (DonViDTO dv : getAllDonVi()) {
            if (dv.getTenDonVi().equals(tenDonVi)) {
                return dv.getMaDonVi();
            }
        }

        return null;
    }
}   
