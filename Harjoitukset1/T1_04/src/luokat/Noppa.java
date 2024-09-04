package luokat;

import java.util.Random;

public class Noppa {

	private int summa = 0;
	private int tulos = 0;
	private int heittoLkm = 0;
	private double keskiarvo = 0.0;
	private Random rand = new Random();

	public int getSumma() {
		return summa;
	}

	public int getTulos() {
		return tulos;
	}
	
	public int getHeittolkm() {
		return heittoLkm;
	}
	
	public double getKeskiarvo() {
		if (heittoLkm == 0) {
			keskiarvo = 0.0;
			return keskiarvo;
		}
		else {
			keskiarvo = summa / Double.valueOf(heittoLkm);
			return keskiarvo;
		}
	}
	public void heita() {
		tulos = rand.nextInt(6) + 1;
		summa += tulos;
		heittoLkm += 1;
	}

	public void nollaa() {
		summa = 0;
		heittoLkm = 0;
		keskiarvo = 0;
	}
}
