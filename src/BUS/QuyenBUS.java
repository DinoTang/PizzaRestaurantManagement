package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import java.util.List;

public class QuyenBUS {

    private QuyenDAO quyenDAO = new QuyenDAO();

    public List<QuyenDTO> getAllQuyen(){
        return quyenDAO.getAllQuyen();
    }

    public QuyenDTO getQuyenById(String maQuyen){
        return quyenDAO.getQuyenById(maQuyen);
    }

    public boolean themQuyen(QuyenDTO q){
        return quyenDAO.addQuyen(q);
    }

    public boolean suaQuyen(QuyenDTO q){
        return quyenDAO.updateQuyen(q);
    }

    public boolean xoaQuyen(String maQuyen){
        return quyenDAO.deleteQuyen(maQuyen);
    }

    public boolean isAdmin(String maQuyen){
        return quyenDAO.isAdmin(maQuyen);
    }
    public QuyenDTO getQuyenBangTenQuyen(String tenQuyen){
        return quyenDAO.getQuyenBangTenQuyen(tenQuyen);
    }
}