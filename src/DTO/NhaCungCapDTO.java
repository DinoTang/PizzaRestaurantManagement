/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author quock
 */
public class NhaCungCapDTO {
    private String maNCC;
    private String tenNCC;
    private String DienThoai;
    private String diaChi;
    private boolean trangThaiXoa;
    
    public NhaCungCapDTO(String maNCC, String tenNCC, String DienThoai, String diaChi, boolean trangThaiXoa) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.DienThoai = DienThoai;
        this.diaChi = diaChi;
        this.trangThaiXoa = trangThaiXoa;
    }
    public NhaCungCapDTO() {}

    // getters & setters

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.DienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(boolean trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
}

