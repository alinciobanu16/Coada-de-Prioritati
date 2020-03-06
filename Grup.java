/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Grup extends Pasager{
	Persoana[] a;
	int idx = 0;
	
	Grup(int dim) {
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
		
		return prioritate + 5;
	}
	
	/**
	 * @param nume
	 * cauta dupa nume persoana din grupul dat si il elimina
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
