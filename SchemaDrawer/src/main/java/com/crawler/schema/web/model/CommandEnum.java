package com.crawler.schema.web.model;

import java.util.ArrayList;
import java.util.List;

public enum CommandEnum {
	
	Text(1, "text"),
	Graph(2, "graph");

	int serialNo;
	String command;
	CommandEnum(int serialNo, String command) {
		this.serialNo = serialNo;
		this.command = command;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public String getCommand() {
		return command;
	}
	public static CommandEnum getCommandEnumByString(String command) {
		for (CommandEnum com : values()) {
			if(com.getCommand().equals(command)) {
				return com;
			}
		}
		return null;
	}
	
	public static List<String> getAllCommandEnum() {
		List<String> commandList = new ArrayList<>();
		for (CommandEnum com : values()) {
			commandList.add(com.getCommand());
		}
		return commandList;
	}
}
