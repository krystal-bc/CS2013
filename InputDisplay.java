package intelliGreen;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputDisplay extends GridPane {

	static ArrayList<Integer> desired = new ArrayList<Integer>();

	TextField temperature = new TextField();
	TextField Humidity = new TextField();
	TextField Moisture = new TextField();
	TextField pH = new TextField();
	TextField cO2 = new TextField();
	TextField SunTime = new TextField();

	Label tropical = new Label();
	Label dry = new Label();
	Label cold = new Label();
	Label lbl_coldHeading = new Label("Cold environment");
	Label lbl_tropicalHeading = new Label("Tropical environment");
	Label lbl_dryHeading = new Label("Dry environment");

	Label lbl_instructions = new Label("Desired environment values");
	Label lbl_temp = new Label("Temperature (deg F): ");
	Label lbl_humid = new Label("Humidity (%): ");
	Label lbl_moist = new Label("Soil Moisture (%): ");
	Label lbl_pH = new Label("Soil PH: ");
	Label lbl_cO2 = new Label("CO2 (ppm): ");
	Label lbl_SunTime = new Label("Light (hours): ");
	Label lbl_empty = new Label("");

	VBox vbox_labels = new VBox();
	VBox vbox_desired = new VBox();
	VBox vbox_tropicalPresets = new VBox();
	VBox vbox_dryPresets = new VBox();
	VBox vbox_coldPresets = new VBox();

	public InputDisplay() {
		setPadding(new Insets(10, 20, 20, 20));

		lbl_instructions.setPadding(new Insets(5, 10, 10, 10));
		lbl_temp.setPadding(new Insets(5, 10, 10, 10));
		lbl_humid.setPadding(new Insets(5, 10, 10, 10));
		lbl_moist.setPadding(new Insets(5, 10, 10, 10));
		lbl_pH.setPadding(new Insets(5, 10, 10, 10));
		lbl_cO2.setPadding(new Insets(5, 10, 10, 10));
		lbl_SunTime.setPadding(new Insets(5, 10, 2.5, 10));
		lbl_empty.setPadding(new Insets(5, 10, 10, 10));

		Button btn_tropicalPreset = new Button("Tropical Preset");
		Button btn_coldPreset = new Button("Cold Preset");
		Button btn_dryPreset = new Button("Dry Preset");

		ArrayList<Integer> tPreset = new ArrayList<Integer>();
		ArrayList<Integer> cPreset = new ArrayList<Integer>();
		ArrayList<Integer> dPreset = new ArrayList<Integer>();

		Button rtnButton = new Button("Enter");

		tPreset.add(88);
		tPreset.add(80);
		tPreset.add(60);
		tPreset.add(7);
		tPreset.add(1500);
		tPreset.add(14);
		
		cPreset.add(55);
		cPreset.add(10);
		cPreset.add(25);
		cPreset.add(7);
		cPreset.add(1400);
		cPreset.add(12);
		
		dPreset.add(85);
		dPreset.add(40);
		dPreset.add(25);
		dPreset.add(7);
		dPreset.add(1400);
		dPreset.add(16);

		cold.setText("Temperature = 55 \n" + "Humidity = 10 \n" + "Moisture = 25 \n" + "pH = 7 \n"
				+ "Carbon Dioxide = 1400 \n" + "Amount of Light = 12\n");

		dry.setText("Temperature = 85 \n" + "Humidity = 40 \n" + "Moisture = 25 \n" + "pH = 7 \n"
				+ "Carbon Dioxide = 1400 \n" + "Amount of Light = 16\n");

		tropical.setText("Temperature = 88 \n" + "Humidity = 80 \n" + "Moisture = 60 \n" + "pH = 7 \n"
				+ "Carbon Dioxide = 1500 \n" + "Amount of Light = 14\n");

		vbox_labels.getChildren().addAll(lbl_instructions, lbl_temp, lbl_humid, lbl_moist, lbl_pH, lbl_cO2, lbl_SunTime);
		vbox_desired.getChildren().addAll(lbl_empty, temperature, Humidity, Moisture, pH, cO2, SunTime, rtnButton);
		vbox_tropicalPresets.getChildren().addAll(lbl_tropicalHeading, tropical, btn_tropicalPreset);
		vbox_dryPresets.getChildren().addAll(lbl_dryHeading, dry, btn_dryPreset);
		vbox_coldPresets.getChildren().addAll(lbl_coldHeading, cold, btn_coldPreset);
		
		vbox_tropicalPresets.setPadding(new Insets(5, 10, 2.5, 20));
		vbox_dryPresets.setPadding(new Insets(5, 10, 2.5, 20));
		vbox_coldPresets.setPadding(new Insets(5, 10, 2.5, 20));
		
		
		add(vbox_labels, 0, 0);
		add(vbox_desired, 1, 0);
		add(vbox_tropicalPresets, 2, 0);
		add(vbox_dryPresets, 3, 0);
		add(vbox_coldPresets, 4, 0);

		rtnButton.setOnAction(e -> {

			if (temperature.getText().length() > 0 && temperature.getText() != null)
				desired.add(Integer.parseInt(temperature.getText()));

			if (Humidity.getText().length() > 0 && Humidity.getText() != null)
				desired.add(Integer.parseInt(Humidity.getText()));

			if (Moisture.getText().length() > 0 && Moisture.getText() != null)
				desired.add(Integer.parseInt(Moisture.getText()));

			if (pH.getText().length() > 0 && pH.getText() != null)
				desired.add(Integer.parseInt(pH.getText()));

			if (cO2.getText().length() > 0 && cO2.getText() != null)
				desired.add(Integer.parseInt(cO2.getText()));
			
			if(SunTime.getText().length() > 0 && SunTime.getText() != null)
				desired.add(Integer.parseInt(SunTime.getText()));

			final Node source = (Node) e.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

			Display.setLbl_desiredTemp(desired.get(0));
			Display.setLbl_desiredHumid(desired.get(1));
			Display.setLbl_desiredMoisture(desired.get(2));
			Display.setLbl_desiredPH(desired.get(3));
			Display.setLbl_desiredCO2(desired.get(4));

			Controller.desired = desired;

		});

		btn_tropicalPreset.setOnAction(e -> {

			Controller.desired = tPreset;
			final Node source = (Node) e.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		});

		btn_coldPreset.setOnAction(e -> {

			Controller.desired = cPreset;
			final Node source = (Node) e.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		});

		btn_dryPreset.setOnAction(e -> {

			Controller.desired = dPreset;
			final Node source = (Node) e.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		});

	}

	public static ArrayList<Integer> getDesired() {
		return desired;
	}

}
