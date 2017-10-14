package com.javarush.controller;

import java.util.Map;

import com.javarush.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javarush.service.Serviceable;

@Controller
public class BookController {

	@Autowired
	private Serviceable<Book> bookService;
	
	@RequestMapping(value = "/index")
	public String setupForm(Map<String, Object> map, Model model){
		Book book = new Book();
		map.put("selectedBook", new Book());
		map.put("book", new Book());
		map.put("bookList", bookService.getAll());
		map.put("isUpdating", false);
//		model.addAttribute("book", book);
//		model.addAttribute("bookList", bookService.getAll());
		return "books";
	}
	@RequestMapping(value = "/book.do", method = RequestMethod.GET)
	public String f5(Map<String, Object> map, Model model){
		return setupForm(map, model);
	}
	@RequestMapping(value="/book.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute Book book,@ModelAttribute Book selectedBook, BindingResult result, @RequestParam String action, Map<String, Object> map){
		Book bookResult = new Book();
		book=selectedBook!=null?selectedBook:book;
		System.out.println("id="+book.getId());
		System.out.println("author="+book.getAuthor());
		System.out.println("description="+book.getDescription());
		System.out.println("title="+book.getTitle());
		switch(action.toLowerCase()){	//only in Java7 you can put String in switch
		case "add":
			bookService.add(book);
			bookResult = new Book();
			map.put("isUpdating", false);
			break;
		case "save":
			bookService.edit(book);
			bookResult = book;
			map.put("isUpdating", false);
			break;
		case "delete":
			bookService.delete(book.getId());
			bookResult = new Book();
			map.put("isUpdating", false);
			break;
		case "update":
		case "search":
			map.put("isUpdating", true);
			Book searchedBook = (Book)bookService.get(book.getId());
			bookResult = searchedBook !=null ? searchedBook : new Book();
			break;

		}
		map.put("selectedBook", new Book());
		map.put("book", bookResult);
		map.put("bookList", bookService.getAll());
		return "books";
	}
}
