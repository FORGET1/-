package com.pingdu.baseModel;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.portlet.ModelAndView;

@Controller
@Scope("prototype")
public class BaseController {
	@SuppressWarnings("deprecation")
	protected  static  Category logger=Category.getInstance("console");
	public static final String ERROR="error";
	
	public ModelAndView modelAndView;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
	public String getControllerName(){
		return this.getClass().getName()+"_";
	}	
	
	public void set(HttpServletRequest request, String name, Object value) {
		request.getSession().setAttribute(name, value);
	}
	
	public Object get(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}

	public void clearSession(HttpServletRequest request){
		request.getSession().invalidate();
	}

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

	public void setModelAndView(ModelAndView modelAndView) {
		this.modelAndView = modelAndView;
	}
	public String getActionName(){
		return this.getClass().getName()+"_";
		
	}
	
}
