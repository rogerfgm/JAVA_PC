package srm599;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class BigFatInteger {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minOperations(int A, int B){
		int ans =  0;
		Map<Integer, Integer> map = prime_factor(A);
		Integer[] d = new Integer[map.size()];
		List<Integer> list = new ArrayList<Integer>();
		int max = 0;
		boolean hazure = false;
		for(int key : map.keySet()){
			int num = map.get(key) * B;
			long b = 1;
			int cnt = 0;
			ans++;
			while(b < num){
				b *= 2;
				cnt++;
			}
			max = max(max, cnt);

		}
		ans += max;
		
		return ans;
	}
	
	Map<Integer, Integer> prime_factor(int n){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		for(int i = 2; i * i <= n; i++){
			int num = 0;
			while( n % i == 0){
				num++;
				n /= i;
			}
			if(num != 0){
				res.put(i, num);
			}
		}
		if(n != 1){
			if(!res.containsKey(n)){
				res.put(n, 1);
			}
			else{
				int num = res.get(n) + 1;
				res.put(n, num);
			}
		}
		
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigFatInteger t = new BigFatInteger();
		int A = 360;
		int B = 8;
		int r = t.minOperations(A, B);
		out.println(r);
	}

}
