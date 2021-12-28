package assignment2;

/*Một node là một đơn vị cấu tạo cho danh sách móc nối
* nó bao gồm dữ liệu là một đối tượng và một con trỏ trỏ đến nốt khác phía sau nó
* dành cho kiểu danh sách liên kết đơn.
* */
public class Node<T> {
    private T data;
    private Node<T> next;

    //Khởi tạp một node bằng dữ liệu và con trỏ next
    public Node(T data, Node<T> node) {
        this.data = data;
        next = node;
    }

    //Khởi tạo node chỉ với một đối tượng, con trỏ next trỏ đến null
    public Node(T data) {
        this(data, null);
    }

    //Đặt dư liệu cho node
    public void setData(T data) {
        this.data = data;
    }

    //Đặt con trỏ next cho node
    public void setNext(Node<T> node) {
        next = node;
    }

    //Lấy dữ liệu từ node
    public T getData() {
        return data;
    }

    //Lấy giá trị con trỏ là node tiếp theo của node của node hiện tại
    public Node<T> getNext() {
        return next;
    }
}
