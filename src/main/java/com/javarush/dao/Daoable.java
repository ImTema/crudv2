package com.javarush.dao;

import java.util.List;

import com.javarush.model.Book;

public interface Daoable<T> {
	 void add(T item);
	 void edit(T item);
	 void delete(int itemId);
	 T get(int itemId);
	 List<T> getAll();
}
