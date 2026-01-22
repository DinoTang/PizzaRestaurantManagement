/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

/**
 *
 * @author quock
 */
import java.sql.Connection;
import Utils.DBConnection;
public class PizzaRestaurantManagement {

  public static void main(String[] args) {

        // Test kết nối database
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("test thanh cong.");
        } else {
            System.out.println("test that bai");
        }
    }
}