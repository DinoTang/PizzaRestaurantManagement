/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import Utils.DBConnection;
import java.sql.Connection;

/**
 *
 * @author quock
 */

public class Main {

  public static void main(String[] args){
    Connection conn = DBConnection.getConnection();
    if(conn == null) return;
    Utils.WindowUtil.showWindow(new DangNhapGUI());
}}