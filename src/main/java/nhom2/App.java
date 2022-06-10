package nhom2;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Workbook;

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        // Initialize
        Scanner scanner = new Scanner(System.in);

        ThuNhap thuNhap = new ThuNhap();
        ChiPhi chiPhi = new ChiPhi();
        NganHang nganHang = new NganHang();
        Date today = new Date(5, 2022);
        Vector<KhoanNo> khoanNos = new Vector<KhoanNo>();
        Boolean[] check = { false, false, false, false, false, false, false, false, false };
        Date lastDate = new Date(today);

        // Create excel file path
        // System.out.println("Nhap ten file: ");
        String fileName = "Output";
        // fileName = scanner.nextLine();
        String excelPath = "./" + fileName + ".xlsx"; // "./Output.xlsx";
        Excel excel = new Excel();
        // Workbook book = excel.createExcel(excelPath);
        File file = new File(excelPath);
        Workbook book = null;

        if (file.exists()) {
            book = excel.readExcel(excelPath);
            today = excel.getToday(book);
            thuNhap = excel.getThuNhap(book);
            chiPhi = excel.getChiPhi(book);
            nganHang = excel.getNganHang(book);
            khoanNos = excel.getKhoanNo(book);
            Vector<SoNganHang> soNganHangs = nganHang.getKhoanGui();
            if (soNganHangs.get(soNganHangs.size() - 1).getNgayGui().isEqual(today)) {
                thuNhap.setLuongVoChong(
                        thuNhap.getLuongVoChong() - soNganHangs.get(soNganHangs.size() - 1).getSoTienGui());
            }
            check[0] = true;
            check[1] = true;
            check[2] = false;
            check[3] = excel.isGuiNganHang(book, today);
            check[4] = excel.isTraLaiNo(book, today);
            check[5] = true;
        } else {
            book = excel.createExcel(excelPath);
            clrscr();
            System.out.println("Nhap ngay hien tai: ");
            today.input(scanner);
            lastDate = new Date(today);
            inputKhoanNo(scanner, khoanNos, today);

            excel.writeExcelCacKhoanNo(book, excelPath, khoanNos, today);
            today.plusMonths(1);
        }

        for (int i = 0; i < khoanNos.size(); ++i) {
            lastDate = Date.max(lastDate, khoanNos.get(i).getNgayTraNo());
        }

        Menu menu = new Menu();

        boolean isExit = false;

        do {
            if (!check[5]) {
                if (!traTienNo(thuNhap, khoanNos, today)) {
                    thongBaoVoNo();
                    break;
                }
                check[5] = true;
            }
            if (!check[0]) {
                thuNhap.setThuNhapTraNo(thuNhap.getThuNhapTraNo() + nganHang.getLais(today));
                check[0] = true;
            }
            clrscr();
            menu.showStatus(thuNhap, chiPhi, nganHang, khoanNos, today);
            int choose = menu.createMenu(scanner);
            switch (choose) {
                case 1:
                    if (check[1] == true) {
                        System.out.println("Ban da nhap luong va chi phi.");
                        break;
                    }
                    thuNhap.input(scanner);
                    chiPhi.input(scanner);
                    check[1] = true;
                    excel.writeExcelThuNhap(book, excelPath, thuNhap, today);
                    excel.writeExcelChiPhi(book, excelPath, chiPhi, today);
                    break;
                case 2:
                    if (check[1] == false) {
                        System.out.println("Ban chua nhap luong va chi phi.");
                        break;
                    }
                    if (check[2] == true) {
                        System.out.println("Ban da tra chi phi.");
                        break;
                    }
                    if (!traTienChiPhi(thuNhap, chiPhi)) {
                        thongBaoVoNo();
                        System.exit(0);
                    }
                    check[2] = true;
                    break;
                case 3:
                    if (check[1] == false) {
                        System.out.println("Ban chua nhap luong.");
                        break;
                    }
                    if (!check[2]) {
                        System.out.println("Ban chua tra tien chi phi");
                        break;
                    }
                    if (check[3] == true) {
                        System.out.println("Ban da gui ngan hang.");
                        break;
                    }
                    nganHang.add(scanner, thuNhap, today);
                    check[3] = true;
                    excel.writeExcelNganHang(book, excelPath, nganHang, today);
                    break;
                case 4:
                    if (check[1] == false) {
                        System.out.println("Ban chua nhap luong va chi phi.");
                        break;
                    }
                    if (check[4] == true) {
                        System.out.println("Ban da tra no.");
                        break;
                    }
                    if (!traTienLai(thuNhap, nganHang, khoanNos, today, scanner)) {
                        thongBaoVoNo();
                        System.exit(0);
                    }
                    check[4] = true;
                    excel.writeExcelLaiNoHangThang(book, excelPath, khoanNos, today);
                    break;
                case 5:
                    if (check[2] && check[4]) {
                        if (!(check[1] || check[3])) {
                            System.out.println("Ban chua nhan luong.");
                        } else {
                            today.plusMonths(1);
                            for (int i = 0; i < 6; ++i) {
                                check[i] = false;
                            }

                            thuNhap.setLuongChungDu(
                                    thuNhap.getLuongChungDu() + thuNhap.getLuongChung());
                            thuNhap.setThuNhapTraNo(thuNhap.getThuNhapTraNo() + thuNhap.getLuongVoChong());
                            thuNhap.setLuongVoChong(0);
                            thuNhap.setLuongChung(0);
                        }
                    } else {
                        System.out.println("Ban chua tra chi phi hoac no.");
                    }
                    break;
                case 6:
                    duDoanNo(thuNhap, chiPhi, nganHang, khoanNos, today);
                    break;
                case 7:
                    hienThiNo(khoanNos);
                    break;
                case 8:
                    hienThiNganHang(nganHang);
                    break;
                case 9:
                    isExit = true;
                    book.close();
                    break;
                default:
                    break;
            }
            scanner.nextLine();
            System.out.println("Press enter to continue ...");
            scanner.nextLine();
            // Ngay cuoi?
            if (Date.max(today, lastDate) == today) {
                isExit = true;
            }
        } while (!isExit);
        scanner.close();
    }

    public static void clrscr() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static boolean traTienChiPhi(ThuNhap thuNhap, ChiPhi chiphi) {
        long chiPhi = chiphi.getChiPhi();
        long temp;

        temp = Long.valueOf(thuNhap.getLuongChungDu());
        thuNhap.setLuongChungDu(Math.max(thuNhap.getLuongChungDu() - chiPhi, 0));
        chiPhi = Math.max(chiPhi - temp, 0);

        temp = Long.valueOf(thuNhap.getLuongChung());
        thuNhap.setLuongChung(Math.max(thuNhap.getLuongChung() - chiPhi, 0));
        chiPhi = Math.max(chiPhi - temp, 0);

        temp = Long.valueOf(thuNhap.getLuongVoChong());
        thuNhap.setLuongVoChong(Math.max(thuNhap.getLuongVoChong() - chiPhi, 0));
        chiPhi = Math.max(chiPhi - temp, 0);

        if (chiPhi != 0) {
            System.out.println("So tien am: " + Long.toString(-chiPhi));
            return false;
        }

        return true;
    }

    public static boolean traTienLai(ThuNhap thuNhap, NganHang nganHang, Vector<KhoanNo> khoanNos, Date today,
            Scanner scanner) {
        // int laiNoChung = khoanNo1.getLai(today) + khoanno2.getLai(today);
        long laiNoChung = 0;
        for (int i = 0; i < khoanNos.size(); ++i) {
            if ((khoanNos.get(i).linhHoat) && (khoanNos.get(i).toiThoiDiem(today))) {

                khoanNos.get(i).inputLai(scanner);
            }
            laiNoChung += khoanNos.get(i).getLai(today);
        }
        // khoanno2.inputLai(scanner);

        thuNhap.setLuongChungDu(thuNhap.getLuongChungDu());

        long temp;

        temp = Long.valueOf(thuNhap.getLuongChungDu());
        thuNhap.setLuongChungDu(Math.max(thuNhap.getLuongChungDu() - laiNoChung, 0));
        laiNoChung = Math.max(laiNoChung - temp, 0);

        temp = Long.valueOf(thuNhap.getLuongChung());
        thuNhap.setLuongChung(Math.max(thuNhap.getLuongChung() - laiNoChung, 0));
        laiNoChung = Math.max(laiNoChung - temp, 0);

        temp = Long.valueOf(thuNhap.getLuongVoChong());
        thuNhap.setLuongVoChong(Math.max(thuNhap.getLuongVoChong() - laiNoChung, 0));
        laiNoChung = Math.max(laiNoChung - temp, 0);

        if (laiNoChung != 0) {
            System.out.println("So tien am: " + Long.toString(-laiNoChung));
            return false;
        }

        return true;
    }

    public static void thongBaoVoNo() {
        System.out.println("Vo no.");
    }

    public static void inputKhoanNo(Scanner scanner, Vector<KhoanNo> khoanNos, Date today) {
        System.out.println("Nhap so khoan no: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; ++i) {
            khoanNos.add(new KhoanNo());
            khoanNos.get(i).input(scanner, today);
        }

        // Sort vector khoanNo
        Collections.sort(khoanNos);
    }

    // Add them thuNhapChung
    public static boolean traTienNo(ThuNhap thuNhap, Vector<KhoanNo> khoanNos, Date today) {
        for (int i = 0; i < khoanNos.size(); ++i) {
            if (khoanNos.get(i).getNgayTraNo().isEqual(today)) {
                long tienNo = khoanNos.get(i).getTienNo();
                long temp;

                // Thu nhap tra no
                temp = Long.valueOf(thuNhap.getThuNhapTraNo());
                thuNhap.setThuNhapTraNo(Math.max(thuNhap.getThuNhapTraNo() - tienNo, 0));
                tienNo = Math.max(tienNo - temp, 0);

                // Luong chung du: LuongChungDu
                temp = Long.valueOf(thuNhap.getLuongChungDu());
                thuNhap.setLuongChungDu(Math.max(thuNhap.getLuongChungDu() - tienNo, 0));
                tienNo = Math.max(tienNo - temp, 0);

                // Luong vo chong: LuongVoChong
                temp = Long.valueOf(thuNhap.getLuongVoChong());
                thuNhap.setLuongVoChong(Math.max(thuNhap.getLuongVoChong() - tienNo, 0));
                tienNo = Math.max(tienNo - temp, 0);

                // Luong chung: LuongChung
                temp = Long.valueOf(thuNhap.getLuongChung());
                thuNhap.setLuongChung(Math.max(thuNhap.getLuongChung() - tienNo, 0));
                tienNo = Math.max(tienNo - temp, 0);

                if (tienNo != 0) {
                    System.out.println("So tien am: " + Long.toString(-tienNo));
                    return false;
                } else {
                    // khoanNos.remove(i);
                    // i--;
                    khoanNos.get(i).setDaTra(true);
                }
            }
        }
        return true;
    }

    public static boolean duDoanNo(ThuNhap thuNhap, ChiPhi chiPhi, NganHang nganHang, Vector<KhoanNo> khoanNos,
            Date today) {
        // Lay tong thu nhap
        long vao = thuNhap.getThuNhapTraNo();
        // Chot cho ngan hang
        int pivot = 0;
        if (nganHang.getKhoanGui().size() == 0) {
            pivot = -1;
        }
        Date temp = new Date(today);
        for (int i = 0; i < khoanNos.size(); ++i) {
            // Lay ti le => Tong tien tich luy toi ngay tra no
            if (khoanNos.get(i).getDaTra() == true) {
                continue;
            }
            Date temp2 = new Date(khoanNos.get(i).getNgayTraNo());
            while (pivot != -1) {
                if (temp2.isGreater(nganHang.getKhoanGui().get(pivot).getNgayTra()))
                    break;
                vao += nganHang.getKhoanGui().get(pivot).getLai();
                pivot++;
                if (pivot == nganHang.getKhoanGui().size()) {
                    pivot = -1; // Het ngan hang
                }
            }
            if (vao + thuNhap.getLuongVoChong() * today.diffMonths(temp) >= khoanNos.get(i).getTienNo()) {
                vao = vao + thuNhap.getLuongVoChong() * today.diffMonths(temp) - khoanNos.get(i).getTienNo();
                temp = new Date(khoanNos.get(i).getNgayTraNo());
            } else {
                temp = new Date(khoanNos.get(i).getNgayTraNo());
                System.out.println("Khong the tra no");
                System.out.println("Vo no khi tra khoan no thu " + Long.toString(i + 1) + " vao thang "
                        + Long.toString(temp.getMonth())
                        + "/" + Long.toString(temp.getYear()));
                return false;
            }
        }
        System.out.println("Co the tra het no.");
        return true;
    }

    public static void hienThiNo(Vector<KhoanNo> khoanNos) {
        System.out.println("Cac khoan no: ");
        for (int i = 0; i < khoanNos.size(); ++i) {
            KhoanNo khoanNo = khoanNos.get(i);
            System.out.println(Long.toString(i + 1) + ". Khoan no: "
                    + Long.toString(khoanNo.getTienNo()) + " | Thang tra: "
                    + Long.toString(khoanNo.getNgayTraNo().getMonth()) + "/"
                    + Long.toString(khoanNo.getNgayTraNo().getYear())
                    + (khoanNo.getDaTra() ? " | Da tra" : ""));
        }
    }

    public static void hienThiNganHang(NganHang nganHang) {
        System.out.println("Cac so ngan hang: ");
        for (int i = 0; i < nganHang.getKhoanGui().size(); ++i) {
            SoNganHang soNganHang = nganHang.getKhoanGui().get(i);
            System.out.println(Long.toString(i + 1) + ". So ngan hang: "
                    + Long.toString(soNganHang.getSoTienGui()) + " | Thang tra: "
                    + Long.toString(soNganHang.getNgayTra().getMonth()) + "/"
                    + Long.toString(soNganHang.getNgayTra().getYear()));
        }
    }

}
