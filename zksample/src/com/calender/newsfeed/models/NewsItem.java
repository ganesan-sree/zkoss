package com.calender.newsfeed.models;

import org.zkoss.calendar.impl.SimpleCalendarEvent;

public class NewsItem extends SimpleCalendarEvent {
	
	private int _news_item;

	public int getNews_item() {
		return _news_item;
	}
	
	public void setNews_item(int id) {
		_news_item = id;
	}
	
	public NewsItem() {
		
	}
}
