package mvc.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Car {

	@Min(5)
	private int power;
	
	@NotEmpty
	private String brand;
	
	
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
