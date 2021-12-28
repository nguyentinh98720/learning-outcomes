package assignment2;

import java.io.File;

/*Lớp OperationToProduct chứa các hàm chạy với các chức năng tương ứng trên menu.*/
public class OperationToProduct {
    /*Lớp có 3 đối tượng chính để thực hiện chức năng:
    * - Đối tượng file tepDanhSach là file cần cho quá trình đọc và lưu dữ liệu.
    * - Đối tượng danhSachSanPham là một Linked List dùng để lưu và thao tác trên dữ liệu là các sản phẩm.
    * - Đối tượng writeToOutput ghi lại thông tin vào file console_output.txt.*/
    private final File tepDanhSach;
    private final MyLinkedList<Product> danhSachSanPham;
    FileAction writeToOutput = new FileAction();




    //Hàm tạo
    public OperationToProduct(){
        tepDanhSach = new File("Data.txt"); //Đường dẫn cho file DATA.TXT.
        danhSachSanPham = new MyLinkedList<>();     //linked list mới cho đối tượng danh sách.
    }





    /*Hàm loadFideToList được sử dụng trong chức năng số 1 trên menu:
    * Đọc dữ liệu từ file, chuyển thành các đối tượng sản phẩm và lưu vào danh sách Linked List
    *
    * Quy trình:- Gọi đối tượng thaoTac (thuộc lớp FileAction) chứa các phương thức thao tác với file.
    *           - Nếu quá trình đọc file thành công thì đối tượng trả về một mảng String.
    *               - Mỗi phần tử trong mảng là thông tin cho một sản phẩm.
    *               - Chuyển dữ liệu String sản phẩm đó thành đối tượng Product tương ứng.
    *               - Thêm lần lượt các dối tượng Product vào cuối danh sách Linhed List.
    *           - Nếu quá trình đọc file gặp lỗi thì quy trình trên không được thực hiện.
    * */
    void loadFileToList(){
        String[] mangDS;//Mảng String
        //tạo đối tượng thao tác với file
        FileAction thaoTac = new FileAction();
        /*Truyền cho phương thức của đối tương file cần đọc
         * - Phương thức trả về true nếu quá trình đọc file diễn ra thành công và ngược lại.
         * */
        if (thaoTac.readFromFile(tepDanhSach)) {
            mangDS = thaoTac.getArrText();
            /*Nhận mảng String chữa dữ liệu các sản phẩm mà đối tượng trả về.
            * */
            for (String s : mangDS) {
                /*Duyệt qua mỗi phần tử của mảng:
                *   - Mối phần tử trong mảng lớn sẽ chứa các thông tin (ngăn cách nhau bằng dấu ',') của 1 sản phẩm.
                *   - Tạo một mảng String nhỏ hơn để lưu các thành phần như ID, tên,.. của mỗi sản phẩm.
                *   - Tử một phần tử của mảng lớn ta chia nhỏ với dấu ',' đã quy ước và lưu vào mảng con.
                *   - Chuyển các dữ liệu từ mảng nhỏ thành các thuộc tính đối tượng Product.
                *   - Hoàn chỉnh một đối tượng Product và thêm nó vào cuối Linked List.
                * */
                String[] thongTinSP = s.split(", ");            //chia nhỏ
                String maVach = thongTinSP[0];                      //ID hay mã vạch sản phẩm
                String tenSanPham = thongTinSP[1];                  //tên
                Integer soLuong = Integer.parseInt(thongTinSP[2]);  //số lượng
                double giaThanh = Double.parseDouble(thongTinSP[3]);//giá sản phẩm
                //tạo đối tượng với các thuộc tính nhận được.
                Product sanPham = new Product(maVach, tenSanPham, soLuong, giaThanh);
                //thêm vào cuối linkedList.
                danhSachSanPham.addLast(sanPham);
            }
            //Thông báo khi thành công.
            System.out.println("Access to " + tepDanhSach.getPath() + " : >>> Successfully!");
        } else {
            //Thông báo khi không đọc được file.
            System.out.println("Access to " + tepDanhSach.getPath() + " : >>> ERROR!!!");
        }
    }



    /*Hàm displayFromList được sử dụng đến trong chức năng số 1 và số 3 trong menu:
    * - Hiển thị danh sách sản phẩm có trong linkedlist.
    * - Hàm này có gọi tới phương thức display dùng chung ở phía dưới.
    * */
    void displayFromList(){
        //ghi vào tệp console_output.txt
        writeToOutput.write("List of Products");

        System.out.println("List of Products from Linked List::");
        //gọi tới phương thức display và truyền vào danh sách sản phẩm cần hiển thị
        //danh sách cần hiển thị ở đây là LinkedList chính: danhSachSanPham.
        display(danhSachSanPham);
    }



    /*Phương thức display() dược tạo để hiển thị dữ liệu trong LinkedList được truyền vào:
    * - Bao gồm đường viền, tiêu đề đã được format.
    * - Hiển thị các thông tin sản phẩm đã được format
    * - Đối với quả trình lưu tài liệu vào file CONSOLE_OUTPUT thì diễn ra riêng và dữ liệu lưu vào không
    * được format.
    * */
    void display(MyLinkedList<Product> danhSach) {
        String duongVien = "+----------------------------------------------------+";
        String format = "| %-5s | %-18s | %8s | %10s |%n";
        System.out.println(duongVien);
        System.out.printf(format, "ID", "Title", "Quanlity", "Price");
        System.out.println(duongVien);
        /*gọi tiếp đến phương thức hiển thị được xây dựng trong linkedList.
        * Ở phương thức này thì các sản phẩm trong linkedlist được hiển thị ra console dưới dạng đã được fomat*/
        danhSach.displayList();
        System.out.println(duongVien);

        /*Lưu danh sách vào file CONSOLE_OUTPUT:
        * Mỗi sản phẩm có phương thức toSring() thể hiện thông tin sản phẩm đó.
        * Phương thức tótring() này được xây dựng trong lóp Product.*/
        writeToOutput.write("-----------");
        Node<Product> current = danhSach.head;
        //lặp qua các phần tử trong danh sách.
        while (current != null) {
            //Lấy thông tin là các chuỗi String.
            String chuoiSp = current.getData().toString();
            //lưu chuỗi vào file
            writeToOutput.write(chuoiSp);
            current = current.getNext();
        }
        writeToOutput.write("-----------");
    }



    /*Hàm addNewProduct được gọi đến trong chức năng số 2 trên menu:
    * - Hàm gọi đối tượng checkInput để yêu cầu user nhập và kiểm tra giá trị nhập vào.
    * - Lần lượt lưu các giá trị đó với định dạng tương.
    * - Tạo đối tượng Product mới với thông tin nhận được.
    * - Thêm đối tượng vào cuối danh sách LinkedList.
    * - Lưu kết quả vào file CONSOLE_OUTPUT.
    * */
    void addNewProduct(){
        String format = "%-18s: ";
        String maSP, tenSP;
        Integer soLuong;
        double giaSP;
        /*đối tượng kiểm tra dữ liệu user nhập vào.
        Các giá trị sẽ được validate trước khi dùng để tạo đối tượng mới.*/
        CheckInput checkInput = new CheckInput();
        System.out.printf(format,"Input new ID");
        maSP = checkInput.checkID();        //Mã ID sản phẩm.
        System.out.printf(format,"Product's name");
        tenSP = checkInput.checkName();     //Tên sản phẩm.
        System.out.printf(format, "Product's quanlity");
        soLuong = checkInput.checkInt();    //Số lượng sản phẩm.
        System.out.printf(format, "Product's price");
        giaSP = checkInput.checkDouble();   //Giá sản phẩm
        //Tạo đối tượng Product mới dựa trên giá trị nhận được.
        Product sanPham = new Product(maSP, tenSP, soLuong, giaSP);
        //Thêm vào cuối danh sách linkedlist.
        danhSachSanPham.addLast(sanPham);

        System.out.print("The Product: " + sanPham);
        System.out.println("Add to the List successfully!");

        //Ghi kết quả vào file CONSOLE_OUTPUT.
        writeToOutput.write("New product: <" + maSP + ", " + tenSP + ", " + soLuong + ", " + giaSP
        + "> add to list successfully!");
    }



    /*Hàm saveListToFile được gọi với chức năng số 4 trên menu:
    * - Nếu danh sách hiện tại đang trống thì sẽ nhận được thông báo và không thực hiện lưu vào file.
    * - Khi danh sách có dữ liệu thì tiến hành ghi vào file.
    * - Hàm vừa kết hợp in ra console đi kèm với việc lưu kết quả vào file CONSOLE_INPUT.
    * */
    void saveListToFile(){
        if (danhSachSanPham.size() == 0) {//Không lưu khi danh sách đang trống.
            System.out.println("There're nothing to save!\nThe list is empty.");
            writeToOutput.write("Nothing to save!");
        } else {
            /*Tạo một StringBuilder:
            * Duyệt qua danh sách lấy thông tin của từng sản phẩm và thêm vào StringBuilder.
            * Cuối cùng ta được một String chứa dữ liệu của tất cả các sản phẩm.
            * Tiến hành lưu Strin đó vào file
            * */
            StringBuilder danhSach = new StringBuilder();
            Node<Product> current = danhSachSanPham.head;
            while (current != null) {
                String s = current.getData().toString();
                danhSach.append(s);
                current = current.getNext();
            }
            //gọi đối tượng thao tác đê lưu file
            FileAction thaoTacFile = new FileAction();
            //phương thức của đối tượng nhận vào chuỗi String và File để tiến hành lưu.
            //nếu lưu thành công thì phương thức trả về true và ngược lại.
            if (thaoTacFile.writeToFile(danhSach.toString(), tepDanhSach)) {
                System.out.println("SAVED!");
                writeToOutput.write("Save List successfully.");
            } else {
                System.out.println("CANNOT SAVE!!");
                writeToOutput.write("Save failed.");
            }
        }
    }



    /*Hàm searchProduct được gọi với chức năng số 5 trên menu:
    * - Nếu danh sách trống thì hẳn là không có gì để tìm -> thông báo ra console.
    * - Khi danh sách có chứa dữ liệu:
    *   + Gọi đối tượng checkInput để người dùng nhập giá trị tìm kiếm vào
    *   + Tạo một danh sách để lưu các giá trị tìm kiếm được
    *   + Duyệt qua danh sách và tìm kiếm sản phẩm
    *   + Sản phẩm tìm kiếm được sé được lưu vào danh sách đã tạo trước đó
    *   + Hiển thị danh sách tìm kiếm được ra màn hình
    * - Hàm kết hợp hiển thị ra màn hình và lưu kết quả vào file CONSOLE_OUTPUT*/
    void searhProduct(){
        if (danhSachSanPham.isEmpty()) {
            System.out.println("The list is empty!!!\nYou should load from file or add new Products before search..");
            writeToOutput.write("Nothing to search!");
        } else {
            //đối tượng kiểm tra giá trị nhập vào.
            CheckInput check = new CheckInput();
            String format = "%-18s: ";
            System.out.printf(format,"Enter Product's ID");

            //Nhận và kiểm tra giá trị nhập vào - Ở đây yêu cầu là tìm kếm bằng ID.
            String findId = check.checkID();
            //Danh sách chứa kết quả tìm kiếm được.
            MyLinkedList<Product> danhSachSpTimKiemDuoc = new MyLinkedList<>();

            /*Duyệt qua danh sách:
            * - Lấy thông tin là mã ID của từng sản phẩn trong danh sách
            * - So sánh với giá trị mà user nhập vào
            * - Nếu trùng khớp thì thêm sản phẩm đó vào danh sách tìm kiếm được
            * */
            Node<Product> current = danhSachSanPham.head;
            while (current != null) {
                Product sanPham = current.getData();
                if (sanPham.getBcode().equals(findId)) {
                    danhSachSpTimKiemDuoc.addLast(sanPham);
                }
                current = current.getNext();
            }

            /*Kiểm tra danh sách tìm kiếm được và hiển thị kết quả*/
            if (danhSachSpTimKiemDuoc.isEmpty()) {
                System.out.println("No product found!!");
                writeToOutput.write("Not found.");
            } else {
                writeToOutput.write("Found.");
                System.out.println(">>Products found>> " + danhSachSpTimKiemDuoc.size() + " result(s).");
                //gọi phương thức display để hiển thị danh sách
                display(danhSachSpTimKiemDuoc);
                System.out.println("<<<Found>>>");
            }
        }
    }



    /*Hàm deleteProduct được gọi với chức năng số 6 trên menu:
    * - Nếu danh sách trống thì chẳng có gì để xóa -> thông báo ra console
    * - Khi danh sách có dữ liệu:
    *   + Nhận và kiểm tra giá trị nhập vào
    *   + Duyệt qua danh sách và tìm kiếm sản phẩm
    *   + Lưu sản phẩm tìm kiếm được vào một danh sách mới và xóa nó ở danh sách cũ
    *   + Hiển thị danh sách các sản phẩm bị xóa ra màn hình
    * - Hàm kết hơpj việc hiển thị ra console và lưu vào tệp CONSOLE_OUTPUT bằng đối tượng writeToOutput
    * */
    void deleteProduct(){
        if (danhSachSanPham.isEmpty()) {
            System.out.println("There're nothing in the list!!");
            writeToOutput.write("Nothing to delete!");
        } else {
            //đối tượng kiểm tra giá trị nhập vào.
            CheckInput checkInput = new CheckInput();
            System.out.println("All of Products with this ID will be deleted.");
            System.out.print("Enter the ID: ");

            //Nhận và kiểm tra giá trị nhập vào.
            String deletionId = checkInput.checkID();
            //Tạo danh sách chứa dữ liệu các sản phẩm bị xóa.
            MyLinkedList<Product> danhSachSpBiXoa = new MyLinkedList<>();

            /*Duyệt qua danh sách:
            * - Lấy thông tin ID của từng sản phẩm trong danh sách
            * - So sánh với giá trị nhập vào của user
            * - Nếu trùng khớp thì thêm sản phẩm đó vòa danh sách bị xóa và xóa nó ở danh sách gốc
            * */
            Node<Product> current = danhSachSanPham.head;
            while (current != null) {
                Product sanPham = current.getData();
                if (sanPham.getBcode().equals(deletionId)) {
                    danhSachSpBiXoa.addLast(sanPham);
                    danhSachSanPham.delete(sanPham);
                }
                current = current.getNext();
            }

            //Kiểm tra danh sách và hiển thị kết quả tìm kiếm được
            if (danhSachSpBiXoa.isEmpty()) {
                System.out.println("No products deleted!");
                writeToOutput.write("No product was deleted.");
            } else {
                writeToOutput.write("Deleted:");
                System.out.println(">>> " + danhSachSpBiXoa.size() + " product(s) deleted.");
                //gọi tới phương thức hiển thị danh sách
                display(danhSachSpBiXoa);
                System.out.println("<<<Deleted>>>");
            }
        }
    }




    /*Hàm sortProductById thực hiện cho chức năng số 7 trên menu:
    * - Tiến hành đưa dữ liệu trong LinkedList ra một mảng.
    * - Gọi phương thức sắp xếp nhanh cho mảng
    * - Sau khi sắp xếp thì duyệt qua mảng và lưu lại vào linkedlist
    * - Hiển thị dữ liệu đã được sắp xếp trong linkedlisr
    * */
    void sortProductsById(){
        //tạo mảng sản phẩm với độ dài bằng với độ dài của linkedlist
        Product[] mangSanPham = new Product[danhSachSanPham.size()];

        //Duyệt qua linkedlist và lưu dữ liệu vào mảng.
        Node<Product> current = danhSachSanPham.head;
        int i = 0;
        while(current != null){
            mangSanPham[i] = current.getData();
            i++;
            current = current.getNext();
        }

        //Gọi phương thức sắp xếp nhanh cho mảng
        quickSort(mangSanPham, 0, mangSanPham.length - 1);

        //Xóa dữ liệu cũ trong linkedlist bằng phương thức clear()
        danhSachSanPham.clear();

        //Duyệt qua mảng và lưu giá trị từ mảng vào linkedlist
        for (Product product : mangSanPham) {
            danhSachSanPham.addLast(product);
        }

        //Hiển thị danh sách đã được sắp xếp
        display(danhSachSanPham);

        System.out.println("<<<Sorted>>>");
        writeToOutput.write("Sorted.");
    }




    /*Chức năng số 8 trong menu:
    * Chuyển đổi giá trị số lượng sản phẩm của sản phẩm đầu tiên trong linkedlist từ hệ đếm thập phân sang nhị phân
    *
    * Quy trình:
    * - Nếu list trống thì thông báo và kết thúc hàm
    * - Khi list có dữ liệu:
    *   + Lấy thông tin số lượng sản phẩm của sản phẩm đầu tiên
    *   + Tiến hành chuyển đổi và hiển thị ra màn hình
    * (Sử dụng phương pháp đệ quy và ngăn xếp)
    * */
    void convertToBinary(){
        if (danhSachSanPham.isEmpty()) {
            System.out.println("The list is empty!!!");
            writeToOutput.write("The list is empty!");
            return;
        }

        //lấy sản phẩm đầu tiên
        Product sanPhamDauTien = danhSachSanPham.head.getData();
        //lấy dữ liệu số lượng sản phẩm
        Integer soLuongSp = sanPhamDauTien.getQuantity();
        System.out.println(" - decimal: " + soLuongSp);
        writeToOutput.write("The quanlity of first Product:\n - decimal: " + soLuongSp.toString());
        System.out.print(" - binary: ");

        //nếu giá trị == 0 thì không cần chuyển đổi và hiển thị luôn ra màn hình
        if(soLuongSp == 0){
            System.out.println(0);
            writeToOutput.write(" - binary: 0");
        } else {
            //Phần stringbuilder này dùng để lưu kết quả của từng bước đệ quy
            StringBuilder bieuDienNhiPhan = new StringBuilder();

            //gọi hàm binary để chuyển đổi
            binary(soLuongSp, bieuDienNhiPhan);
            //Đảo ngược giá trị StringBuider để nhận được số nhị phân
            String nhiPhan = bieuDienNhiPhan.reverse().toString();

            //Hiển thị và lưu kết quả vào tệp
            System.out.println(nhiPhan);
            System.out.println();
            writeToOutput.write(" - binary: " + nhiPhan);
        }

    }


    /*Hàm được gọi với chức năng số 9: Tạo Stack load dữ liệu vào và hiển thị ra màn hình.
    * Các bước:
    *   - Tạo Stack mới
    *   - Duyệt qua và lưu dữ liệu trong LinkedList vào Stack
    *   - Duyệt qua Stack và hiển thị dữ liệu
    * */
    void loadToStack(){
        String duongVien = "+----------------------------------------------------+";
        //Tạo đối tượng stack mới
        MyStack<Product> nganXep = new MyStack<>();

        //duyệt qua linkedlist và sao chép dữ liệu vào stack
        Node<Product> current = danhSachSanPham.head;
        while(current != null){
            Product sanPham = current.getData();
            nganXep.push(sanPham);
            current = current.getNext();
        }

        //duyệt qua stack, lấy từng phần tử và hiển thị
        writeToOutput.write("Reverse list:\n");
        System.out.println(duongVien);
        while (!nganXep.isEmpty()) {
            Product spLayRaTuNganXep = nganXep.pop();
            //sử dụng phương thức hiển thị của Product cho từng dứ liệu
            spLayRaTuNganXep.display();

            //kết hợp với việc ghi vào tệp CONSOLE_OUTPUT
            writeToOutput.write(spLayRaTuNganXep.toString());
        }
        System.out.println(duongVien);

    }



    /*Hàm được gọi với chức năng số 10: Load dữ liệu vào Queue rồi hiển thị
    * Các bước:
    *   - Tạo Queue mới
    *   - Duyệt qua và lưu dữ liệu từ LinkedList vảo Queue
    *   - Duyệt qua Queue và hiển thị dữ liệu
    * */
    void loadToQueue(){
        String duongVien = "+----------------------------------------------------+";
        //Tạo đối tượng queue mới
        MyQueue<Product> hangDoi = new MyQueue<>();

        //duyệt qua list và sao chép dữ liệu vào queue
        Node<Product> current = danhSachSanPham.head;
        while (current != null) {
            Product sanPham = current.getData();
            hangDoi.enQueue(sanPham);
            current = current.getNext();
        }
        writeToOutput.write("Traverse list:\n");
        System.out.println(duongVien);

        //duyệt qua queue và hiển thị dữ liệu
        while (!hangDoi.isEmpty()) {
            Product spLayRaTuHangDoi = hangDoi.deQueue();
            //sử dụng phương thức hiển thị của lớp Product
            spLayRaTuHangDoi.display();

            //kết hợp xuất ra file console_output
            writeToOutput.write(spLayRaTuHangDoi.toString());
        }
        System.out.println(duongVien);

    }



    //phương thức xóa sạch dữ liệu trong danh sách sản phẩm gốc
    void clearList(){
        //gọi đến phương thức clear() trong lớp LinkedList
        danhSachSanPham.clear();
    }



    /*Phương thức tính toán mã nhị phân
    * Nhận vào số number (khác 0) muốn chuyển đổi và một stringbuilder để lưu kết quả
    * - Chia number cho 2 và lưu số dư vào stringbuilder
    * - Tiếp tục chia thương lượt trước cho 2 và lấy số dư
    * - Quá trình tiếp tục cho đến khi thương nhận được == 0
    * - Đảo ngược thức tự các số trong stringbuilder để nhận kết quả là số nhị phân
    * */
    void binary(int number, StringBuilder s) {
        if (number != 0) {
            int soDu = number%2;
            s.append(soDu);
            //đệ quy
            binary(number/2, s);
        }
    }


    /*Hàm quickSort nhận vào 3 tham số gồm: mảng cần sắp xếp, chỉ mục bắt đầu và chỉ mục kết thúc
    * - hàm chỉ hoát động khi chỉ mục bắt đầu còn nhỏ hơn chỉ mục kết thúc
    * - gọi hàm partition để xác định vị trí chốt và sắp xếp các giá trị, nhỏ hơn về trước và lớn hơn về sau chốt
    * và các gíá trị đó mới chỉ có thể phân cách bởi chốt chứ chưa có thứ tự chính xác
    * - tiến hành đệ quy để sắp xếp các phần tử phía trước và sau chốt. Trong vòng đệ quy lại tiếp tục chọn các chốt
    * con và thực hiện sắp xếp, cho đến khi mảng nhận vào chỉ còn một phần tử đó là chốt
    * */
    void quickSort(Product[] arr, int lowIndex, int highIndex) {
        //điều kiện dừng đệ quy
        if (lowIndex < highIndex) {
            /*xác định chỉ số của phần tủ chốt
            * đưa các phần tử có giá trị nhỏ hơn chốt về trước chốt
            * các phần lớn hơn chốt ở sau chốt
            * Quy trình cụ thể được nêu trong hàm partition
            * Giá trị đem ra so sánh ở đây là mã ID của mỗi sản phẩm trong mảng
            * */
            int chooseIndex = partition(arr, lowIndex, highIndex);

            //đệ quy để chia nhỏ mảng và sắp xếp trước và sau chốt

            //các phần tử có giá trị nhỏ hơn chốt tiếp tục được chọn chốt mới và sắp xếp
            quickSort(arr, lowIndex, chooseIndex - 1);

            //các phần tử có giá trị lớn hơn chốt tiếp tục được chọn chốt mới và sắp xếp
            quickSort(arr, chooseIndex + 1, highIndex);
        }
    }

    /*Hàm nhận vào một mảng và các giá trị chỉ số mảng (thấp nhất và cao nhất)
    * chọn một phần tử làm chốt
    * đưa các giá trị nhỏ hơn về trưóc chốt và lớn hơn về sau chốt
    * trả về chỉ mục của chốt trong mảng đó
    * */
    private int partition(Product[] arr, int lowIndex, int highIndex) {
        //chọn chốt là phần tử cuối trong mảng
        Product pivot = arr[highIndex];
        //Lấy giá trị ID của phần chốt (sản phẩm được đánh dấu là chốt)
        String bCodeOfPivot = pivot.getBcode();
        //chỉ số của phần tử có giá trị ID nhỏ nhất
        int smIndex = (lowIndex - 1);

        //duyệt qua các phần tử trong mảng sản phẩm
        for (int i = lowIndex; i < highIndex; i++) {
            //lấy giá trị ID của từng phần tử và so sánh với ID của phần tử gốc
            String currentBCode = arr[i].getBcode();
            if (currentBCode.compareTo(bCodeOfPivot) < 0) {
                /*nếu giá trị ID của phần tử hiện tại nhỏ hơn phần tử chốt thì hoán đổi chúng với những phần
                phía đầu mảng*/
                smIndex++;
                Product temp = arr[smIndex];
                arr[smIndex] = arr[i];
                arr[i] = temp;
            }
        }
        /*Kết thúc việc đưa các phần tử nhỏ hơn về đầu và chỉ phần tử chốt là phần tử nhỏ nhất còn lại
        thì đưa phần tử chốt về vị trí mà tại đó các phần tử phía trước đều nhỏ hơn chốt
        */
        Product temp = arr[smIndex + 1];
        arr[smIndex + 1] = arr[highIndex];
        arr[highIndex] = temp;
        //trả về vị trí chốt
        return smIndex + 1;
    }

}
