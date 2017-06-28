package greedy;

public class SpringerAufSchachbrett {

	private static final int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	private static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	public static void main(String[] args) {
		
		// n x m schachbrett
		int n = 5;
		int m = 5;
		
		// startposition springer: 2,2 hat lösung, 2,3 nicht!
		int sx = 2;
		int sy = 2;
		
		// init schachbrett
		int[][] a = new int[m][n];
		
		// springer auf start pos stellen, pos an array index anpassen
		sx--;
		sy--;
		a[sx][sy] = 1;
		
		int ctr = 1;
		
		// startpos x bzw y
		int[] xy = new int[2];
		xy[0] = sx;
		xy[1] = sy;
		
		// hüpf greedy aufm brett rum
		while(findBestSucc(a, m, n, xy)) {
			a[xy[0]][xy[1]] = ++ctr;
		}
		
		// gib lösung aus
		if(ctr == m*n){
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++){
					System.out.printf("%4d ", a[i][j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("Keine Lösung");
		}
		
	}
	
	private static boolean findBestSucc(int[][] a, int m, int n, int[] xy) {
		int aux = Integer.MAX_VALUE;
		int xn, yn, xx = 0, yy = 0;
		for(int i = 0; i < 8; i++) {
			xn = xy[0] + dx[i];
			yn = xy[1] + dy[i];
			if(onTable(xn, yn, m, n) && a[xn][yn] == 0) {
				int steps = nrAllowedSteps(a, xn, yn, m, n);
				if(steps < aux) {
					aux = steps;
					xx = xn;
					yy = yn;
				}
			}
		}
		if(aux < Integer.MAX_VALUE) {
			xy[0] = xx;
			xy[1] = yy;
			return true;
		}
		return false;
	}
	
	private static int nrAllowedSteps(int[][] a, int x, int y, int m, int n) {
		int nr = 0;
		int xn, yn;
		for(int i = 0; i < 8; i++) {
			xn = x + dx[i];
			yn = y + dy[i];
			if(onTable(xn, yn, m, n) && a[xn][yn] == 0) {
				nr++;
			}
		}
		return nr;
	}
	
	private static boolean onTable(int x, int y, int m, int n) {
		return 0 <= x && x < m && 0 <= y && y < n;
	}
	
}
