package com.teawill.wechat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA. User: oO Date: 13-12-10 Time: 下午6:17 To change
 * this template use File | Settings | File Templates.
 */
public class WeChatClient {
	private String appId = "";
	private String appsecret = "";
	private String token = "";
	private MessageProcessor processor;
	public WeChatClient(String appId, String appsecret, String token,MessageProcessor processor) {
		super();
		this.appId = appId;
		this.appsecret = appsecret;
		this.token = token;
		this.processor = processor;
	}

	/**
	 * @param strSrc
	 * @return
	 */
	public static String encrypt(String strSrc) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(strSrc.getBytes());
			return String.valueOf(Hex.encodeHex(md.digest())); // to HexString
		} catch (Exception e) {
			return null;
		}
	}

	public boolean validate(String signature, String timestamp, String nonce,
			String echostr) {
		if (StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp)
				|| StringUtils.isBlank(nonce) || StringUtils.isBlank(echostr)) {
			return false;
		}
		String[] ArrTmp = { token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		String sb = StringUtils.join(ArrTmp);
		String pwd = encrypt(sb);
		if (pwd.equals(signature)) {
			return true;
		}
		return false;
	}

	public String getAccessToken() throws WeChatException {

		String url = WeChatConstants.ACCESS_TOKEN_URL
				+ "?grant_type=client_credential&appid=" + this.appId
				+ "&secret=" + this.appsecret;
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			JSONObject json = new JSONObject(IOUtils.toString(in));
			if (json.has("access_token"))
				return json.getString("access_token");
			return null;
		} catch (Exception e) {
			throw new WeChatException("获取用户授权的用户信息失败"+e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public String getUserAccessToken(String code) throws WeChatException {

		String url = WeChatConstants.USER_ACCESS_TOKEN_URL + "?appid="
				+ this.appId + "&secret=" + this.appsecret + "&code=" + code
				+ "&grant_type=authorization_code";
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("获取用户授权AccssToken失败" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public String refreshUserAccessToken(String refreshToken)
			throws WeChatException {
		String url = WeChatConstants.USER_REFRESH_TOKEN_URL + "?appid="
				+ this.appId + "&grant_type=refresh_token&refresh_token="
				+ refreshToken;
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("刷新用户授权AccssToken失败" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public String getAuthUserInfo(String accessToken, String openId)
			throws WeChatException {

		String url = WeChatConstants.USER_GET_USERINFO_URL + "?access_token="
				+ accessToken + "&openid=" + openId;
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("获取用户授权的用户信息失败" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public String createMenu(String accessToken, String menu)
			throws WeChatException {
		if (null == menu)
			return null;

		String url = WeChatConstants.MENU_CREATE_URL + "?access_token="
				+ accessToken;
		InputStream in = null;
		InputStream inc = null;
		try {
			inc = IOUtils.toInputStream(menu);
			InputStreamRequestEntity entity = new InputStreamRequestEntity(inc,
					inc.available(), "application/x-www-form-urlencoded");
			in = HttpKit.post(url, entity);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("创建用户菜单失败：" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(inc);
		}
	}

	public String getMenu(String accessToken) throws WeChatException {

		String url = WeChatConstants.MENU_GET_URL + "?access_token="
				+ accessToken;
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("获取用户菜单失败" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public String deleteMenu(String accessToken) throws WeChatException {
		String url = WeChatConstants.MENU_DELETE_URL + "?access_token="
				+ accessToken;
		InputStream in = null;
		try {
			in = HttpKit.get(url);
			return IOUtils.toString(in);
		} catch (Exception e) {
			throw new WeChatException("删除用户菜单失败" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	public Message processMessage(InputStream content) {
		return processor.process(content);
	}

	public String getAuthorizeUrl(String redirectUrl, String scope) {
		return WeChatConstants.USER_AUTHORIZE_URL + "?appid=" + this.appId
				+ "&redirect_uri=" + redirectUrl + "&response_type=code&scope="
				+ scope + "&state=" + RandomStringUtils.randomAlphabetic(5)
				+ "#wechat_redirect";
	}

	public File getAudio(String mediaId, String accessToken, File amr) {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpClientParams.SO_TIMEOUT, 30000);
		client.getParams().setParameter(
				HttpClientParams.CONNECTION_MANAGER_TIMEOUT, 30000L);
		String url = WeChatConstants.DOWNLOAD_MEDIA_URL + "?access_token="
				+ accessToken + "&media_id=" + mediaId;
		if (StringUtils.isBlank(url))
			return null;
		GetMethod get = new GetMethod(url);
		InputStream in = null;
		OutputStream out = null;
		try {
			get.addRequestHeader(HttpKit.HEAD_USER_AGENT, HttpKit.USER_AGENT);
			get.addRequestHeader(HttpKit.HEAD_REFER, HttpKit.refer(url));
			int code = client.executeMethod(get);
			if (code == HttpStatus.SC_OK) {
				in = get.getResponseBodyAsStream();
				if (!amr.getParentFile().exists())
					amr.getParentFile().mkdirs();
				out = new FileOutputStream(amr);

				IOUtils.copy(in, out);
				return amr;
			}
		} catch (Exception e) {
			return null;
		} finally {
			get.releaseConnection();
		}
		return null;
	}
	
	public  static  void sendCustomerMsg(String accessToken,String openid,String msg) throws WeChatException{
		if (null == msg || null == openid)
			return;

		String url = WeChatConstants.POST_CUSTOM_MSG + "?access_token="
				+ accessToken;
		InputStream in = null;
		InputStream inc = null;
		try {
			inc = IOUtils.toInputStream(msg,"utf-8");
			InputStreamRequestEntity entity = new InputStreamRequestEntity(inc,
					inc.available(), "application/json");
			in = HttpKit.post(url, entity);
			return;
		} catch (Exception e) {
			throw new WeChatException("发送客服消息失败：" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(inc);
		}
	}
}
