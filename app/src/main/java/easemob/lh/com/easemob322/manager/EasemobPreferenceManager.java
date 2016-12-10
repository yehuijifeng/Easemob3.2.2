package easemob.lh.com.easemob322.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LuHao on 2016/12/10.
 * 环信聊天的偏好设置
 */

public class EasemobPreferenceManager {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;
    private static EasemobPreferenceManager mPreferencemManager;

    //个性化设置标识
    public static final String PREFERENCE_NAME = "saveInfo";
    //通知
    private String SHARED_KEY_SETTING_NOTIFICATION = "shared_key_setting_notification";
    //声音
    private String SHARED_KEY_SETTING_SOUND = "shared_key_setting_sound";
    //震动
    private String SHARED_KEY_SETTING_VIBRATE = "shared_key_setting_vibrate";
    //扬声器
    private String SHARED_KEY_SETTING_SPEAKER = "shared_key_setting_speaker";
    //聊天室群主是否可以离开
    private static String SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE = "shared_key_setting_chatroom_owner_leave";
    //退出群时候是否删除消息
    private static String SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP = "shared_key_setting_delete_messages_when_exit_group";
    //自动接收群邀请
    private static String SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION = "shared_key_setting_auto_accept_group_invitation";
    //自适应视频编码格式
    private static String SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE = "shared_key_setting_adaptive_video_encode";
    //离线推送电话
    private static String SHARED_KEY_SETTING_OFFLINE_PUSH_CALL = "shared_key_setting_offline_push_call";
    //群资料同步
    private static String SHARED_KEY_SETTING_GROUPS_SYNCED = "SHARED_KEY_SETTING_GROUPS_SYNCED";
    //联系人同步
    private static String SHARED_KEY_SETTING_CONTACT_SYNCED = "SHARED_KEY_SETTING_CONTACT_SYNCED";
    //同步黑名单
    private static String SHARED_KEY_SETTING_BALCKLIST_SYNCED = "SHARED_KEY_SETTING_BALCKLIST_SYNCED";
    //未知的用户名
    private static String SHARED_KEY_CURRENTUSER_USERNAME = "SHARED_KEY_CURRENTUSER_USERNAME";
    //未知的昵称
    private static String SHARED_KEY_CURRENTUSER_NICK = "SHARED_KEY_CURRENTUSER_NICK";
    //未知的头像
    private static String SHARED_KEY_CURRENTUSER_AVATAR = "SHARED_KEY_CURRENTUSER_AVATAR";
    //rest server
    private static String SHARED_KEY_REST_SERVER = "SHARED_KEY_REST_SERVER";
    //im server
    private static String SHARED_KEY_IM_SERVER = "SHARED_KEY_IM_SERVER";
    //启用自定义server
    private static String SHARED_KEY_ENABLE_CUSTOM_SERVER = "SHARED_KEY_ENABLE_CUSTOM_SERVER";
    //启用自定义appkey
    private static String SHARED_KEY_ENABLE_CUSTOM_APPKEY = "SHARED_KEY_ENABLE_CUSTOM_APPKEY";
    //启用appkey
    private static String SHARED_KEY_CUSTOM_APPKEY = "SHARED_KEY_CUSTOM_APPKEY";
    //调用最小视频毫秒数
    private static String SHARED_KEY_CALL_MIN_VIDEO_KBPS = "SHARED_KEY_CALL_MIN_VIDEO_KBPS";
    //调用最大视频毫秒数
    private static String SHARED_KEY_CALL_MAX_VIDEO_KBPS = "SHARED_KEY_CALL_Max_VIDEO_KBPS";
    //调用最大视频分辨率
    private static String SHARED_KEY_CALL_MAX_FRAME_RATE = "SHARED_KEY_CALL_MAX_FRAME_RATE";
    //调用音频帧数
    private static String SHARED_KEY_CALL_AUDIO_SAMPLE_RATE = "SHARED_KEY_CALL_AUDIO_SAMPLE_RATE";
    //调用相机回调分辨率
    private static String SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION = "SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION";
    //调用前置摄像头分辨率
    private static String SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION = "SHARED_KEY_FRONT_CAMERA_RESOLUTIOIN";
    //调用固定分辨率
    private static String SHARED_KEY_CALL_FIX_SAMPLE_RATE = "SHARED_KEY_CALL_FIX_SAMPLE_RATE";

    private EasemobPreferenceManager(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static synchronized void getInstance(Context cxt) {
        if (mPreferencemManager == null) {
            synchronized (EasemobPreferenceManager.class) {
                if (mPreferencemManager == null) {
                    mPreferencemManager = new EasemobPreferenceManager(cxt);
                }
            }
        }
    }

    /**
     * 设置消息通知
     *
     * @param paramBoolean
     */
    public void setSettingMsgNotification(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_NOTIFICATION, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgNotification() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_NOTIFICATION, true);
    }

    /**
     * 设置消息声音
     *
     * @param paramBoolean
     */
    public void setSettingMsgSound(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_SOUND, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgSound() {

        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_SOUND, true);
    }

    /**
     * 设置消息震动
     *
     * @param paramBoolean
     */
    public void setSettingMsgVibrate(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_VIBRATE, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgVibrate() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_VIBRATE, true);
    }

    /**
     * 设置消息扬声器
     *
     * @param paramBoolean
     */
    public void setSettingMsgSpeaker(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_SPEAKER, paramBoolean);
        editor.apply();
    }

    public boolean getSettingMsgSpeaker() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_SPEAKER, true);
    }

    /**
     * 设置允许聊天室群主离开
     *
     * @param value
     */
    public void setSettingAllowChatroomOwnerLeave(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, value);
        editor.apply();
    }

    public boolean getSettingAllowChatroomOwnerLeave() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, false);
    }

    /**
     * 退出群删除群消息记录
     *
     * @param value
     */
    public void setDeleteMessagesAsExitGroup(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP, value);
        editor.apply();
    }

    public boolean isDeleteMessagesAsExitGroup() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP, true);
    }

    /**
     * 自动同意群邀请
     *
     * @param value
     */
    public void setAutoAcceptGroupInvitation(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION, value);
        editor.commit();
    }

    public boolean isAutoAcceptGroupInvitation() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION, false);
    }

    /**
     * 自适应视频编码
     *
     * @param value
     */
    public void setAdaptiveVideoEncode(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, value);
        editor.apply();
    }

    public boolean isAdaptiveVideoEncode() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, true);
    }

    /**
     * 设置调用推送
     *
     * @param value
     */
    public void setPushCall(boolean value) {
        editor.putBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, value);
        editor.apply();
    }

    public boolean isPushCall() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, true);
    }

    /**
     * 群同步
     *
     * @param synced
     */
    public void setGroupsSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, synced);
        editor.apply();
    }

    public boolean isGroupsSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, true);
    }

    /**
     * 联系人同步
     *
     * @param synced
     */
    public void setContactSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, synced);
        editor.apply();
    }

    public boolean isContactSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, true);
    }

    public void setBlacklistSynced(boolean synced) {
        editor.putBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, synced);
        editor.apply();
    }

    /**
     * 黑名单同步
     *
     * @return
     */
    public boolean isBacklistSynced() {
        return mSharedPreferences.getBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, true);
    }

    /**
     * 设置昵称
     *
     * @param nick
     */
    public void setCurrentUserNick(String nick) {
        editor.putString(SHARED_KEY_CURRENTUSER_NICK, nick);
        editor.apply();
    }

    public String getCurrentUserNick() {
        return mSharedPreferences.getString(SHARED_KEY_CURRENTUSER_NICK, null);
    }


    /**
     * 设置头像
     *
     * @param avatar
     */
    public void setCurrentUserAvatar(String avatar) {
        editor.putString(SHARED_KEY_CURRENTUSER_AVATAR, avatar);
        editor.apply();
    }

    public String getCurrentUserAvatar() {
        return mSharedPreferences.getString(SHARED_KEY_CURRENTUSER_AVATAR, null);
    }

    /**
     * 设置用户名
     *
     * @param username
     */
    public void setCurrentUserName(String username) {
        editor.putString(SHARED_KEY_CURRENTUSER_USERNAME, username);
        editor.apply();
    }

    public String getCurrentUsername() {
        return mSharedPreferences.getString(SHARED_KEY_CURRENTUSER_USERNAME, null);
    }

    /**
     * 设置 rest server
     *
     * @param restServer
     */
    public void setRestServer(String restServer) {
        editor.putString(SHARED_KEY_REST_SERVER, restServer).commit();
        editor.commit();
    }

    public String getRestServer() {
        return mSharedPreferences.getString(SHARED_KEY_REST_SERVER, null);
    }

    /**
     * 设置im server
     *
     * @param imServer
     */
    public void setIMServer(String imServer) {
        editor.putString(SHARED_KEY_IM_SERVER, imServer);
        editor.commit();
    }

    public String getIMServer() {
        return mSharedPreferences.getString(SHARED_KEY_IM_SERVER, null);
    }

    /**
     * 启用自定义server
     *
     * @param enable
     */
    public void enableCustomServer(boolean enable) {
        editor.putBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, enable);
        editor.apply();
    }

    public boolean isCustomServerEnable() {
        return mSharedPreferences.getBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, false);
    }

    /**
     * 设置自定义appkey
     *
     * @param enable
     */
    public void enableCustomAppkey(boolean enable) {
        editor.putBoolean(SHARED_KEY_ENABLE_CUSTOM_APPKEY, enable);
        editor.apply();
    }

    public boolean isCustomAppkeyEnabled() {
        return mSharedPreferences.getBoolean(SHARED_KEY_ENABLE_CUSTOM_APPKEY, false);
    }

    /**
     * 设置appkey
     *
     * @return
     */
    public String getCustomAppkey() {
        return mSharedPreferences.getString(SHARED_KEY_CUSTOM_APPKEY, "");
    }

    public void setCustomAppkey(String appkey) {
        editor.putString(SHARED_KEY_CUSTOM_APPKEY, appkey);
        editor.apply();
    }

    /**
     * 移除当前的用户设置
     */
    public void removeCurrentUserInfo() {
        editor.remove(SHARED_KEY_CURRENTUSER_NICK);
        editor.remove(SHARED_KEY_CURRENTUSER_AVATAR);
        editor.apply();
    }

    /**
     * 最小的视屏 千位节/秒
     * 如果没有，则返回-1
     */
    public int getCallMinVideoKbps() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, -1);
    }

    public void setCallMinVideoKbps(int minBitRate) {
        editor.putInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, minBitRate);
        editor.apply();
    }

    /**
     * 最大的视屏 千位节/秒
     * 如果没有，则返回-1
     */
    public int getCallMaxVideoKbps() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, -1);
    }

    public void setCallMaxVideoKbps(int maxBitRate) {
        editor.putInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, maxBitRate);
        editor.apply();
    }

    /**
     * 最大视频分辨率
     * i如果没有，则返回-1
     *
     * @return
     */
    public int getCallMaxFrameRate() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_MAX_FRAME_RATE, -1);
    }

    public void setCallMaxFrameRate(int maxFrameRate) {
        editor.putInt(SHARED_KEY_CALL_MAX_FRAME_RATE, maxFrameRate);
        editor.apply();
    }

    /**
     * 音频采样率;
     * 如果没有，则返回-1
     *
     * @return
     */
    public int getCallAudioSampleRate() {
        return mSharedPreferences.getInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, -1);
    }

    public void setCallAudioSampleRate(int audioSampleRate) {
        editor.putInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, audioSampleRate);
        editor.apply();
    }

    /**
     * 相机回调分辨率率
     * 格式: 320x240
     * 如果没有，则返回 ""
     */
    public String getCallBackCameraResolution() {
        return mSharedPreferences.getString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, "");
    }

    public void setCallBackCameraResolution(String resolution) {
        editor.putString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, resolution);
        editor.apply();
    }

    /**
     * 前置摄像头的分辨率
     * 格式: 320x240
     * 如果没有，则返回 ""
     */
    public String getCallFrontCameraResolution() {
        return mSharedPreferences.getString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, "");
    }

    public void setCallFrontCameraResolution(String resolution) {
        editor.putString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, resolution);
        editor.apply();
    }

    /**
     * 固定视频采样率;
     * 如果没有，返回false
     *
     * @return
     */
    public boolean isCallFixedVideoResolution() {
        return mSharedPreferences.getBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, false);
    }

    public void setCallFixedVideoResolution(boolean enable) {
        editor.putBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, enable);
        editor.apply();
    }

}
