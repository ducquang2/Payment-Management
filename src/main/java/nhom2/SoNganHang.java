package nhom2;

import java.util.Scanner;

public class SoNganHang {
    protected int soTienGui = 0;
    protected Date ngayGui = new Date();
    protected Date ngayTra = new Date();
    protected double phanTramLai = 0;

    public SoNganHang() {

    }

    public SoNganHang(int soTienGui, Date ngayGui, Date ngayTra) {
        this.soTienGui = soTienGui;
        this.ngayGui = ngayGui;
        this.ngayTra = ngayTra;
    }

    public SoNganHang(SoNganHang SoMoi) {
        this.soTienGui = SoMoi.soTienGui;
        this.ngayGui = SoMoi.ngayGui;
        this.ngayTra = SoMoi.ngayTra;
        this.phanTramLai = SoMoi.phanTramLai;
    }

    public int getSoTienGui() {
        return this.soTienGui;
    }

    public void setSoTienGui(int soTienGui) {
        this.soTienGui = soTienGui;
    }

    public Date getNgayGui() {
        return this.ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

    public double getPhanTramLai() {
        return this.phanTramLai;
    }

    public void setPhanTramLai(double phanTramLai) {
        this.phanTramLai = phanTramLai;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public int getLai() {
        return (int) (this.getSoTienGui() / 100 * this.getPhanTramLai() + this.getSoTienGui());
    }

    public void input(Scanner scanner, ThuNhap thuNhap, Date today) {
        ngayGui = new Date(today);
        this.ngayTra = new Date(ngayGui);
        System.out.println("Nhap so tien muon gui tiet kiem: ");
        boolean loop = true;
        int temp = 0;
        do {
            temp = scanner.nextInt();
            if (temp <= thuNhap.getLuongVoChong() && temp > 50000) {
                loop = false;
            } else {
                System.out.println("So tien khong hop le !");
            }
        } while (loop);

        this.setSoTienGui(temp);
        thuNhap.setLuongVoChong(thuNhap.getLuongVoChong() - temp);
        System.out.print("Nhap chu ky gui (thang): ");
        temp = scanner.nextInt();
        this.ngayTra.plusMonths(temp);
        System.out.print("Nhap phan tram lai (?%): ");
        this.phanTramLai = scanner.nextDouble();
    }
}