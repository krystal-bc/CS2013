package intelliGreen;

public class Controller {

	public static void main(String[] args) {
		HardwareSimulator simulator = new HardwareSimulator();
		
		System.out.println("The current temperature is "+simulator.generateTemperature() + " degrees Fahrenheit");
		System.out.println("The current humidity is "+simulator.generateHumidity() + "%");
		//System.out.println("The current moisture is "+simulator.generateMoisture());
		System.out.println("The current pH is "+simulator.generatePH());

	}

}
