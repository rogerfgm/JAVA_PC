package srm579;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;



public class MarblePositioning {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	public double totalWidth(int[] rs){
		double ans = Long.MAX_VALUE / 10;
		int N = rs.length;
		Permutation p = new Permutation(rs.length);
		Iterator<int[]> ite = p.iterator();
		while(ite.hasNext()){
			int[] d = ite.next();
			double[] dist = new double[N];
			for(int i = 1; i < d.length; i++){
				
				dist[i] = dist[i-1] + get(rs[d[i-1]], rs[d[i]]);
				for(int j = i-1; j >= 0; j--){
					if(dist[j] + get(rs[d[j]], rs[d[i]]) > dist[i]){
						dist[i] = dist[j] + get(rs[d[j]], rs[d[i]]);
					}
				}
			}
			ans = min(ans, dist[N-1]);
			
		}
		
		
		return ans;
	}
	
	
	double get(long r1, long r2){
		long l = (r1 + r2)*(r1 + r2);
		long a = (r1-r2)*(r1-r2);
		return sqrt(l - a);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarblePositioning t = new MarblePositioning();
		int[] in = {1,999950884,1};
		double r = t.totalWidth(in);
		out.println(r);
	}
	
	public class Permutation implements Iterable<int[]> {
		private final int size;


		public Permutation(int size) {
			if (size <= 0) throw new IllegalArgumentException();
			this.size = size;
		}

		public Iterator<int[]> iterator() {
			return new Iter(size);
		}

		private class Iter implements Iterator<int[]> {
			private final int[] source;
			private boolean isFirst = true;

			private Iter(int size) {
				source = new int[size];
				for(int i = 0; i < size; ++i) {
					source[i] = i;
				}
			}


			public boolean hasNext() {
				if (isFirst) {
					isFirst = false;
					return true;
				}

				int n = source.length;
				for (int i = n - 1; i > 0; i--) {
					if (source[i - 1] < source[i]) {
						int j = n;
						while (source[i - 1] >= source[--j]);
						swap(source, i - 1, j);
						reverse(source, i, n);
						return true;
					}
				}
				reverse(source, 0, n);
				return false;
			}


			public int[] next() {
				return source;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void swap(int[] is, int i, int j) {
				int t = is[i];
				is[i] = is[j];
				is[j] = t;
			}

			private void reverse(int[] is, int s, int t) {
				while (s < --t) swap(is, s++, t);
			}
		}
	}

}
