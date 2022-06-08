package nhom2;

import java.util.Scanner;

public class ThuNhap { // thuNhap
    private int luongVoChong = 0;
    private int luongChung = 0;
    private int luongChungDu = 0;
    private int thuNhapTraNo = 0;

    // Constructor
    public ThuNhap() {

    }

    public ThuNhap(int luongVoChong, int luongChung) {
        this.luongVoChong = luongVoChong;
        this.luongChung = luongChung;
    }

    public ThuNhap(int luongVoChong, int luongChung, int luongChungDu, int thuNhapTraNo) {
        this.luongVoChong = luongVoChong;
        this.luongChung = luongChung;
        this.luongChungDu = luongChungDu;
        this.thuNhapTraNo = thuNhapTraNo;
    }

    public ThuNhap(ThuNhap luong) {
        this.luongVoChong = luong.luongVoChong;
        this.luongChung = luong.luongChung;
    }

    // Other method
    public int getLuongVoChong() {
        return luongVoChong;
    }

    public int getLuongChung() {
        return luongChung;
    }

    public int getLuongChungDu() {
        return luongChungDu;
    }

    public int getThuNhapTraNo() {
        return thuNhapTraNo;
    }

    public int getLuong() {
        return luongVoChong + luongChung;
    }

    public void setLuongVoChong(int luongVoChong) {
        this.luongVoChong = luongVoChong;
    }

    public void setLuong(int luongVoChong, int luongChung) { // Using for input
        this.luongVoChong = luongVoChong;
        this.luongChung = luongChung;
    }

    public void setLuongChungDu(int luongChungDu) {
        this.luongChungDu = luongChungDu;
    }

    public void setLuongChung(int luongChung) {
        this.luongChung = luongChung;
    }

    public void setThuNhapTraNo(int thuNhapTraNo) {
        this.thuNhapTraNo = thuNhapTraNo;
    }

    public void input(Scanner scanner) {
        int temp = 0;
        System.out.println("Nhap luong: ");
        System.out.print("Nhap luong chong: ");
        scanner.nextLine();
        temp += scanner.nextInt();
        System.out.print("Nhap luong vo: ");
        temp += scanner.nextInt();
        System.out.print("Nhap luong chung: ");
        luongChung = scanner.nextInt();
        this.setLuong(temp, luongChung);
    }
}