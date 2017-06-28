package teileHerrsche;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		
		// init bsp
		int[] a = {345, 67, 13, 789, 90, 76, -45};
		System.out.println(Arrays.toString(a));
		
		mergeSort(a, 0, a.length-1);
		
		System.out.println(Arrays.toString(a));
	}

	private static void mergeSort(int[] a, int l, int r) {
		if(r <= l){
			return;
		}
		int m = (l+r) / 2;
		mergeSort(a, l, m);
		mergeSort(a, m+1, r);
		merge(a, l, m, r);
	}

	private static void merge(int[] a, int l, int m, int r) {
		int i = m+1; 
		int	j = m; 
		int	k;
		int[] aux = new int[r+1];
		
		while(i > l){
			aux[--i] = a[i];
		}
		while(j < r){
			aux[r+m-j] = a[++j];
		}
		for(k = l; k <= r; k++){
			if(aux[i] > aux[j]){
				a[k] = aux[j--];
			} else {
				a[k] = aux[i++];
			}
		}
	}
	
}
