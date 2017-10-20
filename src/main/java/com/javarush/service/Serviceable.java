package com.javarush.service;

import com.javarush.model.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Serviceable<T> {
	 void add(T book);
	 void edit(T book);
	 void delete(int itemId);
	 T get(int itemId);
	 List<T> getAll();
	 List<T> getAllPerPage(int page, int limitResultsPerPage);

	List<Book> searchAllPerPage(String param, int page, int limit);

	List<Book> searchAll(String param);
}
