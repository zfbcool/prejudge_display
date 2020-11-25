package cn.lancoo.bo;

import oracle.jrockit.jfr.events.RequestableEventEnvironment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "CheckServlet",urlPatterns = "/check")
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        HashSet<String> checks=(HashSet<String>) session.getAttribute("checks");
        if(checks==null){
            checks = new HashSet<>();
        }

        Set<Integer> keys = ((Map)session.getAttribute("allInfos")).keySet();
        if(keys!=null){
            for (int key: keys) {
                String[] values = request.getParameterValues(Integer.toString(key));
                if(values!=null){
                    System.out.print(key+":");
                    for (String value:values
                    ) {
                        checks.add(value);
                        System.out.print(value+"  ");
                    }
                }
                System.out.println();
            }
            session.setAttribute("checks",checks);
            //response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");


            //response.sendRedirect("http://localhost:8080/resultdisplay/question.jsp");
            request.getRequestDispatcher("/question.jsp").forward(request,response);
        }

        //request.getRequestDispatcher("/question.jsp").forward(request,response);

    }
}
