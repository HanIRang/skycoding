package kr.hmember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		//로그아웃 처리
		session.invalidate();

		//JSP 경로 반환
		//redirect 시에는 앞에 redirect: 을 붙여줘야함.
		return "redirect:/main/main.do";
	}

}
