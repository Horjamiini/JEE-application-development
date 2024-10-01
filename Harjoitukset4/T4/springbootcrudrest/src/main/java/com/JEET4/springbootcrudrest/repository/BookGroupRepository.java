package com.JEET4.springbootcrudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JEET4.springbootcrudrest.entity.BookGroup;

public interface BookGroupRepository extends JpaRepository<BookGroup, Integer> {

}
