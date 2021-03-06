package com.javarush.controller;

import com.javarush.model.Book;
import com.javarush.service.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {

	@Autowired
	private Serviceable<Book> bookService;

	private static int displayPerPage=10;

	@RequestMapping(value = "/index/{page}")
	public String setupForm(@PathVariable("page") int page,
							HttpServletRequest request, Model model) {
		model.addAttribute("page", page);
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", bookService.getAllPerPage(page, displayPerPage));
		model.addAttribute("selectedBook", new Book());
		model.addAttribute("isUpd", Boolean.TRUE);
		model.addAttribute("count", bookService.getAll().size()%displayPerPage==0?
				bookService.getAll().size()/displayPerPage-1:
				bookService.getAll().size()/displayPerPage);
		model.addAttribute("searchField", "");
		return "books";
	}

	@RequestMapping({"/index", "/"})
	public String redirect(HttpServletRequest r, Model model){
		return setupForm(0, r, model);
	}


	@RequestMapping(value="/book.do/{page}")
	public String doActions(@PathVariable("page") int page,
							@ModelAttribute("book") Book book,
							@ModelAttribute("selectedBook") Book selectedBook,
							@ModelAttribute("search_field") String searchField,
							HttpServletRequest request,
							Model model){
		String action = request.getParameter("action");
		Book bookResult = new Book();
		List<Book> booksListResult=null;
		action=action==null?"":action;
		try{//i know that is bad mannered code, sorry
			switch(action.toLowerCase()){
				case "clear":return "redirect:/index";
				case "find":
					booksListResult = bookService.searchAllPerPage(searchField, page, displayPerPage);
					model.addAttribute("isUpd", Boolean.TRUE);
					break;
				case "read":
					Book searchedBook = (Book)bookService.get(selectedBook.getId());
					searchedBook.setIsReadAlready(Byte.valueOf("1"));
					bookService.edit(searchedBook);
					bookResult=book;
					model.addAttribute("isUpd", Boolean.TRUE);
					break;
				case "add":
					bookService.add(book);
					bookResult = new Book();
					model.addAttribute("isUpd", Boolean.TRUE);
					break;
				case "save":
					if(book.getId()==0)book=new Book();
					book.setIsReadAlready(Byte.valueOf("0"));
					bookService.edit(book);
					bookResult = new Book();
					model.addAttribute("isUpd", Boolean.FALSE);
					break;
				case "delete":
					bookService.delete(book.getId());
					bookResult=new Book();
					model.addAttribute("isUpd", Boolean.TRUE);
				case "update":
				case "search":
					searchedBook = (Book)bookService.get(selectedBook.getId());
					bookResult = searchedBook !=null ? searchedBook : new Book();
					model.addAttribute("isUpd", Boolean.FALSE);
					break;
				default:
					model.addAttribute("isUpd", Boolean.TRUE);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/index";
		}
		model.addAttribute("page", page);
		model.addAttribute("book", bookResult);
		model.addAttribute("searchField", searchField);
		if(searchField.isEmpty()){
			model.addAttribute("bookList", bookService.getAllPerPage(page, displayPerPage));
			model.addAttribute("count", bookService.getAll().size()%displayPerPage==0?
					bookService.getAll().size()/displayPerPage-1:
					bookService.getAll().size()/displayPerPage);
		}else{
			booksListResult = bookService.searchAllPerPage(searchField, page, displayPerPage);
			model.addAttribute("bookList", booksListResult);
			model.addAttribute("count", bookService.searchAll(searchField).size()%displayPerPage==0?
					bookService.searchAll(searchField).size()/displayPerPage-1:
					bookService.searchAll(searchField).size()/displayPerPage);
		}
		model.addAttribute("selectedBook", new Book());
		return "books";
	}
}
