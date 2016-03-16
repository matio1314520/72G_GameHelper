package matio.angel.com.myapplication_gamehelper.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import java.util.List;

import matio.angel.com.myapplication_gamehelper.common.OtherConstant;
import matio.angel.com.myapplication_gamehelper.fragment.FirstPresentFragment;

/**
 * Created by Angel on 2016/1/23.
 * 一键分享工具类
 */
public class ShareUtils {

    /**
     * 判断是否安装该应用
     *
     * @param context      上下文对象
     * @param pakageName   包名
     * @param activityName 类名
     * @return
     */
    public static boolean isInstalled(Context context, String pakageName, String activityName) {
        Intent intent = new Intent();
        intent.setClassName(pakageName, activityName);
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 去QQ
     *
     * @param context
     */
    public static void toQq(Context context) {
        if (isInstalled(context, OtherConstant.qqFriPackageName, OtherConstant.qqFriActivityName)) {
            Intent qqIntent = new Intent();
            qqIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            qqIntent.putExtra("Kdescription", "ssss");
            qqIntent.setType("image/*");
            qqIntent.setClassName(OtherConstant.qqFriPackageName, OtherConstant.qqFriActivityName);
            context.startActivity(qqIntent);
        } else {
            Toast.makeText(context, "您还没有安装QQ", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 去微信
     */
    public static void toWechat(Context context) {
        if (isInstalled(context, OtherConstant.tencentPackageName, OtherConstant.tencentActivityName)) {
            Intent wechatIntent = new Intent();
            wechatIntent.setAction(Intent.ACTION_SEND);
            wechatIntent.putExtra(Intent.EXTRA_TEXT, "s");
            wechatIntent.setType("text/plain");
            wechatIntent.setClassName(OtherConstant.tencentPackageName, OtherConstant.tencentActivityName);
            context.startActivity(wechatIntent);
        } else {
            Toast.makeText(context, "请先安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 去新浪微博
     *
     * @param context
     */
    public static void toSina(Context context) {
        if (isInstalled(context, OtherConstant.sinaPackageName, OtherConstant.sinaActivityName)) {
            Intent sinaIntent = new Intent();
            sinaIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            sinaIntent.putExtra("Kdescription", "ssss");
            sinaIntent.setType("image/*");
            sinaIntent.setClassName(OtherConstant.sinaPackageName, OtherConstant.sinaActivityName);
            context.startActivity(sinaIntent);
        } else {
            Toast.makeText(context, "请先安装新浪微博", Toast.LENGTH_SHORT).show();
        }
    }

}
