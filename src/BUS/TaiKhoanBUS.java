/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.MyDialog;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

/**
 *
 * @author quock
 */
public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public TaiKhoanDTO Login(String username, String password) {

        if(username == null || username.isEmpty())
            return null;

        if(password == null || password.isEmpty())
            return null;

        return this.taiKhoanDAO.Login(username, password);
    }
    
    public boolean themTaiKhoan(TaiKhoanDTO tk){
        return taiKhoanDAO.AddTaiKhoan(tk);
    }
    public String GetNextTaiKhoanId()
    {
        return taiKhoanDAO.GetNextTaiKhoanId();
    }
    
    public boolean daCoTaiKhoan(String maNV){
        return taiKhoanDAO.kiemTraMaNV(maNV);
    }
    
    public String getTenDangNhapTheoMa(String maNV) {
        return taiKhoanDAO.getTenDangNhapTheoMaNV(maNV);
    }

    // lấy quyền
    public String getQuyenTheoMa(String maNV) {
        return taiKhoanDAO.getQuyenTheoMaNV(maNV);
    }

    public boolean datLaiMatKhau(String maNV, String matKhauMoi) {
        return taiKhoanDAO.datLaiMatKhau(maNV, matKhauMoi);
    }

    public boolean datLaiQuyen(String maNV, String maQuyen) {
        return taiKhoanDAO.datLaiQuyen(maNV, maQuyen);
    }
}
