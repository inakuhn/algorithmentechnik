package backtracking;

public class SpringerAlleWege {

	private final int[][] a;
	private int noSol;
	private final int n;
	
	public static void main(String[] args) {
		
		// 5x5 schachbrett
		int n = 5;
		
		// startposition line / column (array index!)
		int l = 3;
		int c = 1;
		
		new SpringerAlleWege(n).run(l, c);
	}
	
	public SpringerAlleWege(int n){
		this.n = n;
		this.a = new int[n][n];
	}
	
	public void run(int l, int c){
		this.noSol = 0;
		this.a[l][c] = 1;
		back(l, c, 1);
	}
	
	private void back(int l, int c, int step){
		if(this.n * this.n == step){
			writeSolution();
			return;
		}
		
		int dx, dy, lnew, cnew;
		for(dx = -2; dx < 3; dx++){
			for(dy = -2; dy < 3; dy++){
				if(Math.abs(dx * dy) == 2){
					lnew = l + dx;
					cnew = c + dy;
					if(0 <= lnew && lnew < this.n && 0 <= cnew && cnew < this.n && this.a[lnew][cnew] == 0){
						this.a[lnew][cnew] = step +1;
						back(l + dx, c + dy, step +1);
						this.a[lnew][cnew] = 0;
					}
				}
			}
		}
		
	}

	private void writeSolution() {
		System.out.printf("Solution: %d\n", ++this.noSol);
		for(int i = 0; i < this.n; i++){
			for(int j = 0; j < this.n; j++){
				System.out.printf("%3d ", this.a[i][j]);
			}
			System.out.println();
		}
	}
	
}
