import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class MaxMinTreeGame {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[] cs = null;
	int[] es = null;
	public int findend(int[] es, int[] cs){
		this.cs = cs;
		this.es = es;
		N = cs.length;
		boolean[] rm = new boolean[N];
		
		
		return check1(rm);
	}
	
	int check1(boolean[] rm){
		
		int ans = 0;
		int[] cnt = new int[N];
		for(int i = 0; i < es.length; i++){
			if(rm[i]) continue;
			int e = es[i];
			
			cnt[i+1]++;
			cnt[e]++;
			
		}
		int maxside = -1;
		int one = 0;
		for(int i = 0; i < N; i++){
			if(cnt[i] == 1){
				if(ans < cs[i]){
					ans = max(ans, cs[i]);
					maxside = i;
				}
				
				one++;
			}
		}

		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MaxMinTreeGame t = new MaxMinTreeGame();
		int[] a = {0, 0};
		int[] b = {3, 2, 5};
		int r = t.findend(a, b);
		out.println(r);
	}

}
