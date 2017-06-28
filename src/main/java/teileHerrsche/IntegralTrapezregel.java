package teileHerrsche;


public class IntegralTrapezregel {

	private static final double EPS = 0.0001;
	
	public static void main(String[] args) {
		
		// f(x) = 1 / (1+x²)
		
		double z[][] = {
				{0d, 1d},
				{90.09, 123.45},
				{-45.6, 23d}
		};
		
		double a, b, integral;
		
		for(int i = 0; i < z.length; i++){
			a = z[i][0];
			b = z[i][1];
			integral = trapez(a, b);
			System.out.printf("I(%.3f, %.3f) = %f \n", a, b, integral);
		}
		
	}
	
	private static final double trapez(double a, double b){
		if(b-a > EPS){
			double m = (b+a) / 2;
			double l = trapez(a, m);
			double r = trapez(m, b);
			return r+l;
		}
		return (b-a)*((f(a)+f(b)) /2);
	}
	
	private static final double f(double x){
		return 1 / (1+x*x);
	}
	
}
