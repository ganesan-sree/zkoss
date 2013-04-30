/**
 * 
 */
package com.task.springsec.springsec.ui;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Textbox;

import com.task.springsec.SecurityUtil;
import com.task.springsec.springsec.model.Article;
import com.task.springsec.springsec.model.ArticleService;
import com.task.springsec.springsec.model.MyUser;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
@VariableResolver(DelegatingVariableResolver.class)
public class NewArticleViewCtrl extends SelectorComposer<Component> {

	@Wire
	private Textbox titleTxb;
	@Wire
	private Textbox contentTxb;
	@WireVariable
	private ArticleService articleService;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	
	@Listen("onClick=#postBtn")
	public void doPostArticle(){
		Execution exec = Executions.getCurrent();
		MyUser u = SecurityUtil.getUser();
		
		Article article = 
			new Article(titleTxb.getValue(), contentTxb.getValue(), u.getUsername(), new Date());

		articleService.create(article);
		exec.sendRedirect("/");
	}
}
