/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Coada {
	
	Pasager queue[];
	int idx = 0;
	
	/**
	 * initializeaza coada de asteptare
	 * @param dim dimensiunea cozii de asteptare
	 */
	Coada(int dim) {
		queue = new Pasager[dim];
		this.idx = 0;
		
	}
	
	/**
	 * @param i pozitia i din coada
	 * @param j pozitia j din coada
	 * interschimba grupul de pasageri (Familie, Grup, Singur) de pe pozitia i cu cel de pozitia j
	 */
	private void swap(int i, int j) {
		Pasager aux;
		aux = queue[i];
		queue[i] = queue[j];
		queue[j] = aux;
	}
	
	/**
	 * insereaza grupul de pasageri (Familie, Grup, Singur) in coada de prioritate
	 * @param p grupul de pasageri care urmeaza a fi inserat in coada de prioritate
	 * @param prioritate prioritatea asociata grupului de pasageri
	 */
	public void insert(Pasager p, int prioritate) {
		queue[idx] = p;
		int length = idx;
		while (length > 0 && queue[(length - 1) / 2].getPrioritate() < queue[length].getPrioritate()) {
			swap(length, (length - 1) / 2);
			length = (length - 1) / 2;
		}
		idx++;
	}
	
	/**
	 * extrage din coada grupul de pasageri (Familie, Grup, Singur)
	 * care urmeaza sa fie imbarcat - prima pozitie din heap
	 */
	public void embark() {
		if (idx == 0) {
			return;
		}
		
		queue[0] = queue[idx - 1];
		idx--;
		max_heapify(queue, 0, idx);
	}
	
	private void max_heapify(Pasager queue[], int i, int N) {
		int left = 2 * i + 1; // left child
		int right = 2 * i + 2; // right child
		int largest;
		
		if (left <= N - 1 && queue[left].getPrioritate() > queue[i].getPrioritate()) {
			largest = left;
		} else {
			largest = i;
		}
		
		if (right <= N - 1 && queue[right].getPrioritate() > queue[largest].getPrioritate()) {
			largest = right;
		}
		
		if (largest != i) {
			swap(i, largest);
			max_heapify(queue, largest, N);
		}
	}
		
	private void RSD(int i, int q) {
		q++;
		if (i >= idx) return;
		if (q > 1) System.out.print(" ");
		System.out.print(queue[i].getId());
		RSD(2 * i + 1, q);
		RSD(2 * i + 2, q);
	}
	
	private int k = 0;
	
	/**
	 * afiseaza pasagerii printr-o parcurgere in preordine a heapului
	 */
	public void list() {
		k++;
		if (k > 1) System.out.print("\n");
		RSD(0, 0);
	}
	
	private int del;
	private String nume;
	
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	/**
	 * @return
	 * 0 - daca comanda delete este urmata doar de id in fisierul de input
	 * 1 - daca comanda delete este urmata de id si de nume in fisierul de input
	 */
	public int getDel() {
		return del;	
	}

	public void setDel(int del) {
		this.del = del;
	}

	/**
	 * @param p
	 * pasagerul care urmeaza a fi sters
	 */
	public void delete(Pasager p) {
		if (del == 0) {
			if (idx == 0) {
				return;
			}
			
			int poz = -1;
			for (int i = 0; i < idx; i++) {
				if (p.getId().equals(queue[i].getId())) {
					poz = i;
					break;
				}
			}
			
			if (poz != -1) {
				queue[poz] = queue[idx - 1];
				idx--;
				max_heapify(queue, poz, idx);
			}
		}
		
		if (del == 1) {
			if (idx == 0) {
				return;
			}
			
			int poz = -1;
			for (int i = 0; i < idx; i++) {
				if (p.getId().equals(queue[i].getId())) {
					poz = i;
					break;
				}
			}
			
			//elimin persoana din grupul de care apartine
			//refac prioritatile dupa eliminarea unei persoane
			if (queue[poz] instanceof Familie) {
				((Familie)queue[poz]).sterge(nume);
				if (((Familie)queue[poz]).getPrioritate() == -1) {
					/*
					 * daca dupa mai multe operatii de delete id nume se elimina
					 * toate persoanele din grupul de care apartin atunci elimin si grupul 
					 */
					queue[poz] = queue[idx - 1];
					idx--;
				} else {
					/*
					 * altfel refac prioritatile
					 */
					queue[poz].setPrioritate(((Familie)queue[poz]).getPrioritate());
				}
			}
			
			if (queue[poz] instanceof Grup) {
				((Grup)queue[poz]).sterge(nume);
				if ((((Grup)queue[poz]).getPrioritate()) == -1) {
					queue[poz] = queue[idx - 1];
					idx--;
				} else {
					queue[poz].setPrioritate(((Grup)queue[poz]).getPrioritate());
				}
			}
			
			if (queue[poz] instanceof Singur) {
				queue[poz] = queue[idx - 1];
				idx--;
			}
			
			max_heapify(queue, poz, idx);
		}
	}
}
