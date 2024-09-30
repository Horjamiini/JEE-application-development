package beanit;

public class Tili1 implements Tili {
	
	private String number = "123456789-123456789";
	private double balance;
	
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addMoney(double amount) {
		balance += amount;
	}
	
	public void takeMoney(double amount) {
		balance -= amount;
	}
	
	public void checkBalance() {
		System.out.println("Tilin " + number + " saldo on: " + getBalance());
	}
	
	public String getNumber() {
		return number;
	}
}
