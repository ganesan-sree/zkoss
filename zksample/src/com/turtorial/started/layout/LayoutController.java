package com.turtorial.started.layout;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.impl.LabelElement;

public class LayoutController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;

	@Listen("onClick = a, button")
	public void cannedResponse(MouseEvent event){
		showNotify("Clicked "+((LabelElement)event.getTarget()).getLabel());
	}
	
	private void showNotify(String msg){
		Clients.showNotification(msg,"info",null,null,1000);
	}
}
