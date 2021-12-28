import java.time.LocalDate;
import java.util.Locale;
import java.text.NumberFormat;

public class Manager extends Staff {
    private String position; //lưu chức vụ quản lý
    
    //chuỗi định dạng hiển thị danh sách theo chiều ngang
    String formatDS = "| %-8s | %-30s | %-5d | %-20s | %-20s | %-14s | %-5d | %-9.1f | %-5s | %-20s |%n";

    //constructor 1
    public Manager() {

    }

    //constructor 2
    public Manager(String id, String name, int age, double coeffSalary, LocalDate startDate, Department department, String position, int numdayOff){
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setCoeffSalary(coeffSalary);
        this.setStartDate(startDate);
        this.setDepartment(department);
        this.position = position;
        this.setNumdayOff(numdayOff);
        this.getDepartment().newStaff();

    }

    //truy cập chức vụ của quản lý
    public String getPosition() {
        return position;
    }
    
    //đặt chức vụ cho quản lý
    public void setPosition(String position) {
        this.position = position;
    }

    //Tính và trả lại giá trị lương theo dạng chuỗi
    public String calculateSalary(){
        double salary;
        if(position.equals("Business Leader")){
            salary = getCoeffSalary()*5000000 + 8000000;
        }else if(position.equals("Project Leader")){
            salary = getCoeffSalary()*5000000 + 5000000;
        }else if(position.equals("Technical Leader")){
            salary = getCoeffSalary()*5000000 + 6000000;
        }else{
            salary = getCoeffSalary()*5000000;
        }
        Locale a = new Locale("vi", "VN");
        NumberFormat b = NumberFormat.getCurrencyInstance(a);
        String money = b.format(salary);
        return money;
    }

    //tính và trả lại giá trị lương theo dạng số thực
    public double mycalculateSalary(){
        double salary = getCoeffSalary()*5000000;
        if(position.equals("Business Leader")){
            return salary + 8000000;
        }
        else if(position.equals("Project Leader")){
            return salary + 5000000;
        }
        else if(position.equals("Technical Leader")){
            return salary + 6000000;
        }
        else{
            return salary;
        }
    }

    //hàm hiển thị thông tin chi tiết theo cột
    public void displayInformation(){
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Tên:",getName());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20d |%n","Tuổi:",getAge());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Mã nhân viên:",getId());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Chức vụ:",position);
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Bộ phận:",getDepartment().getNameDep());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Ngày vào làm:",getStartDate());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20d |%n","Số ngày nghỉ:",getNumdayOff());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20.1f |%n","Hệ số lương:",getCoeffSalary());
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| %-20s | %-20s |%n","Tỏng lương:",calculateSalary());
        System.out.format("+----------------------+----------------------+%n");
    }

    //hàm hiển thị thông tin chi tết theo hàng
    public void toDS(){
        System.out.format(formatDS,getId(),getName(),getAge(),position,getDepartment().getNameDep(),getStartDate(),getNumdayOff(),getCoeffSalary(),"...",calculateSalary());
    }
}
