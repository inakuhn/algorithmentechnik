package teileHerrsche;

import java.util.Arrays;

public class AnzahlVertauschungen {

	public static void main(String[] args) {
		
		int a[] = {5, 4, 6, 3, 1, 2};
		
		System.out.print("Initial a:");
		System.out.println(Arrays.toString(a));
		
		int count = divide(a, 0, a.length -1);
		System.out.println("Anzahl Vertauschungen: "+count);
		
		System.out.print("Result a:");
		System.out.println(Arrays.toString(a));
	}
	
	private static int divide(int a[], int l, int r){
		if(r <= l){
			return 0;
		}
		int m = (l+r) /2;

		int c1 = divide(a, l, m);
		int c2 = divide(a, m+1, r);
		int c3 = mergeAndCount(a, l, m, r);
		
		return c1 + c2 + c3;
	}

	private static int mergeAndCount(int[] a, int l, int m, int r) {
		int aux[] = new int[r+1];
		
		int i, j, k;
		
		for (i = m+1; i > l; i--) {
			aux[i-1] = a[i-1];
		}
		
		for(j = m; j < r; j++){
			aux[r+m-j] = a[j+1];
		}
		
		int n = 0;
		for (k = l; k <= r; k++) {
			if(aux[j] < aux[i]){
				a[k] = aux[j--];
				if(i <= m){
					n += m-i+1;
				}
			} else {
				a[k] = aux[i++];
			}
		}
		
		return n;
	}
	
}
