package nhom2;

import java.util.Scanner;

public class ChiPhi {
    private int dien;
    private int nuoc;
    private int anuong;
    private int chiphikhac;

    public void setDien(int dien) {
        this.dien = dien;
    }

    public void setNuoc(int nuoc) {
        this.nuoc = nuoc;
    }
    
    public void setAnUong(int anuong) {
        this.anuong = anuong;
    }

    public void setChiPhiKhac(int chiphikhac) {
        this.chiphikhac = chiphikhac;
    }

    public int getNuoc() {
        return this.nuoc;
    }

    public int getDien() {
        return this.dien;
    }

    public int getAnUong() {
        return this.anuong;
    }

    public int getChiPhiKhac() {
        return this.chiphikhac;
    }
    
    public int getChiPhi() {
        return (int) (getDien()+getNuoc()+getChiPhiKhac()+getAnUong());
    }

    public void input(Scanner scanner) {
        System.out.println("Nhap chi phi:");
        System.out.print("Nhap tien dien: ");
        this.dien = scanner.nextInt(); 
        System.out.print("Nhap tien nuoc: ");
        this.nuoc = scanner.nextInt(); 
        System.out.print("Nhap tien an uong: ");
        this.anuong = scanner.nextInt(); 
        System.out.print("Nhap tien chi phi khac: ");
        this.chiphikhac = scanner.nextInt(); 
    }
}