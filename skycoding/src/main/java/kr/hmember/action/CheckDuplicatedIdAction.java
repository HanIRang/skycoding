package kr.hmember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;
import kr.hmember.vo.MemberVO;

public class CheckDuplicatedIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String id = request.getParameter("mem_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO hmember = dao.checkMember(id);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		if(hmember == null) {//아이디 미중복
			mapAjax.put("result", "idNotFound");
		}else { //아이디 중복
			mapAjax.put("result", "idDuplicated");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}