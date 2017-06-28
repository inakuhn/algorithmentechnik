package backtracking;

import java.util.Arrays;

public class Referate {

	public static void main(String[] args) {
		
		// n <= m: n schüler, m themen
		int n = 3;
		int m = 2;
		
		int[] x = new int[n];
		int xIdx = 0;
		int[] vMark = new int[n];
		boolean valid = false;
		int noSol = 0;
		
		x[0] = -1;
		while(xIdx >= 0){
			int k = xIdx;
			valid = false;
			
			while(!valid && x[k] < m-1){
				
				if(x[k] >= 0){
					vMark[x[k]]--;
				}
				vMark[++x[k]]++;
				valid = true;
				
				int noMarked = 0;
				for(int i = 0; i <= k; i++){
					if(vMark[i] != 0){
						noMarked++;
					}
				}
				if(noMarked + n-1-k < m){
					valid = false;
				}
			}
			
			if(valid){
				if(k == n-1){
					noSol++;
					System.out.println(Arrays.toString(x));
				} else {
					x[++xIdx] = -1;
				}
			} else {
				if(x[k] >= 0){
					vMark[x[k]]--;
				}
				xIdx--;
			}
		}
		
		System.out.printf("n: %d, m: %d, Solutions: %d", n, m, noSol);
	}
	
}
