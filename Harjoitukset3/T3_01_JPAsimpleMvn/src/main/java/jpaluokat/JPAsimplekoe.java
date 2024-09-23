/**************
  
JPAsimplekoe.java: yksinkertainen JPA-esimerkki joka ei siis ole 
web-sovellus vaan tavallinen merkkipohjainen Java-sovellus joka
suoritetaan konsolissa. Sovellus lisää book-tauluun Book -tyyppisen olion
ja tulostaa konsoliin taulussa olevat kirjat sekä niiden lukumäärän
  
Luodaan yksinkertainen JPA-yhteys MySQL-kantaan. 
Homma tehdään tavallisessa Java-projektissa 
ilman JavaEE-sovelluspalvelinta.
  
Luo seuraavanlainen taulu kantaan jeedb1:

   CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
   PRIMARY KEY  (`id`)
   ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=0;
   
Book.java on entiteettiluokka josta luodaan book-oliot. Olio vastaa tietokannan
book-taulun tietuetta.
 
JPAsimplekoe.java on java-luokka joka käyttää JPA-yhteyttä olioiden tallentamiseen
kantaan ja hakemiseen kannasta. book-oliot menevät book-tauluun ja ne haetaan 
haetaan sieltä NamedQuery-kyselyllä.
  
Persistence.xml on JPA-yhteyden konfiguraatiotiedosto jossa on määritelty yhteyden nimi eli 
persistence unit joka on JPAsimplekoePU. Lisäksi siellä ovat tietokantayhteyden tiedot.

 *****************/
package jpaluokat;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class JPAsimplekoe {
	// alustetaan olioita jotka hoitavat kirja-olioiden tallennuksen kantaan ja
	// hakemisen sieltä
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAsimplekoePU");
		em = emf.createEntityManager();
	}

	public static void closeEntityManager() {
		em.close();
		emf.close();
	}

	public static void initTransaction() {
		tx = em.getTransaction();
	}

	public static void createBook() throws Exception {

		Book b = new Book();

		// b.setId(Long.MIN_VALUE); //voidaan laittaa id ellei taulussa ole
		// valmiiksi auto_increment. Nyt ei tarvitse koska on auto_increment
		b.setAuthor("Väinö Pannu ");
		b.setTitle("Kansalaisen keittokirja");

		initTransaction(); // JPA käyttää aina transaktiota

		tx.begin();
		em.persist(b);
		tx.commit(); // tässä tapahtuu olion tallennus kantaan

	}

	// kannassa olevan kirjan näyttäminen
	public static void showBook() throws Exception {

		// Jos kyselyssä käytetään NamedQueryä haetaan kirjat näin:
		@SuppressWarnings("unchecked")
		List<Book> results = emf.createEntityManager().createNamedQuery("Book.findAll").getResultList();

		// Jos ei käytetä NamedQueryä vaan JPA:n JPQL -kyselykieltä, haetaan näin:
		// TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);

		// ja sitten otetaan tuloksena saadut oliot List-tyyppiseen muuttujaan
		// List<Book> results = query.getResultList();

		// printataan olioiden attribuuttien arvot silmukassa
		for (Book b : results) {

			System.out.println("Kirjailija: " + b.getAuthor());
			System.out.println("Kirja: " + b.getTitle());
		}

		// printataan saatujen olioiden määrä
		System.out.println("Taulussa on kirjoja: " + results.size());
	}
	
	public static void  showBookGroup() throws Exception {
		TypedQuery<BookGroup> query = em.createQuery("SELECT b FROM BookGroup b",BookGroup.class);
		List<BookGroup> results = query.getResultList();
		
		for (BookGroup b : results) {
			System.out.println("Kirjaryhmän nimi: " + b.getName());
		}
		
		System.out.println("Taulussa on kirjaryhmiä: " + results.size());
	}
	
	public static void deleteGroup() throws Exception {
		
		
		// Kirjoijen poistaminen tehty erikseen, en saanut toimimaan niin että Cascading toimisi suoraan ja poistaisi BookGrouppiin kuuluvat
		// kirjat samalla kun ryhmä poistuu (Mikäli se edes mahdollista)
		
		initTransaction();
		tx.begin();
		
		
		
		TypedQuery<Book> deleteBooksQuery = em.createQuery("DELETE FROM Book b where b.bookGroup.bookGroupId = :bookGroupId",Book.class);
		deleteBooksQuery.setParameter("bookGroupId",1);
		deleteBooksQuery.executeUpdate();
		
		TypedQuery<BookGroup> deleteGroupQuery = em.createQuery("DELETE FROM BookGroup b WHERE b.bookGroupId = :bookGroupId",BookGroup.class);
		deleteGroupQuery.setParameter("bookGroupId", 1);
		int queryNum = deleteGroupQuery.executeUpdate();
		tx.commit();
		
		if (queryNum > 0) {
			System.out.println("BookGroup poistettu");
		}
		else {
			System.out.println("Kirja ryhmää ei poistettu");
		}
	}

	// pääohjelma jossa metodit suoritetaan
	public static void main(String args[]) {

		try {
			initEntityManager();

			createBook();
			showBook();
			showBookGroup();
			//deleteGroup();

			closeEntityManager();
		} catch (Exception e) {
		}

	}

}
