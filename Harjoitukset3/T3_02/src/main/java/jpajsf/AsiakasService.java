/* BookService.java on sovelluksen Service eli se hoitaa 

 * yhteyden tietokantaan ja suorittaa tietokantaoperaatiot.
 * 
 * BookService ottaa yhteyden modeliin (Entity-luokka Book.java)
 * ja suorittaa sovelluslogiikan eli olioiden tallennuksen ja haun 
 * kannasta ja välittää oliot kontrollerille (BookController.java)
 * joka on tässä tapauksessa JSF Named Bean.
 *
 * BookService on EJB-lite -tyyppinen EJB, eli sijaitsee 
 * Web Containerissa muiden web-sovelluksen komponenttien kanssa,
 * toisin kuin EJB -full -tyyppiset EJB:t jotka sijaitsevat 
 * EJB -containerissa.
 * 
 * @PersistenceContext -annotaation ansiosta kantayhteys löytyy
 * ilman että tarvitsee viitata persistence.xml:ssä määriteltyyn 
 * Persistence Unitin nimeen.
 *
 */
package jpajsf;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless // tilaton ejb
@LocalBean
public class AsiakasService {

	@PersistenceContext // Bean kuuluu JPA-kontekstiin
	EntityManager em;

	// kirjan tallennus kantaan
	public void save(Asiakas asiakas) {
		this.em.persist(asiakas);
	}

	// kaikkien kirjojen haku kannasta
	public List<Asiakas> getAllAsiakkaat() {

		@SuppressWarnings("unchecked")
		List<Asiakas> kaikkiasiakkaat = this.em.createNamedQuery("Asiakas.findAll").getResultList();
		return kaikkiasiakkaat;
	}
}
