package teileHerrsche;

public class QuadTree {

	private static StringBuilder unifiedTree = new StringBuilder();
	
	public static void main(String[] args) {
		
		StringBuilder t1 = new StringBuilder("ppeeefpffeefe");
		StringBuilder t2 = new StringBuilder("pefepeefe");
		
		unifyTrees(t1, t2);
		
		System.out.printf("%s + %s = %s\n", t1, t2, unifiedTree);
		System.out.printf("%d + %d = %d\n", getValue(t1, 1024), getValue(t2, 1024), getValue(unifiedTree, 1024));
		System.out.println("-----------------------");
	}
	
	private static void unifyTrees(StringBuilder t1, StringBuilder t2) {
		if('p' == t1.charAt(0) && 'p' == t2.charAt(0)){
			unifiedTree.append('p');
			StringBuilder sr1 = new StringBuilder();
			StringBuilder sr2 = new StringBuilder();
			int i1 = 1;
			int i2 = 1;
			for(int i = 0; i < 4; i++){
				i1 = subTree(t1, sr1, i1);
				i2 = subTree(t2, sr2, i2);
				unifyTrees(sr1, sr2);
			}
		} else if('f' == t1.charAt(0) || 'f' == t2.charAt(0)){
			unifiedTree.append('f');
		} else if('e' == t2.charAt(0)){
			unifiedTree.append(t1);
		} else if('e' == t1.charAt(0)){
			unifiedTree.append(t2);
		}
	}

	private static int subTree(StringBuilder ss, StringBuilder sr, int t){
		sr.setLength(0);
		if('p' == ss.charAt(t)){
			for(int n = 1; n != 0 && t < ss.length(); ){
				if(isLeaf(ss.charAt(t))){
					n--;
				} else {
					n += 3;
				}
				sr.append(ss.charAt(t++));
			}
		} else {
			sr.append(ss.charAt(t++));
		}
		return t;
	}
	
	private static int getValue(StringBuilder ss, int v){
		if('p' == ss.charAt(0)){
			StringBuilder sr = new StringBuilder();
			int t = 1;
			int suma = 0;
			for(int i = 0; i < 4; i++){
				t = subTree(ss, sr, t);
				suma += getValue(sr, v/4);
			}
			return suma;
		}
		return 'f' == ss.charAt(0) ? v : 0;
	}
	
	private static boolean isLeaf(char c){
		return 'e' == c || 'f' == c;
	}
	
}
