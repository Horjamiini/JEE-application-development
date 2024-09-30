package beanit;

public class Tili2 implements Tili {

	private String number = "987654321-987654321";
	private double balance;
	
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public void addMoney(double amount) {
		balance += amount;
	}
	
	public void takeMoney(double amount) {
		balance += amount;
	}
	
	public void checkBalance() {
		System.out.println("Tilin " + number + " saldo on: " + getBalance());
	}
	
	public String getNumber() {
		return number;
	}
}
