package srm620;
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
	Map<Integer, Set<Integer>> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int maxSum(int a, int b, int c, int d){
	
		Set<Long> set = new HashSet<Long>();
		long base = 10000000;
		while(a > 0 && b > 0){
			long key = a * base + b;
			set.add(key);
			
			if(a > b){
				a -= b;
			}
			else if(b > a){
				b -= a;
			}
			else{
				break;
			}
		}

		while(c > 0 && d > 0){
			long key = c * base + d;
			if(set.contains(key)){
				return c + d;
			}
			if(c > d){
				c -= d;
			}
			else if(d > c){
				d -= c;
			}
			else{
				break;
			}
		}
		

		
		return -1;
		
	}
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairGame t = new PairGame();
		int a = 1000000;
		int b = 1;
		int c = 999999;
		int d = 2;
	
		int r = t.maxSum(a, b, c, d);
		out.println(r);
	}

}
