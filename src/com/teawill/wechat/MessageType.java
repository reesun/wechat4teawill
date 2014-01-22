package com.teawill.wechat;

/**
 * Created by oO on 13-12-5.
 */
public enum MessageType {
    TEXT, //文本消息
    IMAGE,//图片消息
    VOICE, //语音消息
    VIDEO,//视频消息
    ARTICLE,//图文消息
    MUSIC,//音乐消息
    LOCATION,//地理位置消息
    LINK, //链接消息
    EVENT_SUBSCRIBE,//订阅事件消息
    EVENT_UNSUBSCRIBE,//取消订阅事件消息
    EVENT_QRSCENE, //带参数二维码扫描消息
    EVENT_SCAN, //用户已关注时事件推送消息
    EVENT_LOCATION,//上报地理位置事件消息
    EVENT_MENU_CLICK,//用户菜单单击事件消息
    RECONGNITION;//带有语音识别文本的消息

}
