package backtracking;

import java.util.Arrays;

public class NDamen {

	public static void main(String[] args) {
		
		// n x n Schachbrett
		int n = 4;
		
		int noSol = 0;
		int[] x = new int[n];
		int k = 0;
		x[k] = -1;
		
		while(k >= 0){
			
			boolean valid = false;
			
			while(!valid && x[k] < n-1){
				x[k]++;
				valid = true;
				for(int i = 0; i < k; i++){
					if(x[i] == x[k] || Math.abs(x[i] - x[k]) == k - i){
						valid = false;
					}
				}
			}
			
			if(valid){
				if(k == n-1){
					System.out.println(Arrays.toString(x));
					noSol++;
				} else {
					x[++k] = -1;
				}
			} else {
				k--;
			}
		}
		
		System.out.printf("n: %d, Solutions: %d", n, noSol);
	}
	
}
