package com.encore.spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.Board;
import com.encore.spring.BoardService;
import com.encore.spring.UploadDataVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("boardInput.do")
	public ModelAndView boardInput() {
		System.out.println("boardInput.do method in");
		// 게시판 입력폼인 board/boardInsert.jsp로 결과 페이지 연결한다.
		return new ModelAndView("board_input");
	}

	@RequestMapping("boardInsert.do")
	public ModelAndView boardInsert(HttpServletRequest request, UploadDataVO vo) throws Exception {
		String id = request.getParameter("id").trim();
		String title = request.getParameter("title").trim();
		String contents = request.getParameter("contents").trim();
		String filename = null;


		// ############## 파일업로드 ###############
		MultipartFile mFile = vo.getUploadFile();
		System.out.println("mFile :: " + mFile);

		// 3. 업로드한 파일을 지정한 경로에다 copy해서 이동시킴.../upload/copy해온 파일이 저장됨.
		if (!mFile.isEmpty()) {
			String root = request.getSession().getServletContext().getRealPath("/");
			System.out.println("ROOT :: " + root);
			String path = root + "\\upload\\";

			File copyFile = new File(path + mFile.getOriginalFilename());
			mFile.transferTo(copyFile); // 업로드한 파일의 카피본이 해당경로에 저장된다...이동한다
			System.out.println("path :: " + path);
			filename = mFile.getOriginalFilename();
		}

		// ############### 글 작성 #################
		
		String msg = "";
		String path = "errorPage";
		
		try {
//			throw new Exception();
			Board board = new Board(id, title, contents, filename);
			boardService.insert(board);
			
			
			msg = "게시글이 정상적으로 등록 되었습니다.";
			path = "redirect:index.jsp";
			

			
		} catch (Exception e) {
			System.out.println(e);
			msg = "게시글 업로드 실패";
			path = "errorPage";
		}

		return new ModelAndView(path, "msg", msg);
	}

	@RequestMapping("boardList.do")
	public ModelAndView boardList() throws Exception {
		System.out.println("boardList.do method in");
		String path = "boardList";

		List<Board> list = boardService.selectAll();

		return new ModelAndView(path, "list", list);
	}

	@RequestMapping("boardDetail.do")
	public ModelAndView boardDetail(String no) throws Exception {
		System.out.println("boardDetail.do method in");

		String path = "boardDetail";
		Board vo = boardService.selectOne(no);

		return new ModelAndView(path, "vo", vo);
	}

	@RequestMapping("fileDown.do")
	public ModelAndView filedown(HttpServletRequest request, String filename) throws Exception {
		System.out.println("filename :: " + filename);
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root + "\\upload\\";

		HashMap map = new HashMap();
		map.put("path", path);
		return new ModelAndView("downloadView", map);
	}
}
