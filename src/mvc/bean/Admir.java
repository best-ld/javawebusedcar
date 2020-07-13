package mvc.bean;

public class Admir {
	private Integer admir_id;
	private String admir_name;
	private String password;
	
	public Admir() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admir(Integer admir_id, String admir_name, String password) {
		super();
		this.admir_id = admir_id;
		this.admir_name = admir_name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admir [admir_id=" + admir_id + ", admir_name=" + admir_name + ", password=" + password + "]";
	}
	public Integer getAdmir_id() {
		return admir_id;
	}
	public void setAdmir_id(Integer admir_id) {
		this.admir_id = admir_id;
	}
	public String getAdmir_name() {
		return admir_name;
	}
	public void setAdmir_name(String admir_name) {
		this.admir_name = admir_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
