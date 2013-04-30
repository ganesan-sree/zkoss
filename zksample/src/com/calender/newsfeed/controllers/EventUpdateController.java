package com.calender.newsfeed.controllers;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Chart;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.calender.newsfeed.data.NewsColors;
import com.calender.newsfeed.models.DatabaseCalendarModel;
import com.calender.newsfeed.models.NewsItem;

public class EventUpdateController extends GenericForwardComposer {
	Window updateMyEntry;
	Combobox cmbType;
	Textbox tbText;
	
	String[] _colors = {"red", "green", "blue"};
	
	public void prepareWindow(int left, int top, NewsItem ni) {
		updateMyEntry.setLeft(left + "px");
		updateMyEntry.setTop(top + "px");
		
		int colorPosition = NewsColors.getColorPosition(ni.getHeaderColor());
		
		if(colorPosition == -1)
			colorPosition = 0;
		
		cmbType.setSelectedIndex(colorPosition);
		tbText.setValue(ni.getContent());
	}
	
	public void onClick$btnUpdateNews() {	
		CalendarsEvent evt = ((org.zkoss.calendar.event.CalendarsEvent)updateMyEntry.getAttribute("calevent"));
		
		Window win = (Window)updateMyEntry.getParent();
		org.zkoss.calendar.Calendars cals = (org.zkoss.calendar.Calendars)win.getAttribute("cal", false);
		Chart piechart = (Chart)win.getAttribute("piechart", false);
		
		NewsItem ni = ((NewsItem)updateMyEntry.getAttribute("ni"));
		
		ni.setContent(tbText.getValue());
		int selectedColor = cmbType.getSelectedIndex();
		
		if(selectedColor == -1)
			selectedColor = 0;
		
		ni.setContentColor(NewsColors._colors[selectedColor]);
		ni.setHeaderColor(NewsColors._colors[selectedColor]);
			
		DatabaseCalendarModel.dao.updateNewsItem(ni);
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();
		
		cals.setModel(dcm.getSimpleCalendarModel());
		piechart.setModel(dcm.getSimplePieModel());
		
		evt.clearGhost();
		
		updateMyEntry.setVisible(false);
	}
	
	public void onClick$btnDeleteNews() {
		NewsItem ni = ((NewsItem)updateMyEntry.getAttribute("ni"));
		
		Window win = (Window)updateMyEntry.getParent();
		org.zkoss.calendar.Calendars cals = (org.zkoss.calendar.Calendars)win.getAttribute("cal", false);
		Chart piechart = (Chart)win.getAttribute("piechart", false);
		
		DatabaseCalendarModel.dao.deleteNewsItem(ni);
		DatabaseCalendarModel dcm = new DatabaseCalendarModel();
		
		cals.setModel(dcm.getSimpleCalendarModel());
		piechart.setModel(dcm.getSimplePieModel());
		
		updateMyEntry.setVisible(false);
	}
	
	public void onClick$btnCancel() {
		CalendarsEvent evt = ((org.zkoss.calendar.event.CalendarsEvent)updateMyEntry.getAttribute("calevent"));
		updateMyEntry.setVisible(false);
		evt.clearGhost();
	}
}
