package GUI;

import DTO.HoaDonDTO;
import DTO.CTHoaDonDTO;
import BUS.HoaDonBUS;
import BUS.CTHoaDonBUS;
import BUS.KhachHangBUS;
import BUS.SanPhamBUS;
import BUS.NhanVienBUS;

import DTO.KhachHangDTO;
import DTO.NhanVienDTO;

import Custom.TransparentPanel;
import Custom.MyTable;
import Utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.*;
import java.io.File;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;


public class PnQuanLyHoaDonGUI extends JPanel {

    HoaDonBUS hoaDonBUS = new HoaDonBUS();
    CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    SanPhamBUS spBUS = new SanPhamBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();

    MyTable tblHoaDon;
    MyTable tblChiTietHoaDon;

    DefaultTableModel dtmHoaDon;
    DefaultTableModel dtmChiTiet;

    JButton btnXuatPDF;

    final Color colorPanel = new Color(247,247,247);

    public PnQuanLyHoaDonGUI() {
        addControls();
        addEvents();
        loadDataHoaDon();
    }

    private void addControls() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ HÓA ĐƠN</h1></html>");
        pnTitle.add(lblTitle);
        this.add(pnTitle);

        JPanel pnTableHD = new TransparentPanel(new BorderLayout());

        dtmHoaDon = new DefaultTableModel(
                new Object[]{"Mã HD","Khách hàng","Ngày lập","Nhân viên","Tổng tiền"},0
        ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tblHoaDon = new MyTable(dtmHoaDon);

        // căn giữa ngày lập và tổng tiền
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tblHoaDon.getColumnModel().getColumn(2).setCellRenderer(center);
        tblHoaDon.getColumnModel().getColumn(4).setCellRenderer(center);

        JScrollPane scrHD = new JScrollPane(tblHoaDon);
        pnTableHD.add(scrHD,BorderLayout.CENTER);

        this.add(pnTableHD);

        JPanel pnTableCT = new TransparentPanel(new BorderLayout());

        JLabel lblCT = new JLabel("CHI TIẾT HÓA ĐƠN");
        lblCT.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));

        pnTableCT.add(lblCT,BorderLayout.NORTH);

        dtmChiTiet = new DefaultTableModel(
                new Object[]{"Mã SP","Tên SP","Số lượng","Đơn giá","Thành tiền"},0
        ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tblChiTietHoaDon = new MyTable(dtmChiTiet);
        tblChiTietHoaDon.setRowSelectionAllowed(false);
        tblChiTietHoaDon.setFocusable(false);

        JScrollPane scrCT = new JScrollPane(tblChiTietHoaDon);
        pnTableCT.add(scrCT,BorderLayout.CENTER);

        // nút xuất pdf
        JPanel pnBtn = new TransparentPanel();
        btnXuatPDF = new JButton("Xuất PDF");
        java.awt.Font fontButton = new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16);
        btnXuatPDF.setFont(fontButton);
        btnXuatPDF.setIcon(Constants.loadIcon("/images/PDF-icon.png"));
        pnBtn.add(btnXuatPDF);
        
        this.add(pnBtn);
        this.add(pnTableCT);
    }

    private void addEvents() {

        tblHoaDon.getSelectionModel().addListSelectionListener(e -> {

            int row = tblHoaDon.getSelectedRow();

            if(row >= 0){
                String maHD = tblHoaDon.getValueAt(row,0).toString();
                loadChiTietHoaDon(maHD);
            }
        });

        btnXuatPDF.addActionListener(e -> xuatPDF());
    }

    private void loadDataHoaDon() {

        dtmHoaDon.setRowCount(0);

        List<HoaDonDTO> list = hoaDonBUS.getAllHoaDon();

        for(HoaDonDTO hd : list){

            Vector row = new Vector();

            row.add(hd.getMaHD());

            KhachHangDTO kh = khBUS.getKhachHangById(hd.getMaKH());
            if(kh != null)
                row.add(kh.getTenKhachHang());
            else
                row.add("Khách lẻ");

            row.add(hd.getNgayLap());

            NhanVienDTO nv = nvBUS.getNhanVienById(hd.getMaNV());
            if(nv != null)
                row.add(nv.getHoTen());
            else
                row.add("Không rõ");

            row.add(hd.getTongTien());

            dtmHoaDon.addRow(row);
        }
    }

    private void loadChiTietHoaDon(String maHD){

        dtmChiTiet.setRowCount(0);

        List<CTHoaDonDTO> list = ctHoaDonBUS.getCTHoaDonByMaHD(maHD);

        for(CTHoaDonDTO ct : list){

            Vector row = new Vector();

            row.add(ct.getMaSanPham());

            var sp = spBUS.getSanPhamById(ct.getMaSanPham());
            row.add(sp != null ? sp.getTenSP() : "Không tồn tại");

            row.add(ct.getSoLuong());
            row.add(ct.getDonGia());
            row.add(ct.getThanhTien());

            dtmChiTiet.addRow(row);
        }
    }

    private void xuatPDF() {

        int row = tblHoaDon.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
            return;
        }

        String maHD = tblHoaDon.getValueAt(row, 0).toString();
        String tenKH = tblHoaDon.getValueAt(row, 1).toString();
        String ngayLap = tblHoaDon.getValueAt(row, 2).toString();
        String tenNV = tblHoaDon.getValueAt(row, 3).toString();
        String tongTien = tblHoaDon.getValueAt(row, 4).toString();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu file PDF");
        chooser.setSelectedFile(new File("HoaDon_" + maHD + ".pdf"));

        int result = chooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = chooser.getSelectedFile().getAbsolutePath();

        if (!filePath.endsWith(".pdf")) {
            filePath += ".pdf";
        }

        try {

            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            BaseFont baseFont = BaseFont.createFont(
                    getClass().getResource("/font/arial.ttf").toString(),
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 20, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(baseFont, 12);
            com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);

            DecimalFormat formatter = new DecimalFormat("#,###");

            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Mã hóa đơn: " + maHD, normalFont));
            document.add(new Paragraph("Khách hàng: " + tenKH, normalFont));
            document.add(new Paragraph("Nhân viên: " + tenNV, normalFont));
            document.add(new Paragraph("Ngày lập: " + ngayLap, normalFont));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 2, 2, 2});

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Mã SP", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tên SP", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Số lượng", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Đơn giá", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Thành tiền", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            List<CTHoaDonDTO> list = ctHoaDonBUS.getCTHoaDonByMaHD(maHD);

            for (CTHoaDonDTO ct : list) {

                var sp = spBUS.getSanPhamById(ct.getMaSanPham());
                String tenSP = sp != null ? sp.getTenSP() : "Không tồn tại";

                table.addCell(new Phrase(ct.getMaSanPham(), normalFont));
                table.addCell(new Phrase(tenSP, normalFont));

                cell = new PdfPCell(new Phrase(String.valueOf(ct.getSoLuong()), normalFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(formatter.format(ct.getDonGia()) + " đ", normalFont));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(formatter.format(ct.getThanhTien()) + " đ", normalFont));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph tong = new Paragraph(
                    "Tổng tiền: " + formatter.format(Double.parseDouble(tongTien)) + " đ",
                    boldFont
            );
            tong.setAlignment(Element.ALIGN_RIGHT);

            document.add(tong);

            document.close();

            JOptionPane.showMessageDialog(this,
                    "Xuất PDF thành công!\nFile lưu tại:\n" + filePath);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất PDF thất bại!");
        }
    }
}