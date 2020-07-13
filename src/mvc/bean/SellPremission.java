package mvc.bean;

public class SellPremission {
	private Integer s_id;
	private Integer car_id;
	private Integer user_id;
	private Integer premission;
	private Integer releasing;
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getPremission() {
		return premission;
	}
	public void setPremission(Integer premission) {
		this.premission = premission;
	}
	public Integer getReleasing() {
		return releasing;
	}
	public void setReleasing(Integer releasing) {
		this.releasing = releasing;
	}
	public SellPremission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SellPremission(Integer s_id, Integer car_id, Integer user_id, Integer premission, Integer releasing) {
		super();
		this.s_id = s_id;
		this.car_id = car_id;
		this.user_id = user_id;
		this.premission = premission;
		this.releasing = releasing;
	}
	@Override
	public String toString() {
		return "SellPremission [s_id=" + s_id + ", car_id=" + car_id + ", user_id=" + user_id + ", premission="
				+ premission + ", releasing=" + releasing + "]";
	}
	
	
}
