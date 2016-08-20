package com.work.chenxb.popuwindowdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 *  自定义PopupWindow ， 实现仿QQ空间分享效果
 * 作者：ChenXb on 2016/8/20.22:44
 * 邮箱：810464826@qq.com
 */
public class SelectPopupWindow extends PopupWindow {

    //一个LinearLayout 表示一个可选操作
    private LinearLayout fp_hide_all,fp_hide_pic,fp_report,fp_linear_sharetoWeixin,fp_linear_sharetoquan,fp_linear_sharetoQzone,fp_linear_sharetoQQ;
    //popupWindow 取消文本按钮
    private TextView fp_cancel;
    private View mMenuView;
    public SelectPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow, null);

        fp_hide_pic = (LinearLayout) mMenuView.findViewById(R.id.fp_hide_pic);
        fp_hide_all = (LinearLayout) mMenuView.findViewById(R.id.fp_hide_all);
        fp_report = (LinearLayout) mMenuView.findViewById(R.id.fp_report);
        fp_linear_sharetoWeixin = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoWeixin);
        fp_linear_sharetoquan = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoquan);
        fp_linear_sharetoQzone = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoQzone);
        fp_linear_sharetoQQ = (LinearLayout) mMenuView.findViewById(R.id.fp_linear_sharetoQQ);
        fp_cancel = (TextView) mMenuView.findViewById(R.id.fp_cancel);
        //点击取消按钮，关闭popupWindow
        fp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        fp_hide_pic.setOnClickListener(itemsOnClick);
        fp_hide_all.setOnClickListener(itemsOnClick);
        fp_report.setOnClickListener(itemsOnClick);
        fp_linear_sharetoWeixin.setOnClickListener(itemsOnClick);
        fp_linear_sharetoquan.setOnClickListener(itemsOnClick);
        fp_linear_sharetoQzone.setOnClickListener(itemsOnClick);
        fp_linear_sharetoQQ.setOnClickListener(itemsOnClick);

        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        //点击popupWindow之外的部分  关闭popupWindow
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.fp_linear_share).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
    // 可自主添加其他功能需求方法
}
