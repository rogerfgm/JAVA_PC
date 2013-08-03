package gcj20131A;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class B {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		int E = sc.nextInt();
		int R = sc.nextInt();
		int N = sc.nextInt();
		int[] v = new int[N];
		for(int i = 0; i < N; i++){
			v[i] = sc.nextInt();
		}
		
		if(R >= E){
			long ans = 0;
			for(int j = 0; j < N; j++){
				ans += v[j] * E;
			}
			out.println(ans);
			
			return;
		}
		
		long[] dp = new long[E+1];
		for(int i = 0; i < N; i++){
			long[] ndp = new long[E+1];


			for(int j = R; j <= E; j++){
				ndp[j] = max(ndp[j], dp[j] + v[i] * R);
				ndp[j] = max(ndp[j], ndp[j-1] + v[i]);
				if(j > E-R){
					
				}
			}
			dp = ndp;
		}
		long ans = 0;
		for(int i = 0; i <= E; i++){
			ans = max(ans, dp[i]);
		}
		out.println(ans);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-small-attempt0.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		B b = new B();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			if(t == 3){
				int tmp = 0;
			}
			b.solve();
			t++;
		}
		out.close();
	}
}
