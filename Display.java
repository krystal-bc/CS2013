package intelliGreen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Display extends FlowPane {

	/*
	 * This class creates the user Display using JavaFX
	 */
	Label lbl_temperature = new Label();
	Label lbl_humidity = new Label();
	Label lbl_moisture = new Label();
	Label lbl_PH = new Label();
	Label lbl_CO2 = new Label();
	VBox vbox_sensorValues = new VBox();
	
	Label lbl_AirCond = new Label();
	Label lbl_time = new Label();
//	Label lbl_moisture = new Label();
//	Label lbl_PH = new Label();
//	Label lbl_CO2 = new Label();
	VBox vbox_hardwareStatus = new VBox();

	public Display() {
		setPadding(new Insets(10, 20, 20, 20));
		vbox_sensorValues.getChildren().addAll(lbl_temperature, lbl_humidity, lbl_moisture, lbl_PH, lbl_CO2);
		getChildren().add(vbox_sensorValues);
		setHgap(100);
		vbox_hardwareStatus.getChildren().addAll(lbl_AirCond, lbl_time);
		getChildren().add(vbox_hardwareStatus);
		
	}

	public Label getLbl_temperature() {
		return lbl_temperature;
	}

	public void setLbl_temperature(int temp) {
		lbl_temperature.setText("Temperature: " + temp + " deg F");
	}

	public Label getLbl_time() {
		return lbl_time;
	}

	public void setLbl_time(String s) {
		lbl_time.setText(s);
	}

	public Label getLbl_humidity() {
		return lbl_humidity;
	}

	public void setLbl_humidity(int humidity) {
		lbl_humidity.setText("Humidity: " + humidity + "%");
	}

	public Label getLbl_moisture() {
		return lbl_moisture;
	}

	public void setLbl_moisture(int moisture) {
		lbl_moisture.setText("Soil Moisture: " + moisture + "%");
	}

	public Label getLbl_PH() {
		return lbl_PH;
	}

	public void setLbl_PH(int ph) {
		lbl_PH.setText("Soil PH: " + ph);
	}

	public Label getLbl_CO2() {
		return lbl_CO2;
	}

	public void setLbl_CO2(int CO2) {
		lbl_CO2.setText("CO2: " + CO2 + "ppm");
	}

	public void setLbl_AirCond(String string) {
		lbl_AirCond.setText(string);
		
	}

}
