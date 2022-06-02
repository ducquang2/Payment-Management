package nhom2;

import java.util.Vector;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    public int rowLuong = 0;
    public int rowChiPhi = 0;
    public int rowNganHang = 0;
    public int rowNoHangThang = 0;
    public int rowCacKhoanNo = 0;

    public Excel() {

    }

    public Workbook createExcel(String excelFilePath) throws IOException {
        // File xlsxFile = new File(excelFilePath);

        // FileInputStream inputStream = new FileInputStream(xlsxFile);

        // Get Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheetLuong = workbook.createSheet("Luong"); // Create sheet with sheet name
        Sheet sheetChiphi = workbook.createSheet("Chi Phi"); // Create sheet with sheet name
        Sheet sheetNganHang = workbook.createSheet("Ngan Hang"); // Create sheet with sheet name
        Sheet sheetNoLinhHoat = workbook.createSheet("Lai No Hang Thang"); // Create sheet with sheet name
        Sheet sheetCacKhoanNo = workbook.createSheet("Cac Khoan No"); // Create sheet with sheet name

        // writeHeader(sheetChiphi, rowChiPhi);
        // writeHeader(sheetNganHang, rowNganHang);
        // writeHeader(sheetNo, rowNo);

        createOutputFile(workbook, excelFilePath);
        // workbook.close();
        return workbook;
    }

    public void writeExcelThuNhap(Workbook workbook, String excelFilePath, ThuNhap thuNhap, Date today)
            throws IOException {
        // Workbook workbook = getWorkbook(excelFilePath);

        // Sheet sheet = workbook.createSheet("Luong"); // Create sheet with sheet name
        // Get sheet
        // System.out.println(workbook.getNumberOfSheets());

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

        // Write footer
        // writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        ++this.rowLuong;
        // System.out.println("Done!!!");
    }

    public void writeExcelChiPhi(Workbook workbook, String excelFilePath, ChiPhi chiPhi, Date today)
            throws IOException {
        // Get sheet
        // Workbook workbook = getWorkbook(excelFilePath);

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

        // Create file excel
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
            cell.setCellValue(soNganHang.getLai() - soNganHang.getSoTienGui());

            cell = row.createCell(6);
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
            ++this.rowCacKhoanNo;
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
    }

    public static Workbook getWorkbook(String excelFilePath) {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
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
            cell.setCellValue("Tiền lãi");

            cell = row.createCell(6);
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
}
