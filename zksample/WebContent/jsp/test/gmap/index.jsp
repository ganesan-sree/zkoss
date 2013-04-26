<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page import="org.zkoss.jspdemo.model.*" %>
<%@ page import="org.zkoss.jspdemo.bean.*" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:init use="org.zkoss.zkplus.databind.AnnotateDataBinderInit"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Use ZK JSP to show Google Map features</title>
</head>
<body>
<h1>test gmap</h1>
<% 
	RestaurantRepository respository = new RestaurantRepository(session);
	session.setAttribute("respository", respository);
%>

<z:page id="gmapShow">
	<div style="background:#E0E0E0" height="100%">
		<%-- the following javascript can NOT be loaded dynamically
		Note: gmapsKey is generated by org.zkoss.jspdemo.ui.GmapMainWindow
		if the server matches one of the predefined keys.--%>
		<z:script use="org.zkoss.jspdemo.ui.GmapImportScript"/>
		
		<z:gmaps id="mymap" width="1024px" height="768px" 
			showSmallCtrl="true" showTypeCtrl="true"  zoom="14"
			lng="@{current.location.longitude}" 
			lat="@{current.location.latitude}">
			<z:attribute name="lng">121.55298871046938</z:attribute>
			<z:attribute name="lat">25.042063183495276</z:attribute>
			<z:attribute name="onMapClick">
				Gmarker gmarker = event.getGmarker();
				if (gmarker != null) {
					gmarker.setOpen(true);
				}
			</z:attribute>
			<z:ginfo id="myinfo" open="true"  lat="25.042063183495276" lng="121.55297871035938">
				<z:attribute name="content">
					Hello, <a href="http://www.zkoss.org">ZK</a>.
				</z:attribute>
			</z:ginfo>
			
			<%-- ***** preprocessing generate gmarkers ***** --%>
			<c:forEach var="restaurant" items="${respository.all}">
				<z:gmarker  draggable="true"
					lat="${restaurant.location.latitude}" 
					lng="${restaurant.location.longitude}">
					<z:attribute name="content">
						${restaurant.name}<br/> 
						${restaurant.description}<br/> 
						${restaurant.location.address}
					</z:attribute>
				</z:gmarker>
			</c:forEach>
				
		</z:gmaps>
	</div>


					

</z:page>
</body>
</html>