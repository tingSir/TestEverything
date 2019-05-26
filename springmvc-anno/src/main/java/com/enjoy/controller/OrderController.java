package com.enjoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enjoy.service.OrderService;

@Controller
public class OrderController   {
	@Autowired
	OrderService OrderServiceImp2;

	@ResponseBody
	@RequestMapping("/buy")
	public String buy(){
		return OrderServiceImp2.goBuy("12345678");
	}

	//相当于会找 /WEB-INF/pages/ok.jsp
	@RequestMapping("/ok")
	public String ok(){
		return "ok";
	}
}
