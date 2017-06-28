package backtracking;

public class HausVomNikolaus {

	private final int[][] a = {
			{ 0, 1, 1, 0, 1 },
			{ 1, 0, 1, 0, 1 },
			{ 1, 1, 0, 1, 1 },
			{ 0, 0, 1, 0, 1 },
			{ 1, 1, 1, 1, 0 }
		};

	private final int[] b = new int[9];
	private int sol = 0;
	
	
	public static void main(String[] args) {
		
		/*	 4		Nummerierung der Ecken
		 *   /\		a enthält 1 wenn zwischen
		 * 	5--3	a[i][j] kante im graph existiert
		 *  |\/|
		 *  |/\|
		 *  1--2
		 */
		
		new HausVomNikolaus().run();
	}


	private void run() {
		back(1);
	}


	private void back(int k) {
		int i;
		if(9 == k){
			writeSolution();
		} else {
			for(i = 0; i < 5; i++){
				if(this.a[i][this.b[k-1]] == 1 && i != this.b[k-1]){
					this.b[k] = i;
					this.a[i][this.b[k-1]] = 0;
					this.a[this.b[k-1]][i] = 0;
					back(k +1);
					this.a[i][this.b[k-1]] = 1;
					this.a[this.b[k-1]][i] = 1;
				}
			}
		}
	}
	
	private void writeSolution(){
		System.out.printf("Loesung %d: ", ++this.sol);
		for(int i = 0; i < 9; i++){
			System.out.printf("%d ", this.b[i] +1);
		}
		System.out.println();
	}
	
}
