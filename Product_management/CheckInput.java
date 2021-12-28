package assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

/*Lớp CheckInput chứa các phương thức kiểm tra giá trị nhập vào của user và trả về kết quả với định dạng hợp lệ
nếu user nhập vào một giá trị sai định dạng hoặc quá giới hạn thì được yêu cầu nhập lại
* - nhập vào lựa chọn cho menu
* - nhập vào cho một mã ID, tên .. cho một sản phẩm
* */
public class CheckInput {
    private final Scanner input;

    public CheckInput(){
        input = new Scanner(System.in);
    }


    //Dứ liệu cho một ID là một chuỗi không trống và không quá 'maxLength' ký tự
    public String checkID(){
        String s;
        int maxLengthOfId = 5;
        while (true) {
            s = input.nextLine();
            if (s.length() == 0) {
                System.out.println("Field cannot be empty!\nPlease enter id: ");
            } else if (s.length() <= maxLengthOfId){
                break;
            } else {
                System.out.println("Opps, sorry!!");
                System.out.print("Please enter id <= " + maxLengthOfId + " characters: ");
            }
        }
        return s;
    }

    //Dữ liệu cho tên một sản phẩm không được trống và không quá 'maxLwngthOfName' ký tự
    public String checkName(){
        String s;
        int maxLengthOfName = 20;
        while (true) {
            s = input.nextLine();
            if (s.length() == 0) {
                System.out.print("Field cannot be empty!\nPlease enter name: ");
            } else if (s.length() <= maxLengthOfName) {
                break;
            } else {
                System.out.println("The name is too long!!");
                System.out.print("Please enter name <= " + maxLengthOfName + " characters: ");
            }
        }
        return s;
    }


    //Dữ liệu nhập vào cho số lượng sản phẩm phải là một số nguyên không âm
    public Integer checkInt(){
        int i;
        while (true) {
            try {
                i = input.nextInt();
                if (i >= 0) break;
                System.out.print("Quanlity ís not a negative number!\nInput again: ");
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Wrong format!!");
                System.out.print("Please enter a number for quanlity: ");
                input.nextLine();
            }
        }
        return i;
    }

    //Dữ liệu cho giá sản phẩm phải là một số thực không âm
    public double checkDouble(){
        double d;
        while (true) {
            try {
                d = input.nextDouble();
                if(d >= 0) break;
                System.out.print("Price is not a negative number!\nInput again: ");
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Uh.Wrong format..\nPlease enter a number for price: ");
                input.nextLine();
            }
        }
        return d;
    }


    //Lựa chọn cho menu phải là một số nguyên và nằm trong khoảng cho phép
    public int checkMenu() {
        boolean check = false;
        int choose = 0;
        while(!check){
            try {
                choose = input.nextInt();
                if (choose < 0 || choose > 10) {
                    throw new Exception();
                }
                check = true;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number!!");
                input.nextLine();
                System.out.print("Your choose: ");
            } catch (Exception e) {
                System.out.println("Out of menu!!");
                input.nextLine();
                System.out.print("Choose again: ");
            }
        }
        return choose;
    }
}
