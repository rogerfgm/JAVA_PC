package srm614;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class MinimumSquare {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public long minArea(int[] x, int[] y, int K){
		List<D> list = new ArrayList<D>();
		N = x.length;
		for(int i = 0;i < N; i++){
			D d = new D();
			d.x = x[i];
			d.y = y[i];
			list.add(d);
		}
		long maxl = Long.MAX_VALUE / 1000;
		Collections.sort(list, new Comparator<D>() {

			@Override
			public int compare(D o1, D o2) {
				// TODO Auto-generated method stub
				return o1.x - o2.x;
			}
		});
		
		Comparator<D> ycmp = new Comparator<D>() {

			@Override
			public int compare(D o1, D o2) {
				// TODO Auto-generated method stub
				return o1.y - o2.y;
			}
		};
		
		for(int i = 0; i < N; i++){
			for(int j = i + K - 1; j < N; j++){
				List<D> nl = new ArrayList<D>();
				for(int k = i; k <= j; k++){
					nl.add(list.get(k));
				}
				long xl = nl.get(nl.size()-1).x - nl.get(0).x;
				if(nl.size() < K) continue;
				Collections.sort(nl, ycmp);
				for(int k = 0; k < nl.size() - K + 1; k++){
					int m = k + K - 1;
					long yl = nl.get(m).y - nl.get(k).y;
					maxl = min(maxl, max(xl, yl));
				}
			}
		}
		
		return (maxl+2) * (maxl+2);
	}
	
	class D{
		int x, y;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinimumSquare t = new MinimumSquare();
		int[] x = {-47881, 28623, 1769, -38328, -16737, 16653, -23181, 37360, 41429, 26282, 254, 728, 8299, -41080, -29498, 17488, -23937, -11, 33319, 25232};
		int[] y = {-46462, 48985, -43820, -19587, -33593, -28337, 13667, -48131, -5568, -2332, -41918, -31370, -3695, 42599, -37788, -40096, 39049, 25045, -2122, 3874};
		int K = 8;
		long r = t.minArea(x, y, K);
		out.println(r);
	}

}
