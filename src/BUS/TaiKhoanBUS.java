/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

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
}
