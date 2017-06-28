package dynprog;



public class Arbitrage {

	// minimal zu realisierender gewinn: 1%
	private static final float MIN_WIN = 1.01f;

	public static void main(String[] args) {
		
		int[] ns = {3, 4, 2};
		float[][] cases = {
				{1.2f, 0.89f, 0.88f, 5.1f, 1.1f, 0.15f},
				{3.1f, 0.0023f, 0.35f, 0.21f, 0.00353f, 8.13f, 200f, 180.559f, 10.339f,	2.11f, 0.089f, 0.06111f},
				{2.0f, 0.45f}
		};
		
		int numCase = 0;
		
		for (int k = 0; k < cases.length; k++) {
			float[] c = cases[k];
			int n = ns[k];
			int cidx = 0;
			
			float[][] table = new float[n][n];
			int[][] pred = new int[n][n];
			
			for(int i = 0; i < n; i++){
				table[i][i] = 1.0f;
				for(int j = 0; j < n; j++){
					if(i != j){
						table[i][j] = c[cidx++];
						pred[i][j] = i;
					}
				}
				pred[i][i] = -1;	
			}
			
			System.out.printf("Fall %d: %n", ++numCase);
			if(!findWithWarshall(n, table, pred)){
				System.out.println("Keine L�sung!");
				System.out.println("--------------");
			}
		}
		
	}

	private static boolean findWithWarshall(int n, float[][] table, int[][] pred) {
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(table[i][k] * table[k][j] > table[i][j]){
						table[i][j] = table[i][k] * table[k][j];
						pred[i][j] = pred[k][j];
						if(i == j && table[i][j] >= MIN_WIN){
							System.out.printf("W�hrung %d:%n", i+1);
							System.out.printf(" %d ", i+1);
							recoverPath(pred, i, i);
							System.out.printf(" -> %s%n", i+1);
							System.out.printf("Gewinn: %.3f%n", table[i][i]);
							System.out.println("--------------");
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	private static void recoverPath(int[][] pred, int i, int j) {
		int t = pred[i][j];
		if(i != t){
			recoverPath(pred, i, t);
			System.out.printf(" -> %s", t+1);
		}
	}
	
}
