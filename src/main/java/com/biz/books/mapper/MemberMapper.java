package com.biz.books.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.books.model.MemberVO;

public interface MemberMapper {

	@Select(" select * from tbl_book_member ")
	public List<MemberVO> selectAllMember();
	
	@Insert(" insert into tbl_book_member values(#{m_userid}, #{m_password}, #{m_role} ) ")
	public int insertMember(MemberVO vo);
	
	@Update(" update tbl_book_member set m_password=#{m_password}, m_role=#{m_role} where m_userid=#{m_userid} ")
	public int updateMember(MemberVO vo);
	
	@Delete(" delte from tbl_book_member where m_userid=#{m_userid} ")
	public int deleteMember(String m_userid);

	@Select(" select * from tbl_book_member where m_userid=#{m_userid} ")
	public MemberVO selectMemberById(String m_userid);
	
}
