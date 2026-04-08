package com.nadine.books.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nadine.books.dto.BookDTO;
import com.nadine.books.service.BookService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookRESTController {
	@Autowired
	BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<BookDTO> getAllProduits() {
		return bookService.getAllBooks();
	 } 		
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public BookDTO getProduitById(@PathVariable("id") Long id) {	
		return bookService.getBook(id);
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public BookDTO createProduit(@RequestBody BookDTO bookDTO) {
		return bookService.saveProduit(bookDTO);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public BookDTO updateProduit(@RequestBody BookDTO bookDTO) {
		return bookService.updateProduit(bookDTO);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id)
	{
		bookService.deleteProduitById(id);
	}
	
	@RequestMapping(value="/prodscat/{idCat}",method = RequestMethod.GET)
	public List<BookDTO> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
		return bookService.findByGenreIdG(idCat).stream()
				.map(bookService::convertEntityToDto)
				.collect(Collectors.toList());
	 }


	
	
}
