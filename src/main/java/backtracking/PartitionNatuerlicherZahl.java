package backtracking;

public final class PartitionNatuerlicherZahl {

	public static void main(String[] args) {
		
		int n = 4;
		partition(n);
		
	}
	
	private static final void partition(int n){
		int s[] = new int[n+1];
		int x[] = new int[n+1];

		boolean isSucc = false;
		int k = 1;
		int no = 0;
		
		while(k > 0){
			
			do{
				isSucc = isSuccessor(x[k], n);
				if(isSucc){
					x[k]++;
					s[k] = s[k-1] + x[k];
				}
			} while(isSucc && !isCandidate(s[k], n));
			
			if(isSucc){
				if(s[k] == n){
					no++;
					printSolution(x, n);
				} else {
					++k;
					x[k] = x[k-1] -1;
				}
			} else {
				k--;
			}
			
		}
		
		System.out.println("Number of Solutions: "+no);
	}

	private static final boolean isCandidate(int s, int n){
		return s <= n;
	}
	
	private static final boolean isSuccessor(int x, int n){
		return x < n;
	}

	private static final void printSolution(int[] x, int n) {
		System.out.print(x[1]);
		int sum = x[1];
		for (int i = 2; i < x.length; i++) {
			if(sum + x[i] <= n){
				sum += x[i];
				System.out.print(" + " + x[i]);
			}
		}
		System.out.println();
	}

}
