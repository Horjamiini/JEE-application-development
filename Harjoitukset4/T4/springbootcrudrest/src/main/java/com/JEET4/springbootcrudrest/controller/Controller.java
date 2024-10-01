package com.JEET4.springbootcrudrest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JEET4.springbootcrudrest.exception.ResourceNotFoundException;
import com.JEET4.springbootcrudrest.entity.Book;
import com.JEET4.springbootcrudrest.entity.BookGroup;
import com.JEET4.springbootcrudrest.repository.*;

@RestController
@RequestMapping("/api")
public class Controller {

	
	// Tehty kaikki crud mappaukset bookeille ja vain getti bookgroupille
	// En nähnyt tarpeelliseki harjoituksen puitteissa kirjoittaa käytännössä
	// samaa koodia kahteen kertaan
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookGroupRepository bookGroupRepository;
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/bookgroups")
	public List<BookGroup> getAllBookGroups() {
		return bookGroupRepository.findAll();
	}
	
	@GetMapping("books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value="id") Integer bookid) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookid)
		.orElseThrow(() -> new ResourceNotFoundException("Book not found on :: "+ bookid));
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value="id") Integer bookId,
			@Valid @RequestBody Book bookDetails ) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
		.orElseThrow(() -> new ResourceNotFoundException("Book not found on :: "+ bookId));
		
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		final Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	@DeleteMapping("/books/{id}")
	public Map<String, Boolean> deleteBook(
			@PathVariable(value="id") Integer bookId) throws Exception {
		Book book = bookRepository.findById(bookId).
				orElseThrow(() -> new ResourceNotFoundException("Book not found on ::" + bookId));
		
		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}
