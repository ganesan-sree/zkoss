package com.calender.newsfeed.data;

public final class NewsColors {
	public static String[] _colors = {"red", "blue", "green"};
	public static String[] _type = {"Release", "Small talk", "Event"};
	
	public static int getColorPosition(String color) {
		for(int i=0; i<_colors.length; i++) {
			if(color.equals(_colors[i])) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static String getColorType(String color) {
		String ret = "";
		
		for(int i=0; i<_colors.length; i++) {
			if(color.equals(_colors[i])) {
				ret= _type[i];
			}
		}
		
		return ret;
	}
}
