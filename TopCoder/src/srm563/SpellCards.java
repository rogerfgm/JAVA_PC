package srm563;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

// 未だTLE
public class SpellCards {

	int INF = Integer.MAX_VALUE / 100;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int[] l = null;
	int[] d = null;
	int N = 0;
	int[][][] dp = null;

	public int maxDamage(int[] level, int[] damage){
		l = level;
		d = damage;
		N = level.length;
		dp = new int[N+1][N+1][N+1];
		for(int i = 0; i < N+1; i++){
			for(int j = 0; j < N+1; j++){
				for(int k =0; k < N+1; k++){
					dp[i][j][k] = -1;
				}
				
			}
		}
		int ret = check(0, 0, 0);
		return ret;
	}
	
	int check(int p, int ow, int rem){
		if(p == N){
			if(rem - ow >= 0){
				return 0;
			}
			else{
				return -INF;
			}
		}
		if(ow > N){
			return -INF;
		}
		if(dp[p][ow][rem] != -1){
			return dp[p][ow][rem];
		}
		int ret = -INF;
		
		{
			int now = ow;
			int nrem = rem;
			if(now > 0){
				now--;
			}
			else{
				nrem++;
			}

			ret = max(ret, check(p+1, now, nrem));
			
			
		}
		{
			int lev = l[p] -1;
			int now = ow;
			int nrem = rem;
			
			if(nrem > lev){
				nrem -= lev;
				lev = 0;
			}
			else{
				lev -= nrem;
				nrem = 0;
			}
			now += lev;
			ret = max(ret, d[p] + check(p+1, now, nrem));
			
		}
		
		dp[p][ow][rem] = ret;
		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpellCards t = new SpellCards();
		
		{
			int[] l = {1,1,1};
			int[] d = {10,20,30};
			int ret = t.maxDamage(l, d);
			out.println(ret);
		}
		
		{
			int[] l = {3,3,3};
			int[] d = {10,20,30};
			int ret = t.maxDamage(l, d);
			out.println(ret);
		}
		
		{
			int[] l ={4,4,4};
			int[] d = {10,20,30};
			int ret = t.maxDamage(l, d);
			out.println(ret);
		}
		
		
		{
		int[] l = {3, 2, 1, 3, 1, 3, 3, 3, 1, 2, 1, 1, 2, 1, 1, 1, 3, 2, 2, 1, 3, 1, 1, 2, 3, 2, 2, 3, 3, 2, 3, 2, 2, 2, 2, 1, 2, 3, 3, 3, 1, 3};
		int[] d = {192, 179, 187, 195, 176, 176, 189, 187, 183, 187, 192, 190, 189, 185, 191, 191, 192, 181, 186, 192, 192, 194, 194, 192, 184, 189, 192, 182, 190, 187, 181, 187, 194, 176, 177, 177, 193, 186, 188, 190, 195, 186};
		
		out.println(new Date());
		int ret = t.maxDamage(l, d);
		out.println(ret);
		out.println(new Date());
		}
		/*
		 TLE
		 
Problem: 500
Test Case: 40
Succeeded: No
Execution Time: 0 ms
Args:
{{3, 2, 1, 3, 1, 3, 3, 3, 1, 2, 1, 1, 2, 1, 1, 1, 3, 2, 2, 1, 3, 1, 1, 2, 3, 2, 2, 3, 3, 2, 3, 2, 2, 2, 2, 1, 2, 3, 3, 3, 1, 3}, 
{192, 179, 187, 195, 176, 176, 189, 187, 183, 187, 192, 190, 189, 185, 191, 191, 192, 181, 186, 192, 192, 194, 194, 192, 184, 189, 192, 182, 190, 187, 181, 187, 194, 176, 177, 177, 193, 186, 188, 190, 195, 186}}

Expected:
5075

Received:
The code execution time exceeded the 2 second time limit.

		 */
	}
}
