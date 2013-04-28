<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<z:page>
<%-- <z:window apply="com.test.controller.FormController"> --%>
<form id="f" name="f" action="/zksample/disp/login" method="POST"
    xmlns:html="native">
    
    <z:textbox id="titleTextbox" name="titleTextbox"	constraint="no empty" />

<input type="submit" value="subb"/>
<z:button type="submit" label="Accedi_os" />	</form>
<%-- </z:window> --%>
</z:page>






</body>
</html>