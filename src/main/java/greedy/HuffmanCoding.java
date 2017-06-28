package greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HuffmanCoding {

	public static void main(String[] args) {
		HuffmanCoding h = new HuffmanCoding();
		
		// map f�r wald / ergebnisbaum
		Map<Integer, List<Node>> map = new TreeMap<Integer, List<Node>>();

		// init bsp
		char[] chrs = {'p', 'q', 'r', 'x', 'y', 'z'};
		int[] csts = {100, 17, 2, 58, 80, 5};
		
		for (int i = 0; i < csts.length; i++) {
			int cost = csts[i];
			List<Node> list = map.get(cost);
			if(list == null){
				list = new ArrayList<Node>();
				map.put(cost, list);
			}
			char ch = chrs[i];
			list.add(h.new Node(cost, ch));
		}
		
		// baue greedy den baum
		while(map.size() > 1){
			Iterator<Entry<Integer, List<Node>>> it = map.entrySet().iterator();
			
			// entry mit geringsten cost: treemap -> nach key (cost) sortiert
			Entry<Integer, List<Node>> entry = it.next();
			ListIterator<Node> listIt = entry.getValue().listIterator();
			
			Node first = listIt.next();
			listIt.remove();
			if(!listIt.hasNext() && !listIt.hasPrevious()){
				it.remove();
				entry = it.next();
				listIt = entry.getValue().listIterator();
			}
			
			Node second = listIt.next();
			listIt.remove();
			if(!listIt.hasNext() && !listIt.hasPrevious()){
				it.remove();
			}
			
			Node newNode = h.new Node(first, second);
			List<Node> list = map.get(newNode.getCost());
			if(list == null){
				list = new ArrayList<Node>();
				map.put(newNode.getCost(), list);
			}
			list.add(newNode);
		}
		
		// gib ergebnis aus
		map.entrySet().iterator().next().getValue().iterator().next().writeCosts(new ArrayList<Boolean>());
		
	}
	
	private final class Node implements Comparable<Node> {
		
		private final int cost;
		private char ch;
		private Node left;
		private Node right;
		
		public Node(int cost, char c){
			this.cost = cost;
			this.ch = c;
		}
		
		public Node(Node left, Node right){
			this.left = left;
			this.right = right;
			this.cost = this.left.getCost() + this.right.getCost();
		}

		public int getCost() {
			return this.cost;
		}


		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
		public void writeCosts(List<Boolean> pathPrefix){
			if(this.left == null || this.right == null){ // Blattknoten
				System.out.print(this.ch + "\t");
				for (Boolean bool : pathPrefix) {
					System.out.print(bool.booleanValue() ? 1 : 0);
				}
				System.out.println();
			} else {
				pathPrefix.add(Boolean.FALSE);
				this.left.writeCosts(pathPrefix);
				pathPrefix.set(pathPrefix.size() -1, Boolean.TRUE);
				this.right.writeCosts(pathPrefix);
				pathPrefix.remove(pathPrefix.size() -1);
			}
		}
		
	}
	
}
