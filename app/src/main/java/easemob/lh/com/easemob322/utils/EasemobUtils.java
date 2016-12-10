package easemob.lh.com.easemob322.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by LuHao on 2016/12/10.
 * 聊天工具类
 */

public class EasemobUtils {

    /**
     * check the application process name if process name is not qualified, then we think it is a service process and we will not init SDK
     * 检查应用程序进程名称如果进程名称不合格,那么我们认为这是一个服务的过程,我们不会init SDK;
     * @param pID
     * @return
     */
    public static String getAppName(Context appContext,int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = appContext.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

}
