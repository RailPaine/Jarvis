package com.jarvis.zhoufeng.jarvis.account.dev;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zhoufeng on 2017/7/27.
 */

public class CustomViewManager {
    //上下文
    private Context mContext;
    //本类实例
    private static CustomViewManager instance;
    //自定义的FloatView
    private FloatView mFloatView;
    //窗口管理类
    private WindowManager mWindowManager;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    private boolean isControlViewShowing = false;
    private boolean initViewPlace = false;
    WindowManager.LayoutParams parmas = new WindowManager.LayoutParams();
    private CustomViewManager(Context context) {
        this.mContext = context;
        mFloatView = new FloatView(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * @param
     * @description 通过单例模式获取实例对象
     * @author ldm
     * @time 2016/8/17 11:59
     */
    public static CustomViewManager getInstance(Context mContext) {
        if (null == instance) {
            synchronized (CustomViewManager.class) {
                if (null == instance) {
                    instance = new CustomViewManager(mContext);
                }
            }
        }
        return instance;
    }

    /**
     * @param
     * @description 在手机屏幕上显示自定义的FloatView
     * @author ldm
     * @time 2016/8/17 13:47
     */
    public void showFloatViewOnWindow() {

        parmas.width = mFloatView.getFloatWidth();
        parmas.height = mFloatView.getFloatHeight();
        //窗口图案放置位置
        parmas.gravity = Gravity.LEFT | Gravity.CENTER;
        // 如果忽略gravity属性，那么它表示窗口的绝对X位置。
        parmas.x = 0;
        //如果忽略gravity属性，那么它表示窗口的绝对Y位置。
        parmas.y = 0;
        ////电话窗口。它用于电话交互（特别是呼入）。它置于所有应用程序之上，状态栏之下。
        parmas.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //FLAG_NOT_FOCUSABLE让window不能获得焦点，这样用户快就不能向该window发送按键事件及按钮事件
        //FLAG_NOT_TOUCH_MODAL即使在该window在可获得焦点情况下，仍然把该window之外的任何event发送到该window之后的其他window.
        parmas.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 期望的位图格式。默认为不透明。参考android.graphics.PixelFormat。
        parmas.format = PixelFormat.RGBA_8888;
        mWindowManager.addView(mFloatView, parmas);
    }

    private void initEvent() {
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!initViewPlace) {
                            initViewPlace = true;
                            //获取初始位置
                            mTouchStartX += (event.getRawX() - parmas.x);
                            mTouchStartY += (event.getRawY() - parmas.y);
                        } else {
                            //根据上次手指离开的位置与此次点击的位置进行初始位置微调
                            mTouchStartX += (event.getRawX() - x);
                            mTouchStartY += (event.getRawY() - y);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取相对屏幕的坐标，以屏幕左上角为原点
                        x = event.getRawX();
                        y = event.getRawY();
                        updateViewPosition();
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 更新浮动窗口位置
     */
    private void updateViewPosition() {
        parmas.x = (int) (x - mTouchStartX);
        parmas.y = (int) (y - mTouchStartY);
        mWindowManager.updateViewLayout(mFloatView, parmas);
    }
}