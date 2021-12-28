import java.util.Scanner;

public class GradeStudent {

    static Scanner input = new Scanner(System.in);
    static int midWeight, finWeight, homWeight;                 //các biến lưu giá trị trọng số của mỗi phần điểm điểm
    static double midTermMark, finalTermMark, homeworkMark;  //các biến lưu giá trị điểm được tính theo trọng số điểm

    //hàm main điều khiển luồng chính của chương trình, nó gọi các hàm con theo thứ tự tương ứng
    public static void main(String[] args) {

        begin();                //giới thiệu về ứng dụng        
        System.out.println();

        midTerm();              //nhập và tính toán điểm thi giữa kỳ
        System.out.println();

        finalTerm();            //nhập và tính toán điểm thi cuối kỳ
        System.out.println();

        homework();             //nhập và tính toán điểm Assignment và điểm chuyên cần
        System.out.println();

        report();               //đưa ra tổng điểm và đánh giá cho sinh viên
    }

    //hàm begin() hiển thị thông báo giới thiệu
    public static void begin(){
        System.out.println("Chào mừng bạn đến với ứng dụng đánh giá năng lực sinh viên của chúng tôi!\n");
        System.out.println("Ứng dụng sẽ dựa trên số điểm mà bạn nhập và quy tắc xếp loại của nhà trường để\n" +
        "tính toán và đưa ra xếp hạng của sinh viên đó.\nHãy nhập đúng và đủ thông tin cần thiết sau đây:");
    }

    //hàm midTerm() hướng dẫn người dùng nhập điểm thi giữa kỳ
    public static void midTerm(){


        //khai báo các biến để lưu điểm được nhập vào
        int diem, tongdiem;
        int diemcong = 0;
        
        //nhập trọng số của phần điểm
        System.out.println("MidTerm - Điểm thi giữa kỳ:");
        System.out.print("Trọng số (0 - 100) :  ");        
        midWeight = input.nextInt();

        //kiểm tra và hướng dẫn người dùng cho đến khi người dùng nhập giá trị phù hợp
        while(midWeight > 100){
            System.out.print("Trọng số của phần điểm phải <= 100\nHãy nhập lại trọng số: ");
            midWeight = input.nextInt();
        }

        //nhập số điểm thi đã đạt được
        System.out.print("Điểm số đã đạt được :  ");
        diem = input.nextInt();

        //hỏi người dùng trường hợp điểm cộng cho sinh viên
        System.out.println("Sinh viên có thuộc đối tượng được cộng điểm không?");
        System.out.print("Nếu có hãy nhập '1', nhập '2' để bỏ qua:  ");
        int k = input.nextInt();
        if(k == 1){
            System.out.print("Điểm cộng :  ");
            diemcong = input.nextInt();
        }

        //tính toán phần tổng điểm và phần điểm trọng số của sinh viên
        tongdiem = diem + diemcong;
        if(tongdiem > 100){
            tongdiem = 100;
            System.out.println(" **Đã đưa tổng điểm về mức cao nhất là 100");
        }
        midTermMark = (double)Math.round(((double)tongdiem*(double)midWeight/100) * 10) / 10;

        //đưa kết quả ra màn hình
        System.out.println("Tổng điểm :   " + tongdiem + " / 100");
        System.out.println("Điểm trọng số : " + midTermMark + " / "+ midWeight);
    }

    //hàm finalTerm() hướng dẫn người dùng nhập điểm thi cuối kỳ
    //quá trình nhập và tính toán tương tự với hàm midTerm()
    public static void finalTerm(){
        int diem, tongdiem;
        int diemcong = 0;
        System.out.println("Final - Điểm thi cuối kỳ:");

        //phần trọng số
        System.out.print("Trọng số (0 - 100) :  ");
        finWeight = input.nextInt();
        while(finWeight > (100 - midWeight)){
            System.out.print("Đảm bảo tổng trọng số của các phần điểm là 100\nMức trọng số có thể nhập còn lại (0 - " +
            (100 - midWeight) + ")\nHãy nhập lại trọng số: ");
            finWeight = input.nextInt();
        }

        System.out.print("Điểm số đã đạt được :  ");
        diem = input.nextInt();

        //phần điểm cộng
        System.out.println("Sinh viên có thuộc đối tượng được cộng điểm không?");
        System.out.print("Nếu có hãy nhập '1', nhập '2' để bỏ qua:  ");
        int k = input.nextInt();
        if(k == 1){
            System.out.print("Điểm cộng :  ");
            diemcong = input.nextInt();
        }

        //phần tổng điểm
        tongdiem = diem + diemcong;
        if(tongdiem > 100){
            tongdiem = 100;
            System.out.println(" **Đã đưa tổng điểm về mức cao nhất là 100");
        }
        finalTermMark = (double)Math.round(((double)tongdiem*(double)finWeight/100) * 10) / 10;
        System.out.println("Tổng điểm :   " + tongdiem + " / 100");
        System.out.println("Điểm trọng số : " + finalTermMark + " / "+ finWeight);
    }

    //hàm homework hướng dẫn người dùng nhập điểm Assignment, số buổi tham gia học
    //sau đó tính toán và trả về điểm chuyên cần, tổng điểm của sinh viên
    public static void homework(){
        int number_of_homework;
        System.out.println("Homework - Bài tập về nhà:");

        //kiểm tra và hướng dẫn nhập đúng giá trị trọng số của phần điểm
        System.out.print("Trọng số (0 - 100) :  ");
        homWeight = input.nextInt();        
        while(homWeight != (100 - midWeight - finWeight)){
            System.out.print("Đảm bảo tổng trọng số của các phần điểm là 100\nMức trọng số có thể nhập còn lại (" +
            (100 - midWeight - finWeight) + ")\nHãy nhập lại trọng số: ");
            homWeight = input.nextInt();
        }

        System.out.print("Tổng số bài tập về nhà: ");
        number_of_homework = input.nextInt();

        /**sau khi nhận được số lượng bài Assignment mà sinh viên đã làm
         * gọi hàm homeworkList() với tham số truyền vào là số lượng bài làm sau đó nhận lại giá trị tổng điểm
         * tính toán điểm trọng số dựa trên giá trị tổng điểm 
        */
        homeworkMark = (double)Math.round(homeworkList(number_of_homework)*(double)homWeight*10)/10;
        System.out.println("Điểm trọng số : " + homeworkMark + " / " + homWeight);
    }


    /**hàm homeworkList() nhận một tham số là số lượng bài Assignment của sinh viên
     * hiển thị danh sách lần lượt các bài tập để người dùng nhập điểm
     * hướng dẫn người dùng nhập số buổi học mà sinh viên tham gia và tính toán điểm chuyên cần
     * dựa vào điểm bài tập và điểm chuyên cần để tính toán tổng điểm
    */
    public static double homeworkList(int num){
        int attend, point;                      //số buổi học đã tham gia và điểm chuyên cần
        int mysum = 0;                          //tổng số điểm bài tập đạt được
        int yoursum = 0;                        //tổng số điểm bài tập tối đa
        int[][] arr = new int[num][2];          //mảng lưu điểm của các bài tập

        //lần lươt hiển thị và lưu điểm bài tập vào mảng
        for(int i = 0; i < arr.length; i++){
            System.out.print("Assignment " + (i+1) + "( điểm đạt được và điểm tối đa ) :  ");
            arr[i][0] = input.nextInt();
            arr[i][1] = input.nextInt();

            //thông báo cho người dùng khi nhập điểm bài tập không hợp ký, yêu cầu nhập lại
            while(arr[i][0] > arr[i][1]){
                System.out.print("Số điểm đạt được đã vượt quá số điểm tối đa\nHãy nhập lại điểm Assignment " + (i+1) + " :  ");
                arr[i][0] = input.nextInt();
                arr[i][1] = input.nextInt();
            }

            //đưa các giá trị về mức tối đa
            if(arr[i][0] > 150){
                arr[i][0] = 150;
                System.out.println("*  ");
            }
            if(arr[i][1] > 150){
                arr[i][1] = 150;
                System.out.println("* Đã quy về mức điểm tối đa là 150");
            }
        }

        //hỏi só lượng buổi học cua sinh viên và tính toán điểm chuyên cần
        System.out.print("Số buổi học đã tham gia và được điểm danh: ");
        attend = input.nextInt();
        point = attend*5;
        if(point > 30){
            point = 30;
        }
        System.out.print("Điểm chuyên cần :  " + point + " / 30\n");

        //tính và thông báo tổng điểm của sinh viên
        for(int j = 0; j < arr.length; j++){
            mysum += arr[j][0];
            yoursum += arr[j][1];
        }
        System.out.print("Tổng điểm : " + (mysum + point) + " / " + (yoursum + 30) + "\n");

        //trả về giá trị là tỷ lệ diểm đạt được với điểm tối đa dưới dạng số double
        return (double)(mysum + point)/(double)(yoursum + 30);
    }

    //hàm report() tính toán tổng điểm của cả 3 phần điểm theo thang điểm 100, đưa ra GPA và đánh giá tham khảo cho sinh viên
    public static void report(){
        double overall = (midTermMark + finalTermMark + homeworkMark);
        System.out.println("Điểm tổng thể (thang điểm 100) :  " + overall);
        double gpa = 0;
        if(overall == 100){ gpa = 4.0; }
        else if(overall >= 85){ gpa = 3.0; }
        else if(overall >= 75){ gpa = 2.0; }
        else if(overall >= 60){ gpa = 1.0; }
        else{ gpa = 0.0; }
        System.out.println("GPA của sinh viên : " + gpa + "\n");
        if(gpa == 4.0){ System.out.print("Kết quả ấn tượng!!!!\nSinh viên nên tiếp tục phát huy.");}
        else if(gpa == 3.0){ System.out.print("3.0 - Đạt tiêu chí");}
        else if(gpa == 2.0){ System.out.print("2.0 - Not bad\nSinh viên cần cố gắng thêm.");}
        else if(gpa <= 1.0){ System.out.print("Sinh viên cần cố gắng thêm.");}
    }
}