package beanit;

public class Asiakas {
	
	private String Name;
	
	private Tili t;
	
	public Asiakas(Tili t) {
		this.t = t;
	}
	
	public void balanceCheck() {
		t.checkBalance();
	}
	
	public void addBalance(double amount) {
		t.addMoney(amount);
	}
	
	public void decreaseBalance(double amount) {
		t.takeMoney(amount);
	}
}
