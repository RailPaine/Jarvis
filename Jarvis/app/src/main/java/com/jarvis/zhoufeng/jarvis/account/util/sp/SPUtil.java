package com.jarvis.zhoufeng.jarvis.account.util.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Mypc on 2017/7/16 0016.
 */

public class SPUtil {
    private SPUtil() {
    }

    public static SPUtil getSPUtil() {
        return SPUtilHolder.spUtil;
    }

    private static class SPUtilHolder {
        private static SPUtil spUtil = new SPUtil();
    }

    public void init(Context context) {
        mContext = context;
        sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private static final String FILE_NAME = "sp_data";
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public void setNormalData(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getNormalData(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public void setObjectData(String key, Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(obj);
            String value = new String(android.util.Base64.encode(out.toByteArray(), android.util.Base64.DEFAULT));
            Log.e("objectString", value);
            editor.putString(key, value);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObjectData(String key) {

        String value = sp.getString(key, null);

        if (value != null) {
            Log.e("objectString", value);
            byte[] valueBytes = android.util.Base64.decode(value, android.util.Base64.DEFAULT);
            ByteArrayInputStream bin = new ByteArrayInputStream(valueBytes);
            try {
                ObjectInputStream oin = new ObjectInputStream(bin);
                return oin.readObject();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }


}
