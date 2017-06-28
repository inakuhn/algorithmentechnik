package backtracking;

import java.util.Arrays;

public class NMTuermeAufsteigend {

	public static void main(String[] args) {
		
		// n x n schachbrett
		int n = 6;
		
		// m reihen
		int m = 4;
		
		int x[] = new int[m];
		int k = 0;
		boolean valid = false;
		int noSol = 0;
		
		x[k] = -1;
		while(k >= 0){
			
			valid = false;
			
			while(!valid && x[k] < n-1){
				x[k]++;
				valid = true;
				
				for(int i = 0; i < k; i++){
					if(x[k] <= x[i]){
						valid = false;
					}
				}
			}
			
			if(valid){
				if(k == m-1){
					noSol++;
					System.out.println(Arrays.toString(x));
				} else {
					x[++k] = -1;
				}
			} else {
				k--;
			}
			
		}
		
		System.out.printf("n: %d, m: %d, Solutions: %d", n, m, noSol);
	}
	
}
