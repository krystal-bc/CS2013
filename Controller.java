package intelliGreen;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {

	static ArrayList<Integer> current = new ArrayList<>();
	ArrayList<Integer> desired = new ArrayList<>();

	static SensorModule simulator = new SensorModule();
	HardwareModule hardware = new HardwareModule();

	Display display = new Display();

	public static void main(String[] args) {
		current.add(0, simulator.generateTemperature());
		current.add(1, simulator.generateHumidity());
		current.add(2, simulator.generateMoisture());
		current.add(3, simulator.generatePH());
		current.add(4, simulator.generateCO2());
		Application.launch(args);
	}

	public void start(Stage primaryStage) {
		// TODO: at startup ask for desired values, save them to 'desired' ArrayList
		// TODO: determine at what time interval these will be changed
		

		display.setLbl_temperature(current.get(0));
		display.setLbl_humidity(current.get(1));
		display.setLbl_moisture(current.get(2));
		display.setLbl_PH(current.get(3));
		display.setLbl_CO2(current.get(4));
		
		primaryStage.setTitle("IntelliGarden");
		Scene mainScene = new Scene(display, 500, 500);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		//hardware.checkAir(current.get(0), desired.get(0));
		//display: update current temperature and A/C status
	}

}
