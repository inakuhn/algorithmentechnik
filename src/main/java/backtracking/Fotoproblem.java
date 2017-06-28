package backtracking;

import java.util.Arrays;

public class Fotoproblem {

	private final int m;
	private final int n;
	private final int a[][];
	
	public static void main(String[] args) {
		
		int m = 4;
		int n = 5;
		int a[][] = {
				{1, 1, 0, 1, 1},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 1, 1},
				{1, 0, 1, 1, 0},
		};
		
		new Fotoproblem(a, m, n).run();
	}
	
	public Fotoproblem(int a[][], int m, int n){
		super();
		this.a = a;
		this.m = m;
		this.n = n;
	}
	
	public void run(){
		int col = 1;
		for(int i = 0; i < this.m; i++){
			for(int j = 0; j < this.n; j++){
				if(1 == this.a[i][j]) {
					color(i, j, ++col);
				}
			}
		}
		
		//print solution
		for(int i = 0; i < this.m; i++){
			System.out.println(Arrays.toString(this.a[i]));
		}
		System.out.printf("Objects: %d", col-1);
	}

	private void color(int i, int j, int col) {
		if(0 <= i && i < this.m && 0 <= j && j < this.n && 1 == this.a[i][j]){
			this.a[i][j] = col;
			color(i, j-1, col);
			color(i, j+1, col);
			color(i-1, j, col);
			color(i+1, j, col);
		}
	}
	
}
