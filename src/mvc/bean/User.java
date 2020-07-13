package mvc.bean;

public class User {
	private Integer user_id;//��Ա��
	private String user_name;//�û���
	private String password;//�û�����
	private String name;//�û���ʵ����
	private String idcard;//�û����֤����
	private String phone;//�û��绰����
	private String city;//�û����ڳ���
	private Integer permission;//�û�Ȩ��
	
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
