package com.teawill.wechat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DefaultMessageProcessor implements MessageProcessor {
	private static Map<String, String> xpaths = new HashMap<String, String>();
	private static Map<MessageType, Map<String, String>> types = new HashMap<MessageType, Map<String, String>>();

	public DefaultMessageProcessor() {
		configureXPaths();
		configureTypes();
	}

	@Override
	public Message process(InputStream content) {
		Message msg = parse(content);
		msg = parseMsgType(msg);
		return msg;
	}

	private Message parse(InputStream content) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(content);

			Message msg = null;
			for (Map.Entry<String, String> enty : xpaths.entrySet()) {
				Node node = doc.selectSingleNode(enty.getKey());
				if (null == node)
					continue;
				if (null == msg)
					msg = new Message();
				msg.set(enty.getValue(), StringUtils.trim(node.getText()));
			}
			return msg;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	/**
	 * 配置xml的xpath解析到对应的map的key
	 */
	private void configureXPaths() {
		xpaths.put("xml/ToUserName", "ToUserName");
		xpaths.put("xml/FromUserName", "FromUserName");
		xpaths.put("xml/CreateTime", "CreateTime");
		xpaths.put("xml/MsgType", "MsgType");
		xpaths.put("xml/Content", "Content");
		xpaths.put("xml/MsgId", "MsgId");
		xpaths.put("xml/PicUrl", "PicUrl");
		xpaths.put("xml/MediaId", "MediaId");
		xpaths.put("xml/Format", "Format");
		xpaths.put("xml/ThumbMediaId", "ThumbMediaId");
		xpaths.put("xml/Location_X", "Location_X");
		xpaths.put("xml/Location_Y", "Location_Y");
		xpaths.put("xml/Scale", "Scale");
		xpaths.put("xml/Label", "Label");
		xpaths.put("xml/Title", "Title");
		xpaths.put("xml/Description", "Description");
		xpaths.put("xml/Url", "Url");
		xpaths.put("xml/Label", "Label");
		xpaths.put("xml/Event", "Event");
		xpaths.put("xml/EventKey", "EventKey");
		xpaths.put("xml/Ticket", "Ticket");
		xpaths.put("xml/Latitude", "Latitude");
		xpaths.put("xml/Longitude", "Longitude");
		xpaths.put("xml/Precision", "Precision");
		xpaths.put("xml/Recognition", "Recognition");
	}

	/**
	 * 解析微信发送过来的消息类型
	 * 
	 * @param message
	 * @return
	 */
	private Message parseMsgType(Message message) {
		if (null == message)
			return null;
		for (Map.Entry<MessageType, Map<String, String>> enty : types
				.entrySet()) {
			Map<String, String> values = enty.getValue();
			int i = 0;
			for (Map.Entry<String, String> en : values.entrySet()) {
				String key = en.getKey();
				String value = en.getValue();
				String val = (String) message.get(key);
				if ((StringUtils.equals(key, "EventKey")
						&& (enty.getKey() == MessageType.EVENT_SUBSCRIBE || enty
								.getKey() == MessageType.EVENT_UNSUBSCRIBE) && StringUtils
							.isBlank(val))
						|| (StringUtils.equals(key, "EventKey")
								&& enty.getKey() == MessageType.EVENT_QRSCENE && StringUtils
									.isNotBlank(val))
						|| (StringUtils.equals(value, val)
								&& !StringUtils.equals(key, "EventKey") && StringUtils
									.isNotBlank(val))
						|| (StringUtils.equals(key, "Recognition")
								&& enty.getKey() == MessageType.VOICE && StringUtils
									.isBlank(val))
						|| (StringUtils.equals(key, "Recognition")
								&& enty.getKey() == MessageType.RECONGNITION && StringUtils
									.isNotBlank(val))) {
					i++;
				}
			}
			if (i == values.size()) {
				message.setType(enty.getKey());
				break;
			}
		}
		return message;
	}

	/**
	 * 消息类型参数配置
	 */
	@SuppressWarnings("serial")
	private void configureTypes() {
		types.put(MessageType.TEXT, new HashMap<String, String>() {
			{
				put("MsgType", "text");
			}
		});
		types.put(MessageType.IMAGE, new HashMap<String, String>() {
			{
				put("MsgType", "image");
			}
		});
		types.put(MessageType.VOICE, new HashMap<String, String>() {
			{
				put("MsgType", "voice");
				put("Recognition", "");
			}
		});
		types.put(MessageType.VIDEO, new HashMap<String, String>() {
			{
				put("MsgType", "video");
			}
		});
		types.put(MessageType.LOCATION, new HashMap<String, String>() {
			{
				put("MsgType", "location");
			}
		});
		types.put(MessageType.LINK, new HashMap<String, String>() {
			{
				put("MsgType", "link");
			}
		});
		types.put(MessageType.LINK, new HashMap<String, String>() {
			{
				put("MsgType", "link");
			}
		});
		types.put(MessageType.EVENT_SUBSCRIBE, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "subscribe");
				put("EventKey", "");
			}
		});
		types.put(MessageType.EVENT_UNSUBSCRIBE, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "unsubscribe");
				put("EventKey", "");
			}
		});
		types.put(MessageType.EVENT_QRSCENE, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "subscribe");
				put("EventKey", "qrscene");
			}
		});
		types.put(MessageType.EVENT_SCAN, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "scan");
			}
		});
		types.put(MessageType.EVENT_LOCATION, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "LOCATION");
			}
		});
		types.put(MessageType.EVENT_MENU_CLICK, new HashMap<String, String>() {
			{
				put("MsgType", "event");
				put("Event", "CLICK");
			}
		});
		types.put(MessageType.RECONGNITION, new HashMap<String, String>() {
			{
				put("MsgType", "voice");
				put("Recognition", "recognition");
			}
		});
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new DefaultMessageProcessor()
				.process(new FileInputStream("H:/weixin.xml")));
	}
}
