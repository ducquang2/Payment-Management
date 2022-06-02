package nhom2;

import java.util.Scanner;

public class Date {
    protected int month = 05;
    protected int year = 2022;

    public Date() {

    }

    public Date(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public Date(Date newDate) {
        this.month = newDate.month;
        this.year = newDate.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void plusMonths(int months) {
        month += months;
        if (month > 12) {
            int temp = month / 12;
            year += temp;
            month -= temp * 12;
        }
    }

    public int diffMonths(Date date) {
        return Math.abs(date.year * 12 + date.month - month - year * 12);
    }

    public void input(Scanner scan) {
        System.out.print("Nhap thang: ");
        this.month = scan.nextInt();
        System.out.print("Nhap nam: ");
        this.year = scan.nextInt();
    }

    public static Date max(Date date1, Date date2) {
        if ((date1.year > date2.year) || ((date1.year == date2.year) && (date1.month > date2.month))) {
            return date1;
        }
        return date2;
    }

    public boolean isEqual(Date date) {
        if ((date.getMonth() == month) && (date.getYear() == year)) {
            return true;
        }
        return false;
    }

    public boolean isGreater(Date date) {
        if ((year > date.year) || ((year == date.year) && (month > date.month))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return this.getMonth() + "/" + this.getYear();
    }
}
