package Beanit;

public class KayttajaBean_B {

	private String name;
	private String address;
	private String hometown;
	private String email;
	
	public KayttajaBean_B() {
		name = "Nimi";
		address = "Osoite";
		hometown = "Kotikaupunki";
		email = "Sposti";
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String newHometown) {
		hometown = newHometown;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
}
