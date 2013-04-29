package com.task.springmvc.controller.springmvc;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.task.springmvc.data.service.OrderDAO;
import com.task.springmvc.data.service.ProductDAO;

@Controller
@RequestMapping("/shopping")
public class PageFlowController {

	@Autowired
	private ProductDAO prodDao;
	@Autowired
	private OrderDAO orderDao;
	
	@RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
	public String index() {
		return "zul/login.zul";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(ModelMap model, HttpSession session) {
		if (isLogged(session)) {
			model.addAttribute("productList", prodDao.findAll());
			System.out.println("Entering into shoping page !!!!!!!!!!!!!!!!!!!!!!");
			return "zul/shopping.zul";
		}
		return "redirect:index";
	}
	
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public String confirm(@PathVariable("id") int id, ModelMap model, HttpSession session) {
		if (isLogged(session)) {
			model.addAttribute("order", orderDao.findById(id));
			System.out.println("ConfirmOrder @@@@@@@@@@@ after pathVariable");
			return "jsp/confirm.jsp";
		}
		return "redirect:index";
	}
	
	private boolean isLogged(HttpSession session) {
		return session.getAttribute("logged") != null;
	}
}
