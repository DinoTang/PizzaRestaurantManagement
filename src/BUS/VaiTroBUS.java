package BUS;

import DAO.VaiTroDAO;
import DTO.VaiTroDTO;

public class VaiTroBUS {
    
    private VaiTroDAO vaiTroDAO;

    public VaiTroBUS(){
        vaiTroDAO = new VaiTroDAO();
    }
    
    public VaiTroDTO getVaiTroById(String maVaiTro){
        if(maVaiTro == null || maVaiTro.trim().isEmpty()){
            return null;
        }
        
        return vaiTroDAO.getVaiTroById(maVaiTro);
    }
}