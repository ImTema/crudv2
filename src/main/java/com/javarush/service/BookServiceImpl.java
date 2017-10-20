package com.javarush.service;

import com.javarush.dao.Daoable;
import com.javarush.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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

	@Transactional
	public List<Book> getAllPerPage(int page, int limitResultsPerPage) {
		return bookDao.getAllPerPage(page, limitResultsPerPage);
	}

	@Transactional
	public List<Book> searchAllPerPage(String param, int page, int limit) {
		return (List<Book>) bookDao.searchPerPage(param, page, limit);
	}

	@Override
	@Transactional
	public List<Book> searchAll(String param){
		return bookDao.search(param).list();
	}

}
