package com.javarush.dao;

import com.javarush.model.Book;
import org.hibernate.query.Query;

import java.util.List;

public interface Daoable<T> {
	 void add(T item);
	 void edit(T item);
	 void delete(int itemId);
	 T get(int itemId);
	 List<T> getAll();
	List<T> getAllPerPage(int page, int limitResultsPerPage);
	List<Book> searchPerPage(String param, int page, int limit);
	Query search(String param);
}
