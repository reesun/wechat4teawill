package com.teawill.wechat;

/**
 * 微信平台常量<br>
 * 返回码	说明
 */
public class WeChatConstants {
    /**
     * @method ： get
     * @example : https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * @args: grant_type 是     获取access_token填写client_credential<BR/>
     * appid	 是	 第三方用户唯一凭证<BR/>
     * secret	 是	 第三方用户唯一凭证密钥，既appsecret<BR/>
     * @success : {"access_token":"ACCESS_TOKEN","expires_in":7200}
     * @failed : {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * @method : post
     * @example : http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     * @args:access_token 是     调用接口凭证<BR/>
     * type	 是	 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）<BR/>
     * media	 是	 form-data中媒体文件标识，有filename、filelength、content-type等信息<BR/>
     * @success : {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
     * @failed : {"errcode":40004,"errmsg":"invalid media type"}
     */
    public final static String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
    /**
     * @method ： get
     * @example : http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
     * @args : access_token	 是	 调用接口凭证
     * media_id	 是	 媒体文件ID
     * @success : HTTP/1.1 200 OK
     * Connection: close
     * Content-Type: image/jpeg
     * Content-disposition: attachment; filename="MEDIA_ID.jpg"
     * Date: Sun, 06 Jan 2013 10:20:18 GMT
     * Cache-Control: no-cache, must-revalidate
     * Content-Length: 339721
     * curl -G "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
     * @failed : {"errcode":40007,"errmsg":"invalid media_id"}
     */
    public final static String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get";
    /**
     * @method ： post
     * @arg ： access_token	 是	 调用接口凭证
     * msg 是 发送的消息，请参考net.oschina.weixin.msg.post.*
     */
    public final static String POST_CUSTOM_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    /**
     * @method : get
     * @example : https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN
     * @method : get
     * @arg :
     * access_token	 调用接口凭证
     */
    public final static String GET_USER_GROUPS = "https://api.weixin.qq.com/cgi-bin/groups/get";
    /**
     * @method : post
     * @example :  https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN
     * @args : POST数据格式：json
     * POST数据例子：{"group":{"name":"test"}}
     * access_token	 调用接口凭证
     * name	 分组名字（30个字符以内）
     * @success ：返回说明 正常时的返回JSON数据包示例：
     * <p/>
     * {
     * "group": {
     * "id": 107,
     * "name": "test"
     * }
     * }
     * @failed : 错误时的JSON数据包示例（该示例为AppID无效错误）：
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String CREATE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create";
    /**
     * @example :  https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN
     * @args :
     * POST数据格式：json
     * POST数据例子：{"group":{"id":108,"name":"test2_modify2"}}
     * 参数说明
     * <p/>
     * 参数	说明
     * access_token	 调用接口凭证
     * id	 分组id，由微信分配
     * name	 分组名字（30个字符以内）
     * @success :
     * 正常时的返回JSON数据包示例：
     * <p/>
     * {"errcode": 0, "errmsg": "ok"}
     * @failed :
     * 错误时的JSON数据包示例（该示例为AppID无效错误）：
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String UPDATE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/update";

    /**
     * @method : post
     * @example ： https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN
     * @args :
     * POST数据格式：json
     * POST数据例子：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
     * @success :
     * 正常时的返回JSON数据包示例：
     * <p/>
     * {"errcode": 0, "errmsg": "ok"}
     * @failed :
     * 错误时的JSON数据包示例（该示例为AppID无效错误）：
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String MOVE_GROUP_USER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update";
    /**
     * @method ： get
     * @example ：https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID
     * @args :
     * access_token	 是	 调用接口凭证
     * openid	 是	 普通用户的标识，对当前公众号唯一
     * @success :
     * 正常情况下，微信会返回下述JSON数据包给公众号：
     * <p/>
     * {
     * "subscribe": 1,
     * "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
     * "nickname": "Band",
     * "sex": 1,
     * "language": "zh_CN",
     * "city": "广州",
     * "province": "广东",
     * "country": "中国",
     * "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     * "subscribe_time": 1382694957
     * }
     * @failed :
     * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * @method ：get
     * @example ： https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
     * @args :
     * access_token	 是	 调用接口凭证
     * next_openid	 是	 第一个拉取的OPENID，不填默认从头开始拉取
     * @success :
     * 正确时返回JSON数据包：
     * <p/>
     * {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
     * @failed :
     * 错误时返回JSON数据包（示例为无效AppID错误）：
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     * 当公众号关注者数量超过10000时，可通过填写next_openid的值，从而多次拉取列表的方式来满足需求。
     * <p/>
     * 具体而言，就是在调用接口时，将上一次调用得到的返回中的next_openid值，作为下一次调用中的next_openid值。
     */
    public final static String GET_FAV_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
    /**
     * @method ： get
     * @example : https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     * @args :
     * appid	 是	 公众号的唯一标识
     * redirect_uri	 是	 授权后重定向的回调链接地址
     * response_type	 是	 返回类型，请填写code
     * scope	 是	 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * state	 否	 重定向后会带上state参数，开发者可以填写任意参数值
     * #wechat_redirect	 否	 直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
     * @suc
     */
    public final static String USER_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * @method : get
     * @args :
     * appid	 是	 公众号的唯一标识
     * secret	 是	 公众号的appsecret
     * code	 是	 填写第一步获取的code参数
     * grant_type	 是	 填写为authorization_code
     * @success ：
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     * @failed :
     * {"errcode":40029,"errmsg":"invalid code"}
     */
    public final static String USER_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * @method :get
     * @args :
     * appid	 是	 公众号的唯一标识
     * grant_type	 是	 填写为refresh_token
     * refresh_token	 是	 填写通过access_token获取到的refresh_token参数
     * @success: {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     * @failed :
     * {"errcode":40029,"errmsg":"invalid code"}
     */
    public final static String USER_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /**
     * @method : get
     * @args :
     * access_token	 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * openid	 用户的唯一标识
     * @success :
     * 正确时返回的JSON数据包如下：
     * <p/>
     * {
     * "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[
     * "PRIVILEGE1"
     * "PRIVILEGE2"
     * ]
     * }
     * @failed :
     * 错误时微信会返回JSON数据包如下（示例为openid无效）:
     * <p/>
     * {"errcode":40003,"errmsg":" invalid openid "}
     */
    public final static String USER_GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * @method : post
     * @example ：  https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
     * @arg ：
     * {
     * "button":[
     * {
     * "type":"click",
     * "name":"今日歌曲",
     * "key":"V1001_TODAY_MUSIC"
     * },
     * {
     * "type":"click",
     * "name":"歌手简介",
     * "key":"V1001_TODAY_SINGER"
     * },
     * {
     * "name":"菜单",
     * "sub_button":[
     * {
     * "type":"view",
     * "name":"搜索",
     * "url":"http://www.soso.com/"
     * },
     * {
     * "type":"view",
     * "name":"视频",
     * "url":"http://v.qq.com/"
     * },
     * {
     * "type":"click",
     * "name":"赞一下我们",
     * "key":"V1001_GOOD"
     * }]
     * }]
     * }
     * 参数说明
     * <p/>
     * 参数	是否必须	说明
     * button	 是	 一级菜单数组，个数应为1~3个
     * sub_button	 否	 二级菜单数组，个数应为1~5个
     * type	 是	 菜单的响应动作类型，目前有click、view两种类型
     * name	 是	 菜单标题，不超过16个字节，子菜单不超过40个字节
     * key	 click类型必须	 菜单KEY值，用于消息接口推送，不超过128字节
     * url	 view类型必须	 网页链接，用户点击菜单可打开链接，不超过256字节
     * @success :
     * 正确时的返回JSON数据包如下：
     * <p/>
     * {"errcode":0,"errmsg":"ok"}
     * @error 错误时的返回JSON数据包如下（示例为无效菜单名长度）：
     * <p/>
     * {"errcode":40018,"errmsg":"invalid button name size"}
     */
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
    /**
     * @method ： get
     * @example : https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
     * @success ：
     * 对应创建接口，正确的Json返回结果:
     * {"menu":{"button":[{"type":"click","name":"今日歌曲","key":"V1001_TODAY_MUSIC","sub_button":[]},{"type":"click","name":"歌手简介","key":"V1001_TODAY_SINGER","sub_button":[]},{"name":"菜单","sub_button":[{"type":"view","name":"搜索","url":"http://www.soso.com/","sub_button":[]},{"type":"view","name":"视频","url":"http://v.qq.com/","sub_button":[]},{"type":"click","name":"赞一下我们","key":"V1001_GOOD","sub_button":[]}]}]}}
     */
    public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
    /**
     * @method ： get
     * @example ：https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
     * @success :
     * 对应创建接口，正确的Json返回结果:
     * {"errcode":0,"errmsg":"ok"}
     */
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";
    /**
     * @method ： post
     * @example ： https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
     * @arg ：
     * 临时二维码请求说明
     * <p/>
     * http请求方式: POST
     * URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
     * POST数据格式：json
     * POST数据例子：{"expire_seconds": 1800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 永久二维码请求说明
     * <p/>
     * http请求方式: POST
     * URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
     * POST数据格式：json
     * POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * expire_seconds	 该二维码有效时间，以秒为单位。 最大不超过1800。
     * action_name	 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * action_info	 二维码详细信息
     * scene_id	 场景值ID，临时二维码时为32位整型，永久二维码时最大值为1000
     * @success :
     * 正确的Json返回结果:
     * <p/>
     * {"ticket":"gQG28DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0FuWC1DNmZuVEhvMVp4NDNMRnNRAAIEesLvUQMECAcAAA==","expire_seconds":1800}
     * @error :
     * 错误的Json返回示例:
     * <p/>
     * {"errcode":40013,"errmsg":"invalid appid"}
     */
    public final static String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    /**
     * @method :get
     * @example : https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * @arg :
     * ticket=TICKET
     * @success :
     * 返回二维码图片
     */
    public final static String QRCODE_SHOW_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

}
