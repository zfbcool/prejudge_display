package cn.lancoo.bo;

import cn.lancoo.common.WebServiceUtils;
import cn.lancoo.dto.PrejudgeInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QuestionInfoServlet",urlPatterns = "/displayQuestion")
public class QuestionInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String quesID=request.getParameter("quesID");
        QuestionInfo quesInfo=new QuestionInfo();
        PrejudgeInfoHandler prejudgeInfoHandler=new PrejudgeInfoHandler(quesID);

        String questionContent=quesInfo.DownloadInfo(quesID);
        Map<Integer, List<PrejudgeInfo>> allInfos = prejudgeInfoHandler.getAllInfos();

        request.getSession().setAttribute("questionContent",questionContent);

        request.getSession().setAttribute("allInfos",allInfos);
        //response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        request.getRequestDispatcher("question.jsp").forward(request,response);
    }
}
