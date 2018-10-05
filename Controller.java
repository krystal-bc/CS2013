package intelliGreen;

import java.util.ArrayList;

public class Controller {

	public static void main(String[] args) {

		ArrayList<Integer> current = new ArrayList<>();
		ArrayList<Integer> desired = new ArrayList<>();

		SensorModule simulator = new SensorModule();
		HardwareModule hardware = new HardwareModule();

		// TODO: at startup ask for desired values, save them to 'desired' ArrayList

		// TODO: determine at what time interval these will be changed
		current.set(0, simulator.generateTemperature());
		current.set(1, simulator.generateHumidity());
		current.set(2, simulator.generateMoisture());
		current.set(3, simulator.generatePH());
		current.set(4, simulator.generateCO2());

		// These prints will eventually be migrated to the Display
		System.out.println("The current temperature is " + current.get(0) + " degrees Fahrenheit");
		System.out.println("The current humidity is " + current.get(1) + "%");
		System.out.println("The current soil moisture is " + current.get(2) + "%");
		System.out.println("The current soil pH is " + current.get(3));
		System.out.println("The current CO2 level is " + current.get(4) + "ppm");
		
		//hardware.checkAir(current.get(0), desired.get(0));
		//display: update current temperature and A/C status

	}

}
