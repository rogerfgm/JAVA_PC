package giveup;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ2836 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int n = 0;
	int[][] dp = null;

	int[][] d = null;
	int[][] men = null;
	int[][] ct = null;
	
	public void solve() throws Exception{
		d = new int[n][2];
		men = new int[n][n];
		ct = new int[n][n];
		for(int i = 0; i < n; i++){
			d[i][0] = sc.nextInt();
			d[i][1] = sc.nextInt();
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j) continue;
				int x = abs(d[i][0] - d[j][0]);
				int y = abs(d[i][1] - d[j][1]);
				if(x == 0) x = 1;
				if(y == 0) y = 1;
				men[i][j] = men[j][i] = x * y;
			}			
		}
		
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				int c = 0;
				for(int k = 0; k < n; k++){
					if(i == k || j == k) continue;
					if(contain(i, j, k)){
						c |= 1 << k;
					}
				}
				ct[i][j] = ct[j][i] = c;
			}
		}
		
		dp = new int[n][1<<n];
		for(int j = 0; j < n; j++){
			for(int i = 0; i < 1 << n; i++){
				dp[j][i] = -1;
			}
		}
		
		int ret = check(0, 0);
		
		out.println(ret);
	}
	
	int check(int s, int idx){
		if(s == (1 << n) -1){
			return 0;
		}
		if(idx >= n){
			return INF;
		}
		if(dp[idx][s] != -1){
			return dp[idx][s];
		}

		int ret = check(s, idx+1);
		int rck = 1 << idx;
				
		for(int i = idx+1; i < n; i++){
			int ck = 1 << i;
			
			int ns = s | rck;
			ns |= ck;
			ns |= ct[idx][i];
			
			ret = min(ret, men[idx][i] + check(ns, idx+1));
		}
		
		dp[idx][s] = ret;
		return ret;
	}
	
	boolean contain(int i, int j, int k){
		int x = d[k][0];
		int y = d[k][1];
		int minx = min(d[i][0], d[j][0]);
		int maxx = max(d[i][0], d[j][0]);
		int miny = min(d[i][1], d[j][1]);
		int maxy = max(d[i][1], d[j][1]);
		if(x >= minx && x <= maxx && y >= miny && y <= maxy){
			return true;
		}
		
		return false;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ2836 p = new POJ2836();

		while(true){
			n = sc.nextInt();
			if(n == 0) break;
			p.solve();
		}
	}

}
