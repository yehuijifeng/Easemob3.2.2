package easemob.lh.com.easemob322.application;

import android.app.Application;

import easemob.lh.com.easemob322.helper.EasemobHelper;

/**
 * Created by LuHao on 2016/12/10.
 * 环信的application
 */

public class EasemobApplication extends Application {

    private static EasemobApplication easemobApplication;

    public static EasemobApplication getInstance() {
        return easemobApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (easemobApplication == null)
            easemobApplication = this;
        initEasemob();
    }

    /**
     * 要求在 application 的oncreate方法中做初始化，初始化的时候需要传入设置好的 options。
     */
    private void initEasemob() {
        EasemobHelper.initEMOptions(this);
    }
}
