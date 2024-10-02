package com.JEE.T5WebFlux.service;

import com.JEE.T5WebFlux.model.Book;
import com.JEE.T5WebFlux.repository.BookRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }
    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }
}