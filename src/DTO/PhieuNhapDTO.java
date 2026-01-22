/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author quock
 */
public class PhieuNhapDTO {
    private String maPN;
    private String maNCC;
    private String maNhanVien;
    private LocalDateTime thoiGianNhap;
    private double tongTien;
    private double tienChietKhau;
    private double tongTienThanhToan;

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public LocalDateTime getThoiGianNhap() {
        return thoiGianNhap;
    }

    public void setThoiGianNhap(LocalDateTime thoiGianNhap) {
        this.thoiGianNhap = thoiGianNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienChietKhau() {
        return tienChietKhau;
    }

    public void setTienChietKhau(double tienChietKhau) {
        this.tienChietKhau = tienChietKhau;
    }

    public double getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(double tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public boolean isTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(boolean trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
    private boolean trangThaiXoa;

    public PhieuNhapDTO() {}
}
