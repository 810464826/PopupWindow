package com.work.chenxb.mynetdemo.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.work.chenxb.mynetdemo.bean.NewsBean;
import com.work.chenxb.mynetdemo.service.ParserXml;

import java.io.InputStream;
import java.util.List;

/**
 * 测试解析Xml文件
 * 作者：ChenXb on 2016/8/29.16:48
 * 邮箱：810464826@qq.com
 */
public class TestParserXml extends AndroidTestCase {

    public void testParserXmlFromStream() throws Exception {
        // 获取资产目录里面的数据
        InputStream is = getContext().getAssets().open("news.xml");

        List<NewsBean> newsList = ParserXml.parserXmlFromStream(is);
        for (NewsBean bean : newsList) {
            Log.e("TAG","XML解析的数据"+bean.toString());
        }
    }
}