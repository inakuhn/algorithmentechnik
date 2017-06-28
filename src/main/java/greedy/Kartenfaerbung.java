package greedy;

public class Kartenfaerbung {

	public static void main(String[] args) {
		
		int n = 7;
		
		int a[][] = {
				{0, 1, 1, 1, 0, 0, 1},
				{1, 0, 1, 1, 0, 0, 0},
				{1, 1, 0, 1, 1, 0, 1},
				{1, 1, 1, 0, 1, 0, 1},
				{0, 0, 1, 1, 0, 1, 1},
				{0, 0, 0, 0, 1, 0, 1},
				{1, 0, 1, 1, 1, 1, 0}
		};

		int col[] = new int[n];
		boolean ok;
		
		for (int i = 1; i < n; i++) {
			// farbindex
			int c = -1; 
			ok = false;
			
			do {
				c++;
				ok = true;
				
				//nachbarn ablaufen
				for (int j = 0; ok && j < i; j++) { 
					if(a[j][i] == 1 && col[j] == c){
						ok = false;
					}
				}
				
			} while(!ok);
			
			col[i] = c;
		}
		
		// l�sung ausgeben farbe = index + 1
		System.out.print("Colors: ");
		for (int i = 0; i < col.length; i++) {
			System.out.print(col[i]+1 + " ");
		}
		
	}
	
}
