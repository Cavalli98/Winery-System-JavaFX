package utilities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="WineToSend")
public class TableElement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="mail", length= 100)
	private String mail;
	@Id
	@Column(name="name", length= 100)
	private String name;
	@Id
	@Column(name="year")
	private int year;
	@Column(name="quantity")
	private int quantity;
	
	@SuppressWarnings("unused")
	private TableElement() {}
	
	public TableElement(String mail, String name, int year, int quantity) {
		this.mail = mail;
		this.name = name;
		this.year = year;
		this.quantity = quantity;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof TableElement)) {
            return false;
        }
        TableElement te = (TableElement) o;
        return Objects.equals(mail, te.mail) &&
        	   Objects.equals(name, te.name) &&
        	   Objects.equals(year, te.year);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(mail, name, year);
    }
	
	@Override
	public String toString() {
		return mail + " " + name + " " + year;
	}
}
