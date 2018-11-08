package intelliGreen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class InputDisplay extends FlowPane{
	
	Label lbl_instructions = new Label("Input desired values for your IntelliGreen environment");
	Label lbl_temp = new Label("Temperature (deg F): ");
	Label lbl_humid = new Label("Humidity (%): ");
	Label lbl_moist = new Label("Soil Moisture (%): ");
	Label lbl_PH = new Label("Soil PH: ");
	Label lbl_CO2 = new Label("CO2 (ppm): ");
	VBox vbox_labels = new VBox();
	
	public InputDisplay() {
		setPadding(new Insets(10, 20, 20, 20));
		vbox_labels.getChildren().addAll(lbl_instructions, lbl_temp, lbl_humid, lbl_moist, lbl_PH, lbl_CO2);
		getChildren().add(vbox_labels);
	}


}
