package srm585;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;
import java.math.BigInteger;

public class TrafficCongestion {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[] dp =null;
	
	public int theMinCars(int h){
	
		if(h == 0){
			return 1;
		}
		if(h == 1){
			return 1;
		}
		if(h == 2){
			return 3;
		}
		int st = h - 1;
		long ans = 0;

		while(st > 1){
			
			ans += nijyou(2, st) % MOD;
			ans %= MOD;
			st-=2;
		}
		if(st == 1){
			ans += 2;
		}
		ans++;
		
		return (int)ans;
	}
	
	long nijyou(long b, int n){
		if(n == 1){
			return b;
		}
		if(n % 2 == 1){
			return (nijyou(b, n-1)% MOD) * b % MOD;
		}
		b = nijyou(b, n/2) % MOD;
		return b * b % MOD;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrafficCongestion t = new TrafficCongestion();
		int r = t.theMinCars(3);
		out.println(r);
	}

}
