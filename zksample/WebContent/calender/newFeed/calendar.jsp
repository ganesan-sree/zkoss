<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calendar</title>
</head>
<body>
<z:page>
<z:init class="org.zkoss.zkplus.databind.AnnotateDataBinderInit"/>
<z:component  name="CreateDialog" macroURI="createEntry.zul"/>
<z:component  name="UpdateDialog" macroURI="updateEntry.zul"/>

<z:window id="win" border="none" apply="com.calender.newsfeed.controllers.NewsController" height="100%" width="100%">

	
				<z:hlayout sclass="z-valign-middle" height="4%" >
				<z:button id="today" label="Today" />
				<z:button id="Prev" image="/calender/widgets/application/zk_calendar/img/arrow-180.png"/>
				<z:button id="Next" image="/calender/widgets/application/zk_calendar/img/arrow.png"/>
				<z:separator width="50px" />
				<z:button id="pageDay" label="Day" width="55px" />
				<z:button id="pageWeek" label="Week" width="55px"/>
				<z:button id="pageMonth" label="Month" width="55px"/>
				<z:separator width="50px" />
				Filter :
				<z:textbox id="filter"/>
				<z:button id="applyFilter" label="Apply"/>
				<z:button id="resetFilter" label="Reset"/>
			</z:hlayout>
			
			<center>
				<z:calendars firstDayOfWeek="Sunday" timeZone="Main=GMT+5.5"  model="@{win$composer.getCalendarModel}"
					 mold="month" id="cal" height="400px" >
				</z:calendars>
			</center>
			
	<z:south title="South" maxsize="200" size="300" collapsible="true" open="false">
		<z:chart id="piechart" title="What type of News?" width="400px" height="200px" type="pie" model="@{win$composer.getSimplePieModel}"/>
	</z:south>
		
		

	</z:window>
</z:page>
</body>
</html>