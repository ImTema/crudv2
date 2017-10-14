package com.javarush.dao.impl;

import java.util.List;

import com.javarush.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javarush.dao.Daoable;

@Component
public class BookDaoImpl implements Daoable<Book> {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Book book) {
		session.getCurrentSession().save(book);
	}

	@Override
	public void edit(Book book) {
		session.getCurrentSession().update(book);
	}

	@Override
	public void delete(int bookId) {		
		session.getCurrentSession().delete(get(bookId));
	}

	@Override
	public Book get(int bookId) {
		return session.getCurrentSession().get(Book.class, bookId);
	}

	@Override
	public List<Book> getAll() {
		return session.getCurrentSession().createNativeQuery("SELECT * FROM books").addEntity(Book.class).list();
	}

}
