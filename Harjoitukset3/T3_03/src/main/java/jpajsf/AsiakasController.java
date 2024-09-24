
/* BookController toimii tiedon välittäjänä viewin (index.xhtml) 
 * ja Servicen (BookService.java) välillä.
 * 
 * Kontrollerin tehtävänä on siis ottaa vastaan käyttäjän syöte viewistä
 * ja päivittää viewiä välittämällä sinne kannasta tuleva data. 
 * Arkkitehtuurin kannalta on parempi että kontrolleri ei sisällä 
 * sovelluslogiikkaa vaan se hoidetaan Servicessä. 
 * 
 */
package jpajsf;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//import jpajsf.Asiakas; //ei tarvitse importata kun on samassa paketissa
@Named(value = "asiakasController")
@RequestScoped
public class AsiakasController implements Serializable {

	private static final long serialVersionUID = 1L;
	// Service injektoidaan kontrolleriin tässä
	@EJB
	AsiakasService as;
	private Asiakas asiakas;

	public AsiakasController() {
		this.asiakas = new Asiakas();
	}

	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	/*
	 * getAsiakas()-metodin avulla otetaan vastaan käyttäjän syöte viewistä. Tätä
	 * metodia ei pitäisi käyttää muuhun tarkoitukseen.
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/*
	 * Metodi joka kutsuu BookServicen saveAsiakas() -metodia. Tallennus tapahtuu
	 * BookServicessä
	 */
	public void saveAsiakas() {
		this.as.save(asiakas);

	}

	/*
	 * Metodi joka kutsuu AsiakasServicen getAllAsiakkaat() -metodia. Tietokantahaku
	 * tapahtuu AsiakasServicessä.
	 */
	public List<Asiakas> listAllAsiakkaat() {

		List<Asiakas> allAsiakkaat = this.as.getAllAsiakkaat();

		return allAsiakkaat;

	}
}
