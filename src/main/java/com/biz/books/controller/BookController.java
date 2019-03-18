package com.biz.books.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.books.model.BookVO;
import com.biz.books.model.MemberVO;
import com.biz.books.service.BookService;
import com.biz.books.service.MemberService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="bookwrite", method=RequestMethod.GET)
	public String writeBook(Model model, HttpSession httpSession) {
		
		MemberVO memberVO=(MemberVO)httpSession.getAttribute("LOGINFO");
		
		model.addAttribute("BODY", "WRITEFORM");
		model.addAttribute("MENUTYPE", "LOGOUT");
		model.addAttribute("MEMBER", memberVO);
		
		return "home";
	}
	
	@RequestMapping(value="bookwrite", method=RequestMethod.POST)
	public String writeBook(Model model, HttpSession httpSession, @ModelAttribute BookVO bookVO) {
		
		bookService.insertBook(bookVO);
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
	
	@RequestMapping(value="bookupdate/{id}", method=RequestMethod.GET)
	public String updateBook(Model model, @PathVariable long id, HttpSession httpSession) {
		
		BookVO bookVO=bookService.findById(id);
		MemberVO memberVO=(MemberVO)httpSession.getAttribute("LOGINFO");
		
		model.addAttribute("BODY", "WRITEFORM");
		model.addAttribute("MENUTYPE", "LOGOUT");
		model.addAttribute("MEMBER", memberVO);
		model.addAttribute("UPDATE", bookVO);
		
		return "home";
	}
	
	@RequestMapping(value="bookupdate", method=RequestMethod.POST)
	public String updateBook(Model model, @ModelAttribute BookVO bookVO, HttpSession httpSession) {
		
		bookService.updateBook(bookVO);
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
	
	@RequestMapping(value="bookdelete/{id}", method=RequestMethod.GET)
	public String deleteBook(Model model, @PathVariable long id, HttpSession httpSession) {
		
		bookService.deleteBook(id);
		MemberVO memberVO=(MemberVO)httpSession.getAttribute("LOGINFO");
		
		List<BookVO> bookList=bookService.selectBookById(memberVO.getM_userid());
		model.addAttribute("BODY", "LIST");
		model.addAttribute("BOOKS", bookList);
		
		model.addAttribute("MENUTYPE", "LOGOUT");
		
		return "home";
	}
}






















