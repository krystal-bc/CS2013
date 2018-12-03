package intelliGreen;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import eu.hansolo.colors.MaterialDesign;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import intelliGreen.HardwareModule.AirCond;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.*;
import javafx.event.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;


public class Controller extends Application {

	static ArrayList<Integer> current = new ArrayList<>();
	static ArrayList<Integer> desired = new ArrayList<>();
	static DoubleProperty temp = new SimpleDoubleProperty(90);
	static IntegerProperty humid = new SimpleIntegerProperty(45);
	static IntegerProperty moist = new SimpleIntegerProperty(50);
	static IntegerProperty co2 = new SimpleIntegerProperty(1124);
	static IntegerProperty SunTime = new SimpleIntegerProperty(12);


	static SensorModule simulator = new SensorModule();
	HardwareModule hardware = new HardwareModule();
	Display display = new Display();


	ZonedDateTime time1 = ZonedDateTime.now();
	static int lastSeconds = time.getSecond();

	public static void main(String[] args) throws InterruptedException {
		current.add(0, simulator.generateTemperature());
		current.add(1, simulator.generateHumidity());
		current.add(2, simulator.generateMoisture());
		current.add(3, simulator.generatePH());
		current.add(4, simulator.generateCO2());

		// TODO: Collect these at the beginning with display
		desired.add(0, 70);
		desired.add(1, 50);
		desired.add(2, 65);
		desired.add(3, 7);
		desired.add(4, 1500);
		desired.add(5, 12);
		Controller.launch(args);


	}

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		// TODO: at startup ask for desired values, save them to 'desired' ArrayList
		primaryStage.setTitle("IntelliGarden");
		Scene mainScene = new Scene(display);
		primaryStage.setScene(mainScene);
		primaryStage.setMaximized(true);
		primaryStage.show();

//		temp.setValue( simulator.generateTemperature());
//		humid.setValue( simulator.generateHumidity());
//		moist.setValue( simulator.generateMoisture());
//		current.set(3, simulator.generatePH());
//		co2.set(simulator.generateCO2());

		display.tempG.valueProperty().bind(temp);
		display.humidG.valueProperty().bind(humid);
		display.moistG.valueProperty().bind(moist);
		display.co2G.valueProperty().bind(co2);


		display.setLbl_humidity(humid.get());
		display.lbl_humidity.textProperty().bind(Bindings.concat("Humidity: ", humid.asString(),"%"));
		display.setLbl_moisture(moist.get());
		display.lbl_moisture.textProperty().bind(Bindings.concat("Moisture: ", moist.asString(),"%"));
		display.setLbl_PH(current.get(3));

		display.setLbl_CO2(co2.get());
		display.lbl_CO2.textProperty().bind(Bindings.concat("CO2: ", co2.asString(),"PPM"));
		
		display.setLbl_SunTime(SunTime.get());
		display.lbl_SunTime.textProperty().bind(Bindings.concat("Amount of Light: ", SunTime.asString(), " hours"));


		display.setLbl_desiredTemp(desired.get(0));
		display.setLbl_desiredHumid(desired.get(1));
		display.setLbl_desiredMoisture(desired.get(2));
		display.setLbl_desiredPH(desired.get(3));
		display.setLbl_desiredCO2(desired.get(4));
		display.setLbl_desiredSunTime(desired.get(5));


		hardware.checkAir(temp.getValue().intValue(), desired.get(0));
		hardware.checkHumidity(humid.get(), desired.get(1));
		hardware.checkMoisture(moist.get(), desired.get(2));
		hardware.checkCO2(co2.get(), desired.get(4));
		hardware.checkLightsOn(time1.getHour(), desired.get(5));

//		display.setLbl_AirCond("A/C: " + hardware.getCond());
//		display.setLbl_Humidifier("Humidifier: " + ((hardware.isHumidifierOn() == true) ? "ON" : "OFF"));
		display.setLbl_Irrigation("Irrigation: " + ((hardware.isIrrigationOn() == true) ? "ON" : "OFF"));
//		display.setLbl_CO2release("CO2 release: " + ((hardware.isCO2releaseOn() == true) ? "ON" : "OFF"));
		display.setLbl_Ventilator("Ventilator: " + ((hardware.isVentOn() == true) ? "ON" : "OFF"));

		display.setLbl_Lights("Lights: " + ((hardware.isLightsOn() == true) ? "ON" : "OFF"));

		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent)
							{

								if(hardware.isAirOn() && hardware.getCondToString().equals("HEATING")) {
									temp.set(temp.getValue() + 1.5);
									hardware.checkAir(temp.getValue().intValue(),desired.get(0));
									display.setLbl_AirCond("A/C: " + hardware.getCond());

								}
								if(hardware.isAirOn() && hardware.getCondToString().equals("COOLING")) {
									temp.set(temp.getValue() - 1.5);
									hardware.checkAir(temp.getValue().intValue(),desired.get(0));
									display.setLbl_AirCond("A/C: " + hardware.getCond());

								}
								if(hardware.getCondToString().equals("OFF")) {
									hardware.checkAir(temp.getValue().intValue(),desired.get(0));
									display.setLbl_AirCond("A/C: " + hardware.getCond());

								}
								
								if(hardware.isHumidifierOn() && hardware.getHumidCondtoString().equals("ON")) {
									humid.set(humid.getValue() + 1);
									hardware.checkHumidity(humid.getValue(),desired.get(1));
									display.setLbl_Humidifier("Humidifier: " + ((hardware.isHumidifierOn() == true) ? "ON" : "OFF"));

								}
								if(hardware.getHumidCondtoString().equals("OFF")) {
									humid.set(humid.getValue() - 1);
									hardware.checkHumidity(humid.getValue(), desired.get(1));
									display.setLbl_Humidifier("Humidifier: " + ((hardware.isHumidifierOn() == true) ? "ON" : "OFF"));

								}
								
								if(hardware.isIrrigationOn() && hardware.getMoistCondtoString().equals("ON")) {
									moist.set(moist.getValue() + 1);
									hardware.checkMoisture(moist.getValue(),desired.get(2));
									display.setLbl_Irrigation("Irrigation: " + ((hardware.isIrrigationOn() == true) ? "ON" : "OFF"));

								}
								
								if(hardware.getMoistCondtoString().equals("OFF")) {
									moist.set(moist.getValue() - 1);
									hardware.checkMoisture(moist.getValue(),desired.get(2));
									display.setLbl_Irrigation("Irrigation: " + ((hardware.isIrrigationOn() == true) ? "ON" : "OFF"));

								}
								
								if(hardware.isCO2releaseOn() && hardware.getCO2CondtoString().equals("ON")) {
									co2.set(co2.getValue() + 13);
									hardware.checkCO2(co2.getValue(),desired.get(4));
									display.setLbl_CO2release("CO2 release: " + ((hardware.isCO2releaseOn() == true) ? "ON" : "OFF"));		
								}
								
								if(hardware.getCO2CondtoString().equals("OFF")) {
									co2.set(co2.getValue() - 13);
									hardware.checkCO2(co2.getValue(),desired.get(4));
									display.setLbl_CO2release("CO2 release: " + ((hardware.isCO2releaseOn() == true) ? "ON" : "OFF"));		
								}
								
								
								display.setLbl_dTemp("DESIRED TEMPERATURE: " + desired.get(0));
								display.setLbl_dHumid("DESIRED HUMIDITY: " + desired.get(1));
								display.setLbl_dMoist("DESIRED MOISTURE: " + desired.get(2));
								display.setLbl_dPh("DESIRED PH: " + desired.get(3));
								display.setLbl_dCO2("DESIRED CO2: " + desired.get(4));
								
								ZonedDateTime time = ZonedDateTime.now();
								SunTime.set(desired.get(5));
								hardware.checkLightsOn(time.getHour(), desired.get(5));
								display.setLbl_RealTime("Time: " + formatTime(time.getHour(), time.getMinute()));
								display.lbl_DesiredSunTime.textProperty().bind(Bindings.concat("Amount of Light: ", desired.get(5), " hours"));
								display.setLbl_Lights("Lights: " + ((hardware.isLightsOn() == true) ? "ON" : "OFF"));

							}
						}
				),
				new KeyFrame(Duration.seconds(2))//Do something every second. In this case we are going to increment setStrP.
		);
		timeline.setCycleCount(1000);//Repeat this 10 times
		timeline.play();

	}
	public void updateDesired() {
		desired = InputDisplay.desired;
	}

	private static String formatTime(int hour, int minute) {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(hour % 12));
		sb.append(":");
		if (minute < 10) {
			sb.append(Integer.toString(0));
		}
		sb.append(Integer.toString(minute));
		
		if (hour < 12) {
			sb.append(" AM");
		} else {
			sb.append(" PM");
		}
		return sb.toString();
	}

}
