package intelliGreen;

import java.util.ArrayList;

public class HardwareModule {

	/*
	 * This class simulates hardware systems that adjust the greenhouse environment
	 * to user selected values.
	 * 
	 */

	private ArrayList<Integer> current = new ArrayList<>();
	private ArrayList<Integer> desired = new ArrayList<>();


	private boolean airOn;
	private boolean humidifierOn;
	private boolean lightsOn;
	private boolean ventOn;
	private boolean irrigationOn;
	private boolean CO2releaseOn;

	public enum AirCond {
		HEATING, COOLING, OFF
	};

	public enum Humid {
		ON, OFF
	};
	
	public enum Moist {
		ON, OFF
	};

	public enum Co2 {
		ON, OFF
	};
	
	private AirCond cond;
	private Humid humidCond;
	private Moist moistCond;
	private Co2 co2Cond;

	public HardwareModule() {

	}

	public HardwareModule(ArrayList<Integer> current, ArrayList<Integer> desired) {
		this.setCurrent(current);
		this.setDesired(desired);
	}

	public void checkAir(int currentTemp, int desiredTemp) {
		if (currentTemp < desiredTemp) {
			setCond(AirCond.HEATING);
			setAirOn(true);
		} else if (currentTemp > desiredTemp) {
			setCond(AirCond.COOLING);
			setAirOn(true);
		} else {
			setCond(AirCond.OFF);
			setAirOn(false);
		}
	}

	public void checkHumidity(int currentHumid, int desiredHumid) {
		if (currentHumid < desiredHumid) {
			setHumidCond(Humid.ON);
			setHumidifierOn(true);
		} else if (currentHumid > desiredHumid) {
			setHumidCond(Humid.OFF);
			setVentOn(true);
			setHumidifierOn(false);
		} else {
			setHumidCond(Humid.OFF);
			setVentOn(false);
			setHumidifierOn(false);
		}

	}

	public boolean isHumidifierOn() {
		return humidifierOn;
	}

	public void setHumidifierOn(boolean humidifierOn) {
		this.humidifierOn = humidifierOn;
	}

	public void checkLightsOn(int hour, int desiredSunTime) {
		if (hour >= 6 && hour < (6 + desiredSunTime) ) {
			setLightsOn(true);
		}
		else {
		setLightsOn(false);
		}
	}
	
	public boolean isLightsOn() {
		return lightsOn;
	}

	public void setLightsOn(boolean lightsOn) {
		this.lightsOn = lightsOn;
	}

	public boolean isVentOn() {
		return ventOn;
	}

	public void setVentOn(boolean ventOn) {
		this.ventOn = ventOn;
	}

	public void checkMoisture(int currentMoisture, int desiredMoisture) {
		if (currentMoisture < desiredMoisture) {
			setMoistCond(Moist.ON);
			setIrrigationOn(true);
		} else {
			setIrrigationOn(false);
			setMoistCond(Moist.OFF);
		}
	}

	public boolean isIrrigationOn() {
		return irrigationOn;
	}

	public void setIrrigationOn(boolean irrigationOn) {
		this.irrigationOn = irrigationOn;
	}
	
	public void checkCO2(int currentCO2, int desiredCO2) {
		if (currentCO2 < desiredCO2) {
			setCO2Cond(Co2.ON);
			setCO2releaseOn(true);
		} else if (currentCO2 > desiredCO2) {
			setCO2Cond(Co2.OFF);
			setVentOn(true);
			setCO2releaseOn(false);
		}else {
			setCO2Cond(Co2.OFF);
			setCO2releaseOn(false);
			setVentOn(false);
		}
	}

	public boolean isCO2releaseOn() {
		return CO2releaseOn;
	}

	public void setCO2releaseOn(boolean cO2releaseOn) {
		CO2releaseOn = cO2releaseOn;
	}

	public AirCond getCond() {
		return cond;
	}

	public void setCond(AirCond cond) {
		this.cond = cond;
	}

	public boolean isAirOn() {
		return airOn;
	}

	public void setAirOn(boolean airOn) {
		this.airOn = airOn;
	}

	public ArrayList<Integer> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<Integer> current) {
		this.current = current;
	}

	public ArrayList<Integer> getDesired() {
		return desired;
	}

	public void setDesired(ArrayList<Integer> desired) {
		this.desired = desired;
	}
	public String getCondToString() {
		if (getCond() == AirCond.HEATING) {
			return "HEATING";
		}
		else if (getCond() == AirCond.COOLING) {
			return "COOLING";
		}
		else{
			return "OFF";
		}
		
	}
	public Humid getHumidCond() {
		return humidCond;
	}
	public void setHumidCond(Humid x) {
		this.humidCond = x;
	}
	
	public String getHumidCondtoString() {
		if (getHumidCond() == Humid.ON) {
			return "ON";
		}
		else if(getHumidCond() == Humid.OFF) {
			return "OFF";
		}
		else{
			return "OFF";
		}
		
	}
	public Moist getMoistCond() {
		return moistCond;
	}
	public void setMoistCond(Moist x) {
		this.moistCond = x;
	}
	
	public String getMoistCondtoString() {
		if (getMoistCond() == Moist.ON) {
			return "ON";
		}
		else if(getMoistCond() == Moist.OFF) {
			return "OFF";
		}
		else{
			return "OFF";
		}
		
	}
	public Co2 getCO2Cond() {
		return co2Cond;
	}
	public void setCO2Cond(Co2 x) {
		this.co2Cond = x;
	}
	public String getCO2CondtoString() {
		if (getCO2Cond() == Co2.ON) {
			return "ON";
		}
		else if(getCO2Cond() == Co2.OFF) {
			return "OFF";
		}
		else{
			return "OFF";
		}
		
	}

}