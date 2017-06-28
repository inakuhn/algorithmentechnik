package backtracking;

import java.util.Arrays;

public class NTuerme {

	public static void main(String[] args) {

		// n x n schachbrett
		int n = 5;
		
		int x[] = new int[n];
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
					if(x[k] == x[i]){
						valid = false;
					}
				}
			}

			if(valid){
				if(k == n-1){
					noSol++;
					System.out.println(Arrays.toString(x));
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
