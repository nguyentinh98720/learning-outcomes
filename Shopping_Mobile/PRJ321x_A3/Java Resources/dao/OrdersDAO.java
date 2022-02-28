package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.DBcontext;
import model.Cart;
import model.Orders;
import model.Product;
/*
 * 
 * 
 * Chứa các phương thức làm việc vói bảng order và order detail trong cơ sở dữ liệu
 * - Insert dữ liệu vào 2 bảng vơi các thông tin đơn hàng vào khách mua hàng
 * -
 * 
 * 
 * 
 * */
public class OrdersDAO {
	
	//insert information of Order to data source
	//that including list of products in cart and information of buyer in Orders
	public void inserOrders(Orders order, Cart cart) throws SQLException {
		DBcontext action = new DBcontext();
		Connection connect = action.getConnection();
		//insert information to Orders table
		PreparedStatement statement = connect.prepareStatement("INSERT INTO Orders([user_mail],[order_status],[order_date],[order_discount_code],[order_address]) VALUES (?,?,?,?,?)");
		statement.setString(1, order.getUserMail());
		statement.setInt(2, order.getStatus());
		statement.setDate(3, order.getOrderDate());
		statement.setString(4, order.getDiscount());
		statement.setString(5, order.getAddress());
		statement.executeUpdate();
		statement.close();
		//select order_id from Orders table
		PreparedStatement statementSelect = connect.prepareStatement("SELECT TOP(1) order_id FROM Orders ORDER BY order_id desc;");
		ResultSet result = statementSelect.executeQuery();
		int orderId = 0;
		if(result.next()) {
			orderId = result.getInt("order_id");
		}
		
		//insert information to Orders_detail table
		PreparedStatement statementOther = connect.prepareStatement("INSERT INTO Orders_detail(order_id, product_id, amount_product, price_product) VALUES (?, ?, ?, ?)");
		//the loop for select each product from cart
		ArrayList<Product> listProduct = cart.getItems();
		for(Product product : listProduct) {
			statementOther.setInt(1, orderId);
			statementOther.setInt(2, product.getId());
			statementOther.setInt(3, product.getNumber());
			statementOther.setFloat(4, product.getPrice());
			statementOther.executeUpdate();
		}
		statementOther.close();
		connect.close();
	}
}
