package teileHerrsche;

import java.util.ArrayList;
import java.util.List;

public class FourierTransformation {

	private static FourierTransformation f = new FourierTransformation();
	
	public static void main(String[] args) {

		// init bsp
		double[][] a = {
				{3.45, 2.1},
				{0.8, 7.6},
				{-5.4, 3.1},
				{-7.4, 2.1},
				{7.6, 43.21},
				{6.54, 32.1},
				{8.76, 43},
				{3.12, -3.21}
		};
		
		List<Complex> c = new ArrayList<Complex>();
		for(int i = 0; i < a.length; i++){
			c.add(f.new Complex(a[i][0], a[i][1]));
		}
		
		// fast fourier transformation
		List<Complex> y = fft(c);
		
		// print result
		for (Complex complex : y) {
			System.out.printf("%.3f\t %.3f\n", complex.re, complex.im);
		}
		
	}
	
	private static final List<Complex> fft(List<Complex> a){
		int n = a.size();
		
		if(n <= 1){
			return a;
		}
		
		Complex wn = f.new Complex(Math.cos(2 * Math.PI / n), Math.sin(2 * Math.PI / n));
		Complex w = f.new Complex(1.0, 0.0);
		
		List<Complex> a0 = new ArrayList<Complex>();
		List<Complex> a1 = new ArrayList<Complex>();
		List<Complex> y = new ArrayList<Complex>();
		
		for(int i = 0; i < n; i++){
			Complex c = a.get(i);
			if( i%2 == 0){
				a0.add(c);
			} else {
				a1.add(c);
			}
			y.add(f.new Complex(0, 0));
		}
		
		List<Complex> y0 = fft(a0);
		List<Complex> y1 = fft(a1);
		
		for(int i = 0; i < n/2; i++){
			Complex aux = w.multiply(y1.get(i));
			y.set(i, y0.get(i).add(aux));
			y.set(i + n/2, y0.get(i).substract(aux));
			w = w.multiply(wn);
		}
		
		return y;
	}
	
	private final class Complex {
		
		private final double re;
		private final double im;
		
		public Complex(double re, double im) {
			this.re = re;
			this.im = im;
		}
		
		public Complex add(Complex z){
			return new Complex(this.re + z.re, this.im + z.im);
		}

		public Complex substract(Complex z){
			return new Complex(this.re - z.re, this.im - z.im);
		}

		public Complex multiply(Complex z){
			return new Complex(this.re * z.re - this.im * z.im, this.re * z.im + this.im * z.re);
		}
		
	}
	
}
