/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Singur extends Pasager {
	Persoana p;
	
	Singur() {
		
	}
	
	@Override
	public void add(Persoana p) {
		this.p = p;
	}
	
	@Override
	public int getPrioritate() {
		return p.getPrioritate();
	}		
}
