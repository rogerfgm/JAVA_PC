package srm575;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TheNumberGameDivOne {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	String J = "John";
	String B = "Brus";
	int[] dp = new int[1401];
	public String find(long n){
		if(n % 2 == 1) return B;
		while(true){
			if(n % 4 == 0) n /= 4;
			else break;
		}
		if(n == 2) return B;
		return J;
		
	}
	
	boolean check(int n){
		if(n == 1){
			return false;
		}
		if(dp[n] > 0){
			if(dp[n] == 1){
				return true;
			}
			else{
				return false;
			}
		}
		if(is_prime(n)){
			return false;
		}
		List<Integer> list = divisor(n);
	
		for(int t : list){
			if(!check(n-t)){
				dp[n] = 1;
				return true;
			}
		}
		dp[n] = 2;
		return false;
	}
	
	boolean is_prime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return n != 1; // 1の場合は除外
	}
	
	List<Integer> divisor(int n){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i * i <= n; i++){
			if(n % i == 0){
				list.add(i);
				if(i != n / i) list.add(n / i);
			}
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheNumberGameDivOne t = new TheNumberGameDivOne();
		out.println(t.find(128));
//		for(int i = 2; i <= 400; i++){
//			out.println(i + " " + t.find(i));
//		}
	}

}
