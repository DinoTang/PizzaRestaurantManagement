package BUS;

import DAO.VaiTroDAO;
import DTO.VaiTroDTO;

public class VaiTroBUS {
    
    private VaiTroDAO vaiTroDAO;

    public VaiTroBUS(){
        vaiTroDAO = new VaiTroDAO();
    }
    
    public boolean isAdmin(String maVaiTro){
        return maVaiTro.equals("VT01");
    }
    
    public VaiTroDTO getVaiTroById(String maVaiTro){
        if(maVaiTro == null || maVaiTro.trim().isEmpty()){
            return null;
        }
        
        return vaiTroDAO.getVaiTroById(maVaiTro);
    }
}