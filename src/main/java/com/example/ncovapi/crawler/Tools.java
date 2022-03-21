package com.example.ncovapi.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * @author Ticsmyc
 * @package fun.ticsmyc.crawler
 * @date 2020-01-26 18:03
 */
public class Tools {
    public static Document page;
    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    /**
     * 正则匹配获取
     * @param regex
     * @param attributeKey
     * @param attributeValue
     * @return
     */
    public static String getInformation(String regex , String attributeKey, String attributeValue){
        String result=null;
        //表达式对象
        Pattern p = Pattern.compile(regex);
        //创建Matcher对象
        Elements timelineService = page.getElementsByAttributeValue(attributeKey,attributeValue);

        Matcher m = p.matcher(timelineService.toString());
        if(m.find()) {  //该方法扫描输入的序列，查找与该模式匹配的一个子序列
            result=m.group();
        }
        return result;
    }

    /**
     * htmlunit执行javascript，再将页面解析为xml获取到Jsoup的document对象
     * @param url
     * @return
     */
    public static void getPageByJSoup(String url) {
        try {
            //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置
            WebClient browser = new WebClient();
            browser.getOptions().setThrowExceptionOnScriptError(false);
            browser.getOptions().setCssEnabled(false);
            browser.getOptions().setJavaScriptEnabled(true);
            browser.getOptions().setThrowExceptionOnScriptError(false);

            //获取页面
            HtmlPage htmlPage = browser.getPage(url);
            //设置等待js的加载时间
            browser.waitForBackgroundJavaScript(1500);//15s

            //使用xml的方式解析获取到jsoup的document对象
            page = Jsoup.parse(htmlPage.asXml());

        } catch (IOException e) {
            logger.error("jsoup获取页面失败");
        }

    }

}
