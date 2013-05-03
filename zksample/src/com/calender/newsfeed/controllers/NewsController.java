package com.calender.newsfeed.controllers;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Chart;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;

import com.calender.newsfeed.models.DatabaseCalendarModel;
import com.calender.newsfeed.models.NewsItem;

public class NewsController extends GenericForwardComposer {//extends SelectorComposer<Component>
	
	Window win;
	
	Calendars cal;
	Chart piechart;
	
	Window creationDialog = null;
	Window updateDialog = null;
	
	public SimpleCalendarModel getCalendarModel() {
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();		
		return dcm.getSimpleCalendarModel();
	}
	
	public SimplePieModel getSimplePieModel() {		
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();
		return dcm.getSimplePieModel();
	}
	
	public void onEventCreate$cal(ForwardEvent event) {
		CalendarsEvent evt = (CalendarsEvent) event.getOrigin();
		
		int left = evt.getX();
		int top = evt.getY();
		
		
		if (top + 245 > evt.getDesktopHeight())
			top = evt.getDesktopHeight() - 245;
		if (left + 310 > evt.getDesktopWidth())
			left = evt.getDesktopWidth() - 310;
		
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
		if (left + 310 > evt.getDesktopWidth())
			left = evt.getDesktopWidth() - 310;
		
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
	
	
	
	
	
	/**
	 * 
	 * @param event
	 */
	
	public void onClick$pageMonth(ForwardEvent event) {
		System.out.println("Month!!!!!!!!!!!!!!!!!");
		cal.setMold("month");
		cal.setDays(30);
	}
	
	
	public void onClick$pageWeek(ForwardEvent event) {
		System.out.println("Week!!!!!!!!!!!!!!!!!");
		cal.setMold("default");
		cal.setDays(7);
	}
	
	public void onClick$pageDay(ForwardEvent event) {
		System.out.println("Day!!!!!!!!!!!!!!!!!");
		cal.setMold("default");
		cal.setDays(1);
	}
	public void onClick$today(ForwardEvent event) {
		System.out.println("Today!!!!!!!!!!!!!!!!!");
		cal.setMold("default");
		cal.setDays(1);
	}
	
	
	public void onClick$Next(){
		System.out.println("move to prevoius page .....................calendars.nextPage()\n\n");
		cal.nextPage();
	}
	
	public void onClick$Prev(){
		System.out.println("move to prevoius page .....................calendars.previousPage()\n\n");
		cal.previousPage();
	}
	
}

