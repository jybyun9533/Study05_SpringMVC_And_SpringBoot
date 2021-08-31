package com.encore.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.service.BookService;
import com.encore.spring.vo.BookVO;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("bookList.do")
	public ModelAndView getBooks() throws Exception {
		List<BookVO> list = bookService.getBooks();

		for (BookVO b : list)
			System.out.println(b);

		return new ModelAndView("book/bookList", "list", list);
	}

	@RequestMapping("bookRegister.do")
	public ModelAndView register(BookVO book, HttpServletRequest request) throws Exception {
		System.out.println("register BookVO : " + book);

		String isbn1 = request.getParameter("isbn1").trim();
		String isbn2 = request.getParameter("isbn2").trim();
		String isbn3 = request.getParameter("isbn2").trim();

		book.setIsbn(isbn1 + "-" + isbn2 + "-" + isbn3);

		String msg = "";
		String path = "Error.jsp";
		try {
			bookService.insertBook(book); // 정상적으로 book이 add됨.
			// try 성공
			msg = "도서가 정상적으로 등록 되었습니다.";
			path = "result.jsp";
		} catch (Exception e) {
			// try 실패 :: catch문 실행
			System.out.println(e);
			msg = "도서 등록 실패";
		}
		// 바인딩
		request.getSession().setAttribute("msg", msg);
		return new ModelAndView("redirect:" + path);
	}

	@RequestMapping("bookSearch.do")
	public ModelAndView search(String searchField, String searchText, HttpServletRequest request) throws Exception {
		System.out.println("searchField : " + searchField + ", seatchText : " + searchText);

		List<BookVO> list = null;
		String path = "find_fail";

		try {
			switch (searchField) {
			case "TITLE":
				list = bookService.searchByTitle(searchText);
				break;
			case "PUBLISHER":
				list = bookService.searchByPublisher(searchText);
				break;
			case "PRICE":
				list = bookService.searchByPrice(Integer.parseInt(searchText));
				break;
			default:
				list = bookService.getBooks();
			} // switch

			path = "book/bookList";
			request.setAttribute("field", searchField);
			request.setAttribute("text", searchText);
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "도서 검색중 오류 발생");
		}

		return new ModelAndView(path, "list", list);
	}

	@RequestMapping("bookView.do")
	public ModelAndView bookView(String isbn, HttpServletRequest request) throws Exception {
		String path = "find_fail.jsp";
		BookVO book = null;

		try {
			book = bookService.searchByIsbn(isbn);
			path = "book/bookView";
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "책 상세보기 오류 발생");
		}
		return new ModelAndView(path, "b", book);
	}
	
	@RequestMapping("hover.do")
	public String hover(String isbn, Model model) {
		System.out.println(isbn);
		try {
			BookVO book = bookService.searchByIsbn(isbn);
			model.addAttribute("descBook", book);
			//System.out.println(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "JsonView";
	}
}
