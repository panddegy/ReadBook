package com.biz.books.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.books.model.MemberVO;
import com.biz.books.service.BookService;
import com.biz.books.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginMember(Model model) {
		
		model.addAttribute("MENUTYPE", "LOGIN");
		model.addAttribute("BODY", "LOGINFORM");
		model.addAttribute("TEXT", "로그인");
		
		return "home";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginMember(Model model, HttpSession httpSession, @ModelAttribute MemberVO memberVO) {
		
		if(memberService.loginMember(memberVO)) {
			httpSession.setAttribute("LOGINFO", memberVO);
			
			model.addAttribute("MENUTYPE", "LOGOUT");
			model.addAttribute("BODY", "LIST");
			model.addAttribute("BOOKS", bookService.selectBookById(memberVO.getM_userid()));
		} else {
			model.addAttribute("BODY", "FAIL");
		}
		
		return "home";
	}
	
	@RequestMapping(value="booklogout", method=RequestMethod.GET)
	public String logoutMember(Model model, HttpSession httpSession) {
			
		httpSession.setAttribute("LOGINFO", null);
		httpSession.removeAttribute("LOGINFO");
		
		model.addAttribute("MENUTYPE", "LOGIN");
		model.addAttribute("BODY", "LOGINFORM");
		model.addAttribute("TEXT", "로그인");
		
		return "home";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String signupMember(Model model) {
		
		model.addAttribute("MENUTYPE", "LOGIN");
		model.addAttribute("BODY", "SIGNFORM");
		model.addAttribute("TEXT", "회원가입");
		
		return "home";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String signupMember(Model model, HttpSession httpSession, @ModelAttribute MemberVO memberVO) {
		log.debug(memberVO.toString());
		int ret=memberService.insertMember(memberVO);
		if(ret>0) {
			model.addAttribute("MENUTYPE", "LOGOUT");
			model.addAttribute("BODY", "LIST");
			model.addAttribute("BOOKS", bookService.selectBookById(memberVO.getM_userid()));
		} else {
			model.addAttribute("BODY", "FAIL");
		}
		httpSession.setAttribute("LOGINFO", memberVO);
		
		return "home";
	}
	
	public String signoutMember() {
		
		return "home";
	}

}

























