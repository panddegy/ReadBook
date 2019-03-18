package com.biz.books.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.books.model.BookVO;
import com.biz.books.model.MemberVO;
import com.biz.books.service.BookService;
import com.biz.books.service.MemberService;

@Controller
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession httpSession) {
		
		MemberVO memberVO=(MemberVO)httpSession.getAttribute("LOGINFO");
		String ret=memberService.checkMember(memberVO);
		
		List<BookVO> bookList=new ArrayList<>();
		if(ret.equalsIgnoreCase("LOGOUT")) {
			bookList=bookService.selectBookById(memberVO.getM_userid());
			model.addAttribute("BODY", "LIST");
			model.addAttribute("BOOKS", bookList);
		}
		
		model.addAttribute("MENUTYPE", ret);

		return "home";
	}
	
}





















