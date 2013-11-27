import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class IncrementAndDoubling {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int[][] dp = null;
	public int getMin(int[] d){
		int max = 0;
		int ans = 0;
		dp = new int[1001][1001];
		for(int i = 0; i < 1001; i++){
			for(int j = 0; j < 1001; j++){
				dp[i][j] = -1;
			}
		}
		for(int i = 0; i < d.length; i++){
			int a = d[i];
			int cnt = 0;
			while(a > 1){
				a /= 2;
				cnt++;
			}
			max = max(max, cnt);
		}
		ans += max;
		for(int i = 0; i < d.length; i++){
			if(d[i] != 0)
			ans += get(d[i], 1) + 1;
		}
		
		return ans;
	}
	
	int get(int t, int n){
		if(dp[t][n] != -1) return dp[t][n];
		
		int INF = 1000000;
		int ans = INF;
		if(t == n){
			return 0;
		}
		if(n * 2 <= t){
			ans = get(t, n*2);
		}
		ans = min(ans, get(t, n+1) + 1);
		dp[t][n] = ans;
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IncrementAndDoubling t = new IncrementAndDoubling();
		int[] in ={0, 0, 1, 0, 1};
		int r = t.getMin(in);
		out.println(r);
	}

}
