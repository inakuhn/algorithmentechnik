package teileHerrsche;

import java.util.Arrays;

public final class Euklid {

	public static void main(String[] args) {
		
		// init bsp
		int[] a = {45, 60, 125, 345, 65, 9875, 4555};
		System.out.println(Arrays.toString(a));
		
		int ggT = euklid(a, 0, a.length-1);
		System.out.printf("ggT = %d", ggT);
	}
	
	private static final int euklid(int[] a, int l, int r){
		if(l < r){
			int m = (l+r) / 2;
			int x = euklid(a, l, m);
			int y = euklid(a, m+1, r);
			return gcd(x, y);
		}
		return 0 <= l && l < a.length ? a[l] : 0;
	}
	
	private static int gcd(int a, int b){
		int r;
		while(b != 0){
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	
}
