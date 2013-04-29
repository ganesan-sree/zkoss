/**
 * 
 */
package com.task.springsec.springsec.ui;

import java.security.Principal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import com.task.springsec.springsec.model.Article;
import com.task.springsec.springsec.model.ArticleService;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
@VariableResolver(DelegatingVariableResolver.class)
public class HomePageViewCtrl extends SelectorComposer<Component> {

	@Wire
	private Grid articlesGrid;
	
	@WireVariable
	private ArticleService articleService;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Principal p = Executions.getCurrent().getUserPrincipal();
		articlesGrid.setModel(new ListModelList<Article>(articleService.findAll()));
	}
}
