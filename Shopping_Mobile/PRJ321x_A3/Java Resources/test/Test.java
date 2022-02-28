package test;

import java.sql.Date;

public class Test {
	/*
	public static void main (String[] args) {
		//đối tượng xử lý dữ liệu trong bảng products
		ListProductDAO action = new ListProductDAO();
		ArrayList<Product> list = new ArrayList<>();
		//lấy ra danh sách 3 sản phẩm để thao tác
		try {
			list = action.queryListProduct(1, 3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProductOrders productOrders;
		ArrayList<ProductOrders> listOther = new ArrayList<>();
		Cart cart = new Cart();
		//duyêtj qua các sản phẩm đã lấy ra được
		for(Product product : list) {
			//thêm vào giỏ hàng
			cart.add(product);
			//đối tượng productOrders tương ứng để thêm vào Orders
			productOrders = new ProductOrders(2, product.getId(), 1, product.getName());
			//thêm vào danh sách các productOrders
			listOther.add(productOrders);
		}
		//đây là cách lấy thời gian hiện tại trong java.sql.Date
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		//Tạo một đối tượng Orders với các dữ liệu test
		Orders orders = new Orders(2, 50.5f, 0, date, "Ha Tinh Quang Loc here", "012344321", listOther, "nguyentinh@email.com", date, "nothing");
		//đây là mục đích chính của chương trình, tạo đối tượng thao tác với bảng Orders và Orders_detail
		OrdersDAO actionOther = new OrdersDAO();
		//lưu dữ liệu vào bảng với hai đối tượng orders và cart ở trên
		try {
			actionOther.inserOrders(orders, cart);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//kết quả hoàn thành
//		System.out.println(date.toString());
	}

	public static void main (String[] args) {
		ListProductDAO productTable = new ListProductDAO();
		Product product = new Product();
		try {
			product = productTable.getProduct(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(product.getName());
	}
	*/
	public static void main(String[] args) {
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		System.out.println(date);
	}
}
