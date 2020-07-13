package mvc.bean;

public class User {
	private Integer user_id;//会员号
	private String user_name;//用户名
	private String password;//用户密码
	private String name;//用户真实姓名
	private String idcard;//用户身份证号码
	private String phone;//用户电话号码
	private String city;//用户所在城市
	private Integer permission;//用户权限
	
	public User() {
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", name=" + name
				+ ", idcard=" + idcard + ", phone=" + phone + ", city=" + city + ", permission=" + permission + "]";
	}

	public User(Integer user_id, String user_name, String password, String name, String idcard, String phone,
			String city, Integer permission) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.name = name;
		this.idcard = idcard;
		this.phone = phone;
		this.city = city;
		this.permission = permission;
	}

	public User(Integer user_id, String user_name, String password, String name, String phone, String city) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.city = city;
	}

}
