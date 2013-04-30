/**
 * 
 */
package com.task.springsec.springsec.ui;

import java.util.Date;

import org.springframework.security.access.AccessDeniedException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.task.springsec.SecurityUtil;
import com.task.springsec.springsec.model.Article;
import com.task.springsec.springsec.model.ArticleService;
import com.task.springsec.springsec.model.MyUser;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
@VariableResolver(DelegatingVariableResolver.class)
public class ArticleContentViewCtrl extends SelectorComposer<Component> {
	@Wire
	private Label titleLbl;
	@Wire
	private Html contentHtml;
	@Wire
	private Vlayout container;
	
	@WireVariable
	private ArticleService articleService;
	
	private Article article;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Execution exec = Executions.getCurrent();
		long aid = -1;
		String idStr = exec.getParameter("articleId");
		try{
			aid = Long.parseLong(idStr);
		}catch(Exception e){
			throw new IllegalArgumentException("the parameter: articleId is not correct or not been set: "+idStr);
		}
		article = articleService.find(aid);
		//TODO: use ZK Error Handling : IllegalAccessEx 
		if(article==null){
			throw new IllegalArgumentException("article was not found by given id: "+aid);
		}
		refresh();
	}
	
	@Listen("onClick=#openEditorBtn")
	public void edit(){
		//ownership & permission check.
		if(!isOwner() && SecurityUtil.isNoneGranted("ROLE_EDITOR")){
			throw new AccessDeniedException(
				"The user is neither the author, nor a privileged user.");
		}
		ArticleEditor editor = new ArticleEditor();
		editor.setParent(container);
		editor.doHighlighted();
	}
	
	private boolean isOwner(){
		MyUser user = SecurityUtil.getUser();
		if(user ==null)return false;
		return article.getAuthor().equals(user.getUsername());
	}
	
	private void refresh(){
		titleLbl.setValue(article.getTitle());
		contentHtml.setContent("<pre>"+article.getContent()+"</pre>");
	}
	
	
	@Listen("onClick=#deleteBtn")
	public void delete(){
		SecurityUtil.assertAll("ROLE_EDITOR");
		// for demonstrate Spring bean's direct Security check in ZK Ajax Request we comment out this line.
		System.out.println(">>>>>>> Delete");
		// Spring Security will check if user has ROLE_EDITOR, 
		// this is the last defense if any attempted access without further role. 
		articleService.delete(article.getId());
		Executions.sendRedirect("/");
	}
	
	/**
	 * An simple Editor UI implementation for Article.
	 * 
	 * @author Ian YT Tsai (zanyking)
	 *
	 */
	public class ArticleEditor extends Window{
		@Wire
		private Textbox titleTxb;
		@Wire
		private Textbox contentTxb;
		
		private  Article temp;
		
		public ArticleEditor() {
			// title="Edit" border="normal" width="600px" height="500px"
			super("Article Editor", "normal", true);
			setWidth("700px");
			setHeight("500px");
			Executions.createComponents("/WEB-INF/partial/edit_article.zul",
					this, null);
			Selectors.wireComponents(this, this, true);
			Selectors.wireEventListeners(this, this);
			temp = new Article(
					article.getTitle(),
					article.getContent(),
					article.getAuthor(),
					article.getLastModifiedDate()
				);
			temp.setId(article.getId());
			
			titleTxb.setValue(temp.getTitle());
			contentTxb.setValue(temp.getContent());
		}		
		
		@Listen("onClick=#submitChangeBtn")
		public void doSubmitChange(){
			temp.setLastModifiedDate(new Date());
			temp.setTitle(titleTxb.getValue());
			temp.setContent(contentTxb.getValue());
			articleService.update(temp);
			article = temp;
			refresh();
			this.detach();
		}
		
	}//end of class
	
	
	
	
	
}
