package nhom2;

import java.util.Scanner;

public class ChiPhi {
    private long dien = 0;
    private long nuoc = 0;
    private long anuong = 0;
    private long chiphikhac = 0;

    public ChiPhi() {

    }

    public ChiPhi(long tienDien, long tienNuoc, long tienAnUong, long tienChiPhiKhac) {
        this.dien = tienDien;
        this.nuoc = tienNuoc;
        this.anuong = tienAnUong;
        this.chiphikhac = tienChiPhiKhac;
    }

    public void setDien(long dien) {
        this.dien = dien;
    }

    public void setNuoc(long nuoc) {
        this.nuoc = nuoc;
    }

    public void setAnUong(long anuong) {
        this.anuong = anuong;
    }

    public void setChiPhiKhac(long chiphikhac) {
        this.chiphikhac = chiphikhac;
    }

    public long getNuoc() {
        return this.nuoc;
    }

    public long getDien() {
        return this.dien;
    }

    public long getAnUong() {
        return this.anuong;
    }

    public long getChiPhiKhac() {
        return this.chiphikhac;
    }

    public long getChiPhi() {
        return (long) (getDien() + getNuoc() + getChiPhiKhac() + getAnUong());
    }

    public void input(Scanner scanner) {
        System.out.println("Nhap chi phi:");
        System.out.print("Nhap tien dien: ");
        this.dien = scanner.nextLong();
        System.out.print("Nhap tien nuoc: ");
        this.nuoc = scanner.nextLong();
        System.out.print("Nhap tien an uong: ");
        this.anuong = scanner.nextLong();
        System.out.print("Nhap tien chi phi khac: ");
        this.chiphikhac = scanner.nextLong();
    }
}