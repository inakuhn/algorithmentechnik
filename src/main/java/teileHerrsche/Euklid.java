package teileHerrsche;

import java.util.Arrays;

public final class Euklid {

	public static void main(String[] args) {
		
		// init bsp
		int[] a = {60,72};
		System.out.println(Arrays.toString(a));
		int klein = findMin(a,0 ,a.length -1 );
		int big = findMax(a,0, a.length -1);
		int ggT = euklid(a, 0, a.length-1);
		System.out.println("ggT = "+ggT);
		System.out.println("Klein = "+klein);
		System.out.println("Großte = "+big);
	}
	
	private static final int euklid(int[] a, int left, int right){
		if(left < right){
			int m = (left+right) / 2;
			int x = euklid(a, left, m);
			int y = euklid(a, m+1, right);
			return gcd(x, y);
		}
		return 0 <= left && left < a.length ? a[left] : 0;
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
	public static int findMax(int[] a, int left, int right) {
		if (left <right) {
			int m = (left + right) / 2;
			int x = findMax(a, 0, m);
			int y = findMax(a,m+1,right);
			return Math.max(x,y);
		} else {
			return  0 <= left && left < a.length ? a[left] : 0;
		}
	}
	public static int findMin(int[] a, int left, int right) {
		if (left < right) {
			int m = (left + right) / 2;
			int x = findMin(a, 0, m);
			int y = findMin(a,m+1,right);
			return Math.min(x,y);
		} else {
			return  0 <= left && left < a.length ? a[left] : 0;
		}

	}
}
