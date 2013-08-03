package gcj2011;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class C2011Small {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		int N = sc.nextInt();
		int min = 0;
		int max = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 2; i <= N; i++){
			Map<Integer, Integer> mp = prime_factor(i);
			boolean flag = false;
			for(int p : mp.keySet()){
				if(map.containsKey(p)){
					if(map.get(p).intValue() < mp.get(p).intValue()){
						flag = true;
						map.put(p, mp.get(p));
					}
				}
				else{
					flag = true;
					map.put(p, mp.get(p));
				}
			}
			
			if(flag){
				max++;
			}
		}
		
		for(int i = 2; i <= N; i++){
			if(is_prime(i)){
				min++;
			}
		}
		
		
		int ans = max - min;
		if(N != 1){
			ans++;
		}
		out.println(ans);
			
	}
	
	boolean is_prime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return n != 1; // 1の場合は除外
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
	public static void main(String[] args) throws Exception{
		File file = new File("C-2011small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		C2011Small b = new C2011Small();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
