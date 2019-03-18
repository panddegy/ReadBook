package com.biz.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.books.service.MemberService;

@RestController
public class BookRestController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="checkid", method=RequestMethod.POST)
	public boolean checkId(@RequestParam String m_userid) {
		
		return memberService.checkId(m_userid);
	}
	
	
}
