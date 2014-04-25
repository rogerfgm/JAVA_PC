import java.util.*;

import static java.lang.Integer.*;
import static java.lang.Math.*;

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
		int ans = 0;
		ans = max(10, 30);
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinimumSquare t = new MinimumSquare();
		int[] x = {0, 3};
		int[] y = {0, 7};
		int k = 2;
		long r = t.minArea(x, y, k);
		out.println(r);
	}

}