/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author quock
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cc?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";   // XAMPP mặc định để trống

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Ket noi mysql thanh cong");
        } catch (Exception e) {
            System.out.println("Ket noi mysql that bai");
            e.printStackTrace();
        }
        return conn;
    }
}