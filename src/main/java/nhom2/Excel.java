package nhom2;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.STTextVertOverflowTypeImpl;

public class Excel {
    public int rowLuong = 0;
    public int rowChiPhi = 0;
    public int rowNganHang = 0;
    public int rowNoHangThang = 0;
    public int rowCacKhoanNo = 0;

    // protected Workbook wb = null;

    public Excel() {

    }

    public Workbook readExcel(String excelFilePath) throws IOException {
        Workbook workbook = checkWorkBook(excelFilePath);

        Sheet sheet = workbook.getSheetAt(0);
        this.rowLuong = sheet.getLastRowNum() + 1;

        sheet = workbook.getSheetAt(1);
        this.rowChiPhi = sheet.getLastRowNum() + 1;

        sheet = workbook.getSheetAt(2);
        this.rowNganHang = sheet.getLastRowNum() + 1;

        sheet = workbook.getSheetAt(3);
        this.rowNoHangThang = sheet.getLastRowNum() + 1;

        sheet = workbook.getSheetAt(4);
        this.rowNoHangThang = sheet.getLastRowNum() + 1;

        // createOutputFile(workbook, excelFilePath);

        return workbook;
    }

    public Workbook createExcel(String excelFilePath) throws IOException {
        // Get Workbook
        Workbook workbook = checkWorkBook(excelFilePath);

        workbook.createSheet("Luong"); // Create sheet with sheet name
        workbook.createSheet("Chi Phi"); // Create sheet with sheet name
        workbook.createSheet("Ngan Hang"); // Create sheet with sheet name
        workbook.createSheet("Lai No Hang Thang"); // Create sheet with sheet name
        workbook.createSheet("Cac Khoan No"); // Create sheet with sheet name

        createOutputFile(workbook, excelFilePath);

        return workbook;
    }

    public void writeExcelThuNhap(Workbook workbook, String excelFilePath, ThuNhap thuNhap, Date today)
            throws IOException {
        // Get sheet
        Sheet sheet = workbook.getSheet("Luong");

        Row row = sheet.createRow(this.rowLuong);

        writeHeader(sheet, row);

        // Write data
        row = sheet.createRow(this.rowLuong);

        Cell cell = row.createCell(0);
        cell.setCellValue(today.toString());

        cell = row.createCell(1);
        cell.setCellValue(thuNhap.getLuongVoChong());

        cell = row.createCell(2);
        cell.setCellValue(thuNhap.getLuongChung());

        cell = row.createCell(3);
        cell.setCellValue(thuNhap.getLuongChungDu());

        cell = row.createCell(4);
        cell.setCellValue(thuNhap.getThuNhapTraNo());

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
        ++this.rowLuong;
    }

    public void writeExcelChiPhi(Workbook workbook, String excelFilePath, ChiPhi chiPhi, Date today)
            throws IOException {
        Sheet sheet = workbook.getSheet("Chi Phi");

        Row row = sheet.createRow(this.rowChiPhi);

        writeHeader(sheet, row);

        // Write data
        row = sheet.createRow(this.rowChiPhi);

        Cell cell = row.createCell(0);
        cell.setCellValue(today.toString());

        cell = row.createCell(1);
        cell.setCellValue(chiPhi.getDien());

        cell = row.createCell(2);
        cell.setCellValue(chiPhi.getNuoc());

        cell = row.createCell(3);
        cell.setCellValue(chiPhi.getAnUong());

        cell = row.createCell(4);
        cell.setCellValue(chiPhi.getChiPhiKhac());

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
        ++this.rowChiPhi;
    }

    public void writeExcelNganHang(Workbook workbook, String excelFilePath, NganHang nganHang, Date today)
            throws IOException {
        // Get sheet
        Sheet sheet = workbook.getSheet("Ngan Hang");

        Row row = sheet.createRow(this.rowNganHang);

        writeHeader(sheet, row);

        row = sheet.createRow(this.rowNganHang);
        // Write data
        // i
        int count = nganHang.getKhoanGui().size();
        for (int i = 0; i < count; ++i) {
            row = sheet.createRow(this.rowNganHang);
            SoNganHang soNganHang = nganHang.getKhoanGui().get(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(i + 1); // placeholder

            cell = row.createCell(1);
            cell.setCellValue(soNganHang.getNgayGui().toString());

            cell = row.createCell(2);
            cell.setCellValue(soNganHang.getSoTienGui());

            cell = row.createCell(3);
            cell.setCellValue(soNganHang.getPhanTramLai());

            cell = row.createCell(4);
            cell.setCellValue(soNganHang.getNgayTra().toString());

            cell = row.createCell(5);
            cell.setCellValue(soNganHang.getChuKy());

            cell = row.createCell(6);
            cell.setCellValue(soNganHang.getLai() - soNganHang.getSoTienGui());

            cell = row.createCell(7);
            cell.setCellValue(soNganHang.getLai());
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
        ++this.rowNganHang;
    }

    public void writeExcelLaiNoHangThang(Workbook workbook, String excelFilePath, Vector<KhoanNo> khoanNos, Date today)
            throws IOException {
        // Get sheet
        Sheet sheet = workbook.getSheet("Lai No Hang Thang");

        Row row = sheet.createRow(this.rowNoHangThang);

        writeHeader(sheet, row);

        row = sheet.createRow(this.rowNoHangThang);

        // Write data
        Cell cell = row.createCell(0);
        cell.setCellValue(today.toString());

        int noChung = 0;
        for (int i = 0; i < khoanNos.size(); ++i) {
            noChung += khoanNos.get(i).getLai(today);
        }
        cell = row.createCell(1);
        cell.setCellValue(noChung);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        ++this.rowNoHangThang;
    }

    public void writeExcelCacKhoanNo(Workbook workbook, String excelFilePath, Vector<KhoanNo> khoanNos, Date today)
            throws IOException {
        Sheet sheet = workbook.getSheet("Cac Khoan No");

        Row row = sheet.createRow(this.rowCacKhoanNo);

        writeHeader(sheet, row);

        row = sheet.createRow(this.rowCacKhoanNo);
        // Write data
        // i
        int count = khoanNos.size();
        for (int i = 0; i < count; ++i) {
            row = sheet.createRow(this.rowCacKhoanNo);
            KhoanNo khoanNo = khoanNos.get(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(i + 1); // placeholder

            cell = row.createCell(1);
            cell.setCellValue(khoanNo.getTienNo());

            cell = row.createCell(2);
            cell.setCellValue(khoanNo.getNgayTraNo().toString());

            cell = row.createCell(3);
            cell.setCellValue(khoanNo.getChuKy());

            cell = row.createCell(4);
            if (khoanNo.getLinhHoat()) {
                cell.setCellValue("Linh hoạt");
            } else {
                cell.setCellValue("Cố định");
            }

            cell = row.createCell(5);
            if (!khoanNo.getLinhHoat()) {
                cell.setCellValue(khoanNo.getPhanTramLai());
            }
            ++this.rowCacKhoanNo;
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
    }

    public static Workbook checkWorkBook(String excelFilePath) throws IOException {
        File file = new File(excelFilePath);

        Workbook workbook = null;

        if (file.exists()) {
            if (excelFilePath.endsWith("xlsx")) {
                InputStream inputStream = new FileInputStream(new File(excelFilePath));
                workbook = new XSSFWorkbook(inputStream);

            } else if (excelFilePath.endsWith("xls")) {
                throw new IllegalArgumentException("The xls file is not supported");
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } else {
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook();
            } else if (excelFilePath.endsWith("xls")) {
                throw new IllegalArgumentException("The xls file is not supported");
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        }

        return workbook;
    }

    public void writeHeader(Sheet sheet, Row row) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create cells
        if (sheet.getSheetName() == "Luong" && this.rowLuong == 0) {
            ++this.rowLuong;
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tháng");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Lương vợ chồng");

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Lương chung");

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Lương dư");

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tiền để trả nợ");
        }

        if (sheet.getSheetName() == "Chi Phi" && this.rowChiPhi == 0) {
            ++this.rowChiPhi;
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tháng");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tiền điện");

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tiền nước");

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Ăn uống");

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Chi phí khác");
        }

        if (sheet.getSheetName() == "Ngan Hang" && this.rowNganHang == 0) {
            ++this.rowNganHang;
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("STT");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tháng gửi");

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Số tiền");

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Phần trăm lãi");

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tháng trả");

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Chu kỳ lãi");

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tiền lãi");

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tổng cộng");
        }

        if (sheet.getSheetName() == "Lai No Hang Thang" && this.rowNoHangThang == 0) {
            ++this.rowNoHangThang;
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tháng");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tổng lãi");
        }

        if (sheet.getSheetName() == "Cac Khoan No" && this.rowCacKhoanNo == 0) {
            ++this.rowCacKhoanNo;
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("STT");

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Số tiền nợ");

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Hạn trả");

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Chu kỳ");

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Linh hoạt");

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Phần trăm lãi");
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    public List<String> getDataRow(Sheet sheet, int rowNum) {

        Row row = sheet.getRow(rowNum);

        List<String> arr = new ArrayList<>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            switch (cell.getCellType()) {
                case STRING:
                    arr.add(cell.getStringCellValue());
                    break;
                case NUMERIC:
                    DecimalFormat decimalFormat = new DecimalFormat("#.0#");
                    arr.add("0" + decimalFormat.format(cell.getNumericCellValue()));
                    break;
                case BLANK:
                    arr.add("");
                    break;
                default:
                    break;
            }
        }
        return arr;
    }

    public Date getToday(Workbook wb) {
        Sheet sheet = wb.getSheet("Luong");

        List<String> data = getDataRow(sheet, sheet.getLastRowNum());

        String[] parts = data.get(0).split("/");

        Date ans = new Date(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

        return ans;
    }

    public ThuNhap getThuNhap(Workbook wb) {
        Sheet sheet = wb.getSheet("Luong");

        List<String> data = getDataRow(sheet, sheet.getLastRowNum());

        int luongVoChong = Integer.parseInt(data.get(1).substring(0, data.get(1).length() - 2));

        int luongChung = Integer.parseInt(data.get(2).substring(0, data.get(2).length() - 2));

        int luongChungDu = Integer.parseInt(data.get(3).substring(0, data.get(3).length() - 2));

        int thuNhapTraNo = Integer.parseInt(data.get(4).substring(0, data.get(4).length() - 2));

        ThuNhap ans = new ThuNhap(luongVoChong, luongChung, luongChungDu, thuNhapTraNo);

        return ans;
    }

    public ChiPhi getChiPhi(Workbook wb) {
        Sheet sheet = wb.getSheet("Chi phi");

        List<String> data = getDataRow(sheet, sheet.getLastRowNum());

        // Tien dien
        int tienDien = Integer.parseInt(data.get(1).substring(0, data.get(1).length() - 2));
        // Tien dien
        int tienNuoc = Integer.parseInt(data.get(2).substring(0, data.get(2).length() - 2));
        // Tien dien
        int tienAnUong = Integer.parseInt(data.get(3).substring(0, data.get(3).length() - 2));
        // Tien dien
        int tienChiPhiKhac = Integer.parseInt(data.get(4).substring(0, data.get(4).length() - 2));

        ChiPhi ans = new ChiPhi(tienDien, tienNuoc, tienAnUong, tienChiPhiKhac);

        return ans;
    }

    public NganHang getNganHang(Workbook wb) {
        Sheet sheet = wb.getSheet("Ngan hang");

        int countRow = sheet.getLastRowNum();
        NganHang ans = new NganHang();

        for (int i = 1; i <= countRow; i++) {
            List<String> data = getDataRow(sheet, i);
            // Thang gui
            Date thangGui = new Date();
            String[] parts = data.get(1).split("/");
            thangGui.setMonth(Integer.parseInt(parts[0]));
            thangGui.setYear(Integer.parseInt(parts[1]));
            // So tien
            int soTien = Integer.parseInt(data.get(2).substring(0, data.get(2).length() - 2));
            // Phan tram lai

            double phanTramLai = Double.parseDouble(data.get(3));
            // Thang tra
            Date thangTra = new Date();
            parts = data.get(4).split("/");
            thangTra.month = Integer.parseInt(parts[0]);
            thangTra.year = Integer.parseInt(parts[1]);
            // Chu ky lai
            int chuKy = Integer.parseInt(data.get(5).substring(0, data.get(5).length() - 2));
            SoNganHang temp = new SoNganHang(soTien, thangGui, thangTra, phanTramLai, chuKy);
            ans.addI(temp);
        }
        return ans;
    }

    public Vector<KhoanNo> getKhoanNo(Workbook wb) {
        Sheet sheet = wb.getSheet("Cac Khoan No");

        int countRow = sheet.getLastRowNum();
        Vector<KhoanNo> ans = new Vector<KhoanNo>();

        for (int i = 1; i <= countRow; i++) {
            List<String> data = getDataRow(sheet, i);
            // So tien no
            int soTien = Integer.parseInt(data.get(1).substring(0, data.get(1).length() - 2));

            // Han tra
            Date hanTra = new Date();
            String[] parts = data.get(2).split("/");
            hanTra.setMonth(Integer.parseInt(parts[0]));
            hanTra.setYear(Integer.parseInt(parts[1]));

            // Chu ky
            int chuKy = Integer.parseInt(data.get(3).substring(0, data.get(3).length() - 2));

            // Linh hoat
            boolean linhHoat = data.get(4) == "Cố định" ? false : true;

            // Phan tram lai
            double phanTramLai = 0;
            if (data.size() == 6) {
                if (data.get(5) != "") {
                    phanTramLai = Double.parseDouble(data.get(5));
                }
            }

            KhoanNo temp = new KhoanNo(soTien, chuKy, linhHoat, new Date(5, 2022), hanTra, phanTramLai);
            ans.add(temp);
        }
        return ans;
    }

}
