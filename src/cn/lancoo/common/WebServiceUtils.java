package cn.lancoo.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebServiceUtils {
    private WebServiceUtils(){}

    public static Document getContent(String callLocation,String callMethod,String guid){

        InputStream is = null;
        HttpClient client = new HttpClient();
        Document document = null;
        PostMethod method = new PostMethod(callLocation+"/"+callMethod);
        // PostMethod method = new PostMethod("www.xxx.com/WeatherWSS/Weather.asmx/GetCityForecastByZIP");
        //需要传递参数时，设置消息头
        // method.setRequestHeader("Host", "www.xxx.com");
        // method.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        //需要传递的参数名ZIP，值为90001
        method.setParameter("guids", guid);
        try {
            client.executeMethod(method);
            is = method.getResponseBodyAsStream();
            document = Jsoup.parse(is, "UTF-8", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return document;
    }
}
