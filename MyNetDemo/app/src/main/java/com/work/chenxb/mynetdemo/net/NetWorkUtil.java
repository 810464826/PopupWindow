package com.work.chenxb.mynetdemo.net;

import android.content.Context;

import com.work.chenxb.mynetdemo.R;
import com.work.chenxb.mynetdemo.bean.NewsBean;
import com.work.chenxb.mynetdemo.service.ParserXml;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 网络通信，获取服务器的数据
 * 作者：ChenXb on 2016/8/29.16:43
 * 邮箱：810464826@qq.com
 */
public class NetWorkUtil {
    /**
     *  获取服务器的新闻数据
     * @return
     * 		 List<NewsBean>所有的新闻条目，null表示访问网络失败
     */
    public static List<NewsBean> getNetData(Context context){
        try {
            String path = context.getString(R.string.serverip);
            // 1. 写一个Url
            URL url = new URL(path);
            // 2. 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3. 设置请求方式和参数
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            // 4. 获取返回码
            int code = conn.getResponseCode();
            System.out.println("code:"+code);
            if (code == 200) {
                // 5. 获取服务器返回的输入流
                InputStream is = conn.getInputStream();
                //解析Xml文件
                return ParserXml.parserXmlFromStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
