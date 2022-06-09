package nhom2;

import java.util.Scanner;

public class SoNganHang {
    protected long soTienGui = 0;
    protected Date ngayGui = new Date();
    protected Date ngayTra = new Date();
    protected double phanTramLai = 0;
    int chuKy = 0;

    public SoNganHang() {

    }

    public SoNganHang(long soTienGui, Date ngayGui, Date ngayTra, double phanTramLai, int chuKy) {
        this.soTienGui = soTienGui;
        this.ngayGui = ngayGui;
        this.ngayTra = ngayTra;
        this.phanTramLai = phanTramLai;
        this.chuKy = chuKy;
    }

    public SoNganHang(SoNganHang SoMoi) {
        this.soTienGui = SoMoi.soTienGui;
        this.ngayGui = SoMoi.ngayGui;
        this.ngayTra = SoMoi.ngayTra;
        this.phanTramLai = SoMoi.phanTramLai;
        this.chuKy = SoMoi.chuKy;
    }

    public long getSoTienGui() {
        return this.soTienGui;
    }

    public void setSoTienGui(long soTienGui) {
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

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getChuKy() {
        return chuKy;
    }

    public void setChuKy(int chuKy) {
        this.chuKy = chuKy;
    }

    // public boolean toiThoiDiem(Date today) {
    //     return ((ngayTra.diffMonths(today) % chuKy == 0) && ((ngayTra.isGreater(today)) || ngayTra.isEqual(today)));
    // }

    public long getLai() {
        // if (!toiThoiDiem(today)) {
        //     return 0;
        // }
        return (long) ((Math.pow(phanTramLai, ngayTra.diffMonths(ngayGui) / chuKy) + 1) * this.soTienGui);
        // return (int) (this.getSoTienGui() / 100 * this.getPhanTramLai());
    }

    public void input(Scanner scanner, ThuNhap thuNhap, Date today) {
        ngayGui = new Date(today);
        this.ngayTra = new Date(ngayGui);
        System.out.println("Nhap so tien muon gui tiet kiem: ");
        boolean loop = true;
        long temp = 0;
        do {
            temp = scanner.nextLong();
            if (temp <= thuNhap.getLuongVoChong() && temp > 50000) {
                loop = false;
            } else {
                System.out.println("So tien khong hop le !");
            }
        } while (loop);
        this.setSoTienGui(temp);
        thuNhap.setLuongVoChong(thuNhap.getLuongVoChong() - temp);

        System.out.println("Nhap ngay nhan: ");
        this.ngayTra.input(scanner);
        do {
            System.out.print("Nhap chu ky (thang): ");
            this.chuKy = scanner.nextInt();
        } while (ngayTra.diffMonths(ngayGui) < chuKy);

        System.out.print("Nhap phan tram lai (?%): ");
        this.phanTramLai = scanner.nextDouble();
    }
}