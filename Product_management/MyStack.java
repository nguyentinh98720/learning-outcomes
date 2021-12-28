package assignment2;

/*Ngăn xếp được triển khai theo kiểu danh sách móc nối
* được dùng để lưo trữ các đối tượng với 2 phương thức chính
* - Thêm một đối tượng vào từ phía trên
* - Lấy một đối tượng ra từ phiá trên
Đối tượng nào được thêm vào sau thì được ra trước
* */
public class MyStack<T> {
    Node<T> top;

    public MyStack(){
        top = null;
    }

    //kiểm tra xem ngăn xếp có trống hay không
    public boolean isEmpty(){
        return top == null;
    }

    //Thêm một đối tượng vào đầu danh sách
    public void push(T data){
        Node<T> newNode = new Node<>(data);
        if (!isEmpty()) {
            newNode.setNext(top);
        }
        top = newNode;
    }

    //lấy ra và và xỏa một đối tượng ở đẩu danh sách
    public T pop(){
        T data;
        if (isEmpty()) {
            data = null;
        } else {
            data = top.getData();
            top.setData(null);
            top = top.getNext();
        }
        return data;
    }
}
