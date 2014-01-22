package com.teawill.wechat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpKit {
	public static final String HEAD_USER_AGENT = "User-Agent";
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36 AlexaToolbar/alxg-3.1";
	public static final String HEAD_REFER = "Referer";
	public static final String PROXY_HOST = "127.0.0.1";
	public static final int PROXY_PORT = 8087;
	public static final Pattern REF_REG = Pattern
			.compile("(^[a-zA-Z]+://[^/]+)[/]?");
	public static final Pattern PROTROL_REG = Pattern
			.compile("(^[a-zA-Z]+://)");
	public static final String[] DATE_PATTERNS = new String[] {
			"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz",
			"EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z",
			"EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z",
			"EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z",
			"EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z",
			"EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z",
			"EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z",
			"E, dd-MMM-yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz" };

	private static HttpClient client(int timeout) {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpClientParams.SO_TIMEOUT, timeout);
		client.getParams().setParameter(
				HttpClientParams.CONNECTION_MANAGER_TIMEOUT, 30000L);
		return client;
	}

	public static InputStream get(String url) throws Exception {
		if (StringUtils.isBlank(url))
			return null;
		GetMethod get = new GetMethod(url);
		try {
			get.addRequestHeader(HEAD_USER_AGENT, USER_AGENT);
			get.addRequestHeader(HEAD_REFER, refer(url));
			int code = client(30000).executeMethod(get);
			if (code == HttpStatus.SC_OK) {
				InputStream in = get.getResponseBodyAsStream();
				try {
					return IOUtils.toBufferedInputStream(in);
				} finally {
					IOUtils.closeQuietly(in);
				}
			}
			if (code == HttpStatus.SC_MOVED_TEMPORARILY
					|| code == HttpStatus.SC_MOVED_PERMANENTLY
					|| code == HttpStatus.SC_SEE_OTHER
					|| code == HttpStatus.SC_TEMPORARY_REDIRECT) {
				String nurl = get.getResponseHeader("Location").getValue();
				return get(nurl);
			}
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	public static InputStream post(String url, RequestEntity enty)
			throws Exception {
		if (StringUtils.isBlank(url))
			return null;
		PostMethod post = new PostMethod(url);
		try {
			post.addRequestHeader(HEAD_USER_AGENT, USER_AGENT);
			post.addRequestHeader(HEAD_REFER, refer(url));
			post.setRequestEntity(enty);
			int code = client(30000).executeMethod(post);
			if (code == HttpStatus.SC_OK) {
				InputStream in = post.getResponseBodyAsStream();
				try {
					return IOUtils.toBufferedInputStream(in);
				} finally {
					IOUtils.closeQuietly(in);
				}
			}
		} finally {
			post.releaseConnection();
		}
		return null;
	}

	public static String refer(String url) {
		Matcher matcher = REF_REG.matcher(url);
		while (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	public static String protrol(String url) {
		Matcher matcher = PROTROL_REG.matcher(url);
		while (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

}
