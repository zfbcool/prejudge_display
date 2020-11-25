package cn.lancoo.bo;

import cn.lancoo.common.WebServiceUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QuestionInfo {
    private String url="http://116.63.62.136:10106/WS_ResourceCloudPrim/SearchResource.asmx";
    private String callMethod="WS_Resource_GetQuesPropByGUID";

    public String DownloadInfo(String quesID){
        Document content = WebServiceUtils.getContent(url, callMethod,quesID);
        Element body = content.body();
        Elements quesOptionAsks = body.getElementsByTag("QuesOptionAsk ");
        Element quesOptionAsk = quesOptionAsks.get(0);
        String question = quesOptionAsk.text();
        String[] quesArr=question.split("____");
        StringBuilder sb=new StringBuilder();
        int count=1;
        for (String s:quesArr) {
            if(count!=quesArr.length){
                sb.append(s).append("__"+count+"__");
                count++;
            }else {
                sb.append(s);
            }
        }

        return sb.toString();
    }
}
