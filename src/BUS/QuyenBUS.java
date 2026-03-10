package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;

public class QuyenBUS {

    private QuyenDAO quyenDAO = new QuyenDAO();

    public QuyenDTO getQuyenById(String maQuyen){
        return quyenDAO.getQuyenById(maQuyen);
    }

    public boolean isAdmin(String maQuyen){
        return quyenDAO.isAdmin(maQuyen);
    }

}