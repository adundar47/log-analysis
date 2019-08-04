package com.teb.logcreator.model;

public enum LogType {
	INFO("INFO"), WARN("WARN"), FATAL("FATAL"), DEBUG("DEBUG"), ERROR("ERROR");

	private final String type;

	private LogType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

	public static LogType fromString(String text) {
		if (text != null) {
			for (LogType eventType : LogType.values()) {
				if (text.equals(eventType.type)) {
					return eventType;
				}
			}
		}
		return null;
	}

	public static LogType getRandomLogType() {
		return values()[(int) (Math.random() * values().length)];
	}
}
