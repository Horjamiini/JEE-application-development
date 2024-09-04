package com.jsf.authentication;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

 
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validateUserLogin() {
		String navResult = "";
		System.out.println("Entered Username is= " + userName + ", password is= " + password);
		if (userName.equalsIgnoreCase("samu") && password.equals("1234")) {
			navResult = "success";
		}
		else {
			navResult = "failure";
		}
		return navResult;
	}
}
