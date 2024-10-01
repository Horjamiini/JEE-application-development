package com.JEET4.springbootcrudrest.entity;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "bookgroup")
@EntityListeners(AuditingEntityListener.class)
public class BookGroup {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookgroup_id")
	private Integer bookGroupId;
	
	@Column(name = "name")
	private String name;
	
	// TEhty viittaus Book-luokan bookGroup muuttujaan
	
	@OneToMany(mappedBy = "bookGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<Book>(0);
	
	public BookGroup() {
	}

	public BookGroup(String name) {
		this.name = name;
	}

	
	
	public Integer getBookGroupId() {
		return bookGroupId;
	}
	
	public void setId(Integer bookGroupId) {
		this.bookGroupId = bookGroupId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
