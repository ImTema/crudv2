package com.javarush.service.impl;

import java.util.List;

import com.javarush.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.javarush.dao.Daoable;
import com.javarush.service.Serviceable;

@Component
public class BookServiceImpl implements Serviceable<Book> {

	@Autowired
	private Daoable<Book> bookDao;
	
	@Transactional
	public void add(Book book) {
		bookDao.add(book);
	}

	@Transactional
	public void edit(Book book) {
		bookDao.edit(book);
	}

	@Transactional
	public void delete(int itemId) {
		bookDao.delete(itemId);
	}

	@Transactional
	public Book get(int itemId) {
		return (Book)bookDao.get(itemId);
	}

	@Transactional
	public List<Book> getAll() {
		return (List<Book>)bookDao.getAll();
	}

}
