package application;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import javafx.collections.ObservableList;
import utilities.TableElement;

/**
 * The Class is used to represent an employee of the winery.
 * It can add wines to the catalog and is advise whenever a type of wine finishes.
 * 
 * @author Dario Cavalli
 *
 */

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@Column(name="identification", length=20)
	private String identification;
	@Transient
	private Winery winery;

	public Employee() {
		identification = null;
		winery = null;
	}
	
	public Employee(String code, Winery winery) {
		identification = code;
		this.winery = winery;
	}

	public Employee(String code) {
		identification = code;
	}
	
	protected void hire(Winery winery) {
		this.winery = winery;
	}
	
	/**
	 * Use this method to add some bottle of wine to the catalog of the winery.
	 * 
	 * @param bottle An array containing the bottles the employee want to add.
	 */
	public void addWine(Bottle[] bottle)
	{
		if (identification != null && winery != null)
			winery.addWine(bottle);
	}

	protected String getIdentification() {
		return identification;
	}

	public Set<String> getWineToAdd() {
		return winery.getWineToAddSet();
	}
	
	public void setWineToAdd(String s) {
		winery.databaseRemoveToAddBottle(s);
	}
	
	public ObservableList<TableElement> getToSendList() {
		return winery.getToSendList();
	}
	
	public Winery getWinery() {
		return winery;
	}

	public void setWinery(Winery winery) {
		this.winery = winery;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee user = (Employee) o;
        return Objects.equals(identification, user.identification);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(identification);
    }
	
	@Override
	public String toString() {
		return identification;
	}

	public void send(ObservableList<TableElement> obs_temp) {
		winery.sendBottle(obs_temp);
	}


}
