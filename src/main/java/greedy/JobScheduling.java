package greedy;

import java.util.Arrays;

public class JobScheduling {

	public static void main(String[] args) {
		
		// minimierung der !durchschnittlichen abschlusszeit! mit shortest job first
		// abschlusszeit = wartezeit + ausführungszeit
		
		// init bsp aus skript, ausführungszeiten für jobs
		int[] j = {6, 5, 3, 10, 14, 11, 20, 15, 18};

		// anzahl prozessoren
		int c = 3;
		
		
		// sortiere aufsteigend nach ausführungszeit
		Arrays.sort(j);
		
		int[][] s = new int[c][j.length / c +1];		
		int sum = 0;
		
		int index = 0;
		for(int i = 0; i < j.length; i++){
			if(i % c == 0 && i != 0){
				index++;
			}
			if(index > 0){
				s[i%c][index] = j[i] + s[i%c][index -1];
				sum += j[i] + s[i%c][index -1];
			} else {
				s[i%c][index] = j[i];
				sum += j[i];
			}
		}
		
		// mittlere abschlusszeit bestimmen
		int max = 0;
		for(int i = 0; i < c; i++){
			if(s[i][index] != 0){
				max = max <= s[i][index] ? s[i][index] : max;
			} else {
				max = max < s[i][index -1] ? s[i][index -1] : max;
			}
		}
		System.out.println("Gesamte Ausführungszeit: "+max);
		System.out.printf("Mittlere Abschlusszeit: sum Abschlusszeiten / anzahl jobs = %d / %d = %.3f", sum, j.length, (double) sum/j.length);
		
	}
	
}
