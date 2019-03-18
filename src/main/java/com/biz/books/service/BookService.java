package com.biz.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.books.mapper.BookMapper;
import com.biz.books.model.BookVO;

@Service
public class BookService {
	
	@Autowired
	BookMapper bookMapper;

	public List<BookVO> selectBookById(String m_userid) {
		
		return bookMapper.selectBooksByUserID(m_userid);
	}

	public int insertBook(BookVO bookVO) {
		
		return bookMapper.bookInsert(bookVO);
	}

	public BookVO findById(long id) {
		
		return bookMapper.findById(id);
	}

	public int updateBook(BookVO bookVO) {
		
		return bookMapper.bookUpdate(bookVO);
	}

	public int deleteBook(long id) {
		return bookMapper.bookDelete(id);
	}

}
