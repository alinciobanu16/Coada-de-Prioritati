/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Persoana {
	private String nume;
	private int varsta;
	private int prioritate = 0;
	private String id;
	private String tipBilet;
	private boolean imbarcarePrioritara = false;
	private boolean nevoiSpeciale = false;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}
	
	public String getTipBilet() {
		return tipBilet;
	}

	public void setTipBilet(String tipBilet) {
		this.tipBilet = tipBilet;
	}

	public boolean isimbarcarePrioritara() {
		return imbarcarePrioritara;
	}

	public void setimbarcarePrioritara(boolean imbarcarePrioritara) {
		this.imbarcarePrioritara = imbarcarePrioritara;
	}

	public boolean isNevoiSpeciale() {
		return nevoiSpeciale;
	}

	public void setNevoiSpeciale(boolean nevoiSpeciale) {
		this.nevoiSpeciale = nevoiSpeciale;
	}

	public String toString() {
		return nume;
	}
	
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public Persoana() {
		
	}
	
	public Persoana(String id, String nume, int varsta, String tipBilet, boolean imbarcarePrioritara, boolean nevoiSpeciale) {
		this.nume = nume;
		this.id = id;
		this.varsta = varsta;
		this.tipBilet = tipBilet;
		this.imbarcarePrioritara = imbarcarePrioritara;
		this.nevoiSpeciale = nevoiSpeciale;
	}
	
	/**
	 * @return
	 * intoarce prioritatea unei persoane
	 */
	public int getPrioritate() {
		prioritate = 0;
		if (this.varsta < 2) {
			prioritate += 20;
		}
		
		if (this.varsta >=2 && this.varsta < 5 ) {
			prioritate += 10;
		}
		
		if (this.varsta >= 5 && this.varsta <= 10) {
			prioritate += 5;
		}
		
		if (this.varsta >= 60) {
			prioritate += 15;
		}
		
		if (this.tipBilet.equals("b")) {
			prioritate += 35;
		}
		
		if (this.tipBilet.equals("p")) {
			prioritate += 20;
		}
		
		if (this.imbarcarePrioritara == true) {
			prioritate += 30;
		}
		
		if (this.nevoiSpeciale == true) {
			prioritate += 100;
		}
		
		return prioritate;	
	}
}
