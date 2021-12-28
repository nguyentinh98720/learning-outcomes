import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Staff implements Icalculator {

    private String id;              //mã nhân viên
    private String name;            //tên nhân viên
    private int age;                //tuổi
    private double coeffSalary;     //hệ số lương
    private LocalDate startDate;    //ngày vào làm
    private Department department;  //bộ phận
    private int numdayOff;          //số ngày nghỉ
    

    //lấy ngày nghỉ
    public int getNumdayOff() {
        return numdayOff;
    }

    //đặt ngày nghỉ
    public void setNumdayOff(int numdayOff) {
        this.numdayOff = numdayOff;
    }

    //lấy mã nhân viên
    public String getId() {
        return id;
    }

    //đặt mã nhân viên
    public void setId(String id) {
        this.id = id;
    }

    //lấy tên nhân viên
    public String getName() {
        return name;
    }

    //đặt tên nhân viên
    public void setName(String name) {
        this.name = name;
    }

    //lấy tuổi
    public int getAge() {
        return age;
    }

    //đặt tuổi
    public void setAge(int age) {
        this.age = age;
    }

    //lấy hệ số lương
    public double getCoeffSalary() {
        return coeffSalary;
    }

    //đặt hệ số lương
    public void setCoeffSalary(double coeffSalary) {
        this.coeffSalary = coeffSalary;
    }

    //lấy ngày vào làm của nhân viên
    public String getStartDate() {
        DateTimeFormatter formatVN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String time = startDate.format(formatVN);
        return time;
    }

    //đặt ngày vào làm của nhân viên
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    //lấy bộ phận làm việc của nhân viên
    public Department getDepartment() {
        return department;
    }

    //lấy mã id bộ phận làm việc của nhân viên
    public String getDepartmentId() {
        return department.getIdDep();
    }
    
    //đặt bộ phận làm việc cho nhân viên
    public void setDepartment(Department department) {
        this.department = department;
    }

    //hàm hiển thị thông tin nhân viên
        //theo chiều dọc
    public abstract void displayInformation();
        //theo chiều ngang
    public abstract void toDS();

}