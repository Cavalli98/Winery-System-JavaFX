package utilities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Notification")
public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="mail", length=100)
	private String mail;
	@Id
	@Column(name="wine_name", length=30)
	private String wine_name;
	@Id
	@Column(name="wine_year")
	private int wine_year;

	@SuppressWarnings("unused")
	private Notification() {}
	
	public Notification(String mail, String wine_name, int year)
	{
		this.mail = mail;
		this.wine_name = wine_name;
		this.wine_year = year;
	}
	
	public String getData() {
		return (wine_name+wine_year);
	}
	
	public String getMail() {
		return mail;
	}

	public String getWine_name() {
		return wine_name;
	}

	public int getWine_year() {
		return wine_year;
	}

	protected void setMail(String mail) {
		this.mail = mail;
	}

	protected void setWine_name(String wine_name) {
		this.wine_name = wine_name;
	}

	protected void setWine_year(int wine_year) {
		this.wine_year = wine_year;
	}

	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Notification)) {
            return false;
        }
        Notification not = (Notification) o;
        return Objects.equals(mail, not.mail) &&
        	   Objects.equals(wine_name, not.wine_name) &&
        	   Objects.equals(wine_year, not.wine_year);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(mail, wine_name, wine_year);
    }
	
	@Override
	public String toString() {
		return mail;
	}
}