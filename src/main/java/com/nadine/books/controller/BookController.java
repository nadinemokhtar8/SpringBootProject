package com.nadine.books.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadine.books.genres.Book;
import com.nadine.books.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/BookList")
	public String BookList(ModelMap modelMap,
	        @RequestParam(name="page", defaultValue="0") int page,
	        @RequestParam(name="size", defaultValue="3") int size) {

	    Page<Book> books = bookService.getAllProduitsParPage(page, size);

	    modelMap.addAttribute("books", books);             
	    modelMap.addAttribute("pages", new int[books.getTotalPages()]);
	    modelMap.addAttribute("currentPage", page);

	    return "BookList";
	}

	@GetMapping("/showCreate")
	public String showCreate() {
		return "createProduit";
	}

	@GetMapping("/saveProduit")
	public String saveProduit(@ModelAttribute("books") Book book, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
//conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		book.setDateCreation(dateCreation);

		Book saveProduit = bookService.saveProduit(book);
		String msg = "produit enregistré avec Id " + saveProduit.getIdBook();
		modelMap.addAttribute("msg", msg);
		return "createProduit";
	}
	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id,
	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
	bookService.deleteProduitById(id);
	Page<Book> books = bookService.getAllProduitsParPage(page, 
			size);
			modelMap.addAttribute("books", books);
			modelMap.addAttribute("pages", new int[books.getTotalPages()]);
			modelMap.addAttribute("currentPage", page);
			modelMap.addAttribute("size", size);
			return "BookList";
			}
	@RequestMapping("/modifierProduit")
	public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		Book p = bookService.getProduit(id);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("produit", p);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		
		return "formProduit";
	}
}