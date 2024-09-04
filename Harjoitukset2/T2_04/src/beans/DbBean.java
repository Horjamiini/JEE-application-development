package beans;


import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named(value = "dbBean")
@Dependent
public class DbBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String source = "C://Projects/tunnarit.txt";

	// Yhteys kantaan
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
	
	// Parsitaan kannan tiedot txt tiedostosta
	public static String readLineFile(String source, int line) throws Exception {
		  FileReader in; LineNumberReader lnr; String s ="";
		  
		  in = new FileReader(source);
		  
		  lnr = new LineNumberReader(in);
		  
		  for(int i=0; i<line;i++) s=lnr.readLine();
		  
		  in.close(); lnr.close(); return s;
		 
		}
	
	// Asiakkaiden listaus
	public List<AsiakasBean> getAsiakkaat(){
		

		List<AsiakasBean> asiakasLista = new ArrayList<AsiakasBean>();
		try {
			
			Connection conn = Connect();
			PreparedStatement pre_stmt = conn.prepareStatement("SELECT * FROM asiakkaat");
			ResultSet rs = pre_stmt.executeQuery();
			
			while(rs.next()) {
				
				AsiakasBean asiakas = new AsiakasBean();
				asiakas.setId(rs.getString("Id"));
				asiakas.setNimi(rs.getString("nimi"));
				asiakas.setOsoite(rs.getString("osoite"));
				asiakas.setPuhelin(rs.getString("puhelin"));
				asiakas.setEmail(rs.getString("email"));
				asiakasLista.add(asiakas);
			}
			
			
		} catch (Exception ex) {

			System.out.println("Tuli poikkeus" + ex);
			return null;
		}
		
		return asiakasLista;
	}
	
	public void LisaaAsiakas(String nimi, String osoite, String puhelin, String email, String salasana) {
		
		
		try {
			
			Connection conn = Connect();
			
			String sql = "INSERT INTO asiakkaat (nimi, osoite, puhelin, email, salasana)" + "VALUES (?,?,?,?,?)";
			
			PreparedStatement pre_stmt = conn.prepareStatement(sql);;
			
			pre_stmt.setString(1, nimi);
			pre_stmt.setString(2, osoite);
			pre_stmt.setString(3, puhelin);
			pre_stmt.setString(4, email);
			pre_stmt.setString(5, salasana);
			
			pre_stmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("Virhe lis‰tess‰ k‰ytt‰j‰‰" + ex);
		}
	}
	
	
}

