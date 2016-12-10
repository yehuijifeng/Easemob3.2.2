package easemob.lh.com.easemob322.helper;

import android.content.Context;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.NetUtils;

import easemob.lh.com.easemob322.application.EasemobApplication;
import easemob.lh.com.easemob322.constant.EasemobConstant;
import easemob.lh.com.easemob322.utils.EasemobUtils;

/**
 * Created by LuHao on 2016/12/10.
 * 环信聊天选项设置
 */

public class EasemobHelper {

    /**
     * 初始化环信
     *
     * @param context
     */
    public static void initEMOptions(Context context) {


        //注：如果你的 APP 中有第三方的服务启动，请在初始化 SDK（EMClient.getInstance().init(applicationContext, options)）
        // 方法的前面添加以下相关代码，使用 EaseUI 库的就不用理会这个。
        int pid = android.os.Process.myPid();
        String processAppName = EasemobUtils.getAppName(context, pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        //聊天选项
        EMOptions options = new EMOptions();
        //  默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //  设置是否需要接受方已读确认
        options.setRequireAck(true);
        //  设置是否需要接受方送达确认,默认false
        options.setRequireDeliveryAck(true);
        // 设置GCM(谷歌云)推送id
        options.setGCMNumber("012345678901");
        // 设置小米推送的appid和appkey
        options.setMipushConfig("0123456789012345678", "0123456789012");
        // 设置华为推送的appid
        options.setHuaweiPushAppId("01234567");
        //设置rest server地址
        options.setRestServer("https://www.baoidu.com/");
        //设置im server地址
        options.setIMServer("https://www.baoidu.com/");
        //设置im server 端口号，默认443
        options.setImPort(8080);
        //设置appkey
        options.setAppKey(EasemobConstant.EASEMOB_APP_KEY);
        //允许聊天室群主离开
        options.allowChatroomOwnerLeave(true);
        //设置退出(主动和被动退出)群组时是否删除聊天消息
        options.setDeleteMessagesAsExitGroup(true);
        //设置是否自动接受加群邀请
        options.setAutoAcceptGroupInvitation(false);
        //是否自动登陆
        options.setAutoLogin(false);
        //初始化
        EMClient.getInstance().init(context, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

    }

    /**
     * 用户注册
     * 注册模式分两种，开放注册和授权注册。只有开放注册时，才可以客户端注册。
     * 1,开放注册是为了测试使用，正式环境中不推荐使用该方式注册环信账号；
     * 2,授权注册的流程应该是您服务器通过环信提供的 REST API 注册，之后保存到您的服务器或返回给客户端。
     * 注意：
     * 注册用户名会自动转为小写字母，所以建议用户名均以小写注册。
     * （强烈建议开发者通过后台调用 REST 接口去注册环信 ID，客户端注册方法不提倡用。）
     */
    public void createAccount(String username, String pwd) throws HyphenateException {
        //注册失败会抛出HyphenateException
        EMClient.getInstance().createAccount(username, pwd);//同步方法
    }

    /**
     * 登录
     * 注意：
     * 登录成功后需要调用:
     * EMClient.getInstance().chatManager().loadAllConversations();
     * EMClient.getInstance().groupManager().loadAllGroups();。
     * 以上两个方法是为了保证进入主页面后本地会话和群组都 load 完毕。
     * <p>
     * 另外如果登录过，APP 长期在后台再进的时候也可能会导致加载到内存的群组和会话为空，
     * 可以在主页面的 oncreate 里也加上这两句代码，
     * 当然，更好的办法应该是放在程序的开屏页。
     */
    public void login(String username, String pwd) {
        EMClient.getInstance().login(username, pwd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                //本地会话和群组都 load 完毕。
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d("main", "登陆中");
            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
    }

    /**
     * 自动登录
     * 即首次登录成功后，不需要再次调用登录方法，在下次 APP 启动时，SDK 会自动为您登录。
     * 并且如果您自动登录失败，也可以读取到之前的会话信息（以上情况是在未调用登出的情况下实现的）。
     * <p>
     * SDK 中自动登录属性默认是 true 打开的。
     * 如果不需要自动登录，在初始化 SDK 初始化的时候，调用options.setAutoLogin(false);
     * 设置为 false 关闭。
     * <p>
     * 自动登录在以下几种情况下会被取消：
     * <p>
     * 用户调用了 SDK 的登出动作；
     * 用户在别的设备上更改了密码，导致此设备上自动登录失败；
     * 用户的账号被从服务器端删除；
     * 用户从另一个设备登录，把当前设备上登录的用户踢出。
     */

    /**
     * 退出登录
     * 如果集成了GCM、小米推送、华为推送，方法里第一个参数需要设为true，这样退出的时候会解绑设备token。
     * 否则可能会出现退出了，还能收到消息的现象。
     * <p>
     * 有时候可能会碰到网络问题而解绑失败，app处理时可以弹出提示框让用户选择。
     * 是否继续退出(弹出框提示继续退出能收到消息的风险)。
     * 如果用户选择继续退出，传false再调用logout方法退出成功；
     * 当然也可以失败后还是返回退出成功，然后在后台起个线程不断调用logout方法直到成功。
     * 这样有个风险是：用户kill了app，然后网络恢复，用户还是会继续收到消息。
     * <p>
     * 还有一个需要注意的是：如果调用异步退出方法，在收到onsucess的回调后才去调用IM相关的方法，比如login
     */
    public void logout() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 注册连接状态监听
     * <p>
     * 当掉线时，Android SDK 会自动重连，无需进行任何操作，通过注册连接监听来知道连接状态。
     * 在聊天过程中难免会遇到网络问题，在此 SDK 为您提供了网络监听接口，实时监听.
     * 可以根据 disconnect 返回的 error 判断原因。
     * 若服务器返回的参数值为EMError.USER_LOGIN_ANOTHER_DEVICE，则认为是有同一个账号异地登录；
     * 若服务器返回的参数值为EMError.USER_REMOVED，则是账号在后台被删除。
     */
    public void addConnectionListener() {
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
            //连接
        }

        @Override
        public void onDisconnected(final int error) {
            //断开连接,注意，这里应该处于非ui线程，我们需要回到主线程
            if (error == EMError.USER_REMOVED) {
                // 显示帐号已经被移除
            } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                // 显示帐号在其他设备登录
            } else {
                if (NetUtils.hasNetwork(EasemobApplication.getInstance())) {
                    //连接不到聊天服务器
                } else {
                    //当前网络不可用，请检查网络设置
                }

            }
        }
    }
}
