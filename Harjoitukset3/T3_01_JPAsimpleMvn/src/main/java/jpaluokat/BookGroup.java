package jpaluokat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "bookgroup")
public class BookGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "bookgroup_id")
	private Integer bookGroupId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 1, max = 30)
	@OneToMany(mappedBy = "bookGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books = new ArrayList<>();
	
	
	public BookGroup() {
		
	}
	
	public BookGroup(int bookGroupId, String name) {
		this.bookGroupId = bookGroupId;
		this.name = name;
	}
	
	public int getBookGroupId() {
		return bookGroupId;
	}
	
	public void setBGroupId(int bGroupId) {
		this.bookGroupId = bGroupId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

}
