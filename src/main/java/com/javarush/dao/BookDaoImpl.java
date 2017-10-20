package com.javarush.dao;

import com.javarush.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
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
		Session localSession = session.getCurrentSession();
		Query bookList = localSession.createQuery("FROM Book");
		return (List<Book>) bookList.list();
	}


	@Override
	public List<Book> getAllPerPage(int page, int limitResultsPerPage) {
		Query q = session.getCurrentSession().createQuery(
				"from Book");
		q.setFirstResult(page * limitResultsPerPage);
		q.setMaxResults(limitResultsPerPage);
		return (List<Book>) q.list();
	}

	@Override
	public List<Book> searchPerPage(String param, int page, int limit){
		Query q = search(param);
		q.setFirstResult(page * limit);
		q.setMaxResults(limit);
		return q.list();
	}

	@Override
	public Query search(String param) {
		String newParam = "%"+param+"%";
		Query q = session.getCurrentSession().createQuery("FROM Book WHERE title like :param " +
				"or description like :param " +
				"or author like :param " +
				"or isbn like :param")
				.setParameter("param", newParam);
		return q;

	}

}
