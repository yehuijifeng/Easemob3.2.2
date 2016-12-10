package easemob.lh.com.easemob322.helper;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;

import java.util.Map;

import easemob.lh.com.easemob322.constant.EaseConstant;



/**
 * Created by LuHao on 2016/12/10.
 * 发送消息帮助类
 */

public class MessageSendHelper {

    /**
     * 发送文本消息
     *
     * @param content        内容
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createTxtSendMessage(String content, String toChatUsername, int chatType) {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送文本消息附带附加属性
     *
     * @param content        内容
     * @param params         附加属性集合
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createTxtSendMessage(String content, Map<String, String> params, String toChatUsername, int chatType) {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        // 增加自己特定的属性,例如：
        // message.setAttribute("attribute1", "value");
        //message.setAttribute("attribute2", true);
        for (Map.Entry entry : params.entrySet()) {
            message.setAttribute(entry.getKey().toString(), entry.getValue().toString());
        }

        //接收消息的时候获取到扩展属性
        //获取自定义的属性，第2个参数为没有此定义的属性时返回的默认值
        //message.getStringAttribute("attribute1", null);
        //message.getBooleanAttribute("attribute2", false);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送语音消息
     *
     * @param filePath       语音文件路径
     * @param length         录音时间(秒)
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createVoiceSendMessage(String filePath, int length, String toChatUsername, int chatType) {
        //filePath为语音文件路径，length为录音时间(秒)
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送视频消息
     *
     * @param videoPath      视频本地路径
     * @param videoLength    视频时间长度
     * @param thumbPath      视频预览图路径
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createVideoSendMessage(String videoPath, String thumbPath, int videoLength, String toChatUsername, int chatType) {
        //videoPath为视频本地路径，thumbPath为视频预览图路径，videoLength为视频时间长度
        EMMessage message = EMMessage.createVideoSendMessage(videoPath, thumbPath, videoLength, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送图片消息
     *
     * @param imagePath      图片本地路径
     * @param isOriginalImg  false为不发送原图（默认超过100k的图片会压缩后发给对方）
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createImageSendMessage(String imagePath, boolean isOriginalImg, String toChatUsername, int chatType) {
        ///imagePath为图片本地路径，false为不发送原图（默认超过100k的图片会压缩后发给对方），需要发送原图传true
        EMMessage message = EMMessage.createImageSendMessage(imagePath, isOriginalImg, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送地理位置消息
     *
     * @param latitude        经度
     * @param longitude       维度
     * @param locationAddress 具体位置内容
     * @param toChatUsername  对方id
     * @param chatType        是否为群
     */
    public void createLocationSendMessage(double latitude, double longitude, String locationAddress, String toChatUsername, int chatType) {
        //latitude为纬度，longitude为经度，locationAddress为具体位置内容
        EMMessage message = EMMessage.createLocationSendMessage(latitude, longitude, locationAddress, toChatUsername);
        //如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送文件消息
     *
     * @param filePath       文件本地路径
     * @param toChatUsername 对方id
     * @param chatType       是否为群
     */
    public void createFileSendMessage(String filePath, String toChatUsername, int chatType) {
        EMMessage message = EMMessage.createFileSendMessage(filePath, toChatUsername);
        // 如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            message.setChatType(EMMessage.ChatType.GroupChat);
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送透传消息
     *
     * @param action     自定义内容本地路径
     * @param toUsername 发送给某个人
     * @param chatType   是否为群
     */
    public void createSendMessage(String action, String toUsername, int chatType) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        //支持单聊和群聊，默认单聊，如果是群聊添加下面这行
        // 如果是群聊，设置chattype，默认是单聊
        if (chatType == EaseConstant.CHATTYPE_GROUP)
            cmdMsg.setChatType(EMMessage.ChatType.GroupChat);
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        cmdMsg.setReceipt(toUsername);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }

    /**
     * 发送扩展消息
     *
     * @param content        自定义内容本地路径
     * @param toChatUsername 发送给某个人
     * @param chatType       是否为群
     */
    public void createSendMessage(String content, Map<String, String> params, String toChatUsername, int chatType) {
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);

        // 增加自己特定的属性,例如：
        // message.setAttribute("attribute1", "value");
        //message.setAttribute("attribute2", true);
        for (Map.Entry entry : params.entrySet()) {
            message.setAttribute(entry.getKey().toString(), entry.getValue().toString());
        }

        EMClient.getInstance().chatManager().sendMessage(message);

        //接收消息的时候获取到扩展属性
        //获取自定义的属性，第2个参数为没有此定义的属性时返回的默认值
        //message.getStringAttribute("attribute1", null);
        //message.getBooleanAttribute("attribute2", false);
    }

}
