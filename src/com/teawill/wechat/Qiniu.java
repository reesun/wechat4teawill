package com.teawill.wechat;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.Entry;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

public class Qiniu {
	public static final transient Log log = LogFactory.getLog(Qiniu.class);
	public static Mac mac;
	public static final String BUICKET_NET = "";
	public static final String BUICKET_TWEET = "";
	public static final String BUICKET_TEST = "";
	static {
		Config.ACCESS_KEY = "";
		Config.SECRET_KEY = "";
		mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

	}

	public static boolean upload(String buicket, String key, File file) {
		try {
			String uptoken = new PutPolicy(buicket).token(mac);
			PutExtra extra = new PutExtra();
			PutRet ret = IoApi.putFile(uptoken, key, file, extra);
			return ret.ok();
		} catch (Exception e) {
			log.info("Qiniu uptoken get failed!\t[ACCESS_KEY="
					+ Config.ACCESS_KEY + ",SECRET_KEY=" + Config.SECRET_KEY
					+ "]");
		}
		return false;
	}

	public static Entry info(String bucket, String key) {
		RSClient client = new RSClient(mac);
		Entry ret = client.stat(bucket, key);
		return ret;
	}

	public static String url(String bucket, String key) {
		return "http://" + bucket + ".qiniudn.com/" + key;
	}

	public static String mp3(String bucket, String key) {
		return url(bucket, key) + "?avthumb/mp3";
	}

	public static String webm(String bucket, String key) {
		return url(bucket, key) + "?avthumb/webm/ab/128k/ar/44100";
	}

	public static void main(String[] args) {
		System.out.println(Qiniu.upload(BUICKET_TWEET, "abc", new File("D:/1.amr")));
		System.out.println(Qiniu.info(BUICKET_TWEET, "abc"));
		System.out.println(Qiniu.url(BUICKET_TWEET, "abc"));
	}
}
