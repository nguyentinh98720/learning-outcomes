package assignment2;

/*Lớp danh sách dùng để tạo danh sách liên kết và thao tác với các đối tượng:
* danh sách có các node chính: đầu danh sách (head) và cuối danh sách (tail).
* với các phương thức thêm và xóa.*/
public class MyLinkedList<T> {
    Node<T> head;
    Node<T> tail;

    public MyLinkedList(){
        head = tail = null;
    }

    //kiểm tra xem danh sách có trống hay không
    public boolean isEmpty(){
        return head == null;
    }

    //trả về số lượng phần tử trong danh sách
    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int size = 0;
            Node<T> current = head;
            while (current != null) {
                size++;
                current = current.getNext();
            }
            return size;
        }
    }

    //thêm một NỐT vào cuối danh sách
    public void addLast(Node<T> newNode) {
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    //thêm một ĐỐI TƯỢNG vào cuối danh sách
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        addLast(newNode);
    }

    //Xóa toàn bộ đối tượng có trong danh sách
    public void clear(){
        if (!isEmpty()) {
            while (head != null) {
                head.setData(null);
                head = head.getNext();
            }
            tail = null;
        }
    }

    //Xóa một đối tượng khỏi danh sách
    public void delete(T data) {
        if (head.getData().equals(data)) {
            head.setData(null);
            head = head.getNext();
        } else {
            Node<T> current = head;
            Node<T> temp = current;
            while (current != null && !current.getData().equals(data)) {
                temp = current;
                current = current.getNext();
            }
            if (current == null) {
                return;
            }
            if(current == tail) {
                current.setData(null);
                temp.setNext(null);
                tail = temp;
                return;
            }
            current.setData(null);
            temp.setNext(current.getNext());
        }
    }

    //phương thức hiển thị cụ thể cho đối tượng là một sản phẩm Product
    public void displayList() {
        if (isEmpty()) {
            System.out.println("Nothing to display!!\nThe Linked List is empty.");
        } else {
            Node<T> current = head;
            while (current != null) {
                Product show = (Product) current.getData();
                show.display();
                current = current.getNext();
            }
        }
    }

}
