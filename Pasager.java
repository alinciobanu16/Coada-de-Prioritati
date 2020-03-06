/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public abstract class Pasager {
	private String id;
	private int prioritate;
	
	Pasager() {
	}
	
	public int getPrioritate() {
		return this.prioritate;
	}

	public void setPrioritate(int prioritate) {
		this.prioritate = prioritate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public abstract void add(Persoana p);
}
