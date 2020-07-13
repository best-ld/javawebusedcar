package mvc.bean;

public class SPAndUserAndCar {
	private Car car;
	private User user;
	private SellPremission sellPremission;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SellPremission getSellPremission() {
		return sellPremission;
	}
	public void setSellPremission(SellPremission sellPremission) {
		this.sellPremission = sellPremission;
	}
	public SPAndUserAndCar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SPAndUserAndCar(Car car, User user, SellPremission sellPremission) {
		super();
		this.car = car;
		this.user = user;
		this.sellPremission = sellPremission;
	}
	@Override
	public String toString() {
		return "SPAndUserAndCar [car=" + car + ", user=" + user + ", sellPremission=" + sellPremission + "]";
	}
	
	
}
