package com.turtorial.started.menu_toolbar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.impl.LabelElement;

public class MenuController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;

	@Listen("onClick = menuitem")
	public void menuAction(MouseEvent event){
		showNotify("Clicked on "+((LabelElement)event.getTarget()).getLabel());
	}
	
	@Listen("onClick = toolbarbutton")
	public void toolbarAction(MouseEvent event){
		showNotify("Clicked on "+((LabelElement)event.getTarget()).getTooltiptext());
	}
	
	@Listen("onClick = button")
	public void buttonAction(){
		showNotify("Switch to a task.");
	}
	
	private void showNotify(String msg){
		Clients.showNotification(msg,"info",null,null,1000);
	}
}
