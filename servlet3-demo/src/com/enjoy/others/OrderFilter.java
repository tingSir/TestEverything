package com.enjoy.others;

import javax.servlet.*;
import java.io.IOException;

public class OrderFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//过滤请求
		System.out.println("OrderFilter....doFilter.....");
		//放行
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
