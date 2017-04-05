package com.pingdu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.BookDao;
import com.pingdu.entity.Book;
import com.pingdu.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;

	public Book getBookById(Integer id) {
		Book book = bookDao.getBookById(id);
		return book;
	}

	public List<Book> getAllBook() {
		List<Book> books = bookDao.getAllBook();
		return books;
	}
	
	

}
