package com.encore.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.encore.board.domain.BoardVO;
import com.encore.board.domain.MemberVO;
import com.encore.board.model.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("write.do")
	public String doWriter(BoardVO bvo, HttpSession session, Model model) throws Exception {
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		if (session.getAttribute("mvo") == null) {
			return "redirect:index.jsp";
		}

		bvo.setMemberVO(mvo);
		model.addAttribute("bvo", bvo);
		boardService.write(bvo);

		return "board/show_content";

	}

	@RequestMapping("list.do")
	public String doList(Model model) {
		try {
			List<BoardVO> list = boardService.getBoardList();
			model.addAttribute("list", list);
			return "board/list";
		} catch (Exception e) {
			model.addAttribute("message", "리스트 출력 실패");
			return "Error";
		}

	}

	@RequestMapping("showContent.do")
	public String doShowContent(Model model, int no) {
		try {
			// System.out.println(no);
			boardService.updateCount(no);
			BoardVO bvo = boardService.showContent(no);
			model.addAttribute("bvo", bvo);
			// System.out.println(bvo);
			return "board/show_content";
		} catch (Exception e) {
			model.addAttribute("message", "상세보기 실패");
			return "Error";
		}

	}

	@RequestMapping("updateView.do")
	public String doUpdateView(Model model, int no) {
		BoardVO bvo = boardService.showContent(no);
		model.addAttribute("bvo", bvo);
		return "board/update";
	}

	@RequestMapping("updateBoard.do")
	public String doUpdateBoard(Model model, BoardVO bvo, HttpSession session) {
		try {
			
			MemberVO mvo = (MemberVO) session.getAttribute("mvo");
			if (session.getAttribute("mvo") == null) {
				return "redirect:index.jsp";
			}
			
			bvo.setMemberVO(mvo);
			boardService.updateBoard(bvo);
			model.addAttribute("bvo", bvo);
			System.out.println(bvo);
			return "redirect:index.jsp";
		} catch (Exception e) {
			model.addAttribute("message", "수정하기 실패");
			return "Error";
		}
	}
	
	
	@RequestMapping("delete.do")
	public String doDelete(Model model, int no) {
		try {
			boardService.delete(no);
			return "redirect:index.jsp";
		} catch (Exception e) {
			model.addAttribute("message", "삭제 실패");
			return "Error";
		}
	}
}
