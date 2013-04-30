package com.calender.newsfeed.controllers;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Chart;
import org.zkoss.zul.Window;

import com.calender.newsfeed.models.DatabaseCalendarModel;
import com.calender.newsfeed.models.NewsItem;

public class NewsController extends GenericForwardComposer {
	
	Window win;
	Calendars cal;
	Chart piechart;
	
	Window creationDialog = null;
	Window updateDialog = null;
	
	public SimpleCalendarModel getCalendarModel() {
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();
		piechart.setModel(dcm.getSimplePieModel());
		return dcm.getSimpleCalendarModel();
	}
	
	public void onEventCreate$cal(ForwardEvent event) {
		CalendarsEvent evt = (CalendarsEvent) event.getOrigin();
		
		int left = evt.getX();
		int top = evt.getY();
		
		
		if (top + 245 > evt.getDesktopHeight())
			top = evt.getDesktopHeight() - 245;
		if (left + 410 > evt.getDesktopWidth())
			left = evt.getDesktopWidth() - 410;
		
		if(creationDialog == null)
		{		
			creationDialog = (Window)Executions.createComponents("createEntry.zul", win, null);
		}
		
		EventCreationController c = (EventCreationController)creationDialog.getAttribute("createMyEntry$composer", false);
		c.prepareWindow(left, top);
		
		creationDialog.setAttribute("calevent", evt);
		creationDialog.setVisible(true);
		
		evt.stopClearGhost();
	}
	
	public void onEventEdit$cal(ForwardEvent event) {
		CalendarsEvent evt = (CalendarsEvent) event.getOrigin();
		
		int left = evt.getX();
		int top = evt.getY();
		
		
		if (top + 245 > evt.getDesktopHeight())
			top = evt.getDesktopHeight() - 245;
		if (left + 410 > evt.getDesktopWidth())
			left = evt.getDesktopWidth() - 410;
		
		NewsItem ni = (NewsItem)evt.getCalendarEvent();
		
		if(updateDialog == null)
		{		
			updateDialog = (Window)Executions.createComponents("updateEntry.zul", win, null);
		}
		
		EventUpdateController c = (EventUpdateController)updateDialog.getAttribute("updateMyEntry$composer", false);
		c.prepareWindow(left, top, ni);
				
		
		updateDialog.setAttribute("calevent", evt);
		updateDialog.setAttribute("ni", ni);
		updateDialog.setVisible(true);
		
		evt.stopClearGhost();
	}
	
	public void onEventUpdate$cal(ForwardEvent event) {
		CalendarsEvent evt = (CalendarsEvent)event.getOrigin();
		NewsItem ni = (NewsItem)evt.getCalendarEvent();
		
		ni.setBeginDate(evt.getBeginDate());
		ni.setEndDate(evt.getEndDate());
		
		DatabaseCalendarModel.dao.updateNewsItem(ni);
		DatabaseCalendarModel dm = new DatabaseCalendarModel();
		cal.setModel(dm.getSimpleCalendarModel());
	}
}
