package mvc.bean;

public class Car {

	private Integer car_id;
	private Integer user_id;
	private String license_plate;
	private String brand;//Æ·ÅÆ
	private String model;//ÐÍºÅ
	private Integer years;
	private Integer evaluation;
	private String photo;
	
	public Car() {
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

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Car(Integer car_id, Integer user_id, String license_plate, String brand, String model, Integer years,
			Integer evaluation, String photo) {
		super();
		this.car_id = car_id;
		this.user_id = user_id;
		this.license_plate = license_plate;
		this.brand = brand;
		this.model = model;
		this.years = years;
		this.evaluation = evaluation;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Car [car_id=" + car_id + ", user_id=" + user_id + ", license_plate=" + license_plate + ", brand="
				+ brand + ", model=" + model + ", years=" + years + ", evaluation=" + evaluation + ", photo=" + photo
				+ "]";
	}
	

}
