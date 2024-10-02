package com.JEE.T5WebFlux.repository;

import com.JEE.T5WebFlux.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
