package com.pingdu.service;

import java.util.List;

import com.pingdu.entity.Book;

public interface BookService {

	public Book getBookById(Integer id);
	
	public List<Book> getAllBook();
}
