package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.List;

public class NhaCungCapBUS {

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

    public List<NhaCungCapDTO> getAllNhaCungCap(){
        return nhaCungCapDAO.getAllNhaCungCap();
    }

    public boolean addNhaCungCap(NhaCungCapDTO ncc){

        if(ncc.getTenNCC() == null || ncc.getTenNCC().isEmpty())
            return false;

        return nhaCungCapDAO.addNhaCungCap(ncc);
    }

    public boolean updateNhaCungCap(NhaCungCapDTO ncc){
        return nhaCungCapDAO.updateNhaCungCap(ncc);
    }

    public boolean deleteNhaCungCap(String maNCC){
        return nhaCungCapDAO.deleteNhaCungCap(maNCC);
    }

    public String getNextId(){
        return nhaCungCapDAO.getNextId();
    }
    public NhaCungCapDTO getNCCById(String maNCC)
    {
        return nhaCungCapDAO.getNCCById(maNCC);
    }
    public String getMaNCC(String ten) {

        for (NhaCungCapDTO ncc : getAllNhaCungCap()) {

            if (ncc.getTenNCC().equals(ten)) {
                return ncc.getMaNCC();
            }
        }

        return null;
    }
}