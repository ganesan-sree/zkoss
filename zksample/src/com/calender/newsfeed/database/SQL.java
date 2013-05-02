package com.calender.newsfeed.database;

import java.sql.Timestamp;

import com.calender.newsfeed.models.NewsItem;



public final class SQL {
	private final static String selectNews = "SELECT * FROM " + DatabaseInformation.newsTableName;
	private final static String insertNews = "INSERT into " + DatabaseInformation.newsTableName + "(date_begin, date_end, title, content, header_color, content_color, isLocked) values ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')";
	private final static String updateNews = "UPDATE " + DatabaseInformation.newsTableName + " SET date_begin = '%1$s', date_end = '%2$s', title = '%3$s', content = '%4$s', header_color = '%5$s', content_color = '%6$s', isLocked = '%7$s' WHERE news_item = %8$d";
	private final static String deleteNews = "DELETE FROM " + DatabaseInformation.newsTableName + " WHERE news_item = %1$d";
	
	public final static String selectAllNewsItems() {
		return selectNews;
	}
	
	public final static String insertNewsItem(NewsItem evt) {		
		Timestamp begin = new Timestamp(evt.getBeginDate().getTime());
		Timestamp end = new Timestamp(evt.getEndDate().getTime());		
		String locked=new  Boolean(evt.isLocked()).toString();
		Object[] args = {begin, end, evt.getTitle(), evt.getContent(), evt.getHeaderColor(), evt.getContentColor(),locked };
		return String.format(insertNews, args);
	}
	
	public final static String updateNewsItem(NewsItem evt) {
		Timestamp begin = new Timestamp(evt.getBeginDate().getTime());
		Timestamp end = new Timestamp(evt.getEndDate().getTime());		
		Integer newsItem = new Integer(evt.getNews_item());
		String locked=new  Boolean(evt.isLocked()).toString();
		
		Object[] args = {begin, end, evt.getTitle(), evt.getContent(), evt.getHeaderColor(), evt.getContentColor(), locked, newsItem};
		return String.format(updateNews, args);
	}
	
	public final static String deleteNewsItem(int id) {
		Integer boxedID = new Integer(id);
		Object[] args = {boxedID};
		return String.format(deleteNews, args);
	}
}
