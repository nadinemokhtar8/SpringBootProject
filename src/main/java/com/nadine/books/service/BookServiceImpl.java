package com.nadine.books.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nadine.books.dto.BookDTO;
import com.nadine.books.genres.Book;
import com.nadine.books.genres.Genre;
import com.nadine.books.repos.BookRepository;
import com.nadine.books.repos.GenreRepository;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ModelMapper modelMapper;

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ── DTO CRUD ─────────────────────────────────────────────

    @Override
    public BookDTO saveProduit(BookDTO p) {
        return convertEntityToDto(bookRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public BookDTO updateProduit(BookDTO p) {
        return convertEntityToDto(bookRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public BookDTO getBook(Long id) {
        return convertEntityToDto(bookRepository.findById(id).get());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // ── Entity CRUD ──────────────────────────────────────────

    @Override
    @Transactional
    public Book saveProduit(Book p) {
        return bookRepository.save(p);
    }

    @Override
    public Book updateProduit(Book p) {
        return bookRepository.save(p);
    }

    @Override
    public void deleteProduit(Book p) {
        bookRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getProduit(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllProduits() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getAllProduitsParPage(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    // ── Query Methods ────────────────────────────────────────

    @Override
    public List<Book> findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    @Override
    public List<Book> findByBookNameContains(String name) {
        return bookRepository.findByBookNameContains(name);
    }

    @Override
    public List<Book> findByBookNameAndPrice(String name, Double price) {
        return bookRepository.findByBookNameAndPrice(name, price);
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByGenreIdG(Long id) {
        return bookRepository.findByGenreIdG(id);
    }

    @Override
    public List<Book> findByOrderByBookNameAsc() {
        return bookRepository.findByOrderByBookNameAsc();
    }

    @Override
    public List<Book> trierBookNameBookPrice() {
        return bookRepository.trierBookNameBookPrice();
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // ── Conversions ──────────────────────────────────────────

    @Override
    public BookDTO convertEntityToDto(Book book) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book convertDtoToEntity(BookDTO bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}