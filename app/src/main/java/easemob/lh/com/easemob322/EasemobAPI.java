package easemob.lh.com.easemob322;

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


}
