package srm621;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class RadioRange {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public double RadiusProbability(int[] X, int[] Y, int[] R, int Z){
		N = X.length;
		List<B> list = new ArrayList<B>();
		for(int i = 0; i < N; i++){
			long x = X[i];
			long y = Y[i];
			double d = Math.sqrt(x * x + y * y);
			if(d < R[i]){
				double f = 0;
				double t = Math.min(R[i] + d, Z);
				B b = new B();
				b.f = f;
				b.t = t;
				list.add(b);
			}
			else{
				double f = d - R[i];
				double t = Math.min(R[i] + d, Z);
				if(f < Z){
					B b = new B();
					b.f = f;
					b.t = t;
					list.add(b);
				}
			}
//			if(d < R[i]){
//				double f = R[i] - d;
//				double t = R[i] + d;
//				if(f < Z){
//					t = Math.min(Z, t);
//					B b = new B();
//					b.f = f;
//					b.t = t;
//					list.add(b);
//				}
//			}
//			else{
//				double f = 0;
//				double t = R[i] + d;
//				t = Math.min(t, Z);
//				B b = new B();
//				b.f = f;
//				b.t = t;
//				list.add(b);
//			}
		}
		
		Collections.sort(list, new Comparator<B>() {

			@Override
			public int compare(B o1, B o2) {
				// TODO Auto-generated method stub
				double c = o1.f - o2.f;
				if(c < 0){
					return -1;
				}
				else if(c > 0){
					return 1;
				}
				return 0;
				
			}
		});
		
		while(true){
			boolean f = false;
			for(int i = 0; i < list.size()-1; i++){
				B b1 = list.get(i);
				B b2 = list.get(i+1);
				if(b1.t > b2.f){
					f = true;
					double t = Math.max(b1.t, b2.t);
					b1.t = t;
					list.remove(b2);
					break;
				}
			}
			
			if(!f){
				break;
			}
		}
		double z = Z;
		for(int i = 0; i < list.size(); i++){
			B b = list.get(i);
			z = z - (b.t - b.f);
		}
		
		return z / Z;
	}
	
	class B{
		double f;
		double t;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RadioRange t = new RadioRange();
		int[] X = {-30, -56, 11, 13, -16};
		int[] Y = {84, 44, 61, -72, -45};
		int[] R = {2, 10, 4, 5, 10};
		int Z = 423;
		/*
		Peak memory used: 7.680MB
		Args:
		{{-30, -56, 11, 13, -16}, {84, 44, 61, -72, -45}, {2, 10, 4, 5, 10}, 423}

		Expected:
		0.8883354458869681

		Received:
		0.8955547753519753
		*/
		double d = t.RadiusProbability(X, Y, R, Z);
		out.println(d);
	}

}
