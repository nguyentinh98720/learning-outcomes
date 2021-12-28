import java.time.LocalDate;

public class Department {
    private String idDep;       //mã bộ phận
    private String nameDep;     //tên bộ phận
    private int numOfStaff;     //số lượng nhân viên trong bộ phận
    
    //constructor 1
    public Department(){

    }

    //constructor 2
    public Department(String idDep, String nameDep){
        this.idDep = idDep;
        this.nameDep = nameDep;
    }
    
    //lấy mã
    public String getIdDep() {
        return idDep;
    }
    
    //đặt mã
    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }
    
    //lấy tên bộ phận
    public String getNameDep() {
        return nameDep;
    }
    
    //đặt tên bộ phận
    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    //lấy số lượng nhân viên
    public int getNumOfStaff() {
        return numOfStaff;
    }

    //tăng số lượng nhân viên
    public void newStaff() {
        this.numOfStaff++;
    }

    //xuất ra bảng thông tin
    public void toDS(){
        System.out.format("+--------------------------------+--------------------------------+%n");
        System.out.format("| %-30s | %-30s |%n","Mã bộ phận:",idDep);
        System.out.format("| %-30s | %-30s |%n","Tên bộ phận:",nameDep);
        System.out.format("| %-30s | %-30d |%n","Tên bộ phận:",numOfStaff);
        System.out.format("+--------------------------------+--------------------------------+%n");
    }


    //các hàm tự tạo để thêm mới nhân viên và quản lý bằng mã
    
    public Staff addEmployee(String id, String name,  int age, double coeffSalary, LocalDate startDate, double overtimeHours, int numdayOff){
        Employee nv = new Employee();
        nv.setId(id);
        nv.setName(name);
        nv.setAge(age);
        nv.setCoeffSalary(coeffSalary);
        nv.setStartDate(startDate);
        nv.setDepartment(this);
        nv.setOvertimeHours(overtimeHours);
        nv.setNumdayOff(numdayOff);
        numOfStaff++;
        return nv;
    }

    public Staff addManager(String id, String name, int age, double coeffSalary, LocalDate startDate, String position, int numdayOff){
        Manager ld = new Manager();
        ld.setId(id);
        ld.setName(name);
        ld.setAge(age);
        ld.setCoeffSalary(coeffSalary);
        ld.setStartDate(startDate);
        ld.setDepartment(this);
        ld.setPosition(position);
        ld.setNumdayOff(numdayOff);
        numOfStaff++;
        return ld;
    }
}
