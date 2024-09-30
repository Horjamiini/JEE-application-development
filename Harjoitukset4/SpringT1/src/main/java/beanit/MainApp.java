package beanit;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfig.xml")) {
			Asiakas a = (Asiakas) ctx.getBean("Asiakas");
			
			a.balanceCheck();
			a.addBalance(100);
			a.balanceCheck();
			a.decreaseBalance(500);
			a.balanceCheck();
		}
	}

}
