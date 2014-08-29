package srm627;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class BubbleSortWithReversals {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int[] A = null;
	int[][] dp = null;
	public int getMinSwaps(int[] A, int K){
		this.A = A;
		dp = new int[50+1][50+1];
		for(int i = 0; i <= 50; i++){
			for(int j = 0; j <= 50; j++){
				dp[i][j] = INF;
			}
		}
		N = A.length;
		
		
		return get(A, 0, K);
	}
	
	int get(int[] a, int idx, int K){
		if(idx >= N-1){
			return 0;
		}
		if(dp[idx][K] != INF){
			return dp[idx][K];
		}
		int ans = INF;
		if(K == 0){
			ans = cnt(a, idx, a.length-1);
			dp[idx][K] = ans;
			return ans;
		}
		
		for(int i = idx; i < N; i++){
			int cnt = cnt(a, idx, i) + get(a, i+1, K);
			ans = Math.min(ans, cnt);
		}
		
		for(int i = idx+1; i < N; i++){
			int l = idx;
			int r = i;
			while(l < r){
				int tmp = a[l];
				a[l] = a[r];
				a[r] = tmp;
				l++;
				r--;
			}
		
			int cnt = cnt(a, idx, i) + get(a, i+1, K-1);
			ans = Math.min(ans, cnt);
			l = idx;
			r = i;
			while(l < r){
				int tmp = a[l];
				a[l] = a[r];
				a[r] = tmp;
				l++;
				r--;
			}
		}
		dp[idx][K] = ans;
		
		return ans;
	}
	int cnt(int[] a, int f, int t){
		int cnt = 0;
		for(int i = f; i <= t; i++){
			for(int j = i+1; j < N; j++){
				if(a[i] > a[j]){
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BubbleSortWithReversals t = new BubbleSortWithReversals();
		int[] A = {482,619,619,601,660,660,691,691,77,77,96,77};
		//int[] A = {2, 3, 1};
		int K = 9;
		int r = t.getMinSwaps(A, K);
		out.println(r);
	}

}
