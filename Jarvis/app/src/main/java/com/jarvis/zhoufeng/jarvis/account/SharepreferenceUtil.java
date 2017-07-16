package com.jarvis.zhoufeng.jarvis.account;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mypc on 2017/7/16 0016.
 */

public class SharepreferenceUtil {
    public static SharedPreferences.Editor Save(Context context, String name, int mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        return editor;
    }

    public static SharedPreferences get(Context context, String name, int mode) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp;
    }
}
