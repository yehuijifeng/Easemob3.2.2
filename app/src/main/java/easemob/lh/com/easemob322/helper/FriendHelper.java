package easemob.lh.com.easemob322.helper;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by LuHao on 2016/12/12.
 * 好友管理
 */

public class FriendHelper {

    /**
     * 获取好友列表
     * 获取好友的 username list，开发者需要根据 username 去自己服务器获取好友的详情。
     *
     * @return
     */
    public List<String> getAllContactsFromServer() {
        List<String> usernames = null;
        try {
            usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    /**
     * 查找好友
     * SDK 不提供好友查找的服务，如需要查找好友，需要调用开发者自己服务器的用户查询接口。
     * 为了保证查找到的好友可以添加，需要将开发者自己服务器的用户数据（用户的环信 ID），
     * 通过 SDK 的后台接口导入到环信服务器中。
     * */

    /**
     * 添加好友
     *
     * @param toAddUsername 添加对象名字
     * @param reason        添加理由
     */
    public void addContact(String toAddUsername, String reason) {
        //参数为要添加的好友的username和添加理由
        try {
            EMClient.getInstance().contactManager().addContact(toAddUsername, reason);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除好友
     *
     * @param username
     */
    public void deleteContact(String username) {
        try {
            EMClient.getInstance().contactManager().deleteContact(username);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同意好友请求
     * 默认好友请求是自动同意的。
     * 如果要手动同意：
     * 需要在初始化SDK时调用 opptions.setAcceptInvitationAlways(false);
     *
     * @param username
     */
    public void acceptInvitation(String username) {
        try {
            EMClient.getInstance().contactManager().acceptInvitation(username);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拒绝好友请求
     *
     * @param username
     */
    public void declineInvitation(String username) {
        try {
            EMClient.getInstance().contactManager().declineInvitation(username);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 好友消息接收
     *
     * @param emContactListener
     */
    public void setContactListener(EMContactListener emContactListener) {
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {

            @Override
            public void onContactAgreed(String username) {
                //好友请求被同意
            }

            @Override
            public void onContactRefused(String username) {
                //好友请求被拒绝
            }

            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法
            }
        });
    }

    /**
     * 从服务器获取黑名单列表
     */
    public List<String> getBlackListFromServer() {
        List<String> list = null;
        try {
            list = EMClient.getInstance().contactManager().getBlackListFromServer();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 从本地db获取黑名单列表
     */
    public List<String> getBlackListUsernames() {
        List<String> list = null;
        try {
            list = EMClient.getInstance().contactManager().getBlackListFromServer();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将好友添加进黑名单
     *
     * @param username
     * @param isSend   true，则把用户加入到黑名单后双方发消息时对方都收不到；
     *                 false，则我能给黑名单的中用户发消息，但是对方发给我时我是收不到的
     */
    public void addUserToBlackList(String username, boolean isSend) {
        try {
            EMClient.getInstance().contactManager().addUserToBlackList(username, isSend);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把好友移除黑名单
     *
     * @param username
     */
    public void removeUserFromBlackList(String username) {
        try {
            EMClient.getInstance().contactManager().removeUserFromBlackList(username);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

}
