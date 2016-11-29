package com.crawler.schema.web.model;

import java.util.ArrayList;
import java.util.List;

public enum OutputEnum {
	
	HTML(1, "text/html"),
	JPG(2, "image/jpeg");

	int serialNo;
	String output;
	OutputEnum(int serialNo, String output) {
		this.serialNo = serialNo;
		this.output = output;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public String getOutput() {
		return output;
	}
	public static OutputEnum getOutputEnumByString(String command) {
		for (OutputEnum com : values()) {
			if(com.getOutput().equals(command)) {
				return com;
			}
		}
		return null;
	}
	
	public static List<String> getAllOutputEnum() {
		List<String> outputList = new ArrayList<>();
		for (OutputEnum output : values()) {
			outputList.add(output.getOutput());
		}
		return outputList;
	}
}
