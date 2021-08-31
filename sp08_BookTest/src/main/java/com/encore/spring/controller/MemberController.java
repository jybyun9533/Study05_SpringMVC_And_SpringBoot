package com.encore.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.service.MemberService;
import com.encore.spring.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("memberLogin.do")
	public ModelAndView login(MemberVO vo, HttpServletRequest request/*HttpSession session*/) throws Exception {
		System.out.println("loginVO : " + vo);

		MemberVO rvo = memberService.loginCheck(vo);

		System.out.println("RVO : " + rvo);

		String path = "index.jsp";
		String msg = "아이디 혹은 패스워드가 일치하지 않습니다.";
		// 결과페이지가 브루아저로 갔다가, 다시 서버로 들어가는 경우 redirect 사용
		// redirect는 viewResolver와 관련x > 확장자 써줘야 함
		// html요청, Error요청, controller 요청
		if (rvo != null) { // 해당 id, pw 멤버가 존재하고 로그인 성공했다면
			// session.setAttribute("user", rvo);
			request.getSession().setAttribute("user", rvo);
			msg = "로그인 성공!";
		}
		
		//session.setAttribute("msg", msg); // 로그인 성공 여부에 따라 msg가 달라짐.
		request.getSession().setAttribute("msg", msg);
		return new ModelAndView("redirect:" + path);
	}

	@RequestMapping("memberLogout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		String path = "index.jsp";
		session.invalidate();
		
		return new ModelAndView("redirect:" + path);
		
	}
	
}
