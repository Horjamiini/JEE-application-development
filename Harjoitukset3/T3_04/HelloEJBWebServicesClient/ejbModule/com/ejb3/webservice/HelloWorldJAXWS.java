/**
 * HelloWorldJAXWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ejb3.webservice;

public interface HelloWorldJAXWS extends java.rmi.Remote {
    public java.lang.String sayHello(java.lang.String name) throws java.rmi.RemoteException;
    public void initiate() throws java.rmi.RemoteException;
    public com.ejb3.webservice.Book saveBook(com.ejb3.webservice.Book book) throws java.rmi.RemoteException;
    public com.ejb3.webservice.Book[] getAllBooks() throws java.rmi.RemoteException;
}
