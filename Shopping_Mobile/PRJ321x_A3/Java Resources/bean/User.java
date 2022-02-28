package bean;

/*
 * bean User lưu dữ liệu và kiểm tra hợp lệ cho tài khoản trong trang login
 *
 * */
public class User {
	private String email;
	private String password;
	private String message;
	private final String regexEmail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
	private final String regexPass = "[a-zA-Z0-9_!@#$%^&*]+";
	
	public User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isValid() {
		if(email.equals("") || password.equals("")) {
			message = "email hoặc mật khẩu không được để trống!";
			return false;
		}
		if(!email.matches(regexEmail) || !password.matches(regexPass)) {
			message = "Email hoặc mật khẩu sai định dạng.";
			return false;
		}
		return true;
	}
	
	public String getMessage() {
		return message;
	}
}
