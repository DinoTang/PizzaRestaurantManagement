/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;

/**
 *
 * @author quock
 */
public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public boolean Login(String username, String password) {

        if(username == null || username.isEmpty())
            return false;

        if(password == null || password.isEmpty())
            return false;

        return this.taiKhoanDAO.Login(username, password);
    }
}
