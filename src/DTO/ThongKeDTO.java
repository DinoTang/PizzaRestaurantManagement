//package DTO;
//public class ThongKeDTO {
//    private String ten;
//    private int soLuong;
//
//    public ThongKeDTO() {}
//
//    public ThongKeDTO(String ten, int soLuong) {
//        this.ten = ten;
//        this.soLuong = soLuong;
//    }
//
//    public String getTen() {
//        return ten;
//    }
//
//    public void setTen(String ten) {
//        this.ten = ten;
//    }
//
//    public int getSoLuong() {
//        return soLuong;
//    }
//
//    public void setSoLuong(int soLuong) {
//        this.soLuong = soLuong;
//    }
//}
package DTO;

public class ThongKeDTO {

    private String ten;
    private double giaTri;

    public ThongKeDTO() {}

    public ThongKeDTO(String ten, double giaTri) {
        this.ten = ten;
        this.giaTri = giaTri;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}