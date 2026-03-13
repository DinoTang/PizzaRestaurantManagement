package DTO;

public class CTPhieuNhapDTO {
    private String maPN;
    private String maNguyenLieu;
    private int soLuong;
    private int donGia;
    private int thanhTien;

    public CTPhieuNhapDTO() {
    }

    public CTPhieuNhapDTO(String maPN, String maNguyenLieu, int soLuong, int donGia, int thanhTien) {
        this.maPN = maPN;
        this.maNguyenLieu = maNguyenLieu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(String maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
