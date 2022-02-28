package model;

import java.sql.Date;
import java.util.ArrayList;

public class Orders {
	private int orderID;
	private float price; //total amount of order
	private int status;
	private Date orderDate;
	private String address;
	private String phoneNumber;
	private ArrayList<ProductOrders> listProduct;
	private String userMail;
	private Date receiveDate;
	private String discount;
	
	
	public Orders() {
		super();
	}

	public Orders(int orderID, float price, int status, Date orderDate, String address, String phoneNumber,
			ArrayList<ProductOrders> listProduct, String userMail, Date receiveDate, String discount) {
		super();
		this.orderID = orderID;
		this.price = price;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.listProduct = listProduct;
		this.userMail = userMail;
		this.receiveDate = receiveDate;
		this.discount = discount;
	}

	public Orders(String userMail, int status, String discount, String address, String phoneNumber, Date orderDate) {
		super();
		this.userMail = userMail;
		this.status = status;
		this.discount = discount;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderDate = orderDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<ProductOrders> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<ProductOrders> listProduct) {
		this.listProduct = listProduct;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return
		orderID + "===" +
		price + "===" +
		status + "===" +
		orderDate + "===" +
		address + "===" +
		phoneNumber + "===" +
		userMail + "===" +
		discount + "-= here =-" +
		receiveDate;
	}
}
