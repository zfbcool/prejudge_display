<%--
  Created by IntelliJ IDEA.
  User: zfbco
  Date: 2020/11/12
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>显示试题ID</title>
</head>
<body>
<h3>当日试题ID</h3><br>
<c:forEach items="${sessionScope.quesIDs}" var="quesID">
    <ul>
        <li><p>
           <a href="/resultdisplay/displayQuestion?quesID=${quesID}" > ${quesID} </a>
        </p></li>
    </ul>
</c:forEach>
</body>
</html>
