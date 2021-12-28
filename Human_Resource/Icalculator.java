public interface Icalculator {
    //hàm tính lương và trả về giá trị dưới dạng chuỗi đã chuyển về mẫu tiền tệ
    //dùng để hiển thị
    abstract String calculateSalary();

    //hàm tính lương và trả về giá trị dưới dạng số thực
    //dùng để so sánh vá sắp xếp
    abstract double mycalculateSalary();
}
