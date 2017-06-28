package backtracking;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orangensport {

	private static final Orangensport o = new Orangensport();
	
	public static void main(String[] args) {
		
		int[][] a = {
			{110, 119},
			{294, 202},
			{343, 49},
			{610, 3599},
			{138, 258},
			{941, 2234},
			{1236, 100},
			{6231, 1500},
			{151, 127},
			{1, 101}
		};
		
		for (int[] x : a) {
			makeDecision(x[0], x[1]);
		}
		
	}

	private static void makeDecision(int n1, int n2) {
		List<Set<Integer>> b = o.new SetsList(n1).getValue();
		List<Set<Integer>> c = o.new SetsList(n2).getValue();
		
		if(b.isEmpty() && c.isEmpty()){
			System.out.println("Unentschieden, beide Luegen!");
		} else if(b.isEmpty()){
			System.out.println("Eins luegt, Zwei gewinnt!");
		} else if(c.isEmpty()){
			System.out.println("Zwei luegt, Eins gewinnt!");
		} else if(n1 < n2){
			if(isADisjointCombination(b, c)){
				System.out.println("Zwei gewinnt!");
			} else {
				System.out.println("Eins gewinnt!");
			}
		} else if(isADisjointCombination(b, c)){
			System.out.println("Eins gewinnt!");
		} else {
			System.out.println("Zwei gewinnt!");
		}
	}
	
	private static boolean isADisjointCombination(List<Set<Integer>> b, List<Set<Integer>> c) {
		boolean flag = false;
		int bSize = b.size();
		int cSize = c.size();
		for(int i = 0; !flag && i < bSize; i++){
			for(int j = 0; !flag && j < cSize; j++){
				if(!isCommonElement(b.get(i), c.get(j))){
					flag = true;
				}
			}
		}
		return flag;
	}

	private static boolean isCommonElement(Set<Integer> a, Set<Integer> b) {
		for (Integer i : a) {
			if(b.contains(i)){
				return true;
			}
		}
		return false;
	}

	private final class SetsList {
		private final BitSet x;
		private final List<Integer> a;
		private final List<Set<Integer>> value;
		
		protected SetsList(int n){
			this.value = new ArrayList<Set<Integer>>();
			this.a = new ArrayList<Integer>();
			this.x = new BitSet();
			for(int i = 2; i <= 100; i++){
				if(0 == n % i){
					this.a.add(i);
				}
			}
			back(n, 1, this.value);
		}
		
		List<Set<Integer>> getValue(){
			return this.value;
		}
		
		private void addSet(int k, List<Set<Integer>> b){
			Set<Integer> auxSet = new HashSet<Integer>();
			for(int i = 1; i < k; i++){
				if(this.x.get(i)){
					auxSet.add(this.a.get(i -1));
				}
			}
			b.add(auxSet);
		}
		
		private void back(int n, int k, List<Set<Integer>> b){
			int p = 1;
			int aSize = this.a.size();
			for(int j = 1; j < k; j++){
				if(this.x.get(j)){
					p *= this.a.get(j-1);
				}
			}
			if(p == n){
				addSet(k, b);
			} else if(k <= aSize){
				for(int i = 0; i < 2; i++){
					this.x.set(k, 0 != i);
					if(i == 0){
						back(n, k+1, b);
					} else if(0 == n/p % this.a.get(k -1)){
						this.x.set(k);
						if(1 == n/p / this.a.get(k -1) || n/p / this.a.get(k -1) > this.a.get(k -1)){
							back(n, k+1, b);
						}
					}
				}
			}
		}
		
	}
	
}
