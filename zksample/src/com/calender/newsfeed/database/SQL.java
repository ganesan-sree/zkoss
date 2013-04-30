package com.calender.newsfeed.database;

import com.calender.newsfeed.models.NewsItem;



public final class SQL {
	private final static String selectNews = "SELECT * FROM " + DatabaseInformation.newsTableName;
	private final static String insertNews = "INSERT into " + DatabaseInformation.newsTableName + "(date_begin, date_end, title, content, header_color, content_color, isLocked) values (%1$d, %2$d, '%3$s', '%4$s', '%5$s', '%6$s', %7$b)";
	private final static String updateNews = "UPDATE " + DatabaseInformation.newsTableName + " SET date_begin = %1$d, date_end = %2$d, title = '%3$s', content = '%4$s', header_color = '%5$s', content_color = '%6$s', isLocked = %7$b WHERE news_item = %8$d";
	private final static String deleteNews = "DELETE FROM " + DatabaseInformation.newsTableName + " WHERE news_item = %1$d";
	
	public final static String selectAllNewsItems() {
		return selectNews;
	}
	
	public final static String insertNewsItem(NewsItem evt) {		
		Long begin = new Long(evt.getBeginDate().getTime());
		Long end = new Long(evt.getEndDate().getTime());
		Boolean locked = new Boolean(evt.isLocked());
		
		Object[] args = {begin, end, evt.getTitle(), evt.getContent(), evt.getHeaderColor(), evt.getContentColor(), locked};
		return String.format(insertNews, args);
	}
	
	public final static String updateNewsItem(NewsItem evt) {
		Long begin = new Long(evt.getBeginDate().getTime());
		Long end = new Long(evt.getEndDate().getTime());
		Boolean locked = new Boolean(evt.isLocked());
		Integer newsItem = new Integer(evt.getNews_item());
		
		Object[] args = {begin, end, evt.getTitle(), evt.getContent(), evt.getHeaderColor(), evt.getContentColor(), locked, newsItem};
		return String.format(updateNews, args);
	}
	
	public final static String deleteNewsItem(int id) {
		Integer boxedID = new Integer(id);
		Object[] args = {boxedID};
		return String.format(deleteNews, args);
	}
}
