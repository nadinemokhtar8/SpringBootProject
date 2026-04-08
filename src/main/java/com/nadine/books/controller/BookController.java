package com.nadine.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;
import com.nadine.books.service.BookService;

import jakarta.validation.Valid;

@Controller
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/")
	public String home() {
		return "redirect:/BookList";
	}

	@GetMapping("/BookList")
	public String bookList(ModelMap modelMap,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "3") int size,
	        Authentication authentication) {

	    Page<Book> books = bookService.getAllProduitsParPage(page, size);

	    modelMap.addAttribute("books", books);
	    modelMap.addAttribute("pages", new int[books.getTotalPages()]);
	    modelMap.addAttribute("currentPage", page);
	    modelMap.addAttribute("size", size);
	    modelMap.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());

	    return "BookList";
	}

	@GetMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("book", new Book());
		List<Genre> genres = bookService.getAllGenres();
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("genres", genres);
		return "formBook";
	}

	@PostMapping("/saveProduit")
	public String saveProduit(@Valid @ModelAttribute("book") Book book,
	                          BindingResult bindingResult,
	                          @RequestParam(name = "page", defaultValue = "0") int page,
	                          @RequestParam(name = "size", defaultValue = "2") int size,
	                          ModelMap modelMap) {
	    int currentPage;
	    boolean isNew = book.getIdBook() == null;

	    if (bindingResult.hasErrors()) {
	    	modelMap.addAttribute("mode", isNew ? "new" : "edit");
	    	modelMap.addAttribute("genres", bookService.getAllGenres());
	        return "formBook";
	    }

	    bookService.saveProduit(book);

	    if (isNew) {
	        Page<Book> books = bookService.getAllProduitsParPage(page, size);
	        currentPage = books.getTotalPages() - 1;
	    } else {
	        currentPage = page;
	    }
	    return "redirect:/BookList?page=" + currentPage + "&size=" + size;
	}

	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		bookService.deleteProduitById(id);
		Page<Book> books = bookService.getAllProduitsParPage(page, size);
		modelMap.addAttribute("books", books);
		modelMap.addAttribute("pages", new int[books.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "BookList";
	}

	@GetMapping("/BookList/supprimerProduit")
	public String supprimerProduitFromList(@RequestParam("id") Long id,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		return supprimerProduit(id, modelMap, page, size);
	}

	@RequestMapping("/modifierLivre")
	public String editerProduit(@RequestParam("id") Long id,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		Book p = bookService.getProduit(id);
		List<Genre> genres = bookService.getAllGenres();
		modelMap.addAttribute("book", p);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		modelMap.addAttribute("genres", genres);
		return "formBook";
	}

	@GetMapping("/BookList/modifierLivre")
	public String editerProduitFromList(@RequestParam("id") Long id,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		return editerProduit(id, modelMap, page, size);
	}
}
