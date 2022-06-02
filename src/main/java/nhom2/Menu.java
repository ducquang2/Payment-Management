package nhom2;

import java.util.Scanner;
import java.util.Vector;

public class Menu {
    protected Boolean loop = true;
    protected int choice;

    public int createMenu(Scanner scanner)
    {
        System.out.println("|*--Chuong trinh quan ly chi tieu hang thang--*|");
        System.out.println("|*Option 1:       Nhap luong va chi phi       *|");
        System.out.println("|*Option 2:          Tra tien chi phi         *|");
        System.out.println("|*Option 3:       Gui tien vao ngan hang      *|");
        System.out.println("|*Option 4:            Tra tien no            *|");
        System.out.println("|*Option 5:        Sang thang tiep theo       *|");
        System.out.println("|*Option 6:             Du doan no            *|");
        System.out.println("|*Option 7:          Hien thi khoan no        *|");
        System.out.println("|*Option 8:        Hien thi so ngan hang      *|");
        System.out.println("|*Option 9:         Thoat chuong trinh        *|");
        System.out.println("|*--------------------------------------------*|");

        do {
            choice = scanner.nextInt();
            if (choice >= 1 && choice <= 9) {
                loop = false;
            }
            else {
                System.out.println("Chon lai");
            }
        } while (loop);
        return choice;
    }

    public void showStatus(ThuNhap thuNhap, ChiPhi chiPhi, NganHang nganHang, Vector<KhoanNo> khoanNos, Date today) {
        // Lich
        System.out.println("Lich: " + Integer.toString(today.getMonth()) + "/" + Integer.toString(today.getYear()));

        // Luong
        System.out.println("Luong: ");
        System.out.println("    + Vo chong: " + Integer.toString(thuNhap.getLuongVoChong()) +        
        " | Chung: " + Integer.toString(thuNhap.getLuongChung()) + 
        " | Du: " + Integer.toString(thuNhap.getLuongChungDu()) + 
        " | Tien de tra no: " + Integer.toString(thuNhap.getThuNhapTraNo()));

        // Chi phi
        System.out.println("Chi phi: ");
        System.out.println("    + Dien: " + Integer.toString(chiPhi.getDien()) + 
        " | Nuoc: " + Integer.toString(chiPhi.getNuoc()) + 
        " | An uong: " + Integer.toString(chiPhi.getAnUong()) + 
        " | Khac: " + Integer.toString(chiPhi.getChiPhiKhac()));

        // Ngan hang
        System.out.println("Ngan hang: ");
        System.out.println("   + Lai chung: " + Integer.toString(nganHang.getLais(today)));

        // Khoan no
        int noChung = 0;
        for (int i = 0; i < khoanNos.size(); ++i) {
            noChung += khoanNos.get(i).getLai(today);
        }
        System.out.println("No du kien thang nay:");
        System.out.println("   + No chung: " + Integer.toString(noChung));
    }
}