package intelliGreen;

import java.util.Random;

public class HardwareSimulator {

	/*
	 * This class simulates hardware readings by generating a random value for
	 * normal ranges in real life.
	 * 
	 * temperature: 20 to 100 degrees Fahrenheit
	 * humidity: 0% to 100% 
	 * moisture: 41% to 80%
	 * pH: 4 to 8
	 * 
	 */

	public int temperature;
	public int humidity;
	public int moisture;
	public int pH;

	private Random random = new Random();

	public HardwareSimulator() {
		
	}

	public int getTemperature() {
		return temperature;
	}

	/*
	 * temperatures are assumed to range between 20 and 100 degrees Fahrenheit. Random
	 * generates a number between 0 and the given upper bound, and since we don't
	 * want values to start at 0, we add 20. Then our upper bound is 100 - 20 = 80.
	 */
	public int generateTemperature() {
		temperature = random.nextInt(80) + 20;
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public int generateHumidity() {
		humidity = random.nextInt(100);
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getMoisture() {
		return moisture;
	}
	
	public int generateMoisture() {
		//TODO: implement random moisture generator
		return moisture;
	}

	public void setMoisture(int moisture) {
		this.moisture = moisture;
	}

	public int getPH() {
		return pH;
	}

	/*
	 * pH levels in soil are assumed to range between 4 and 8. Random generates a
	 * number between 0 and the given upper bound, and since we don't want values to
	 * start at 0, we add 4. Then our upper bound is 8 - 4 = 4.
	 */
	public int generatePH() {
		pH = random.nextInt(4) + 4;
		return pH;
	}

	public void setPH(int pH) {
		this.pH = pH;
	}

}
