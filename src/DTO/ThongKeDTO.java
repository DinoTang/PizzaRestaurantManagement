package DTO;
public class ThongKeDTO {
    private String ten;
    private double soLuong;

    public ThongKeDTO() {}

    public ThongKeDTO(String ten, double soLuong) {
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
}
