package teileHerrsche;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		
		// init bsp
		int[] a = {345, 67, 13, 789, 90, 76, -45};
		System.out.println(Arrays.toString(a));
		
		quickSort(a, 0, a.length-1);
		
		System.out.println(Arrays.toString(a));
	}
	
	private static void quickSort(int[] a, int l, int r){
		if(r <= l){
			return;
		}
		int i = partition(a, l, r);
		quickSort(a, l, i-1);
		quickSort(a, i+1, r);
	}

	private static int partition(int[] a, int l, int r) {
		int i = l-1;
		int j = r;
		int pivot = a[r];
		for(;;){
			while(a[++i] < pivot){}
			while(a[--j] > pivot){
				if(j == l){
					break;
				}
			}
			if(i >= j){
				break;
			}
			swap(a, i, j);
		}
		swap(a, i, r);
		return i;
	}
	
	private static void swap(int[] a, int i, int j){
		int r = a[i];
		a[i] = a[j];
		a[j] = r;
	}
	
}
