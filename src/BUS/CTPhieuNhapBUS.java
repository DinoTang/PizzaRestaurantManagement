package BUS;

import DAO.CTPhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import java.util.ArrayList;
import java.util.List;
public class CTPhieuNhapBUS {

    private List<CTPhieuNhapDTO> listPhieuNhap = null;
    private CTPhieuNhapDAO cTPhieuNhapDAO = new CTPhieuNhapDAO();

    public CTPhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = cTPhieuNhapDAO.getAllCTPhieuNhap();
    }

    public List<CTPhieuNhapDTO> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }
    
    public List<CTPhieuNhapDTO> getListPhieuNhap(String maPN) {
        List<CTPhieuNhapDTO> dsct = new ArrayList<>();
        String ma = maPN;
        
        for(CTPhieuNhapDTO ct: listPhieuNhap) {
            if(ct.getMaPN().equals(ma)) {
                dsct.add(ct);
            }
        }
        
        return dsct;
    }

    public boolean luuCTPhieuNhap(CTPhieuNhapDTO ctpn) {
        return cTPhieuNhapDAO.addCTPhieuNhap(ctpn);
    }
}
