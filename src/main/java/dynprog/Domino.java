package dynprog;

import java.util.ArrayList;
import java.util.List;

public class Domino {
	
	private final List<Piece> vP;
	private int imax;
	private int bit;
	private final int[][] v;
	private final int[][] vNext;

	public static void main(String[] args) {

		// init bsp
		int[][] a = {
			{5, 6},
			{1, 1},
			{2, 5},
			{3, 2},
			{1, 5},
			{6, 4},
			{5, 1},
			{5, 3},
			{2, 1},
			{1, 4},
			{5, 5},
			{5, 2},
			{3, 3}
		};
		
		List<Piece> pieces = new ArrayList<Piece>();
		for(int i = 0; i < a.length; i++){
			pieces.add(new Piece(a[i][0], a[i][1]));
		}
		
		new Domino(pieces).doProcess();
	}

	public Domino(List<Piece> pieces){
		this.vP = pieces;
		this.v = new int[this.vP.size()][2];
		this.vNext = new int[this.vP.size()][2];
	}

	public void doProcess(){
		int n = this.vP.size();
		this.v[n-1][0] = 1;
		this.vNext[n-1][0] = n;
		this.v[n-1][1] = 1;
		this.vNext[n-1][1] = n;
		this.imax = n-1;
		this.bit = 0;
		
		for(int i = n-2; i >= 0; i--){
			this.v[i][0] = 1;
			this.vNext[i][0] = n;
			this.v[i][1] = 1;
			this.vNext[i][1] = n;
			
			for(int j = i+1; j < n; j++){
				if(this.vP.get(i).fits(this.vP.get(j), false) && this.v[i][0] < this.v[j][0] +1){
					this.v[i][0] = this.v[j][0] +1;
					this.vNext[i][0] = j;
				}
				if(this.vP.get(i).fits(this.vP.get(j), true) && this.v[i][0] < this.v[j][1] +1){
					this.v[i][0] = this.v[j][1] +1;
					this.vNext[i][0] = j;
				}
				if(this.vP.get(i).reverse().fits(this.vP.get(j), false) && this.v[i][1] < this.v[j][0] +1){
					this.v[i][1] = this.v[j][0] +1;
					this.vNext[i][1] = j;
				}
				if(this.vP.get(i).reverse().fits(this.vP.get(j), true) && this.v[i][1] < this.v[j][1] +1){
					this.v[i][1] = this.v[j][1] +1;
					this.vNext[i][1] = j;
				}
			}
			int auxMax = this.v[i][0] > this.v[i][1] ? this.v[i][0] : this.v[i][1];
			if(auxMax > this.v[this.imax][this.bit]){
				this.imax = i;
				this.bit = this.v[i][0] >= this.v[i][1] ? 0 : 1;
			}
		}
		
		write();
	}
	
	public void write(){
		System.out.printf("#Steine: %d\n", this.v[this.imax][this.bit]);
		int aux;
		int n = this.vP.size();
		
		if(0 == this.bit){
			aux = this.vP.get(this.imax).second;
			System.out.println(this.vP.get(this.imax));
		} else {
			aux = this.vP.get(this.imax).first;
			System.out.println(this.vP.get(this.imax).reverse());
		}
		this.imax = this.vNext[this.imax][this.bit];
		while(this.imax != n){
			if(aux == this.vP.get(this.imax).first){
				System.out.println(this.vP.get(this.imax));
				aux = this.vP.get(this.imax).second;
				this.bit = 0;
			} else {
				System.out.println(this.vP.get(this.imax).reverse());
				aux = this.vP.get(this.imax).first;
				this.bit = 1;
			}
			this.imax = this.vNext[this.imax][this.bit];
		}
	}
	
	public static final class Piece {
		
		private final int first;
		private final int second;
		
		public Piece(int first, int second){
			this.first = first;
			this.second = second;
		}

		public boolean fits(Piece other, boolean reversed){
			return this.second == (reversed ? other.second : other.first);
		}
		
		public Piece reverse(){
			return new Piece(this.second, this.first);
		}
		
		@Override
		public String toString(){
			return this.first + " " + this.second;
		}
		
	}
	
}
