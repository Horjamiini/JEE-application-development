package com.JEET4.springbootcrudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JEET4.springbootcrudrest.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
