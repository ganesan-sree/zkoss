package com.calender.newsfeed.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.calender.newsfeed.models.NewsItem;



public class NewsDAO {
	
	public NewsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List selectAll() {
		Statement stmt = null;
		Connection conn = null;
		List allObjects = new ArrayList();
		
		
		try {
			// get connection
			conn = DriverManager.getConnection(DatabaseInformation.connectionString, 
											   DatabaseInformation.username, 
											   DatabaseInformation.password);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL.selectAllNewsItems());
			
			NewsItem ni;
			
			while (rs.next()) {
				
				System.out.println(rs.toString());
				
				ni = new NewsItem();
				
				Date dt = new Date();

				ni.setNews_item(rs.getInt(1));
				dt.setTime(rs.getLong(2));
				ni.setBeginDate((Date)dt.clone());
				dt.setTime(rs.getLong(3));
				ni.setEndDate((Date)dt.clone());
				
				ni.setTitle(rs.getString(4));
				ni.setContent(rs.getString(5));
				ni.setHeaderColor(rs.getString(6));
				ni.setContentColor(rs.getString(7));
				ni.setLocked(rs.getBoolean(8));
							
				allObjects.add(ni);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return allObjects;
	}
	
	public boolean updateNewsItem(NewsItem ni) {
		boolean result = false;
		
		Statement stmt = null;
		Connection conn = null;
		List allObjects = new ArrayList();
		
		try {
			conn = DriverManager.getConnection(DatabaseInformation.connectionString, 
											   DatabaseInformation.username, 
											   DatabaseInformation.password);
			stmt = conn.createStatement();
			
			//execute the query
			if (stmt.executeUpdate(SQL.updateNewsItem(ni)) > 0);
			
			result = true;
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			System.out.println(SQL.insertNewsItem(ni));
		}finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				System.out.println(SQL.insertNewsItem(ni));
				ex.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;		
	}
	
	public boolean insertNewsItem(NewsItem ni) {
		boolean result = false;
		
		Statement stmt = null;
		Connection conn = null;
		List allObjects = new ArrayList();
		
		try {
			conn = DriverManager.getConnection(DatabaseInformation.connectionString, 
											   DatabaseInformation.username, 
											   DatabaseInformation.password);
			stmt = conn.createStatement();
			
			//execute the query
			System.out.println(SQL.insertNewsItem(ni));
			if (stmt.executeUpdate(SQL.insertNewsItem(ni)) > 0);
			
			result = true;
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			System.out.println(SQL.insertNewsItem(ni));
		}finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				System.out.println(SQL.insertNewsItem(ni));
				ex.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean deleteNewsItem(NewsItem ni) {
		
		boolean result = false;
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInformation.connectionString, 
											   DatabaseInformation.username, 
											   DatabaseInformation.password);
			stmt = conn.createStatement();
			
			//execute the query
			if (stmt.executeUpdate(SQL.deleteNewsItem(ni.getNews_item())) > 0)
				result = true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
		
	}
}
