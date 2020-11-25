package cn.lancoo.bo;

import cn.lancoo.common.WebServiceUtils;
import cn.lancoo.dao.FileInfoDAO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(name = "QuesIDServlet",urlPatterns = "/displayQuestionIDs")
public class QuesIDServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileInfoDAO fileInfoDAO = new FileInfoDAO();
        List<String> questionIDs = fileInfoDAO.listQuestionIDs();
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        session.setAttribute("quesIDs",questionIDs);

        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("questionIDs.jsp").forward(request,response);

    }
}
