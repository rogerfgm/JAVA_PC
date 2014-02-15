import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;


public class MysticAndCandies {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minBoxes(int C, int X, int[] l, int[] h){
		int ans = 0;
		N = l.length;
		List<Data> ds = new ArrayList<Data>();
	
		
		for(int i = 0; i < N; i++){
			C -= l[i];
			Data d = new Data();
			d.idx = i;
			d.f = l[i];
			ds.add(d);
		}
		
		boolean[] used = new boolean[N];
		while(true){
			if(C <= 0){
				Collections.sort(ds, new Comparator<Data>() {
					@Override
					public int compare(Data o1, Data o2) {
						return o1.f - o2.f;
					}
				});
				for(int i = 0; i < N; i++){
					X -= ds.get(i).f;
					ans++;
					if(X <= 0){
						return ans;
					}
				}
			}
			int nextidx = -1;
			int next = INF;
			for(int i = N-1; i >= 0; i--){
				if(ds.get(i).f < h[i] && h[i] < next){
					next = h[i];
					nextidx = i;
				}
			}
			used[nextidx] = true;
			int sub = next - ds.get(nextidx).f;
			sub = min(sub, C);
			C -= sub;
			ds.get(nextidx).f += sub;
		}
		
	
	}
	
	class Data {
		int idx;
		int f;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MysticAndCandies t = new MysticAndCandies();
		int[] low = {5, 2, 3};
		int[] high = {49, 48, 47};
		int r = t.minBoxes(60, 8, low, high);
		out.println(r);
	}

}
