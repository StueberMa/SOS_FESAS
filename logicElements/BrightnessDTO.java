package de.mannheim.sos.tunnelSAS.model;

import java.util.ArrayList;

public class BrightnessDTO {
	
	private int envBrightness;
	private ArrayList<Integer> lampBrightness;
	
	public int getEnvBrightness() {
		return envBrightness;
	}
	public void setEnvBrightness(int envBrightness) {
		this.envBrightness = envBrightness;
	}
	public ArrayList<Integer> getLampBrightness() {
		return lampBrightness;
	}
	public void setLampBrightness(ArrayList<Integer> lampBrightness) {
		this.lampBrightness = lampBrightness;
	}
}
