<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="zk" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<zk:page>
<zk:window title="table and grid in window" border="normal" width="300px">
    <table border="1">
        <c:forEach var="i" begin="1" end="3" step="1" varStatus="status">
            <tr>
                <td>Item ${i}</td>
            </tr>
        </c:forEach>
    </table>
    <zk:grid>
        <zk:columns>
            <zk:column label="column" sort="auto" />
        </zk:columns>
        <zk:rows>
            <c:forEach var="i" begin="1" end="3" step="1" varStatus="status">
                <zk:row>
                    <zk:label value="Item ${i}" />
                </zk:row>
            </c:forEach>
        </zk:rows>
    </zk:grid>
</zk:window>
</zk:page>
</body>
</html>