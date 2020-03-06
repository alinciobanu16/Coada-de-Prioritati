/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Familie extends Pasager {
	
	Persoana[] a;
	int idx = 0;
	
	Familie(int dim) {
		a = new Persoana[dim];
	}

	@Override
	public void add(Persoana p) {
		a[idx] = p;
		idx++;
	}
	
	@Override
	public int getPrioritate() {
		if (idx == 0) {
			return -1;
		}
		int prioritate = 0;
		for(int i = 0; i < idx; i++) {
			prioritate += a[i].getPrioritate();
		}
		
		return prioritate + 10;
	}
	
	
	/**
	 * @param nume
	 * cauta dupa nume Persoana si o elimina
	 */
	public void sterge(String nume) {
		int poz = -1;
		for (int i = 0; i < idx; i++) {
			if (a[i].getNume().equals(nume)) {
				poz = i;
				break;
			}
		}
		
		if (poz != -1) {
			a[poz] = a[idx - 1];
			idx--;
		}
	}
}
