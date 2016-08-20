package com.work.chenxb.popuwindowdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 自定义PopupWindow
    private SelectPopupWindow feedSelectPopupWindow;
    // 界面父容器
    private RelativeLayout relativeLayout;
    // 打开popupWindow的按钮
    private ImageButton btn_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainlayout);
        btn_more = (ImageButton) findViewById(R.id.btn_more);
        btn_more.setOnClickListener(this);
    }

    // popupWindow 点击事件监听
    private View.OnClickListener selectItemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                //根据popupWindow 布局文件中的id 来执行相应的点击事件
                case R.id.fp_linear_sharetoWeixin:
                    Toast.makeText(MainActivity.this,"点击了微信分享",Toast.LENGTH_SHORT).show();
                    break;
                // ....
            }
            //每次点击popupWindow中的任意按钮，记得关闭此popupWindow，
            feedSelectPopupWindow.dismiss();
        }
    };

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击分享按钮，弹出PopupWindow
            case R.id.btn_more:
                feedSelectPopupWindow = new SelectPopupWindow(this, selectItemsOnClick);
                // 设置popupWindow显示的位置
                // 此时设在界面底部并且水平居中
                feedSelectPopupWindow.showAtLocation(relativeLayout,
                        Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                // 当popupWindow 出现的时候 屏幕的透明度  ，设为0.5 即半透明 灰色效果
                backgroundAlpha(0.5f);
                // 设置popupWindow取消的点击事件，即popupWindow消失后，屏幕的透明度，全透明，就回复原状态
                feedSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1f);
                    }
                });
                break;
        }
    }
}
