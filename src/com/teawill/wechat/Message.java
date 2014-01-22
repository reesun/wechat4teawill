package com.teawill.wechat;

import java.util.HashMap;
import java.util.Map;

public class Message {
	private Map<String, Object> properties = new HashMap<String, Object>();
	private MessageType type;

	public Object get(String property) {
		return properties.get(property);
	}

	public Message set(String property, Object value) {
		properties.put(property, value);
		return this;
	}

	public String json(MessageFormat format) {
		return format.json(this);
	}

	public String xml(MessageFormat format) {
		return format.xml(this);
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message [type=" + type + ",properties=" + properties + "]";
	}

}
