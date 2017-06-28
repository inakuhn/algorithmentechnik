package greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class SpannbaumKruskal {

	public static void main(String[] args) {
		
		TreeMap<Double, List<Integer>> e = new TreeMap<Double, List<Integer>>();

		//init bsp
		int n = 10;
		
		int a[][] = {
				{0, 4, 3, 10, 0, 0, 0, 0, 18, 0},
				{4, 0, 1, 0, 5, 0, 0, 0, 0, 0},
				{3, 1, 0, 9, 5, 0, 0, 0, 0, 0},
				{10, 0, 9, 0, 7, 8, 0, 9, 8, 0},
				{0, 4, 5, 7, 0, 9, 9, 0, 0, 0},
				{0, 0, 0, 8, 9, 0, 2, 2, 0, 0},
				{0, 0, 0, 0, 9, 2, 0, 4, 0, 6},
				{0, 0, 0, 9, 0, 2, 4, 0, 9, 3},
				{18, 0, 0, 8, 0, 0, 0, 9, 0, 9},
				{0, 0, 0, 0, 0, 0, 6, 3, 9, 0}
		};
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				double aux = a[i][j];
				if(i < j && aux != 0d){
					putInMultiMap(e, aux, i*n+j);
				}
			}
		}
		
		// spannbaum finden
		int c[] = new int[n];
		for(int i = 0; i < n; i++){
			c[i] = i;
		}
		
		TreeMap<Double, List<Integer>> h = new TreeMap<Double, List<Integer>>();
		int szh = 0;
		
		while(szh < n-1){
			TreeMultimapIterator it = new TreeMultimapIterator(e);
			it.next();
			
			int uu = it.value().intValue() / n;
			int vv = it.value().intValue() % n;
			while(c[uu] == c[vv]){
				it.next();
				uu = it.value().intValue() / n;
				vv = it.value().intValue() % n;
			}
			putInMultiMap(h, it.key(), it.value());
			szh++;
			it.remove();
			int mi = Math.min(c[uu], c[vv]);
			int ma = Math.max(c[uu], c[vv]);
			
			for(int i = 0; i < n; i++){
				if(c[i] == ma){
					c[i] = mi;
				}
			}
			
			double cost = 0;
			Set<Map.Entry<Double, List<Integer>>> hEntries = h.entrySet();
			for (Map.Entry<Double, List<Integer>> entry : hEntries) {
				List<Integer> values = entry.getValue();
				double key = entry.getKey();
				for (int value : values) {
					System.out.printf("(%d, %d) -> ", value / n +1, value % n +1);
					System.out.println(key);
					cost += key;
				}
			}
			System.out.println("------------------");
			System.out.println("Gewicht: "+cost);
			System.out.println("\n");
		}
		
	}
	
	private static void putInMultiMap(TreeMap<Double, List<Integer>> mmap, Double key, Integer value){
		List<Integer> values = mmap.get(key);
		if(values == null){
			values = new ArrayList<Integer>();
			mmap.put(key, values);
		}
		values.add(value);
	}
	
	private static final class TreeMultimapIterator {
		
		private final Iterator<Map.Entry<Double, List<Integer>>> mmapIt;
		private ListIterator<Integer> currValuesIt;
		private Integer currValue;
		Map.Entry<Double, List<Integer>> currEntry;
		
		public TreeMultimapIterator(TreeMap<Double, List<Integer>> mmap){
			this.mmapIt = mmap.entrySet().iterator();
		}
		
		public boolean next(){
			if(this.currValuesIt != null && this.currValuesIt.hasNext()){
				this.currValue = this.currValuesIt.next();
				return true;
			} else if(this.mmapIt.hasNext()){
				this.currEntry = this.mmapIt.next();
				this.currValuesIt = this.currEntry.getValue().listIterator();
				this.currValue = this.currValuesIt.next();
				return true;
			}
			return false;
		}
		
		public double key(){
			return this.currEntry.getKey();
		}
		
		public Integer value(){
			return this.currValue;
		}
		
		public void remove(){
			this.currValuesIt.remove();
			if(this.currEntry.getValue().isEmpty()){
				this.currValuesIt = null;
				this.mmapIt.remove();
			}
		}
		
	}
	
}
