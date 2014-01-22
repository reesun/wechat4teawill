package com.teawill.wechat;

/**
 * Created with IntelliJ IDEA. User: oO Date: 13-12-11 Time: 下午4:03 To change
 * this template use File | Settings | File Templates.
 */
public class WeChatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6154339023318521631L;

	public WeChatException() {
		super(); // To change body of overridden methods use File | Settings |
					// File Templates.
	}

	public WeChatException(String s) {
		super(s); // To change body of overridden methods use File | Settings |
					// File Templates.
	}

	public WeChatException(String s, Throwable throwable) {
		super(s, throwable); // To change body of overridden methods use File |
								// Settings | File Templates.
	}

	public WeChatException(Throwable throwable) {
		super(throwable); // To change body of overridden methods use File |
							// Settings | File Templates.
	}

	protected WeChatException(String s, Throwable throwable, boolean b,
			boolean b2) {
		super(s, throwable, b, b2); // To change body of overridden methods use
									// File | Settings | File Templates.
	}
}
