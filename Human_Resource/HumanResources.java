import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HumanResources {
    static Scanner input = new Scanner(System.in);                      //đối tượng input có trách nhiệm nhận lấy đầu vào từ việc nhập của người dùng
    static ArrayList<Staff> member = new ArrayList<Staff>();            //mảng chứa các đối tượng là nhân viên công ty
    static ArrayList<Department> dList = new ArrayList<Department>();   //mảng chứa các đối tượng là văn phòng, bộ phận công ty
    static String formatDS = "| %-8s | %-30s | %-5s | %-20s | %-20s | %-14s | %-5s | %-9s | %-5s | %-20s |%n";
    public static void main(String[] args) {
        String formatVati = "| %-20s | %-20s | %-20s | %-20s | %-20s| %-20s |%n";
        String formatVati1 =  "| %-20s | %-20s | %-20s | %-20s |%n";
        String formatVati2 =  "| %-20s | %-20s | %-20s |%n";

        //danh sách một số nhân viên và bộ phận thêm vào mặc định
/*         Department de1 = new Department("BP1", "ke hoach");
        LocalDate time = LocalDate.of(2021,8,9);
        Staff nv1 = new Employee("NV1", "Nguyen Van Quyet", 23, 1.5, time, de1, 15.5, 2);
        Staff nv2 = new Manager("CB1", "Chau Khai Phong", 30, 2, LocalDate.parse("2019-12-12"), de1, "Business Leader", 1);
        Staff nv3 = de1.addEmployee("NV2", "Ho Quang Hieu", 21, 2, LocalDate.of(2020, 3, 22), 11, 4);
        Department del2 = new Department("BP2", "nhan su");
        Staff nv4 = del2.addEmployee("NV4", "Trinh Dinh Quang", 31, 4, LocalDate.of(2020, 9, 11), 2, 0);
        Staff nv5 = del2.addEmployee("NV5", "Nguyen Quang Hai", 22, 3, LocalDate.of(2020, 4, 22), 3, 5);
        Staff nv6 = de1.addManager("CB2", "Dang Van Lam", 44, 3, time, "Project Leader", 0);
        Staff nv7 = del2.addManager("NV7", "Phan Boi Chau", 100, 4, time, "Technical Leader", 2);
        dList.add(de1);
        dList.add(del2);
        member.add(nv1);
        member.add(nv2);
        member.add(nv3);
        member.add(nv4);
        member.add(nv5);
        member.add(nv6);
        member.add(nv7);
 */


        //mở đầu với lời giới thiệu
        System.out.format("+------------------------------------------+%n");
        System.out.format("| %-40s |%n","ỨNG DỤNG QUẢN LÝ NHÂN LỰC");
        System.out.format("| %-40s |%n","Nhập vào số với chức năng tương ứng:");
        System.out.format("+------------------------------------------+%n");

        int k;   //biến k nhận giá trị là số tương ứng với lựa chọn của người dùng
        do{

            //hiển thị menu chính
            System.out.println();
            System.out.format("+----------------------+----------------------+----------------------+----------------------+---------------------+----------------------+%n");
            System.out.format(formatVati," 1 - Thêm mới"," 2 - Nhân viên"," 3 - Bộ phận"," 4 - Tìm kiếm"," 5 - Sắp xếp"," 0 - Kết thúc");
            System.out.format("+----------------------+----------------------+----------------------+----------------------+---------------------+----------------------+%n");
            System.out.print("\n(Nhập số): ");
            k = input.nextInt();

            if(k==1){
                //hiển thị menu con mục thêm mới
                System.out.println("\nTHÊM MỚI:");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+%n");
                System.out.format(formatVati1," 1 - Thêm nhân viên"," 2 - Thêm quản lý"," 3 - Thêm bộ phận"," 0 - Thoát ra");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+%n");
                System.out.print("\n(Nhập số): ");
                int z = input.nextInt();                            //nhận giá trị nhập vào của người dùng

                while(z!=1 && z!=2 && z!=3 && z!=0){                //kiểm tra giá trị nhập vào và yêu cầu nhập lại nếu không phù hợp
                    System.out.print("Nhập sai, hãy nhập lại: ");
                    z = input.nextInt();
                }

                if(z==1){
                    System.out.println("\nMỤC THÊM NHÂN VIÊN MỚI");
                    //gọi hàm thêm nhân viên mới
                    addEmployee();
                }else if(z==2){
                    System.out.println("\nMỤC THÊM QUẢN LÝ MỚI");
                    //gọi hàm thêm quản lý mới
                    addManager();
                }else if(z==3){
                    System.out.println("\nMỤC THÊM BỘ PHẬN MỚI");
                    //gọi hàm thêm bộ phận mới
                    addDepartment();
                }else if(z==0){
                    System.out.println();
                    continue;                                       //số 0 để thoát ra khỏi menu con
                }
            }
            
            else if(k==2){
                //hiển thị menu con mục in ( xem ) danh sách nhân viên
                System.out.println("\nIN DANH SÁCH:");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+%n");
                System.out.format(formatVati1," 1 - In bảng lương"," 2 - In theo bộ phận"," 3 - In tắt cả hồ sơ"," 0 - Thoát ra");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+%n");
                System.out.print("\n(Nhập số): ");
                int y = input.nextInt();                            //nhận giá trị nhập vào

                while(y!=1 && y!=2 && y!=3 && y!=0){                //kiểm tra giá trị
                    System.out.print("Nhập sai, hãy nhập lại: ");
                    y = input.nextInt();
                }

                //chạy các hàm tương ứng với lựa chọn để in danh sách
                if(y==1){
                    System.out.println("\nMỤC IN BẢNG LƯƠNG");
                    //hàm in toàn bộ danh sách theo bảng lương
                    showAllStaff();
                }
                else if(y==2){
                    System.out.println("\nMỤC IN DANH SÁCH THEO BỘ PHẬN");
                    //hàm in danh sách theo bộ phận
                    showDefStaff();
                }
                else if(y==3){
                    System.out.println("\nMỤC IN THÔNG TIN CHI TIẾT");
                    //hàm in thông tin chi tiết từng nhân viên
                    showDetails();
                    System.out.println();
                }
                else{
                    System.out.println();
                    continue;
                }
                
            }else if(k==3){
                //liệt kê danh sách các bộ phận của công ty
                System.out.println("\nMỤC KIỆT KÊ CÁC BỘ PHẬN");
                //hàm in các bộ phận
                showDefList();

            }else if(k==4){
                //hiển thị menu con của mục tìm kiếm
                System.out.println("\nTÌM KIẾM:");
                System.out.format("+----------------------+----------------------+----------------------+%n");
                System.out.format(formatVati2," 1 - Bằng mã số nv"," 2 - Bằng Tên nv"," 0 - Thoát ra");
                System.out.format("+----------------------+----------------------+----------------------+%n");
                System.out.print("\n(Nhập số): ");
                int x = input.nextInt();                            //nhận giá trị nhập vào

                while(x!=1 && x!=2 && x!=0){                        //kiểm tra giá trị nhập vào
                    System.out.print("Nhập sai, hãy nhập lại: ");
                    x = input.nextInt();
                }

                //hiển thị và chạy các hàm tương ứng với lựa chọn của người dùng
                if(x==1){
                    System.out.println("\nMỤC TÌM KIẾM BẰNG ID");
                    //Tìm kiếm bằng ID
                    findById();
                }else if(x==2){
                    System.out.println("\nMỤC TÌM KIẾM BẰNG TÊN");
                    //Tìm kiếm bằng tên
                    findByName();
                }else{
                    System.out.println();
                    continue;
                }

            }else if(k==5){
                if(member.size()==0){
                    System.out.println("\nDanh sách nhân viên công ty đang trống\nChưa có mục để sắp xếp");
                }
                else{
                //hiển thị menu con của mục sắp xếp
                System.out.println("\nSẮP XẾP:");
                System.out.format("+----------------------+----------------------+----------------------+%n");
                System.out.format(formatVati2," 1 - Lương tăng"," 2 - Lương giảm"," 0 - Thoát ra");
                System.out.format("+----------------------+----------------------+----------------------+%n");
                System.out.print("\n(Nhập số): ");
                int z = input.nextInt();                        //nhận giá trị nhập vào

                while(z!=1 && z!=2 && z!=0){                    //kiểm tra giá trị nhập vào
                    System.out.print("Nhập sai, hãy nhập lại: ");
                    z = input.nextInt();
                }

                //hiển thị và chạy các hàm tương ứng với lựa chọn của người dùng
                if(z==1){
                    System.out.println("\nMỤC SẮP XẾP TĂNG DẦN THEO LƯƠNG");
                    //Sắp xếp tăng dần
                    upSorter();
                }else if(z==2){
                    System.out.println("\nMỤC SẮP XẾP GIẢM DẦN THEO LƯƠNG");
                    //Sắp xếp giảm dần
                    downSorter();
                }else{
                    System.out.println();
                    continue;
                }
                }
            }else{
                //tiếp tục vòng lặp nếu người dùng nhập giá trị số không có ý nghĩa
                //kết thúc bằng câu chào khi người dùng đóng ứng dụng
                if(k==0){
                    String formatVati3 = "| %-20s |%n";
                    System.out.format("+----------------------+%n");
                    System.out.format(formatVati3,"<<<< Tạm biệt >>>>");
                    System.out.format("+----------------------+%n");
                }
            }
        }while(k!=0);   //ứng dụng chỉ kết thúc khi người dùng nhập số 0
    }
    





    /**hàm được gọi khi người dùng chọn mục thêm bộ phận mới
     * hiển thị từng mục để nhập thông tin
     */
    public static void addDepartment(){
        Department bp = new Department();               //tạo đối tượng bộ phận mới
        System.out.print("\nNhập mã ID:  ");

        bp.setIdDep(input.next());                      //nhận thông tin và lưu vào đối tượng
        input.nextLine();
        System.out.print("Tên bộ phận: ");
        bp.setNameDep(input.nextLine());
        System.out.println();

        if(dList.add(bp)){                              //thêm đối tượng vào mảng, thông báo kết quả
            System.out.println("Thêm thành công.\n");
        }else{
            System.out.println("Thêm không thành công.\n");
        }
    }





    /**hàm sẽ được gọi khi người dùng chọn mục thêm nhân viên
     * nếu chưa có bất kỳ bộ phận hay phòng ban nào được tạo thì hàm yêu cầu người dùng tạo trước
     * nếu đá có sẵn bộ phận tạo trước thì hàm in danh sách các bộ phận để người dùng chọn
     * hiển thị từng mục để người dùng nhập thông tin
     * thông báo khi thêm thành công
     */
    public static void addEmployee(){
                    //kiểm tra số lượng bộ phận đã có, nếu chưa có hãy tạo trước
        if(dList.size() == 0){
            System.out.println("\nChưa có một bộ phận công ty nào\nHãy thêm một bộ phận trước\n");
        }
        else{       //liệt kê danh sách tên các bộ phận
            System.out.println("Đây là danh sách bô phận:");
            System.out.format("+-------+----------------------+%n");
            for(int i = 0; i < dList.size(); i++){
                System.out.format("| %-5d | %-20s |%n",i+1,dList.get(i).getNameDep());
                System.out.format("+-------+----------------------+%n");
            }
                    //lựa chọn bộ phận để thêm nhân viên
            System.out.print("Chọn bộ phận sẽ thêm nhân viên mới: ");
            int b = input.nextInt();
            while(b<0 || b>dList.size()){
                System.out.print("Nhập sai, Hãy nhập lại: ");
                b = input.nextInt();
            }
                    //tạo đối tượng và nhận thông tin
            Staff nv = new Employee();
            System.out.println("\nDành cho bộ phận: " + dList.get(b-1).getNameDep().toUpperCase());
            System.out.println();
            System.out.print("Mã số nhân viên: ");
            nv.setId(input.next());
            input.nextLine();
            System.out.print("Họ và tên:       ");
            nv.setName(input.nextLine());
            System.out.print("Tuổi:            ");
            nv.setAge(input.nextInt());
            System.out.print("Hệ số lương:     ");
            nv.setCoeffSalary(input.nextDouble());
            System.out.print("Ngày vào làm (dd mm yyyy): ");
            int day = input.nextInt();
            int month = input.nextInt();
            int year = input.nextInt();
            nv.setStartDate(LocalDate.of(year, month, day));
            nv.setDepartment(dList.get(b-1));
            System.out.print("Số giờ làm thêm: ");
            ((Employee) nv).setOvertimeHours(input.nextDouble());
            System.out.print("Số ngày nghỉ:    ");
            nv.setNumdayOff(input.nextInt());            
            nv.getDepartment().newStaff();  //tăng số lượng nhân viên trong bộ phận
            System.out.println();
                    //lưu đối tượng vào mảng và thông báo kết quả
            if(member.add(nv)){
                System.out.println("Thêm thành công.\n");
            }else{
                System.out.println("Thêm không thành công.\n");
            }
        }
    }





    /**hàm được gọi khi người dùng muốn thêm một nhân viên có chức vụ quan trọng
     * các thao tác tương tự như thêm một nhân viên mới
    */
    public static void addManager(){
                    //kiểm tra số lượng bộ phận đã có
        if(dList.size() == 0){
            System.out.println("\nChưa có một bộ phận công ty nào\nHãy thêm một bộ phận trước\n");
        }
        else{       //liệt kê danh sách tên các bộ phận
            System.out.println("Đây là danh sách bô phận:");
            System.out.format("+-------+----------------------+%n");
            for(int i = 0; i < dList.size(); i++){
                System.out.format("| %-5d | %-20s |%n",i+1,dList.get(i).getNameDep());
                System.out.format("+-------+----------------------+%n");
            }
                    //lựa chọn bộ phận để thêm nhân viên
            System.out.print("Chọn bộ phận sẽ thêm quản lý mới: ");
            int b = input.nextInt();
            while(b<0 || b>dList.size()){
                System.out.print("Nhập sai, Hãy nhập lại: ");
                b = input.nextInt();
            }
                    //tạo đối tượng mới và nhận thông tin
            Staff cb = new Manager();
            System.out.println("\nDành cho bộ phận: " + dList.get(b-1).getNameDep().toUpperCase());
            System.out.println();
            System.out.print("Mã số nhân viên:  ");
            cb.setId(input.next());
            input.nextLine();
            System.out.print("Họ và tên:        ");
            cb.setName(input.nextLine());
            System.out.print("Tuổi:             ");
            cb.setAge(input.nextInt());
            System.out.print("Hệ số lương:      ");
            cb.setCoeffSalary(input.nextDouble());
            System.out.print("Ngày vào làm (dd mm yyyy): ");
            int day = input.nextInt();
            int month = input.nextInt();
            int year = input.nextInt();
            cb.setStartDate(LocalDate.of(year, month, day));
            cb.setDepartment(dList.get(b-1));
            input.nextLine();
            System.out.print("Chức vụ đảm nhận: ");
            ((Manager) cb).setPosition(input.nextLine());
            System.out.print("Số ngày nghỉ:     ");
            cb.setNumdayOff(input.nextInt());
            cb.getDepartment().newStaff();  //tăng số lượng nhân viên trong bộ phận
            System.out.println();
                    //thêm đối tượng vào mảng và thông báo kết quả
            if(member.add(cb)){
                System.out.println("Thêm thành công.\n");
            }else{
                System.out.println("Thêm không thành công.\n");
            }
        }
    }




    //hàm dùng để hiển thị danh sách toàn bộ nhân viên theo kiểu bảng lương
    public static void showAllStaff(){
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.format(formatDS,"ID","Tên","Tuổi","Chức vụ","Bộ phận","Ngày vào làm","Nghỉ","Hs lương","TC","Tổng lương");
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        for(int i = 0; i < member.size(); i++){
            member.get(i).toDS();
        }
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.println();
    }




    //hàm dùng để hiển thị danh sách nhân viên theo từng bộ phận
    public static void showDefStaff(){
                //liệt kê các bộ phận
         System.out.println("Đây là danh sách bô phận:");
            System.out.format("+-------+----------------------+%n");
            for(int i = 0; i < dList.size(); i++){
                System.out.format("| %-5d | %-20s |%n",i+1,dList.get(i).getNameDep());
                System.out.format("+-------+----------------------+%n");
            }
                //chờ lựa chọn của người dùng
        System.out.print("Chọn bộ phận với số tương ứng mà bạn muốn hiển thị danh sách: ");
        int k = input.nextInt();
        while(k<0 || k>dList.size()){
            System.out.print("Nhập sai, Hãy nhập lại: ");
            k = input.nextInt();
        }
                //dựa vào lựa chọn để hiển thị danh sách -- Tìm và lọc danh sách dựa trên ID bộ phận
        String id = dList.get(k-1).getIdDep();
        int d =0;
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.format(formatDS,"ID","Tên","Tuổi","Chức vụ","Bộ phận","Ngày vào làm","Nghỉ","Hs lương","TC","Tổng lương");
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        for(int i = 0; i < member.size(); i++){
            if(member.get(i).getDepartmentId().equals(id)){
                member.get(i).toDS();;
                d++;
            }
        }
        if(d==0){
            System.out.println("Không có nhân viên trong bộ phận này!!!");
        }
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.println();
    }




    //hàm dùng để hiển thị thông tin chi tiết của tất cả nhân viên theo kiểu hồ sơ chi tiết
    public static void showDetails(){
        System.out.println();
        for(int i = 0; i < member.size(); i++){
            System.out.println("\nNhân viên số " + (i + 1));
            member.get(i).displayInformation();
        }
    }




    //hàm dùng để liệt kê thông tin chi tiết của các bộ phận gồm tên, id, số lượng nhân viên
    public static void showDefList(){
        if(dList.size() == 0){
            System.out.println("\nChưa có một bộ phận công ty nào\nHãy thêm một bộ phận trước\n");
        }
        else{
            for(int i = 0; i < dList.size(); i++){
                dList.get(i).toDS();
            }
        }
    }





    //hàm tìm kiếm và hiển thị hồ sơ bằng mã ID nhân viên
    public static void findById(){
        System.out.print("Nhập vào ID nhân viên mà bạn muốn tìm: ");
        String id = input.next();
        int d = 0;
        for(int i = 0; i < member.size(); i++){
            if(member.get(i).getId().equals(id)){
                member.get(i).displayInformation();
                d++;
            }
        }
        if(d>0){
            System.out.println("\nKết quả tìm kiếm: " + d + " nhân viên\n");
        }else{
            System.out.println("\nKhông tìm thấy nhân viên nào!\nHãy kiểm tra lại mã ID của bạn.\n");
        }
    }




    //hàm tìm kiếm và hiển thị hồ sơ bằng tên nhân viên
    public static void findByName(){
        input.nextLine();
        System.out.print("Nhập vào tên nhân viên mà bạn muốn tìm: ");
        String name = input.nextLine().toLowerCase();
        int d = 0;
        for(int j = 0; j < member.size(); j++){
            String who = member.get(j).getName().toLowerCase();
            if(who.contains(name)){
                member.get(j).displayInformation();
                d++;
            }
        }
        //đi kèm với thông báo số lượng kết quả tìm được
        if(d>0){
            System.out.println("\nKết quả tìm kiếm: " + d + " nhân viên\n");
        }else{
            System.out.println("\nKhông tìm thấy nhân viên nào!\nHãy kiểm tra lại TÊN nhân viên mà bạn đã nhập.\n");
        }
    }




    //hàm sắp xếp và hiển thị danh sách nhân viên theo thứ tự thu nhập tăng dần
    public static void upSorter(){
        Staff z = member.get(0);
        for(int x = 0; x < member.size() -1; x++){
            for(int y = x + 1; y < member.size(); y++){
                double a = member.get(x).mycalculateSalary();
                double b = member.get(y).mycalculateSalary();
                if(a>b){
                    z = member.get(y);
                    member.set(y, member.get(x));
                    member.set(x, z);
                }
            }
        }
        //hiển thị danh sách đã sắp xếp
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.format(formatDS,"ID","Tên","Tuổi","Chức vụ","Bộ phận","Ngày vào làm","Nghỉ","Hs lương","TC","Tổng lương");
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        for(int i = 0; i < member.size(); i++){
            member.get(i).toDS();
        }
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");

        System.out.println();
    }




    
    //hàm sắp xếp và hiển thị danh sách nhân viên theo thứ tự thu nhập giảm dần
    public static void downSorter(){
        Staff z = member.get(0);
        for(int x = 0; x < member.size() -1; x++){
            for(int y = x + 1; y < member.size(); y++){
                double a = member.get(x).mycalculateSalary();
                double b = member.get(y).mycalculateSalary();
                if(a<b){
                    z = member.get(y);
                    member.set(y, member.get(x));
                    member.set(x, z);
                }
            }
        }
        //hiển thị danh sách đã sắp xếp
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        System.out.format(formatDS,"ID","Tên","Tuổi","Chức vụ","Bộ phận","Ngày vào làm","Nghỉ","Hs lương","TC","Tổng lương");
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");
        for(int i = 0; i < member.size(); i++){
            member.get(i).toDS();
        }
        System.out.format("+----------+--------------------------------+-------+----------------------+----------------------+----------------+-------+-----------+-------+----------------------+%n");

        System.out.println();
    }
}