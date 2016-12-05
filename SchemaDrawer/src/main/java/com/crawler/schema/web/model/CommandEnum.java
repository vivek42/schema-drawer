package com.crawler.schema.web.model;

import java.util.ArrayList;
import java.util.List;

public enum CommandEnum {
	
	Text(1, "text", false),
	Graph(2, "graph", true);

	int serialNo;
	String command;
	boolean active;
	CommandEnum(int serialNo, String command, boolean active) {
		this.serialNo = serialNo;
		this.command = command;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public String getCommand() {
		return command;
	}
	public boolean isActive() {
		return active;
	}
	public static CommandEnum getCommandEnumByString(String command) {
		for (CommandEnum com : values()) {
			if(com.getCommand().equals(command)) {
				return com;
			}
		}
		return null;
	}
	
	public static List<CommandEnum> getAllCommandEnum() {
		List<CommandEnum> commandList = new ArrayList<>();
		for (CommandEnum com : values()) {
			commandList.add(com);
		}
		return commandList;
	}
}
