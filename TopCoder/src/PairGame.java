import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class PairGame {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int maxSum(int a, int b, int c, int d){
		List<int[]> l = new ArrayList<int[]>();
//		if(a > b){
//			int tmp = a;
//			a = b;
//			b = tmp;
//		}
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		while(a > 0 && b > 0){
			if(!map.containsKey(a)){
				map.put(a, new HashSet<Integer>());
			}
			map.get(a).add(b);
		
			if(a == b){
				break;
			}
			else if(a > b){
				a -= b;
			}
			else{
				b -= a;
			}
		}
		
		List<int[]> l2 = new ArrayList<int[]>();
		int ans = -1;
		while(c > 0 && d > 0){
			if(map.containsKey(c)){
				
				if(map.get(c).contains(d)){
					int sum = c + d;
					ans = max(ans, sum);
				}
			}
			if(c == d){
				break;
			}
			else if(c > d){
				c -= d;
			}
			else{
				d -= c;
			}
		}
		
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairGame t = new PairGame();
		int a = 1000000;
		int b = 1;
		int c = 1000000;
		int d = 1;
		int r = t.maxSum(a, b, c, d);
		out.println(r);
	}

}
