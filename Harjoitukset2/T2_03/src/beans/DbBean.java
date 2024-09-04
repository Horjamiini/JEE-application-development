/*
 * DbBean on papu jossa tehdään tietokantaoperaatiot
 * Yleensä JSF-sovelluksissa käytetään kannan käsittelyyn 
 * JPA:ta, mutta tässä on homma tehty tavallisella JDBC:llä.
 * 
 * Tämä papu toimii yhdessä userBean -pavun kanssa. Niiden välillä
 * on riippuvuus ja tämä papu on liitetty @inject -annotaatiolla (CDI)
 * userBeaniin
 * 
 *Sovellus on laitettu toimimaan ilman tietokantaa mutta jos haluat 
 *testata tiedon hakua kannasta, tarvitset seuraavanlaisen taulun:
 * 

 CREATE TABLE `users` (
 `username` varchar(20) NOT NULL ,
 `password` varchar(20) NOT NULL ,
 `ID` int(11) NOT NULL auto_increment,
 PRIMARY KEY  (`ID`)
 ) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=0;

 * 
 */
package beans;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/* @Dependent (riippuvainen) tarkoittaa että beanilla ei ole varsinaista scopea, vaan
DbBean on käytössä silloin kun toinen bean tarvitsee sitä */
@Named(value = "dbBean")
@Dependent
public class DbBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private static String source = "C://Projects/tunnarit.txt";
	
	

	// alustetaan viesti tyhjäksi ettei tule sen puuttumisesta virheilmoitusta
	public DbBean() {
		this.message = "";
	}

	/*
	 * Yhteydenotto kantaan vanhaan JDBC-tyyliin
	 */
	public Connection Connect() throws Exception {

		Connection conn;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		// tietokannan osoite ja tietokannan nimi
		String url = readLineFile(source, 1); String tunnari = readLineFile(source, 2); String salasana = readLineFile(source, 3);

		try {
			// kannan tunnari ja salasana
			conn = (Connection) DriverManager.getConnection(url, tunnari, salasana);
			
			return conn;

		} catch (SQLException e) {
			System.out.println(e);
			return null;

		}

	}
	
	public static String readLineFile(String source, int line) throws Exception {
		  FileReader in; LineNumberReader lnr; String s ="";
		  
		  in = new FileReader(source);
		  
		  lnr = new LineNumberReader(in);
		  
		  for(int i=0; i<line;i++) s=lnr.readLine();
		  
		  in.close(); lnr.close(); return s;
		 
		}

	/*
	 * Haetaan kannasta dataa sisääntulevan tiedon perusteella palauttaa String
	 * message tai null, riippuen siitä onnistuuko kysely. Avaa importeissa ja alla
	 * olevat kommentit ja luo taulu kantaan, niin sovellus toimii kannan kanssa.
	 */
	public String getMessage(String username) {

		try {
			
			Connection conn = Connect();

			// Tässä tietokantoperaatiot joilla haetaan dataa users-taulusta

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "';");

			int rivilkm = 0;
			while (rs.next()) {
			rivilkm++;
			}

			/*
			 * Tämä esimerkki on laitettu toimimaan niin ettei tarvitse käyttää tietokantaa.
			 * Kokeile syöttää käyttöliittymän username-kenttään "username" tai jokin muu
			 * arvo
			 */
			if (rivilkm == 1) {

				message = "On jo k�yt�ss�, valitse joku muu!";
				
				
				
			} else {

				message = "K�ytett�viss�!";
				
			}

			return message;

		} catch (Exception ex) {

			System.out.println("Tuli poikkeus" + ex);

			return null;
		}
		
		

	}
	// K�ytt�jien tallennus
	public void saveUser(String username, String password) {
		
		// save metodiin esto tallentamiselle jos k�ytt�j� jo olemassa samaisella nimell�

		try {
			
			
			Connection conn = Connect();
			
			String sql = "INSERT INTO users (Username, Password)" + "VALUES (?,?)";
			
			
			PreparedStatement pre_stmt = conn.prepareStatement(sql);
			
			pre_stmt.setString(1,username);
			pre_stmt.setString(2, password);
			
			pre_stmt.executeUpdate();
			
			

			
		} catch (Exception ex) {

			System.out.println("Tuli poikkeus tallennuksessa" + ex);
		}
		
	}

	
}
