/* Util.java

Purpose:
		
	Description:
		
	History:
		Thu Jan 28 19:32:58 TST 2010, Created by Jimmy

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

This program is distributed under GPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
 */
package com.calender;

import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;

public class QueueUtil {

	public static final String QUEUE_NAME = "calendarEvent";

	public static EventQueue<QueueMessage> lookupQueue() {
		EventQueue<QueueMessage> queue = EventQueues.lookup(QUEUE_NAME,EventQueues.DESKTOP, true);
		return queue;
	}
}
