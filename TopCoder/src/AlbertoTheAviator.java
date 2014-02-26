import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class AlbertoTheAviator {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	D[] ds = null;
	int[][] dp = null;
	public int MaximumFlights(int F, int[] du, int[] re){
		ds = new D[du.length];
		for(int i = 0; i < du.length; i++){
			D d = new D();
			d.d = du[i];
			d.r = re[i];
			d.s = d.d - d.r;
			ds[i] = d;
		}
		N = du.length;
		Arrays.sort(ds, new Comparator<D>() {
			@Override
			public int compare(D o1, D o2) {	
				return o2.s - o1.s;
			}
			
		});
		dp = new int[du.length][F+1];
		for(int i = 0; i < du.length; i++){
			for(int j = 0; j <= F; j++){
				dp[i][j] = -1;
			}
		}
		
		return get(0, F);
	}
	
	int get(int idx, int f){
		if(idx >= N) return 0;
		if(dp[idx][f] >= 0) return dp[idx][f];
		int a = 0;
		if(ds[idx].d <= f){
			a = get(idx + 1, f - ds[idx].s) + 1;
		}
		a = max(a, get(idx+1, f));
		
		dp[idx][f] = a;
		return a;
	}
	
	class D{
		int d;
		int r;
		int s;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AlbertoTheAviator t = new AlbertoTheAviator();

		int F = 10;
		int[] du = {4, 8};
		int[] re = {2, 0};
		int r = t.MaximumFlights(F, du, re);
		out.println(r);
	}

}
