package com.biz.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.books.mapper.MemberMapper;
import com.biz.books.model.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String checkMember(MemberVO vo) {
		
		String ret="";
		
		if(vo==null) {
			ret="LOGIN";
		} else {
			ret="LOGOUT";
		} 
		return ret;
	}

	public int insertMember(MemberVO memberVO) {
		
		String cryptPass=bCryptPasswordEncoder.encode(memberVO.getM_password());
		memberVO.setM_password(cryptPass);
		log.debug(memberVO.toString());
		int ret=memberMapper.insertMember(memberVO);
		return ret;
	}
	
	public boolean checkId(String m_userid) {
		
		MemberVO memberVO=memberMapper.selectMemberById(m_userid);
		if(memberVO==null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean loginMember(MemberVO memberVO) {

		MemberVO _tVO=memberMapper.selectMemberById(memberVO.getM_userid());
		if(_tVO==null) {
			return false;
		} else {
			if(bCryptPasswordEncoder.matches(memberVO.getM_password(), _tVO.getM_password())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
}






















