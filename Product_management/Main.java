package assignment2;

/*
* Lớp Main chứa hàm main để chạy chương trình, hiển thị menu lựa chọn
*
* */
public class Main {
    /*Lớp cần có 2 đối tượng chính để thực hiện luồng chương trình:
    * - Đối tượng hoatDong sẽ gọi các phương thức tương thực hiện các yêu cầu ững với sự lựa chọn của user.
    * - Đối tượng writeToOutput dùng để ghi quá trình mà chương trình hoạt động vào file console_output.txt
    * */
    static OperationToProduct hoatDong = new OperationToProduct();
    static FileAction writeToOutput = new FileAction();


    //Hàm main điều khiển luồng chương trình.
    public static void main(String[] args) {
        //Khỏi tạo đối tương checkInput dùng để kiểm tra giá trị nhập vào của user.
        CheckInput checkInput = new CheckInput();
        //Biến choose để lưu lựa chọn menu.
        int choose;
        System.out.println("Choose one of this options:");

        //Xóa nội dung trong file console_output.txt để chuẩn bị cho mỗi phiên làm việc mới.
        writeToOutput.clear();
        //Ghi nội dung menu vào file, nội dung này chỉ đươc ghi một lần đầu file và không lặp lại.
        writeToOutput.write("HISTORY:\n<<<Start>>>");
        writeToOutput.write("1 - Load data from file to Linked List and display" +
                "\n2 - Add a new product to Linked List" +
                "\n3 - Display all Products in Linked List" +
                "\n4 - Save data from Linked List to file" +
                "\n5 - Search Product by ID" +
                "\n6 - Delete Product by ID" +
                "\n7 - Sort Products by ID" +
                "\n8 - Convert the first Product's quanlity to binary" +
                "\n9 - Load data from Linked List to Stack and display" +
                "\n10 - Load data from Linked List to Queue amd display" +
                "\n0 - Exit");

        /*Hiển thị menu để user lựa chọn, vòng lặp chương trình chỉ kết thúc khi user chọn exit.*/
        do {
            //phương thức hiển thị menu.
            myMenu();
            System.out.print("Your choose: ");
            writeToOutput.write("\nCHOOSED:");
            /*Kiểm tra và nhận giá trị user nhập vào. Đối tượng checkInput sẽ gọi phương thức checkMenu để đảm bảo
            giá trị nhập vào là hợp lệ. Quá trình hoạt dộng được nêu rõ trong lớp CheckInput*/
            choose = checkInput.checkMenu();
            /*Dựa vào giá trị lựa chọn mà user nhập vào để truyền cho hàm myActive, hàm này sẽ gọi đối tượng hoatDong
            và các phương thức tương ứng với giá trị choose*/
            myActive(choose);
            //Khi giá trị choose == 0 thì vòng lặp kết thúc,
        } while (choose != 0);
        //Hiển thị lời chào, kết thúc chương trình
        System.out.println("Thank you! Goodbye...");
        writeToOutput.write(">>>End<<<");
    }


    /*Hàm nhận tham số là số nguyên tương ứng với lựo chọn của user*/
    private static void myActive(int choose) {
        switch (choose) {
            case 1: //Đọc dữ liệu từ file DATA.TXT lưu vào LinkedList
                System.out.println("\nReading document from file...");
                writeToOutput.write("1: Load document from file to LinkedList and display");
                /*vì chức năng có thể lựa chọn nhiều lần nên khi bắt đầu đọc file và lưu dữ liệu đó vào linkedlist
                thì phải tiến hành làm trống linkedList để tránh sự trùng lặp từ những lượt chọn trước
                Cần gọi 3 phương thức của đối tượng.*/
                hoatDong.clearList();       //làm sạch linkedlist
                hoatDong.loadFileToList();  //đọc dữ liệu từ file và lưu vào linkedlist
                hoatDong.displayFromList(); //hiển thị dữ liệu hiện có trong linkedlist
                System.out.println();
                break;
            case 2: //Thêm một sản phẩm mới vào danh sách.
                System.out.println("\nAdd new product, follow the instruction:");
                writeToOutput.write("2: Add new Product.");
                //gọi phương thức thêm mới của đối tượng hoatDong.
                hoatDong.addNewProduct();
                System.out.println();
                break;
            case 3: //Hiển thị dữ liệu trong danh sách.
                System.out.println("\nShow list");
                writeToOutput.write("3: Display all Products in LinkedList.");
                //gọi phương thức hiển thị dữ liệu từ linkedlist của đối tượng hoatDong.
                hoatDong.displayFromList();
                System.out.println();
                break;
            case 4: //Lưu danh sách sản phẩm vào file DATA.TXT
                System.out.println("\nSave list to file");
                writeToOutput.write("4: Save all Products in LinkedList to file.");
                //gọi phương thức lưu dữ liệu từ linkedlist vào file của đối tượng hoatDong.
                hoatDong.saveListToFile();
                System.out.println();
                break;
            case 5: //Tìm kiếm sản phẩm bằng mã ID.
                System.out.println("\nSearch Product");
                writeToOutput.write("5: Search Product by ID.");
                //gọi phương thức tìm kiếm trong list của đối tượng.
                hoatDong.searhProduct();
                System.out.println();
                break;
            case 6: //Xóa sản phẩm từ mã ID.
                System.out.println("\nDelete Product");
                writeToOutput.write("6: Delete Product by ID.");
                //gọi phương thúc xóa dữ liệu trong list của đối tượng.
                hoatDong.deleteProduct();
                System.out.println();
                break;
            case 7: //Sắp xếp các sản phẩm theo mã ID.
                System.out.println("\nSort Product");
                writeToOutput.write("7: Sort Products by ID (alphabetically).");
                //gọi phương thức sắp xếp linkedlist của đối tượng, Phương thức sẽ sắp xếp và hiển thị list.
                hoatDong.sortProductsById();
                System.out.println();
                break;
            case 8: //Chuyển số lượng sản phẩm của sản phẩm đầu tiên trong danh sách từ thập phân sang nhị phân.
                System.out.println("\nBinary code for the first Product's quanlity.");
                writeToOutput.write("8: Convert to binary.");
                //gọi phương thức chuyển đổi thập phân sang nhị phân (sủ dụng đệ quy).
                hoatDong.convertToBinary();
                System.out.println();
                break;
            case 9: //Sao chép danh sách sản phẩm vào Stack và hiển thị ra màn hình.
                System.out.println("\nLoad Products to Stack and display:");
                writeToOutput.write("9: Load Products to Stack and display:");
                /*gọi phương thức sao chép dữ liệu từ linkedlist sang stack và hiển thị dữ liệu theo thứ tự lấy ra
                từ stack.*/
                hoatDong.loadToStack();
                System.out.println();
                break;
            case 10://Sao chép danh sách sản phẩm vào Queue và hiển thị ra màn hình.
                System.out.println("\nLoad Products to Queue and display:");
                writeToOutput.write("10: Load Products to Queue and display:");
                /*gọi phương thức sao chép dữ liệu tuè linkedlist sang queue và hiển thị dứ liệu theo thứ tự lấy ra
                từ queue.*/
                hoatDong.loadToQueue();
                System.out.println();
                break;
            case 0: //Rời khỏi phiên làm việc.
                writeToOutput.write("0: Exit");
                break;
        }
    }


    //Hàm hiển thị menu ra console theo format.
    private static void myMenu() {
        String format = "| %2d  %-25s|%n";
        String line = "+------------------------------+";
        System.out.printf("%30s%n","+------>> Product list <<------+");
        System.out.printf(format, 1, "Load data from file");
        System.out.printf(format, 2, "Add a new product");
        System.out.printf(format, 3, "Show list");
        System.out.printf(format, 4, "Save list to file");
        System.out.printf(format, 5, "Search (by ID)");
        System.out.printf(format, 6, "Delete (by ID)");
        System.out.printf(format, 7, "Sort (by ID)");
        System.out.printf(format, 8, "Convert to Binary");
        System.out.printf(format, 9, "Data from stack");
        System.out.printf(format, 10, "Data from queue");
        System.out.printf(format, 0, "Exit");
        System.out.println(line);
    }
}
