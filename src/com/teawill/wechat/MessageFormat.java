package com.teawill.wechat;

public interface MessageFormat {
	public String json(Message msg);

	public String xml(Message msg);
}
