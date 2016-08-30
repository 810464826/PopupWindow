package com.work.chenxb.mynetdemo.service;

import android.util.Xml;

import com.work.chenxb.mynetdemo.bean.NewsBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析Xml文件
 * 作者：ChenXb on 2016/8/29.16:46
 * 邮箱：810464826@qq.com
 */
public class ParserXml {
    /**
     * 解析Xml文件的工具类
     *
     * @param is
     *            输入流
     * @return List<NewsBean>新闻数据，null表示失败
     */
    public static List<NewsBean> parserXmlFromStream(InputStream is) {
        List<NewsBean> newsList = new ArrayList<NewsBean>();
        try {
            // 1. 初始化Xml解析器
            XmlPullParser parser = Xml.newPullParser();
            // 2. 设置输入流
            parser.setInput(is, "utf-8");
            // 3. 解析数据
            int type = parser.getEventType();
            NewsBean bean = null;
            while (type != XmlPullParser.END_DOCUMENT) {
                // 获取标签名
                String tag = parser.getName();
                switch (type) {
                    case XmlPullParser.START_TAG:// 开始标签
                        if ("item".equals(tag)) {
                            bean = new NewsBean();
                        } else if ("title".equals(tag)) {// 新闻标题标签
                            String title = parser.nextText();
                            bean.setTitle(title);
                            System.out.println(title);
                        } else if ("des".equals(tag)) {// 新闻描述标签
                            String des = parser.nextText();
                            bean.setDes(des);
                            System.out.println(des);
                        } else if ("image".equals(tag)) {// 新闻图片的Url标签
                            String imageUrl = parser.nextText();
                            bean.setImage(imageUrl);
                            System.out.println(imageUrl);
                        } else if ("comment".equals(tag)) {// 新闻评论标签
                            String comment = parser.nextText();
                            bean.setComment(comment);
                            System.out.println(comment);
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束标签
                        if ("item".equals(tag)) {
                            newsList.add(bean);
                        }
                        break;

                    default:
                        break;
                }
                // 解析下一次事件，把游标下移一步
                type = parser.next();
            }
            // 返回所有的新闻数据
            return newsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

