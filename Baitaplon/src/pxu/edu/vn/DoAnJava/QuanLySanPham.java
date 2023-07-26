package pxu.edu.vn.DoAnJava;

import java.util.Scanner;

class SanPham {
    String tenSanPham;
    String donViTinh;
    int soLuong;
    double giaBan;
    double thanhTien;

    public SanPham(String tenSanPham, String donViTinh, int soLuong, double giaBan) {
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = tinhThanhTien();
    }

    public double tinhThanhTien() {
        return soLuong * giaBan;
    }
}

public class QuanLySanPham {
    private static Scanner scanner = new Scanner(System.in);
    private static SanPham[] danhSachSanPham;
    private static int soLuongSanPham;

    public static void main(String[] args) {
        danhSachSanPham = new SanPham[100];
        soLuongSanPham = 0;

        boolean ketThuc = false;
        while (!ketThuc) {
            System.out.println("----- MENU -----");
            System.out.println("1. Nhập thông tin sản phẩm");
            System.out.println("2. Sắp xếp danh sách sản phẩm theo chiều giảm dần của thành tiền");
            System.out.println("3. Sắp xếp danh sách sản phẩm theo chiều tăng dần của giá bán");
            System.out.println("4. Tìm kiếm sản phẩm theo tên");
            System.out.println("Q. Kết thúc chương trình");

            System.out.print("Nhập lựa chọn: ");
            String luaChon = scanner.nextLine();

            switch (luaChon) {
                case "1":
                    nhapThongTinSanPham();
                    break;
                case "2":
                    sapXepTheoThanhTienGiamDan();
                    xuatDanhSachSanPham();
                    break;
                case "3":
                    sapXepTheoGiaBanTangDan();
                    xuatDanhSachSanPham();
                    break;
                case "4":
                    timKiemSanPham();
                    break;
                case "Q":
                case "q":
                    ketThuc = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    public static void nhapThongTinSanPham() {
        System.out.print("Nhập tên sản phẩm: ");
        String tenSanPham = scanner.nextLine();

        System.out.print("Nhập đơn vị tính: ");
        String donViTinh = scanner.nextLine();

        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập giá bán: ");
        double giaBan = Double.parseDouble(scanner.nextLine());

        SanPham sanPham = new SanPham(tenSanPham, donViTinh, soLuong, giaBan);
        danhSachSanPham[soLuongSanPham] = sanPham;
        soLuongSanPham++;
    }

    public static void sapXepTheoThanhTienGiamDan() {
        for (int i = 0; i < soLuongSanPham - 1; i++) {
            for (int j = i + 1; j < soLuongSanPham; j++) {
                if (danhSachSanPham[i].thanhTien < danhSachSanPham[j].thanhTien) {
                    SanPham temp = danhSachSanPham[i];
                    danhSachSanPham[i] = danhSachSanPham[j];
                    danhSachSanPham[j] = temp;
                }
            }
        }
    }

    public static void sapXepTheoGiaBanTangDan() {
        for (int i = 0; i < soLuongSanPham - 1; i++) {
            for (int j = i + 1; j < soLuongSanPham; j++) {
                if (danhSachSanPham[i].giaBan > danhSachSanPham[j].giaBan) {
                    SanPham temp = danhSachSanPham[i];
                    danhSachSanPham[i] = danhSachSanPham[j];
                    danhSachSanPham[j] = temp;
                }
            }
        }
    }

    public static void timKiemSanPham() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String tenSanPham = scanner.nextLine();

        boolean timThay = false;
        for (int i = 0; i < soLuongSanPham; i++) {
            if (danhSachSanPham[i].tenSanPham.equalsIgnoreCase(tenSanPham)) {
                System.out.println("Thông tin sản phẩm:");
                System.out.println("Tên sản phẩm: " + danhSachSanPham[i].tenSanPham);
                System.out.println("Đơn vị tính: " + danhSachSanPham[i].donViTinh);
                System.out.println("Số lượng: " + danhSachSanPham[i].soLuong);
                System.out.println("Giá bán: " + danhSachSanPham[i].giaBan);
                System.out.println("Thành tiền: " + danhSachSanPham[i].thanhTien);
                timThay = true;
                break;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy sản phẩm có tên " + tenSanPham);
        }
    }

    public static void xuatDanhSachSanPham() {
        System.out.println("Danh sách sản phẩm:");
        for (int i = 0; i < soLuongSanPham; i++) {
            System.out.println("Tên sản phẩm: " + danhSachSanPham[i].tenSanPham);
            System.out.println("Đơn vị tính: " + danhSachSanPham[i].donViTinh);
            System.out.println("Số lượng: " + danhSachSanPham[i].soLuong);
            System.out.println("Giá bán: " + danhSachSanPham[i].giaBan);
            System.out.println("Thành tiền: " + danhSachSanPham[i].thanhTien);
            System.out.println("--------------------");
        }
    }
}