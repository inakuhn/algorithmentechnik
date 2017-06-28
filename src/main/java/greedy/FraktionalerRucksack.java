package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FraktionalerRucksack {

	public static void main(String[] args) {
		FraktionalerRucksack r = new FraktionalerRucksack();
		
		// Bsp Initialisierung
		
		// Kapazit�t (max. Gewicht)
		final int m = 41;
		
		int index = 1;
		RuckObject o1 = r.new RuckObject(index++, 12.34, 123.99);
		RuckObject o2 = r.new RuckObject(index++, 23.45, 600.54);
		RuckObject o3 = r.new RuckObject(index++, 12.78, 90.67);
		RuckObject o4 = r.new RuckObject(index++, 9.34, 45.32);
		
		List<RuckObject> v = new ArrayList<RuckObject>();
		v.add(o1);
		v.add(o2);
		v.add(o3);
		v.add(o4);
		
		// f�lle rucksack greedy
		fraction(v, m);
	}
	
	private static void fraction(List<RuckObject> v, double m) {
		
		// sortiere absteigend nach gewicht/wert verh�ltnis
		Collections.sort(v);
		
		int i = 0;
		
		while(i < v.size() && m > 0){
			RuckObject nextObject = v.get(i);
			double weight = nextObject.getWeight();
			if(weight <= m){
				m -= weight;
				i++;
			} else {
				m = -m;
			}
		}
		
		for(int j = 0; j < i; j++){
			System.out.print(v.get(j));
			System.out.println(" - vollst�ndig");
		}
		if(i < v.size() && m < 0){
			System.out.print(v.get(i));
			System.out.println(" - " + -m + " kg");
		}
		
	}
	
	private final class RuckObject implements Comparable<RuckObject> {
		
		private final int index;
		private final double weight;
		private final double value;
		
		public RuckObject(int index, double weight, double value) {
			super();
			this.index = index;
			this.weight = weight;
			this.value = value;
		}
		
		public double getWeight() {
			return this.weight;
		}
		
		public double getRatio() {
			return this.value / this.weight;
		}

		public int compareTo(RuckObject o) {
			double diff = getRatio() - o.getRatio();
			return diff > 0f ? -1 : diff < 0f ? 1 : 0;
		}

		@Override
		public String toString() {
			return "RuckObject " + this.index + " [weight=" + this.weight + ", value=" + this.value + "]";
		}

	}
	
}
