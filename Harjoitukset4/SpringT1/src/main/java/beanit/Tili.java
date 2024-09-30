package beanit;

public interface Tili {

	public void checkBalance();
	
	public void addMoney(double amount);
	
	public void takeMoney(double amount);
	
	// Getteri löyhää sidontaa varten, jotta saadaan loggaukssin näkyviin tilin numero
	public String getNumber();
}
