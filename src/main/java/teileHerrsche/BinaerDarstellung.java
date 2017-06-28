package teileHerrsche;

public class BinaerDarstellung {

	public static void main(String[] args) {
		
		int n = 11;
		System.out.print("Binaer von "+n+": ");
		binaer(n);
		
	}

	private static void binaer(int n){
		if(n > 0){
			binaer(n/2);
			System.out.print(n%2);
		}
	}
	
}
