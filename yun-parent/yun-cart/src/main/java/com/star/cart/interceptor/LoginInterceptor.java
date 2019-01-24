package com.star.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.CookieUtils;
import com.star.commons.utils.HttpClientUtil;
import com.star.commons.utils.JsonUtils;

public class LoginInterceptor implements HandlerInterceptor {
     /**
      * 这在进入控制器之前 执行的 判断用户是否登录 
      */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*//获取token 有信息就放行 不然就
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if(token!=null&&!token.equals("")){
			String post = HttpClientUtil.doPost("http://localhost:8084/user/token/"+token);
			EgoResult egoResult = JsonUtils.jsonToPojo(post, EgoResult.class);
			if(egoResult.getStatus()==200) {
				return true;
			}
		}
		String num = request.getParameter("num");
		response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum="+num);*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
