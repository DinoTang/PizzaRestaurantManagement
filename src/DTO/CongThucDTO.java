package DTO;

public class CongThucDTO {

    private String maSP;
    private String maNguyenLieu;
    private String tenNguyenLieu;
    private double soLuong;

    public CongThucDTO() {
    }

    public CongThucDTO(String maSP, String maNguyenLieu, double soLuong) {
        this.maSP = maSP;
        this.maNguyenLieu = maNguyenLieu;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }
    
    public String getTenNguyenLieu() {
    return tenNguyenLieu;
}

    public void setTenNguyenLieu(String tenNguyenLieu) {
    this.tenNguyenLieu = tenNguyenLieu;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(String maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
}