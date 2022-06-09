package nhom2;

import java.util.Scanner;

public class ThuNhap { // thuNhap
    private long luongVoChong = 0;
    private long luongChung = 0;
    private long luongChungDu = 0;
    private long thuNhapTraNo = 0;

    // Constructor
    public ThuNhap() {

    }

    public ThuNhap(long luongVoChong, long luongChung) {
        this.luongVoChong = luongVoChong;
        this.luongChung = luongChung;
    }

    public ThuNhap(long luongVoChong, long luongChung, long luongChungDu, long thuNhapTraNo) {
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
    public long getLuongVoChong() {
        return luongVoChong;
    }

    public long getLuongChung() {
        return luongChung;
    }

    public long getLuongChungDu() {
        return luongChungDu;
    }

    public long getThuNhapTraNo() {
        return thuNhapTraNo;
    }

    public long getLuong() {
        return luongVoChong + luongChung;
    }

    public void setLuongVoChong(long luongVoChong) {
        this.luongVoChong = luongVoChong;
    }

    public void setLuong(long luongVoChong, long luongChung) { // Using for input
        this.luongVoChong = luongVoChong;
        this.luongChung = luongChung;
    }

    public void setLuongChungDu(long luongChungDu) {
        this.luongChungDu = luongChungDu;
    }

    public void setLuongChung(long luongChung) {
        this.luongChung = luongChung;
    }

    public void setThuNhapTraNo(long thuNhapTraNo) {
        this.thuNhapTraNo = thuNhapTraNo;
    }

    public void input(Scanner scanner) {
        long temp = 0;
        System.out.println("Nhap luong: ");
        System.out.print("Nhap luong chong: ");
        scanner.nextLine();
        temp += scanner.nextLong();
        System.out.print("Nhap luong vo: ");
        temp += scanner.nextLong();
        System.out.print("Nhap luong chung: ");
        luongChung = scanner.nextLong();
        this.setLuong(temp, luongChung);
    }
}