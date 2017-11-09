package github.soltaufintel.tankstellen.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import de.mwvb.maja.web.Escaper;

@Entity
public class Tankstelle {
	@Id
	private String id;
	@Indexed
	private String userId;
	private int nummer;
	private String bezeichnung;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public String getUrl() {
		return id + "/" + Escaper.toPrettyURL(bezeichnung);
	}
}
