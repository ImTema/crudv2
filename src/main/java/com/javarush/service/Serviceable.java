package com.javarush.service;

import java.util.List;

import com.javarush.model.Book;

public interface Serviceable<T> {
	 void add(T book);
	 void edit(T book);
	 void delete(int itemId);
	 T get(int itemId);
	 List<T> getAll();
}
