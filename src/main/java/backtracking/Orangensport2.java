package backtracking;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Orangensport2 {

	private static final int BOUND = 100;

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
		List<BitSet> b = factorizations(n1);
		List<BitSet> c = factorizations(n2);
		
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

	private static List<BitSet> factorizations(int n) {
		List<BitSet> list = new ArrayList<BitSet>();
		if(n == 1){
			BitSet one = new BitSet(2);
			one.set(1);
			list.add(one);
		} else {
			back(n, 2, new BitSet(BOUND +1), list);
		}
		return list;
	}

	private static void back(int n, int k, BitSet found, List<BitSet> l) {
		for(int f = k; f < BOUND && f*f < n; f++){
			if(n%f == 0){
				found.set(f);
				back(n/f, f+1, found, l);
				found.clear(f);
			}
			if(n <= BOUND){
				BitSet copy = (BitSet) found.clone();
				copy.set(n);
				l.add(copy);
			}
		}
	}

	private static boolean isADisjointCombination(List<BitSet> list1, List<BitSet> list2) {
		for (BitSet b : list1) {
			for (BitSet c : list2) {
				if(!b.intersects(c)){
					return true;
				}
			}
		}
		return false;
	}
	
}
