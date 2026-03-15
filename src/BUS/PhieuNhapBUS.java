package BUS;

import Custom.MyDialog;
import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuNhapBUS {

    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    private List<PhieuNhapDTO> listPhieuNhap = null;

    public PhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = phieuNhapDAO.getAllPhieuNhap();
    }

    public List<PhieuNhapDTO> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }

    public boolean themPhieuNhap(String nhaCungCap, String nhanVien, int tongTien) {

        String[] NCC = nhaCungCap.split(" - ");
        String[] NV = nhanVien.split(" - ");

        String maNCC = NCC[0];
        String maNV = NV[0];

        PhieuNhapDTO pn = new PhieuNhapDTO();
        pn.setMaNCC(maNCC);
        pn.setMaNV(maNV);
        pn.setTongTien(tongTien);
        pn.setMaPN(phieuNhapDAO.getMaPhieuNhapMoi());
        pn.setNgayLap(new Date());

        return phieuNhapDAO.addPhieuNhap(pn);
    }

    public String getLastID() {
        return phieuNhapDAO.getLastID();
    }

    public PhieuNhapDTO timPhieuNhap(String maPN) {
        String ma = maPN;
        for (PhieuNhapDTO pn : listPhieuNhap) {
            if (pn.getMaPN().equals(ma)) {
                return pn;
            }
        }
        return null;
    }

    public ArrayList<PhieuNhapDTO> getListPhieuNhapTheoGia(String giaThap, String giaCao) {
        try {
            int min = Integer.parseInt(giaThap);
            int max = Integer.parseInt(giaCao);
            if (max < min) {
                new MyDialog("Hãy nhập khoảng giá phù hợp!", MyDialog.ERROR_DIALOG);
                return null;
            }
            ArrayList<PhieuNhapDTO> result = new ArrayList<>();
            for (PhieuNhapDTO pn : listPhieuNhap) {
                if (pn.getTongTien() <= max && pn.getTongTien() >= min) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên cho khoảng giá!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<PhieuNhapDTO> getListPhieuNhapTheoNgay(String tuNgay, String denNgay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            
            Date min = sdf.parse(tuNgay);
            Date max = sdf.parse(denNgay);
            if (max.before(min)) {
                new MyDialog("Hãy nhập khoảng ngày phù hợp!", MyDialog.ERROR_DIALOG);
                return null;
            }
            ArrayList<PhieuNhapDTO> result = new ArrayList<>();
            for (PhieuNhapDTO pn : listPhieuNhap) {
                if (pn.getNgayLap().after(min) && pn.getNgayLap().before(max)) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
            new MyDialog("Hãy nhập ngày hợp lệ (dd/MM/yyy)!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }
    
    public String getNextID() {

    String lastID = phieuNhapDAO.getLastID();

    if (lastID == null) {
        return "PN001";
    }

    int num = Integer.parseInt(lastID.substring(2));
    num++;

    return String.format("PN%03d", num);
    }

}
