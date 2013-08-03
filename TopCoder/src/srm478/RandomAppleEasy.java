package srm478;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class RandomAppleEasy {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	double[][][] dp = null;
	int N = 0;
	int[] r = null;
	int[] g = null;
	double ans = 0;
	public double theRed(int[] red, int[] green){
		N = red.length;
		r = red;
		g = green;
		
		long[][] dp = new long[N*10+1][N*10+1];
		dp[0][0] = 1;
		for(int i = 0; i < N; i++){
			long[][] ndp = new long[N*10+1][N*10+1];
			for(int j = 0; j <= N*10; j++){
				for(int k = 0; k <= N*10; k++){
					ndp[j][k] += dp[j][k];
					if(j - r[i] >= 0 && k - g[i] >= 0){
						ndp[j][k] += dp[j-r[i]][k-g[i]];
					}
				}
			}
			dp = ndp;
		}
		long sum = 0;
		for(int i = 0; i <= N*10; i++){
			for(int j = 0; j <= N*10; j++){
				if(i == 0 && j == 0) continue;
				if(dp[i][j] > 0){
					sum += dp[i][j];
					ans += (double)dp[i][j] * i / (i + j);
				}
			}
		}
		
		return ans / sum;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomAppleEasy t = new RandomAppleEasy();
		int[] r = {2, 5, 6, 4, 9, 10, 6, 2};
		int[] g = {6, 7, 4, 5, 3, 2, 9, 1};
		double ret = t.theRed(r, g);
		out.println(ret);
	}

}
