package DTO;

public class CartItemDTO {

    private String maSP;
    private String tenSP;
    private String size;
    private double gia;
    private String img;
    private int soLuong;

    public CartItemDTO(String maSP, String tenSP, String size, double gia, String img) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.size = size;
        this.gia = gia;
        this.img = img;
        this.soLuong = 1;
    }

    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public String getSize() { return size; }
    public double getGia() { return gia; }
    public String getImg() { return img; }
    public int getSoLuong() { return soLuong; }

    public void tangSoLuong(){
        soLuong++;
    }
    public void giamSoLuong(){
    soLuong--;
}
}