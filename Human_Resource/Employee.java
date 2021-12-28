import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Employee extends Staff {
    private double overtimeHours;   //lưu số giờ làm thêm

    //chuỗi định dạng hiển thị theo danh sách ngang
    String formatDS = "| %-8s | %-30s | %-5d | %-20s | %-20s | %-14s | %-5d | %-9.1f | %-5.1f | %-20s |%n";

    //constructor 1
    public Employee(){

    }

    //constructor 2
    public Employee(String id, String name, int age, double coeffSalary, LocalDate startDate, Department department, double overtimeHours, int numdayOff){
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setCoeffSalary(coeffSalary);
        this.setStartDate(startDate);
        this.setDepartment(department);
        this.overtimeHours = overtimeHours;
        this.setNumdayOff(numdayOff);
        this.getDepartment().newStaff();
    }

    //lấy số giờ làm thêm
    public double getOvertimeHours() {
        return overtimeHours;
    }

    //đắt số giờ làm thêm
    void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    //tính lương và trả về giá trị dưới dạng chuỗi
    public String calculateSalary(){
        double salary = getCoeffSalary()*3000000 + overtimeHours*200000;
        Locale a = new Locale("vi", "VN");
        NumberFormat b = NumberFormat.getCurrencyInstance(a);
        String money = b.format(salary);
        return money;
    }

    //tính lương và trả về giá trị dưới dạng số
    public double mycalculateSalary(){
        return getCoeffSalary()*3000000 + overtimeHours*200000;
    }
    
    //hiển thị thông tin theo chiều dọc
    public void displayInformation(){
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Tên:",getName());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20d |%n","Tuổi:",getAge());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Mã nhân viên:",getId());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Bộ phận:",getDepartment().getNameDep());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Ngày vào làm:",getStartDate());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20d |%n","Số ngày nghỉ:",getNumdayOff());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20.1f |%n","Hệ số lương:",getCoeffSalary());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20.1f |%n","Giờ làm thêm:",overtimeHours);
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Tỏng lương:",calculateSalary());
        System.out.format("+----------------------+----------------------+%n");
    }

    //hiển thị thông tin theo chiều ngang
    public void toDS(){
        System.out.format(formatDS,getId(),getName(),getAge(),"...",getDepartment().getNameDep(),getStartDate(),getNumdayOff(),getCoeffSalary(),getOvertimeHours(),calculateSalary());
    }
}
