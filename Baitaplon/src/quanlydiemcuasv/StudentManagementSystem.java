package quanlydiemcuasv;

	import java.util.Scanner;

class Student {
    private String name;
    private float mathScore;
    private float literatureScore;
    private float englishScore;
    private float averageScore;

    public Student(String name, float mathScore, float literatureScore, float englishScore) {
        this.name = name;
        this.mathScore = mathScore;
        this.literatureScore = literatureScore;
        this.englishScore = englishScore;
        this.averageScore = calculateAverageScore();
    }

    public String getName() {
        return name;
    }

    public float getAverageScore() {
        return averageScore;
    }

    private float calculateAverageScore() {
        return (float) (Math.round(((mathScore + literatureScore + englishScore) / 3) * 100.0) / 100.0);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", mathScore=" + mathScore +
                ", literatureScore=" + literatureScore +
                ", englishScore=" + englishScore +
                ", averageScore=" + averageScore +
                '}';
    }
}

public class StudentManagementSystem {
    private static final int MAX_STUDENTS = 100;
    private Student[] students;
    private int count;

    public StudentManagementSystem() {
        students = new Student[MAX_STUDENTS];
        count = 0;
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập điểm Toán: ");
        float mathScore = scanner.nextFloat();

        System.out.print("Nhập điểm Văn: ");
        float literatureScore = scanner.nextFloat();

        System.out.print("Nhập điểm Anh: ");
        float englishScore = scanner.nextFloat();

        students[count] = new Student(name, mathScore, literatureScore, englishScore);
        count++;
    }

    public void displayStudents() {
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    public void sortStudentsByAverageScore() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (students[j].getAverageScore() > students[i].getAverageScore()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    public void searchStudentsByName(String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                System.out.println(students[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có sinh viên nào mang tên \"" + name + "\"");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        System.out.print("Nhập số lượng sinh viên: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nStudent " + (i + 1));
            system.addStudent();
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Danh sách sinh viên theo điểm trung binhg");
            System.out.println("3. Tìm sinh viên theo tên");
            System.out.println("Q. Thoát");

            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                break;
            }

            switch (choice) {
                case "1":
                    system.displayStudents();
                    break;
                case "2":
                    system.sortStudentsByAverageScore();
                    system.displayStudents();
                    break;
                case "3":
                    System.out.print("Nhập tên để tìm: ");
                    String name = scanner.nextLine();
                    system.searchStudentsByName(name);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
    }
}
