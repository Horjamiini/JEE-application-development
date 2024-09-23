/*
 * Book-taulusta luotu entity-luokka Book.java joka vastaa
 * Book-taulun tietuetta.
 * Eclipsessä ja muissakin JEE-IDE:issä entity-luokat
 * voidaan luoda automaattisesti kannassa olevista tauluista.
 * 
 * Tässä ovat mukana myös @NamedQueryt joita ei ole pakko käyttää, sillä
 * niiden sijasta voi käyttää myös JPQL-kyselykieltä.
 * 
 */
package jpajsf;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tuito
 */
// @SuppressWarnings("restriction")
@Entity
@Table(name = "asiakkaat") // Entiteettiluokka vastaa taulua book
// @XmlRootElement
// NamedQueryt ovat abstraktoituja kyselyitä joita voidaan käyttää SQL:n sijasta
@NamedQueries({ @NamedQuery(name = "Asiakas.findAll", query = "SELECT b FROM Asiakas b"),
		@NamedQuery(name = "Asiakas.findById", query = "SELECT b FROM Asiakas b WHERE b.id = :id"),
		@NamedQuery(name = "Asiakas.findByName", query = "SELECT b FROM Asiakas b WHERE b.nimi = :nimi"),
		@NamedQuery(name = "Asiakas.findByEmail", query = "SELECT b FROM Asiakas b WHERE b.email = :email") })
public class Asiakas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	// Tässä on taulun sarakkeiden kuvaukset
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "nimi")
	private String nimi;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "osoite")
	private String osoite;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "puhelin")
	private String puhelin;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "salasana")
	private String salasana;
	

	public Asiakas() {
	}

	public Asiakas(Integer id) {
		this.id = id;
	}

	public Asiakas(Integer id, String nimi, String osoite, String puhelin, String email,String salasana) {
		this.id = id;
		this.nimi = nimi;
		this.osoite = osoite;
		this.puhelin = puhelin;
		this.email = email;
		this.salasana = salasana;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	
	public String getPuhelin() {
		return puhelin;
	}
	
	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSalasana() {
		return salasana;
	}
	
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: metodi ei toimi ellei book_id-kentällä ole arvoa
		if (!(object instanceof Asiakas)) {
			return false;
		}
		Asiakas other = (Asiakas) object;
		return !((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id)));
	}

	@Override
	public String toString() {
		return "entityClasses.Asiakas[ id=" + id + " ]";
	}

}