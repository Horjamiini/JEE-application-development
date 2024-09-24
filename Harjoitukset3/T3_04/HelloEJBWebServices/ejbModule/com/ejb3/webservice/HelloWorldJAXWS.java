package com.ejb3.webservice;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;



/**
 * Session Bean implementation class HelloWorldJAXWS
 */
@WebService
@Stateless
@LocalBean
public class HelloWorldJAXWS {
    /**
     * Default constructor. 
     */
    public HelloWorldJAXWS() {
       
    }
    
    private List<Book> books = new ArrayList<>();
    
    @WebMethod
	public void initiate() {
		books.add(new Book(1, "Kansalaisen Keittokirja", "Väinö Pannu"));
		books.add(new Book(2, "Avaruuden Limanuljaskat", "John Johnson"));
		books.add(new Book(3, "Jännitystä Jyvässeudulla", "Auvo Jyväjemmari"));
	}
    
    @WebMethod
    public String sayHello(@WebParam(name = "name") String name) {
        return "Hello " + name + "!";
    }
    
    @WebMethod
	public List<Book> getAllBooks() {
		return books;
	}
    
    @WebMethod
	public Book saveBook(@WebParam(name = "book") Book book) {
		books.add(book);
		return book;
	}

}
