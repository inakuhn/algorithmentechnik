package teileHerrsche;

public final class Hanoi {

	public static void main(String[] args) {
		
		// anzahl scheiben
		int n = 3;
		
		hanoi(n, 'A', 'C', 'B');
	}
	
	private static final void hanoi(int n, char a, char c, char b){
		if(n == 1){
			write(a, c);
		} else {
			hanoi(n-1, a, b, c);
			write(a, c);
			hanoi(n-1, b, c, a);
		}
	}

	private static void write(char a, char b) {
		System.out.printf("(%s, %s) ", a, b);
	}
	
}
