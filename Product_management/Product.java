package assignment2;


/*Lớp sản phẩm chứa thông tin của một sản phẩm và các phương thức quan trọng như:
* - Lấy tên, id, số lượng, giá của sản phẩm
* - Niểu diễn thông tin sản phẩm thành một string
* - Hiển thị thông tin sản phẩm ra consple theo format
* */
public class Product {
    private String bcode;   //mã ID
    private String title;   //tên sản phẩm
    private Integer quantity;//số lượng
    private double price;   //giá bán

    //hàm tạo
    public Product(){}

    //hàm tạo một đối tượng sản phẩm với các thông tin cụ thể
    public Product(String bcode, String title, Integer quantity, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    //lấy thông tin ID
    public String getBcode() {
        return bcode;
    }

    //lấy thông tin về số lượng sản phẩm
    public Integer getQuantity(){
        return quantity;
    }

    //lấy thông tin về tên sản phẩm
    public String getTitle(){
        return title;
    }

    //lấy thông tin về giá bán sản phẩm
    public double getPrice(){
        return price;
    }

    //Chuyển đối tượng sản phẩm thành một chuỗi thông tin có thể đọc được
    @Override
    public String toString(){
        return bcode + ", " + title + ", " + quantity.toString() + ", " + price + "\n";
    }

    //hiển thị thông tin sản phẩm theo format
    public void display(){
        String format = "| %-5s | %-18s | %-8d | %10.1f |%n";
        System.out.printf(format, bcode, title, quantity, price);
    }
}
