/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quock
 */
public class BanDTO {
    private String maBan;
    private String tenBan;
    private boolean dangSuDung;
    private String maDonHienTai;

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public boolean isDangSuDung() {
        return dangSuDung;
    }

    public void setDangSuDung(boolean dangSuDung) {
        this.dangSuDung = dangSuDung;
    }

    public String getMaDonHienTai() {
        return maDonHienTai;
    }

    public void setMaDonHienTai(String maDonHienTai) {
        this.maDonHienTai = maDonHienTai;
    }

    public BanDTO() {}
}
