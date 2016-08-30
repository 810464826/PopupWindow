package com.work.chenxb.mynetdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.work.chenxb.mynetdemo.bean.NewsBean;
import com.work.chenxb.mynetdemo.image.SmartImageView;
import com.work.chenxb.mynetdemo.service.ParserXml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static final int MSG_SUCC = 0;
    protected static final int MSG_ERR = 1;
    private List<NewsBean> newsList;
    NewsAdapter adapter;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        newsList=new ArrayList<>();
        adapter=new NewsAdapter(this);
        // 获取资产目录里面的数据
        try {
            InputStream is = this.getAssets().open("news.xml");
            newsList= ParserXml.parserXmlFromStream(is);
            adapter.setList(newsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lv.setAdapter(adapter);
//        requestNet();
    }

//    Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            lv.setAdapter(new NewsAdapter());
//        }
//    };

    class NewsAdapter extends BaseAdapter {
        Context context;
        List<NewsBean> list;

        public NewsAdapter(Context context) {
            this.context = context;
            list=new ArrayList<>();
        }

        public List<NewsBean> getList() {
            return list;
        }

        public void setList(List<NewsBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
//            return newsList == null ? 0 : newsList.size();
            return list==null ? 0 : list.size();
        }

        @Override
        public NewsBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 这里暂时没有优化
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            // 1. 用布局填充器填充一个Xml布局
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.adapter_news_item, null);
            }else {
                view = convertView;
            }
            // 2. 找到控件
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvDes = (TextView) view.findViewById(R.id.tv_des);
            TextView tvComment = (TextView) view.findViewById(R.id.tv_comment);
            SmartImageView ivPic = (SmartImageView) view.findViewById(R.id.iv_logo);

            // 3. 给控件设置数据
            NewsBean bean = newsList.get(position);
            tvTitle.setText(bean.getTitle());
            tvDes.setText(bean.getDes());
            tvComment.setText(bean.getComment());
            ivPic.setImageUrl(bean.getImage());
            // 4. 返给listview布局文件的view对象
            return view;
        }

    }



    /**
     * 1. 在子线程中进行网络通讯
     */
//    private void requestNet() {
//        // 开启子线程
//        new Thread() {
//            public void run() {
//                newsList = NetWorkUtil.getNetData(MainActivity.this);
//                if (newsList == null) {
//                    Message msg = Message.obtain();
//                    msg.what = MSG_ERR;
//                    mHandler.sendMessage(msg);
//                } else {
//                    Message msg = Message.obtain();
//                    msg.what = MSG_SUCC;
//                    mHandler.sendMessage(msg);
//                }
//            }
//        }.start();
//    }

}
