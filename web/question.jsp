<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zfbco
  Date: 2020/11/11
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>试题信息</title>
</head>
<body>
    <font size="2"><%=(String) session.getAttribute("questionContent")%></font>

    <p>预判结果：<br/>
    <form action="/resultdisplay/check" method="get">
        <c:forEach items="${sessionScope.allInfos}" var="infoEntry">
        <ul><li>

        <p><strong>${infoEntry.getKey()}:</strong>

        <c:forEach items="${infoEntry.getValue()}" var="info">


            <c:choose>
                <c:when test="${info.getPrejudgeResult().getValue()==0 }">
                    <font color="green">${info.getAnswer()}</font><font color="green" size="1">(${info.getRatio()})</font>;&nbsp &nbsp
                </c:when>
                <c:when test="${sessionScope.checks!=null && sessionScope.checks.contains(info.getAnswer())}">
                    <font color="green">${info.getAnswer()}</font><font color="green" size="1">(${info.getRatio()})</font>;&nbsp &nbsp
                </c:when>
                <c:otherwise>
                    <input type="checkbox" value="${info.getAnswer()}" name="${infoEntry.getKey()}"><font color="blue" >${info.getAnswer()}</font><font color="blue" size="1">(${info.getRatio()})</font>;&nbsp &nbsp
                </c:otherwise>
            </c:choose>

        </c:forEach>
        </p></li>
    </ul>
    </c:forEach>
    <input type="submit" name="提交">
    </form>

    </p>


</body>
</html>
