package com.JEET4.springbootcrudrest.entity;

import javax.persistence.*;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="book")
@EntityListeners(AuditingEntityListener.class)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "author", nullable = false)
	private String author;
	
	//Vittaus Bookgroup-luokan bookgroup id muuttujaan
	// Tehty annotaation hibernate esimerkin tyylillä Entity luokkiin
	
	@ManyToOne
	@JoinColumn(name = "bookgroup_id", nullable = false)
	private BookGroup bookGroup;
	
	
	public Book() {
	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BookGroup getBookGroup() {
		return this.bookGroup;
	}
	
	public void setBookGroup(BookGroup bookgroup) {
		this.bookGroup = bookgroup;
	}
}