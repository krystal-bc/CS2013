package intelliGreen;

public class Controller {

	public static void main(String[] args) {
		SensorModule simulator = new SensorModule();
		
		System.out.println("The current temperature is "+ simulator.generateTemperature() + " degrees Fahrenheit");
		System.out.println("The current humidity is "+simulator.generateHumidity() + "%");
		System.out.println("The current soil moisture is "+simulator.generateMoisture()  + "%");
		System.out.println("The current soil pH is "+simulator.generatePH());
		System.out.println("The current CO2 level is "+simulator.generateCO2() + "ppm");

	}

}
