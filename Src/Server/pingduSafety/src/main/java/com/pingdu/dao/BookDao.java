package com.pingdu.dao;

import static com.pingdu.manager.ThreadLocalManager.em;




import java.util.List;

import javax.persistence.TypedQuery;

//import org.junit.Test;
import org.springframework.stereotype.Repository;
import com.pingdu.entity.Book;

@Repository
public class BookDao {

	public Book getBookById( Integer id){
	
		String jpql = "select b from Book b where id =:id";
		
		TypedQuery<Book> query = em().createQuery(jpql,Book.class);
		query.setParameter("id", id);
		Book book = (Book) query.getSingleResult();
		
		return book;
	}
	public void addOneBook() {
		
	}
	public List<Book> getAllBook() {
		String jpql = "select b from Book b";
		TypedQuery<Book> query = em().createQuery(jpql,Book.class);
		List<Book> booklist = query.getResultList();
		return booklist;
		
	}
	
//	@Test
//	public void testBegin() {
//		Book b = getBookById( new Integer(1));
//		
//		System.out.println("========================================");
//		System.out.println(b.getName()+b.getAuthor());
//	}
//	
//	@Test
//	public void test1() {
//		List<Book> books = getAllBook();
//		for(Book b: books){
//			System.out.println(b.getName()+"  "+b.getAuthor());
//		}
//	}
	
}
