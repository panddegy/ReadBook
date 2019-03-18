package com.biz.books.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.books.model.BookVO;

public interface BookMapper {

	@Select(" select * from tbl_books ")
	public List<BookVO> selectAllBooks();
	
	@Select(" select * from tbl_books where b_userid=#{b_userid} ")
	public List<BookVO> selectBooksByUserID(String b_userid);
	
	@Insert(" insert into tbl_books(b_id, b_userid, b_isbn, b_title, b_date, b_star, b_text) "
			+ " values "
			+ " (seq_bookstory.nextval, #{b_userid}, #{b_isbn}, #{b_title}, #{b_date}, #{b_star}, #{b_text})  ")
	public int bookInsert(BookVO vo);
	
	@Delete(" delete from tbl_books where b_id=#{b_id} ")
	public int bookDelete(long b_id);
	
	@Update(" update tbl_books set b_userid=#{b_userid}, b_isbn=#{b_isbn}, b_title=#{b_title}, b_date=#{b_date}, "
			+ " b_star=#{b_star}, b_text=#{b_text} where b_id=#{b_id} ")
	public int bookUpdate(BookVO vo);

	@Select(" select * from tbl_books where b_id=#{b_id} ")
	public BookVO findById(long b_id);
	
}
