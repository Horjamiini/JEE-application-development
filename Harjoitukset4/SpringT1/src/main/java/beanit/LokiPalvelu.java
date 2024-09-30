package beanit;

import java.util.Date;

public class LokiPalvelu {

	Date date;
	private Tili t;
	
	public LokiPalvelu(Tili t) {
		this.date = new Date();
		this.t = t;
	}

    public void logAdd() {
        System.out.println("Tilille "+ t.getNumber() +" on tallennettu rahaa: "+ date.toString());
        
    }

    public void logTake() {
        System.out.println("Tililtä " + t.getNumber() + " on nostettu rahaa: "+ date.toString());
    }
}