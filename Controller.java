package intelliGreen;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {

	static ArrayList<Integer> current = new ArrayList<>();
	static ArrayList<Integer> desired = new ArrayList<>();

	static SensorModule simulator = new SensorModule();
	HardwareModule hardware = new HardwareModule();
	Display display = new Display();

	static ZonedDateTime time = ZonedDateTime.now();
	static int lastSeconds = time.getSecond();

	public static void main(String[] args) {
		current.add(0, simulator.generateTemperature());
		current.add(1, simulator.generateHumidity());
		current.add(2, simulator.generateMoisture());
		current.add(3, simulator.generatePH());
		current.add(4, simulator.generateCO2());

		// TODO: Collect these at the beginning with display
		desired.add(0, 70);
		desired.add(1, 50);
		desired.add(2, 80);
		desired.add(3, 7);
		desired.add(4, 1500);
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws InterruptedException {
		// TODO: at startup ask for desired values, save them to 'desired' ArrayList

		primaryStage.setTitle("IntelliGarden");
		Scene mainScene = new Scene(display, 1000, 500);
		primaryStage.setScene(mainScene);
		primaryStage.show();

		current.set(0, simulator.generateTemperature());
		current.set(1, simulator.generateHumidity());
		current.set(2, simulator.generateMoisture());
		current.set(3, simulator.generatePH());
		current.set(4, simulator.generateCO2());
		hardware.setCurrent(current);
		hardware.setDesired(desired);

		display.setLbl_temperature(current.get(0));
		display.setLbl_humidity(current.get(1));
		display.setLbl_moisture(current.get(2));
		display.setLbl_PH(current.get(3));
		display.setLbl_CO2(current.get(4));
		
		display.setLbl_desiredTemp(desired.get(0));
		display.setLbl_desiredHumid(desired.get(1));
		display.setLbl_desiredMoisture(desired.get(2));
		display.setLbl_desiredPH(desired.get(3));
		display.setLbl_desiredCO2(desired.get(4));

		hardware.checkAir(current.get(0), desired.get(0));
		hardware.checkHumidity(current.get(1), desired.get(1));
		hardware.checkMoisture(current.get(2), desired.get(2));
		hardware.checkCO2(current.get(4), desired.get(4));

		display.setLbl_AirCond("A/C: " + hardware.getCond());
		display.setLbl_Humidifier("Humidifier: " + ((hardware.isHumidifierOn() == true) ? "ON" : "OFF"));
		display.setLbl_Irrigation("Irrigation: " + ((hardware.isIrrigationOn() == true) ? "ON" : "OFF"));
		display.setLbl_CO2release("CO2 release: " + ((hardware.isCO2releaseOn() == true) ? "ON" : "OFF"));
		display.setLbl_Ventilator("Ventilator: " + ((hardware.isVentOn() == true) ? "ON" : "OFF"));

		//repeat all this stuff
//		current = hardware.getCurrent();
//		display.setLbl_temperature(current.get(0));
//		display.setLbl_humidity(current.get(1));
//		display.setLbl_moisture(current.get(2));
//		display.setLbl_PH(current.get(3));
//		display.setLbl_CO2(current.get(4));
		
//		display.setLbl_AirCond("A/C: " + hardware.getCond());
//		display.setLbl_Humidifier("Humidifier: " + ((hardware.isHumidifierOn() == true) ? "ON" : "OFF"));
//		display.setLbl_Irrigation("Irrigation: " + ((hardware.isIrrigationOn() == true) ? "ON" : "OFF"));
//		display.setLbl_CO2release("CO2 release: " + ((hardware.isCO2releaseOn() == true) ? "ON" : "OFF"));
//		display.setLbl_Ventilator("Ventilator: " + ((hardware.isVentOn() == true) ? "ON" : "OFF"));
		
		// TODO: find a way to track elapsed time

		// if (time.getSecond() - lastSeconds > 10) {
		//
		// display.setLbl_time("Current Time: " + formatTime());
		//
		// lastSeconds = time.getSecond();
		// }

		// TODO: add button for changing desired values and presets
	}
	
	private static void update() {
		
	}

	private static String formatTime() {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(time.getHour() % 12));
		sb.append(":");
		sb.append(Integer.toString(time.getMinute()));
		if (time.getHour() < 12) {
			sb.append(" AM");
		} else {
			sb.append(" PM");
		}
		return sb.toString();
	}

}
