package application;

import javax.persistence.*;


/**
 * The Class represent a bottle of wine, including its name and year as well as 
 * some features and all the vines where the wine came from.
 * 
 * @author Dario Cavalli
 *
 */

@Entity
@Table(name="Bottle")
public class Bottle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "id_bottle")
	private Long id;
	@Column (name = "year")
	private int year;
	@Column (name = "name")
	private String name;
	@Column (name = "features")
	private String features;
	@Column (name = "vines")
	private String vines;
	
	public void setYear(int year) {
		this.year = year;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public void setVines(String vines) {
		this.vines = vines;
	}

	public Bottle() {}
	
	/**
	 * Constructor of Bottle.
	 * 
	 * @param year The year of the wine.
	 * @param name The name of the wine.
	 * @param features Some technical characteristic of the wine.
	 * @param vines The vines where the wine came from.
	 */
	public Bottle(int year, String name, String features, String vines) {
		this.year = year;
		this.name = name;
		this.features = features;
		this.vines = vines;
	}

	public int getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	public String getFeatures() {
		return features;
	}
	
	public String getVines() {
		return vines;
	}
	
	public boolean equalsNameandDate(Bottle b) {
		return (this.name == b.getName() && this.year == b.getYear());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name + " " + year;
	}
}
