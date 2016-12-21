package easemob.lh.com.easemob322;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import easemob.lh.com.easemob322.chatrow.EaseChatRowVoicePlayClickListener;
import easemob.lh.com.easemob322.constant.EaseConstant;

/**
 * Created by LuHao on 2016/12/10.
 * 环信3.0 api
 */

public class EasemobAPI {

    /**
     * jar 包说明：
     * hyphenatechar_3.2.2.jar:环信sdk
     * android-support-v4.jar：这个可以说是每个 APP 中都是不可缺少的 jar 包，这里不多赘述
     * google-play-services.jar：GCM 的 jar 包，不需要 GCM 推送可以删除
     * MiPush_SDK_Client_2_2_19.jar：小米推送的 jar 包，不需要可以删除
     * HwPush_SDK_V2705_nomap.jar:华为推送的jar包，不需要可以删除，考虑到demo里使用到了百度map，会和华为推送里的冲突，这里的jar包移除了map模块，需要完整jar包的开发者可以去华为推送官网下载
     * org.apache.http.legacy.jar：Android6.0 中默认移除了 httpclient 的代码，用这个库兼容，建议不要删除，否则在 6.0 系统中，SDK 会有问题
     * compile 'com.android.support:multidex:1.0.1'：APP 总方法数超过 64k 时，需要用这个库做分包处理，可以删除
     * compile 'com.github.bumptech.glide:glide:3.5.2':glide 图片处理工具
     *
     * */

    /**
     * 共享库说明：
     * 文件夹：arm64-v8a;armeabi;x86
     * 文件：libhyphenate.so；libhyphenate_av.so；libhyphenate_av_recorder.so；libsqlite.so；
     * */


    /**
     * APP 打包混淆
     * 在 ProGuard 文件中加入以下 keep。
     * -keep class com.hyphenate.** {*;}
     * -dontwarn  com.hyphenate.**
     * */

    /**
     * 按住说话，发送语音：
     * <p>
     * return voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderCallback() {
     *
     * @Override public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
     * sendVoiceMessage(voiceFilePath, voiceTimeLength);
     * }
     * });
     */
    protected void sendVoiceMessage(String filePath, int length) {
        //创建一个发送语音的聊天
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, "发送目标人id");
        sendMessage(message);
    }

    protected void sendMessage(EMMessage message) {
        if (message == null) {
            return;
        }
        if (chatFragmentHelper != null) {
            //set extension
            chatFragmentHelper.onSetMessageAttributes(message);
        }
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            message.setChatType(EMMessage.ChatType.GroupChat);
        } else if (chatType == EaseConstant.CHATTYPE_CHATROOM) {
            message.setChatType(EMMessage.ChatType.ChatRoom);
        }
        //send message
        EMClient.getInstance().chatManager().sendMessage(message);
        //refresh ui
        if (isMessageListInited) {
            messageList.refreshSelectLast();
        }
    }

    /**
     * 发送语音的方法
     * on speak button touched
     *
     * @param v
     * @param event
     */
    public boolean onPressToSpeakBtnTouch(View v, MotionEvent event, EaseVoiceRecorderCallback recorderCallback) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                try {
                    if (EaseChatRowVoicePlayClickListener.isPlaying)
                        EaseChatRowVoicePlayClickListener.currentPlayListener.stopPlayVoice();
                    v.setPressed(true);
                    startRecording();
                } catch (Exception e) {
                    v.setPressed(false);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() < 0) {
                    showReleaseToCancelHint();
                } else {
                    showMoveUpToCancelHint();
                }
                return true;
            case MotionEvent.ACTION_UP:
                v.setPressed(false);
                if (event.getY() < 0) {
                    // discard the recorded audio.
                    discardRecording();
                } else {
                    // stop recording and send voice file
                    try {
                        int length = stopRecoding();
                        if (length > 0) {
                            if (recorderCallback != null) {
                                recorderCallback.onVoiceRecordComplete(getVoiceFilePath(), length);
                            }
                        } else if (length == EMError.FILE_INVALID) {
                            Toast.makeText(context, R.string.Recording_without_permission, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, R.string.The_recording_time_is_too_short, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, R.string.send_failure_please, Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            default:
                discardRecording();
                return false;
        }
    }


}
