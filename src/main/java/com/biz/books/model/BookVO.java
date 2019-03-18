package com.biz.books.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookVO {

	private long b_id;
	private String b_userid, b_isbn, b_title, b_date;
	private float b_star;
	private String b_text;
	
}
