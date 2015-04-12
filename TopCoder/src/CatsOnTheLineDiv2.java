import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class CatsOnTheLineDiv2 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	String Y = "Possible";
	String NO = "Impossible";
	public String getAnswer(int[] ps, int[] cs, int T){
		D[] ds = new D[ps.length];
		N = ps.length;
		for(int i = 0; i < ps.length; i++){
			D d = new D();
			d.p = ps[i];
			d.c = cs[i];
			ds[i] = d;
		}
		Arrays.sort(ds, new Comparator<D>() {

			@Override
			public int compare(D o1, D o2) {
				
				return o1.p - o2.p;
			}
		});
		
		int X = -INF;
		for(int i = 0; i < N; i++){
			D d = ds[i];
			int x = d.p;
			int c = d.c;
			if(2 * T + 1 < c){
				return NO;
			}
			int t = T - (c / 2);
			if(c % 2 == 1){
				x = x - c / 2;
				
				int maxleftstart = x - t;
				int maxrightstart = x + t;
				if(maxrightstart <= X){
					return NO;
				}
				if(maxleftstart < X){
					X = X + c;
				}
				else{
					X = maxleftstart + c;
				}
			}
			else{
				int maxleftstart = x - c / 2 - t;
				int maxrightstart = x - c / 2 + 1 + t;
				if(maxrightstart <= X){
					return NO;
				}
				if(maxleftstart < X){
					X = X + c;
				}
				else{
					X = maxleftstart + c;
				}
			}
			
		}
		
		return Y;
	}
	
	class D{
		int p;
		int c;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CatsOnTheLineDiv2 t = new CatsOnTheLineDiv2();

		out.println();
	}

}
