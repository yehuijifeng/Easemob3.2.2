package easemob.lh.com.easemob322.helper;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;
import java.util.Map;


/**
 * Created by LuHao on 2016/12/10.
 * 接收消息帮助类
 * EMMessage:
 * getFrom():获取消息发送者的用户名
 * getTo():获取消息接收者的用户名
 * getUserName():获取通话对象
 *
 */

public class MessageReceiveHelper {

    /**
     * 接收消息
     * <p>
     * 记得在不需要的时候移除listener，比如在activity的onDestroy()时
     * EMClient.getInstance().chatManager().removeMessageListener(msgListener);
     *
     * @param messageListener 接收消息
     * @param emCallBack      监听消息状态
     */
    public void addMessageListener(EMMessageListener messageListener, final EMCallBack emCallBack) {
        EMClient.getInstance().chatManager().addMessageListener(messageListener);

        EMMessageListener msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                messages.get(0).setMessageStatusCallback(emCallBack);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };

        EMCallBack emCallBack1 = new EMCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        };
    }

    /**
     * 获取聊天记录,获取此会话的所有消息
     *
     * @return
     */
    public List<EMMessage> getAllMessages(String username) {
        //建议初始化SDK的时候设置成每个会话默认load一条消息，节省加载会话的时间，
        // 方法为： options.setNumberOfMessagesLoaded(1);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        //获取此会话的所有消息
        List<EMMessage> messages = conversation.getAllMessages();
        return messages;
    }

    /**
     * 获取聊天记录,获取此会话的具体位置的某一数量的消息
     *
     * @return
     */
    public List<EMMessage> loadMoreMsgFromDB(EMMessage emMessage,int pagesize) {
        //建议初始化SDK的时候设置成每个会话默认load一条消息，节省加载会话的时间，
        // 方法为： options.setNumberOfMessagesLoaded(1);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(emMessage.getTo());
        //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
        //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
        List<EMMessage> messages = conversation.loadMoreMsgFromDB(emMessage.getMsgId(), pagesize);
        return messages;
    }

    /**
     * 获取未读消息数量
     *
     * @param username 用户id
     * @return
     */
    public int getUnreadMsgCount(String username) {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        return conversation.getUnreadMsgCount();
    }

    /**
     * 指定会话消息未读数清零
     *
     * @param username
     */
    public void markAllMessagesAsRead(String username) {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        conversation.markAllMessagesAsRead();
    }

    /**
     * 把一条消息置为已读
     *
     * @param emMessage
     */
    public void markMessageAsRead(EMMessage emMessage) {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(emMessage.getTo());
        conversation.markMessageAsRead(emMessage.getMsgId());
    }

    /**
     * 所有未读消息数清零
     */
    public void markAllConversationsAsRead() {
        EMClient.getInstance().chatManager().markAllConversationsAsRead();
    }

    /**
     * 获取此会话在本地的所有的消息数量
     *
     * @param username
     * @return
     */
    public int getAllMsgCount(String username) {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        //获取此会话在本地的所有的消息数量
        return conversation.getAllMsgCount();
        //如果只是获取当前在内存的消息数量，调用
        //conversation.getAllMessages().size();
    }

    /**
     * 如果出现偶尔返回的conversations的sizi为0，
     * 那很有可能是没有调用EMClient.getInstance().chatManager().loadAllConversations()
     * 或者调用顺序不对
     *
     * @return
     */
    public Map<String, EMConversation> getAllConversations() {
        return EMClient.getInstance().chatManager().getAllConversations();
    }

    /**
     * 删除会话及聊天记录
     *
     * @param username 用户id
     * @param isRecord
     */
    public void deleteConversation(String username, boolean isRecord) {
        //删除和某个user会话，如果需要保留聊天记录，传false
        EMClient.getInstance().chatManager().deleteConversation(username, isRecord);
    }

    /**
     * 删除会话及聊天记录
     *
     * @param emMessage
     */
    public void removeMessage(final EMMessage emMessage) {
        //删除当前会话的某条聊天记录
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(emMessage.getTo());
        conversation.removeMessage(emMessage.getMsgId());
    }

    /**
     * 导入消息到数据库
     *
     * @param msgs
     */
    public void importMessages(List<EMMessage> msgs) {
        EMClient.getInstance().chatManager().importMessages(msgs);
    }
}
