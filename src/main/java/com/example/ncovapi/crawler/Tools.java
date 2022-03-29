package com.example.ncovapi.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
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


public class Tools {
    public static Document page;
    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    static {
        try {
            //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置

            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.setCssErrorHandler(new SilentCssErrorHandler());
            webClient.waitForBackgroundJavaScript(1500); //15s
        } catch (Exception e) {
            logger.error("htmlunit webClient 初始化失败");
        }
    }
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
            //获取页面
            HtmlPage htmlPage = webClient.getPage(url);
            //使用xml的方式解析获取到jsoup的document对象
            page = Jsoup.parse(htmlPage.asXml());
        } catch (IOException e) {
            logger.error("jsoup获取页面失败");
        } finally {
            webClient.close();
        }

    }

}
