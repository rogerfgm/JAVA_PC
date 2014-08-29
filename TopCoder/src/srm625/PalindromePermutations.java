package srm625;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PalindromePermutations {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public 	double palindromeProbability(String w){
		N = w.length();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < N; i++){
			String s = w.substring(i, i+1);
			if(!map.containsKey(s)){
				map.put(s, 0);
			}
			int num = map.get(s) + 1;
			map.put(s, num);
		}
		int odd = 0;
		for(String s : map.keySet()){
			if(map.get(s).intValue() % 2 == 1){
				odd++;
			}
		}
		if(odd > 1){
			return 0;
		}
		double bo = 1;
		double si = 1;
		int n = N;
		int m = N / 2;
		for(String s : map.keySet()){
			int num = map.get(s);
			bo *= ncl(n, num);
			int div = num / 2;
			if(div != 0){
				si *= ncl(m, div);
				m -= div;
			}
			
			n-=num;
		}
		
		
		
		return si / bo;
	}
	
	long ncl(int n, int c){
		if(n < c){
			return 0;
		}
		if(n-c < c){
			c = n-c;
		}
		
		long ret = 1;
	    for (int k = 0; k < c; k++) {
	        ret = ret * (n-k) / (k+1);
	    }
		
		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PalindromePermutations t = new PalindromePermutations();
		String word = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhffhhhhhhhhhh";
		double r = t.palindromeProbability(word);
		out.println(r);
	}

}
