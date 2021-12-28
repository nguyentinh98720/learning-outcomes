package assignment2;

/*Hàng đợi được triển khai theo kiểu danh sánh móc nối
được dùng để lưu các đối tượng với hai phương thức chính:
* - Thêm một đối tượng vào một đầu
* - Lấy ra đối tượng từ đầu còn lại
Đối tượng nào đươc thêm vào trước thì được lấy ra trước.*/
public class MyQueue<T> {
    Node<T> head;
    Node<T> tail;

    public MyQueue(){
        head = tail = null;
    }

    //kiểm tra xem hàng đợi có trống hay không
    public boolean isEmpty(){
        return head == null;
    }

    //thêm một đối tượng vòa phía cuối danh sách
    public void enQueue(T data){
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    //lấy và xóa một đối tượng tở phía đầu danh sách
    public T deQueue(){
        T data;
        if (isEmpty()) {
            data = null;
        } else {
            data = head.getData();
            head.setData(null);
            head = head.getNext();
        }
        return data;
    }
}
