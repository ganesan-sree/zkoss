package com.calender.newsfeed.controllers;

import java.util.Collection;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Chart;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.calender.newsfeed.data.NewsColors;
import com.calender.newsfeed.models.DatabaseCalendarModel;
import com.calender.newsfeed.models.NewsItem;

public class EventCreationController extends GenericForwardComposer  {
	

	Window createMyEntry;
	Combobox cmbType;
	Textbox tbText;
	
	public void prepareWindow(int left, int top) {
		cmbType.setSelectedIndex(0);		
		createMyEntry.setLeft(left + "px");
		createMyEntry.setTop(top + "px");
	}

	public void onClick$btnAddNews() {
		CalendarsEvent evt = ((org.zkoss.calendar.event.CalendarsEvent)createMyEntry.getAttribute("calevent"));
		
		Window win = (Window)createMyEntry.getParent();
		Component rootComponent=win.getRoot();
		
		Collection<Component> list=rootComponent.getFellows();
		
		java.util.Iterator<Component> it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		
		
		org.zkoss.calendar.Calendars cals = (org.zkoss.calendar.Calendars)rootComponent.getFellow("cal");
		Chart piechart = (Chart)win.getFellow("piechart", false);
		
		NewsItem ni = new NewsItem();
		
		int selectedColor = cmbType.getSelectedIndex();
		
		if(selectedColor == -1)
			selectedColor = 0;
		
		String color = NewsColors._colors[selectedColor];
		
		ni.setBeginDate(evt.getBeginDate());
		ni.setEndDate(evt.getEndDate());
		
		ni.setHeaderColor(color);
		ni.setContentColor(color);	
		
		ni.setContent(tbText.getValue());
		ni.setLocked(false);
		
		DatabaseCalendarModel.dao.insertNewsItem(ni);
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();
		
		cals.setModel(dcm.getSimpleCalendarModel());
		piechart.setModel(dcm.getSimplePieModel());
		
		evt.clearGhost();
		createMyEntry.setVisible(false);
	}
	
	public void onClick$btnCancel() {
		CalendarsEvent evt = ((org.zkoss.calendar.event.CalendarsEvent)createMyEntry.getAttribute("calevent"));
		createMyEntry.setVisible(false);
		evt.clearGhost();
	}
}
