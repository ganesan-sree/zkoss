package com.task.springmvc.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task.springmvc.data.bean.Order;
import com.task.springmvc.data.service.OrderDAO;


@Controller
@RequestMapping("/shopping")
public class FormController {

	@Autowired
	private OrderDAO orderDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginForm(ModelMap model, HttpServletRequest request, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("zk".equals(username) && "zk".equals(password)) {
			session.setAttribute("logged", true);
			return "redirect:shop";
		}
		return "redirect:index";
	}
	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public String confirmForm(@ModelAttribute Order order, HttpSession session, ModelMap model) {
		if (session.getAttribute("logged") != null) {
			System.out.println("confirmOrder\n\n");
			int id = orderDao.findMaxId();
			order.setId(id);
			orderDao.saveOrder(order);
			return "redirect:confirm/" + id;
		}
		return "redirect:index";
	}
}
