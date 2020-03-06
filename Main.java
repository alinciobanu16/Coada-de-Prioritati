import java.io.File;
import java.io.*; 
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author alin_matei.ciobanu
 * Ciobanu Alin - Matei 325CB
 *
 */
public class Main {
	
	public static void main(String args[]) throws IOException {
		@SuppressWarnings("resource")
		Scanner input = new Scanner("./queue.in");
		File file  = new File(input.nextLine());
		input = new Scanner(file);
        PrintStream o = new PrintStream(new File("./queue.out"));
        System.setOut(o);
        
		int n = input.nextInt();
		input.nextLine();
		
		int nrF = 0, nrG = 0, nrS = 0, nrP = 0,  varsta;
		Coada heap = new Coada(n);
		Familie familii[] = new Familie[n];
		Grup grupuri[] = new Grup[n];
		Singur singuri[] = new Singur[n];
		
		while(input.hasNextLine()) {
			nrP++;
			String line = input.nextLine();
			String line2[] = line.split(" ");
			Persoana p = new Persoana();
			p.setId(line2[0]);
			p.setNume(line2[1]);
			varsta = Integer.parseInt(line2[2]);
			p.setVarsta(varsta);
			p.setTipBilet(line2[3]);
			if (line2[4].equals("true")) {
				p.setimbarcarePrioritara(true);
			} else {
				p.setimbarcarePrioritara(false);
			}
			if (line2[5].equals("true")) {
				p.setNevoiSpeciale(true);
			} else {
				p.setNevoiSpeciale(false);
			}
			
			if (line2[0].charAt(0) == 'f') {	
				int ok = 0;
				if (nrF > 0) {	
					for (int i = 0; i < nrF; i++) {
						if (familii[i].getId().equals(p.getId())) { 
							familii[i].add(p);
							ok = 1;
							break;
						}
					}
					
					if (ok == 0) { //daca nu o gasesc creez o familie noua
						familii[nrF] = new Familie(n);
						familii[nrF].add(p);
						familii[nrF].setId(p.getId());
						nrF++;
					}
					
				} else if (nrF == 0) {//creez prima familie
					familii[nrF] = new Familie(n);
					familii[nrF].add(p);
					familii[nrF].setId(p.getId());
					nrF++;	
				}
			}
			
			if (line2[0].charAt(0) == 'g') {
				int ok = 0;
				if (nrG > 0) {
					for (int i = 0; i < nrG; i++) {
						if (grupuri[i].a[0].getId().equals(p.getId())) { 
							//caut in vectorul de grupuri grupurl de care apatine p
							grupuri[i].add(p);
							ok = 1;
							break;
						}
					}	
					
					if (ok == 0) { //daca nu il gasesc creez un grup nou
						grupuri[nrG] = new Grup(n);
						grupuri[nrG].add(p);
						grupuri[nrG].setId(p.getId());
						nrG++;
					}
					
				} else if (nrG == 0) { //creez primul grup
					grupuri[0] = new Grup(n);
					grupuri[0].add(p);
					grupuri[nrG].setId(p.getId());
					nrG++;
				}
			}
			
			if (line2[0].charAt(0) == 's') {
				singuri[nrS] = new Singur();
				singuri[nrS].add(p);
				singuri[nrS].setId(p.getId());
				nrS++;
			}
		
			if (nrP == n) break;
		}
		
		while(input.hasNextLine()) {
			String line = input.nextLine();
			String line2[] = line.split(" ");
			
			if (line2[0].equals("insert")) {
				if (line2[1].charAt(0) == 'f') {
					for (int i = 0; i < nrF; i++) {
						if (familii[i].getId().equals(line2[1])) {
							heap.insert(familii[i], familii[i].getPrioritate());
							break;
						}
					}
				}
				
				if (line2[1].charAt(0) == 'g') {
					for (int i = 0; i < nrG; i++) {
						if (grupuri[i].getId().equals(line2[1])) {
							heap.insert(grupuri[i], grupuri[i].getPrioritate());
							break;
						}
					}
				}
				
				if (line2[1].charAt(0) == 's') {
					for (int i = 0; i < nrS; i++) {
						if (singuri[i].getId().equals(line2[1])) {
							heap.insert(singuri[i], singuri[i].getPrioritate());
							break;
						}
					}
				}
			}
			
			if (line2[0].equals("embark")) {
				heap.embark();
			}
			
			if (line2[0].equals("list")) {
				heap.list();
			}
			
			if (line2[0].equals("delete")) {
				int poz = -1;
				for (int i = 0; i < heap.idx; i++) {
					if (heap.queue[i].getId().equals(line2[1])) {
						poz = i;
						break;
					}
				}
				
				if (line2.length == 2) {
					heap.setDel(0);
				}
				
				if (line2.length == 3) {
					heap.setDel(1);
					heap.setNume(line2[2]);
				}
				
				if (poz != -1) {
					heap.delete(heap.queue[poz]);
				}		
			}
		}	
	}
}
