package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class BLarge {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int h = 0;
	double P = 0;
	int y = 0;
	double[][] dp = null;
	int n = 0;
	public void solve() throws Exception{
		int N = sc.nextInt();
		int X = sc.nextInt();
		int Y = sc.nextInt();
		
		long prev = 0;
		long sum = 0;
	
		for(int i = 0; i <= (abs(X)+Y)/2; i++){
			
			int nn = 2 * (i + 1) - 1;
			prev = sum;
			sum += 2 * nn -1;
			
		}
		
		if(N >= sum){
			out.println("1.0");
			return;
		}
		if(N <= prev){
			out.println("0.0");
			return;
		}
		if(X == 0){
			if(sum != N){
				out.println("0.0");
				return;
			}
			else{
				out.println("1.0");
				return;
			}
		}

		
		P = 0;
		int base = (abs(X) + Y) / 2 + 1;
		h = 2 * base - 2;
		y = Y+1;
		
		int rem = N - (int)prev;
		if(rem >= h + y){
			out.println("1.0");
			return;
		}
		else if(rem < y){
			out.println("0.0");
			return;
		}
		else{
			n = rem;
			dp = new double[h+1][h+1];
			for(int i = 0; i <= h ; i++){
				for(int j = 0; j <= h; j++){
					dp[i][j] = -1;
				}
			}
			double ret = check(0, 0);
			out.println(ret);
			return;
		}
	
			
	}
	
	double check(int l, int r){
		if(l + r == n){
			if(l >= y){
				return 1;
			}
			else{
				return 0;
			}
		}
		if(dp[l][r] >= 0){
			return dp[l][r];
		}
		double ret = 0;
		if(l == h){
			ret = check(l, r+1);
		}
		else if(r == h){
			ret = check(l+1, r);
		}
		else{
			ret = (check(l+1, r) + check(l, r+1)) / 2;
		}
		
		dp[l][r] = ret;
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		BLarge b = new BLarge();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
