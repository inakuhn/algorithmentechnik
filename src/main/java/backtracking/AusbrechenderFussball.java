package backtracking;

import java.util.ArrayList;
import java.util.List;

public class AusbrechenderFussball {

	public static void main(String[] args) {
		
		// m x n matrix
		int m = 4;
		int n = 4;
		
		int[][] t = {
				{1, 1, 0, 1},
				{3, 2, 1, 3},
				{4, 5, 2, 3},
				{3, 6, 1, 0}
		};
		
		// fuba startpunkt
		int l = 2;
		int c = 1;
		
		List<Integer> x = new ArrayList<Integer>();
		x.add(l * n + c);
		
		back(x, t, m, n);
	}

	private static void back(List<Integer> x, int[][] t, int m, int n) {
		int k = x.size() -1;
		int xk = x.get(k);
		if(onBorder(xk, m, n)){
			writeSolution(x, t, n);
			return;
		}
		
		int l = xk / n;
		int c = xk % n;
		int lnew, cnew, dx, dy;
		for(dx = -1; dx < 2; dx++){
			for(dy = -1; dy < 2; dy++){
				if(1 == Math.abs(dx + dy)){
					lnew = l + dx;
					cnew = c + dy;
					if(onTable(lnew, cnew, m, n) && t[lnew][cnew] < t[l][c]){
						x.add(lnew * n + cnew);
						back(x, t, m, n);
						x.remove(x.size() -1);
					}
				}
			}
		}
		
	}
	
	private static void writeSolution(List<Integer> x, int[][] t, int n){
		for(int i = 0; i < x.size(); i++){
			System.out.printf("(%d, %d) ", x.get(i) / n +1, x.get(i) % n +1);
		}
		System.out.println();
	}
	
	private static boolean onTable(int line, int col, int m, int n){
		return 0 <= line && line < m && 0 <= col && col < n;
	}
	
	private static boolean onBorder(int k, int m, int n){
		int l = k / n;
		int c = k % n;
		boolean flag = false;
		if(0 == l || m-1 == l){
			flag = true;
		}
		if(0 == c || n-1 == c){
			flag = true;
		}
		return flag;
	}
	
}
