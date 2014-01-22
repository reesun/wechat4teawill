package com.teawill.wechat;

import java.io.InputStream;

public interface MessageProcessor {
	public Message process(InputStream content);
}
