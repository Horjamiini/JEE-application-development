package com.jsf.classes;


import java.io.Serializable;
import java.util.Random;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("diceRoll")
@SessionScoped
public class DiceRoll implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	public int getHeittoLkm() {
		return heittoLkm;
	}
	
	public void heita(ActionEvent ae) {
		tulos = rand.nextInt(6) + 1;
		summa += tulos;
		heittoLkm += 1;
	}
	
	public void nollaa(ActionEvent ae) {
		summa = 0;
		heittoLkm = 0;
		keskiarvo = 0;
		tulos = 0;
	}
	
}
