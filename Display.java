
package intelliGreen;


import eu.hansolo.colors.MaterialDesign;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display extends FlowPane {

	/*
	 * This class creates the user Display using JavaFX
	 */

	Label lbl_currentHeading = new Label("Sensor Values");
	Label lbl_temperature = new Label();
	Label lbl_humidity = new Label();
	Label lbl_moisture = new Label();
	Label lbl_PH = new Label();
	Label lbl_CO2 = new Label();
	VBox vbox_sensorValues = new VBox();

	static Label lbl_desiredHeading = new Label("Desired Values");
	static Label lbl_desiredTemp = new Label();
	static Label lbl_desiredHumid = new Label();
	static Label lbl_desiredMoist = new Label();
	static Label lbl_desiredPH = new Label();
	static Label lbl_desiredCO2 = new Label();
	VBox vbox_desiredValues = new VBox();
	
	//TODO: Implement status of lights
	Label lbl_hardwareHeading = new Label("Hardware Statuses");
	Label lbl_AirCond = new Label();
	Label lbl_Humidifier = new Label();
	Label lbl_Irrigation = new Label();
	Label lbl_Ventilator = new Label();
	Label lbl_CO2release = new Label();
	
	Label lbl_hardwareHeading1 = new Label("Hardware Statuses");
	Label lbl_AirCond1 = new Label();
	Label lbl_Humidifier1 = new Label();
	Label lbl_Irrigation1 = new Label();
	Label lbl_Ventilator1 = new Label();
	Label lbl_CO2release1 = new Label();

	Label lbl_dTemp = new Label();
	Label lbl_dPh = new Label();
	Label lbl_dCO2 = new Label();
	Label lbl_dHumid = new Label();
	Label lbl_dMoist = new Label();
	
	Label lbl_lightsystemHeading = new Label("Lighting Status");
	Label lbl_RealTime = new Label();
	Label lbl_SunTime = new Label();
	Label lbl_DesiredSunTime = new Label();
	Label lbl_Lights = new Label();
	VBox vbox_lightsystem = new VBox();
	
	VBox vbox_hardwareStatus = new VBox();
	public Gauge tempG;
	public Gauge humidG;
	public Gauge moistG;
	public Gauge phG;
	public Gauge co2G;
	
	public VBox vbox_tempValues;
	public VBox vbox_humidValues;
	public VBox vbox_moistValues;
	public VBox vbox_phValues;
	public VBox vbox_co2Values;
	

	Button button = new Button("Customize Values");

	public Display() {
		GaugeBuilder builder = GaugeBuilder.create()
					.skinType(Gauge.SkinType.SIMPLE_SECTION)
					.barBackgroundColor(MaterialDesign.GREY_800.get())
					.animated(true)
					.animationDuration(1000);
		tempG = builder.decimals(0).maxValue(130).unit("Temp: F").build();
		tempG.setThreshold(85);
		tempG.setThresholdColor(Color.RED);
		tempG.setThresholdVisible(true);

		humidG = builder.decimals(0).maxValue(100).unit("Humidity: %").build();
		moistG = builder.decimals(0).maxValue(100).unit("Moisture: %").build();
		phG = builder.decimals(0).maxValue(14).unit("PH").build();
		phG.setValue(7);
		co2G = builder.decimals(0).maxValue(2000).unit("CO2: PPM").build();
		co2G.setThreshold(1200);
		co2G.setThresholdColor(MaterialDesign.GREEN_300.get());
		co2G.setThresholdVisible(true);
	
		lbl_dTemp.setText("DESIRED TEMPERATURE: " + Controller.desired.get(0));
		lbl_dHumid.setText("DESIRED HUMIDITY: " + Controller.desired.get(1));
		lbl_dMoist.setText("DESIRED MOISTURE: " + Controller.desired.get(2));
		lbl_dPh.setText("DESIRED PH: " + Controller.desired.get(3));
		lbl_dCO2.setText("DESIRED CO2: " + Controller.desired.get(4));
		
		vbox_tempValues = getVBox(lbl_dTemp, MaterialDesign.GREEN_300.get(), tempG);
		vbox_humidValues = getVBox(lbl_dHumid, MaterialDesign.GREEN_300.get(), humidG);
		vbox_moistValues = getVBox(lbl_dMoist, MaterialDesign.GREEN_300.get(), moistG);
		vbox_phValues = getVBox(lbl_dPh, MaterialDesign.GREEN_300.get(), phG);
		vbox_co2Values = getVBox(lbl_dCO2, MaterialDesign.GREEN_300.get(), co2G);



		setPadding(new Insets(10, 20, 20, 20));
//		vbox_sensorValues.getChildren().addAll(lbl_currentHeading, lbl_temperature, lbl_humidity, lbl_moisture, lbl_PH, lbl_CO2);
		getChildren().add(vbox_tempValues);
		getChildren().add(vbox_humidValues);
		getChildren().add(vbox_moistValues);
		getChildren().add(vbox_phValues);
		getChildren().add(vbox_co2Values);

		setHgap(100);
//		vbox_desiredValues.getChildren().addAll(lbl_desiredHeading, lbl_desiredTemp, lbl_desiredHumid, lbl_desiredMoist, lbl_desiredPH,
//				lbl_desiredCO2);
//		getChildren().add(vbox_desiredValues);
		vbox_hardwareStatus.getChildren().addAll(lbl_hardwareHeading, lbl_AirCond, lbl_Humidifier, lbl_Irrigation, lbl_Ventilator,
				lbl_CO2release, button);
		vbox_lightsystem.getChildren().addAll(lbl_lightsystemHeading, lbl_Lights, lbl_SunTime, lbl_RealTime);
		getChildren().add(vbox_lightsystem);
		button.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Stage inputStage = new Stage();
			InputDisplay inputDisplay = new InputDisplay();
			inputStage.setTitle("IntelliGarden");
			Scene inputScene = new Scene(inputDisplay, 1000, 500);
			inputStage.setScene(inputScene);
			inputStage.show();
		}
	});
	
		getChildren().add(vbox_hardwareStatus);
		
	}
	public Label getLbl_dTemp() {
		return lbl_dTemp;
	}
	public void setLbl_dTemp(String x) {
		lbl_dTemp.setText(x);
	}
	public Label getLbl_dPh() {
		return lbl_dPh;
	}
	public void setLbl_dPh(String x) {
		lbl_dPh.setText(x);
	}
	public Label getLbl_dCO2() {
		return lbl_dCO2;
	}
	public void setLbl_dCO2(String x) {
		lbl_dCO2.setText(x);
	}
	public Label getLbl_dHumid() {
		return lbl_dHumid;
	}
	public void setLbl_dHumid(String x) {
		lbl_dHumid.setText(x);
	}
	public Label getLbl_dMoist() {
		return lbl_dMoist;
	}
	public void setLbl_dMoist(String x) {
		lbl_dMoist.setText(x);
	}
	private VBox getVBox(final Label TEXT, final Color COLOR, final Gauge GAUGE) {
		Rectangle bar = new Rectangle(200, 3);
		bar.setArcWidth(6);
		bar.setArcHeight(6);
		bar.setFill(COLOR);

		Label label = TEXT;
		label.setTextFill(COLOR);
		label.setAlignment(Pos.CENTER);
		label.setPadding(new Insets(0, 0, 10, 0));

		GAUGE.setBarColor(COLOR);

		VBox vBox = new VBox(bar, label, GAUGE);
		vBox.setSpacing(3);
		vBox.setAlignment(Pos.CENTER);
		return vBox;
	}
	// Sensor Labels
	public Label getLbl_temperature() {
		return lbl_temperature;
	}

	public void setLbl_temperature(double temp) {
		lbl_temperature.setText("Temperature: " + temp + " deg F");
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

	// Desired Labels
	public Label getLbl_desiredTemp() {
		return lbl_desiredTemp;
	}

	public static void setLbl_desiredTemp(int desiredTemp) {
		lbl_desiredTemp.setText(desiredTemp + " deg F");
	}

	public Label getLbl_desiredHumid() {
		return lbl_desiredHumid;
	}

	public static void setLbl_desiredHumid(int desiredHumid) {
		lbl_desiredHumid.setText(desiredHumid + "%");
	}

	public Label getLbl_desiredMoist() {
		return lbl_desiredMoist;
	}

	public static void setLbl_desiredMoisture(int desiredMoist) {
		lbl_desiredMoist.setText(desiredMoist + "%");
	}

	public Label getLbl_desiredPH() {
		return lbl_desiredPH;
	}

	public static void setLbl_desiredPH(int desiredph) {
		lbl_desiredPH.setText(Integer.toString(desiredph));
	}

	public Label getLbl_desiredCO2() {
		return lbl_desiredCO2;
	}

	public static void setLbl_desiredCO2(int desiredCO2) {
		lbl_desiredCO2.setText(desiredCO2 + "ppm");
	}

	// Hardware Labels
	public void setLbl_AirCond(String string) {
		lbl_AirCond.setText(string);
	}

	public void setLbl_Humidifier(String s) {
		lbl_Humidifier.setText(s);
	}

	public void setLbl_Irrigation(String s) {
		lbl_Irrigation.setText(s);
	}

	public void setLbl_Ventilator(String s) {
		lbl_Ventilator.setText(s);
	}

	public void setLbl_CO2release(String s) {
		lbl_CO2release.setText(s);
	}
	
	// Light System Labels
	public void setLbl_RealTime(String s) {
		lbl_RealTime.setText(s);
	}
	
	public Label getLbl_SunTime() {
		return lbl_SunTime;
	}

	public void setLbl_SunTime(int SunTime) {
		lbl_SunTime.setText("Amount of Light: " + SunTime + "hours");
	}

	
	public Label getLbl_desiredSunTime() {
		return lbl_DesiredSunTime;
	}
	
	public static void setLbl_desiredSunTime(int desiredSunTime) {
		lbl_desiredCO2.setText(desiredSunTime + "hours");
	}

	public void setLbl_Lights(String s) {
		lbl_Lights.setText(s);
	}



}
