package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.DBcontext;
import model.Product;
/*
 * 
 * Chứa các phương thức thao tác với bảng product trong cơ sở dữ liệu
 * - Tìm kiếm sản phẩm theo tên, hãng sản xuất
 * - Tìm kiếm sản phẩm theo id
 * - Lấy số lượng sản phẩm tìm được...
 * 
 * 
 * */
public class ListProductDAO {
	
	//return the list of products by product name
	public ArrayList<Product> search(String name, int from, int to) throws SQLException {
		ArrayList<Product> list = new ArrayList<>();
		DBcontext action = new DBcontext();
		Product product;
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("EXECUTE whenFindName @from=?, @to=?, @name=?");
		statement.setInt(1, from);
		statement.setInt(2, to);
		statement.setString(3, name);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			product = new Product();
			product.setId(result.getInt("product_id"));
			product.setName(result.getString("product_name"));
			product.setDescription(result.getString("product_des"));
			product.setPrice(result.getFloat("product_price"));
			product.setSrc(result.getString("product_img_source"));
			product.setType(result.getString("product_type"));
			product.setBrand(result.getString("product_brand"));
			list.add(product);
		}
		connect.close();
		return list;
	}
	
	public int countResultSearch(String name) throws SQLException {
		DBcontext action = new DBcontext();
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT * FROM Products WHERE product_name like '%' + ? + '%'");
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();
		int count = 0;
		while(result.next()) {
			count++;
		}
		return count;
	}
	
	//return the list of product with start and end number
	public ArrayList<Product> queryListProduct(int from, int to) throws SQLException {
		ArrayList<Product> list = new ArrayList<>();
		DBcontext action = new DBcontext();
		Product product;
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("EXECUTE listProduct @from=?, @to=?");
		statement.setInt(1, from);
		statement.setInt(2, to);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			product = new Product();
			product.setId(result.getInt("product_id"));
			product.setName(result.getString("product_name"));
			product.setDescription(result.getString("product_des"));
			product.setPrice(result.getFloat("product_price"));
			product.setSrc(result.getString("product_img_source"));
			product.setType(result.getString("product_type"));
			product.setBrand(result.getString("product_brand"));
			list.add(product);
		}
		connect.close();
		return list;
	}
	
	//get product by id
	public Product getProduct(int id) throws SQLException {
		Product product = null;
		DBcontext action = new DBcontext();
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT TOP(1) * FROM Products WHERE product_id=?");
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			product = new Product();
			product.setId(result.getInt("product_id"));
			product.setName(result.getString("product_name"));
			product.setDescription(result.getString("product_des"));
			product.setPrice(result.getFloat("product_price"));
			product.setSrc(result.getString("product_img_source"));
			product.setType(result.getString("product_type"));
			product.setBrand(result.getString("product_brand"));			
		}
		return product;
	}
	
	public int getTotalNumberProduct() throws SQLException {
		DBcontext action = new DBcontext();
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT COUNT(*) AS count FROM Products");
		ResultSet result = statement.executeQuery();
		int count = 0;
		if(result.next()) {
			count = result.getInt("count");
		}
		return count;
	}
	
	
	public ArrayList<Product> findBrand(String name, int from, int to) throws SQLException {
		ArrayList<Product> list = new ArrayList<>();
		DBcontext action = new DBcontext();
		Product product;
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("EXECUTE whenFindBrand @from=?, @to=?, @name=?");
		statement.setInt(1, from);
		statement.setInt(2, to);
		statement.setString(3, name);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			product = new Product();
			product.setId(result.getInt("product_id"));
			product.setName(result.getString("product_name"));
			product.setDescription(result.getString("product_des"));
			product.setPrice(result.getFloat("product_price"));
			product.setSrc(result.getString("product_img_source"));
			product.setType(result.getString("product_type"));
			product.setBrand(result.getString("product_brand"));
			list.add(product);
		}
		connect.close();
		return list;
	}


	public int countResultSearchBrand(String name) throws SQLException {
		DBcontext action = new DBcontext();
		Connection connect = action.getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT * FROM Products WHERE product_brand = ?");
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();
		int count = 0;
		while(result.next()) {
			count++;
		}
		return count;
	}
}
