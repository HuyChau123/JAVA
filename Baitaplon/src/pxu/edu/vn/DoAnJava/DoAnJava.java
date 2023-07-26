package pxu.edu.vn.DoAnJava;

import java.util.Scanner;

public class DoAnJava {
	
	static Product[] productList = new Product[100];
    static int productCount = 0;

	public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
        
        char choice;
        do {
            System.out.println("------MENU------");
            System.out.println("1. Nhập thông tin sản phẩm");
            System.out.println("2. Sắp xếp theo chiều giảm dần của thành tiền");
            System.out.println("3. Sắp xếp theo chiều tăng dần của giá bán");
            System.out.println("4. Tìm kiếm sản phẩm theo tên");
            System.out.println("Q. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            
            choice = scanner.next().charAt(0);
            scanner.nextLine();  // Xóa bộ đệm
            
            switch (choice) {
                case '1':
                    addProduct(scanner);
                    break;
                case '2':
                    sortProductsByTotalPriceDescending();
                    break;
                case '3':
                    sortProductsByPriceAscending();
                    break;
                case '4':
                    searchProductByName(scanner);
                    break;
                case 'Q':
                case 'q':
                    System.out.println("Chương trình kết thúc.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
            System.out.println();
        } while (choice != 'Q' && choice != 'q');
        
        scanner.close();
    }
    
    static void addProduct(Scanner scanner) {
        System.out.println("Nhập thông tin sản phẩm:");
        System.out.print("Tên sản phẩm: ");
        String name = scanner.nextLine();
        
        System.out.print("Đơn vị tính: ");
        String unit = scanner.nextLine();
        
        System.out.print("Số lượng: ");
        int quantity = scanner.nextInt();
        
        System.out.print("Giá bán: ");
        double price = scanner.nextDouble();
        
        productList[productCount] = new Product(name, unit, quantity, price);
        productCount++;
        
        System.out.println("Sản phẩm đã được thêm thành công.");
    }
    
    static void calculateTotalPrice() {
        for (int i = 0; i < productCount; i++) {
            productList[i].calculateTotalPrice();
        }
        System.out.println("Đã tính giá trị thành tiền cho các sản phẩm.");
    }
    
    static void sortProductsByTotalPriceDescending() {
        for (int i = 0; i < productCount - 1; i++) {
            for (int j = i + 1; j < productCount; j++) {
                if (productList[i].getTotalPrice() < productList[j].getTotalPrice()) {
                    Product temp = productList[i];
                    productList[i] = productList[j];
                    productList[j] = temp;
                }
            }
        }
        System.out.println("Danh sách sản phẩm đã được sắp xếp theo chiều giảm dần của thành tiền:");
        displayProductList();
    }
    
    static void sortProductsByPriceAscending() {
        for (int i = 0; i < productCount - 1; i++) {
            for (int j = i + 1; j < productCount; j++) {
                if (productList[i].getPrice() > productList[j].getPrice()) {
                    Product temp = productList[i];
                    productList[i] = productList[j];
                    productList[j] = temp;
                }
            }
        }
        System.out.println("Danh sách sản phẩm đã được sắp xếp theo chiều tăng dần của giá bán:");
        displayProductList();
    }
    
    static void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm để tìm kiếm: ");
        String productName = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (productList[i].getName().equalsIgnoreCase(productName)) {
                System.out.println("Sản phẩm có tên " + productName + " được tìm thấy:");
                System.out.println(productList[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm có tên " + productName);
        }
    }
    
    static void displayProductList() {
        for (int i = 0; i < productCount; i++) {
            System.out.println(productList[i]);
        }
    }
}

class Product {
    private String name;
    private String unit;
    private int quantity;
    private double price;
    private double totalPrice;
    
    public Product(String name, String unit, int quantity, double price) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void calculateTotalPrice() {
        totalPrice = quantity * price;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String toString() {
        return "Tên sản phẩm: " + name +
                ", Đơn vị tính: " + unit +
                ", Số lượng: " + quantity +
                ", Giá bán: " + price +
                ", Thành tiền: " + totalPrice;
    }
}

