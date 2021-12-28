package assignment2;

import java.io.*;


/*Lớp FileAtion chứa các phương thức dùng để thao tác lên các file
* - Đọc file và lưu dữ liệu vào mảng
* - Nhận tham sô là một chuỗi và file để lưu chuỗi vào file
* - Đảm nhận quá trình lưu các hoạt động của phiên làm việc vào file CONSOLE_OUTPUT
* */

public class FileAction {

    //mảng string chữa dữ liệu và phương thức đọc lấy được từ file
    private String[] arrText;

    //phương thức trả về mảng String. Không để các đối tượng khác thao tác trên mảng này.
    public String[] getArrText(){
        return arrText;
    }

    /*Phương thức nhận vào tham số là một chuỗi và một tệp
    * - THực hiện ghi chuỗi đó vào tệp
    * - Trả về true nếu quá trình ghi diễn ra thành công và ngược lại
    * */
    public boolean writeToFile(String text, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(text);
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("File couldnot be found!");
            return false;
        } catch (IOException ex) {
            System.out.println("Cannot write to file!");
            return false;
        }
    }


    /*Phương thức nhận vào một file và tiến hành đọc dữ liệu trên file đó
    * - dữ liệu đọc được được lưu vào một StringBuilder và sau đó tách ra thành một mảng String
    * vói mỗi String đơn lẻ là dữ liệu của một sản phẩm
    * - phương thức trả về true nếu quả trình đọc file diễn ra thành công và ngược lại
    * */
    public boolean readFromFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                //trong nội dung của file thì mỗi sản phẩm đã nằm trên một dòng
                //lưu ý phải thêm \n để phân cách dòng, việc đọc readLine() đã không đọc dấu \n từ trong tệp
                text.append(s).append("\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File couldnot be found!");
            return false;
        } catch (IOException e) {
            System.out.println("Cannot read from file! ");
            return false;
        }
        //tách dữ liệu thành màng các thông tin sản phảm
        arrText = text.toString().split("\\n");
        return true;
    }



    /*Phương thức có nhiệm vụ lưu chuỗi vào file CONSOLE_OUTPUT
    * chuỗi này được truyền vào khi gọi phương thức
    * */
    public void write(String text) {
        try {
            FileWriter fileWriter = new FileWriter("console_output.txt", true);
            BufferedWriter bufferedReader = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedReader);
            printWriter.println(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*Khi gọị phương thức này thì dữ liệu trong file CONSOLE_OUTPUT bị xóa
    * phương thức chỉ được gọi duy nhất một lần khi khởi chạy chương trình*/
    public void clear(){
        try {
            FileWriter fileWriter = new FileWriter("console_output.txt", false);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
