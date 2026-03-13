package DTO;

public class NguyenLieuDTO {

    private String maNguyenLieu;
    private String tenNguyenLieu;
    private String maDonVi;
    private double tonKho;
    private String maNCC;
    private boolean trangThaiXoa;

    public NguyenLieuDTO(){}

    public NguyenLieuDTO(String maNguyenLieu, String tenNguyenLieu, String maDonVi,
                         double tonKho, boolean trangThaiXoa, String maNCC) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.maDonVi = maDonVi;
        this.tonKho = tonKho;
        this.trangThaiXoa = trangThaiXoa;
        this.maNCC = maNCC;
    }

    public String getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(String maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public double getTonKho() {
        return tonKho;
    }

    public void setTonKho(double tonKho) {
        this.tonKho = tonKho;
    }

    public boolean isTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(boolean trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
}