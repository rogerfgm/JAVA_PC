package srm637;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class GreaterGame {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[] ds = null;
	int[] un = null;
	int[] hd = null;
	double[][][] dp = null;
	public double calc(int[] hand, int[] se){
		List<Integer> snl = new ArrayList<Integer>();
		List<Integer> dsl = new ArrayList<Integer>();
		List<Integer> unl = new ArrayList<Integer>();
		N = hand.length;
		for(int i = 0; i < N; i++){
			int t = se[i];
			if(t != -1){
				dsl.add(t);
			}
			snl.add(hand[i]);
		}
		for(int i = 1; i <= 2 * N; i++){
			if(!snl.contains(i) && !dsl.contains(i)){
				unl.add(i);
			}
		}
		Arrays.sort(hand);
		
		hd = hand;
		Collections.sort(dsl);
		Collections.sort(unl);
		ds = new int[dsl.size()];
		for(int i = 0; i < dsl.size(); i++){
			ds[i] = dsl.get(i);
		}
		un = new int[unl.size()];
		for(int i = 0; i < unl.size(); i++){
			un[i] = unl.get(i);
		}
		dp = new double[N+1][N+1][N+1];
		for(int i = 0; i < N+1; i++){
			for(int j = 0; j < N+1; j++){
				for(int k = 0; k < N+1; k++){
					dp[i][j][k] = -1;
				}
			}
		}
		double ans = get(0, 0, 0);
		
		return ans;
	}
	
	double get(int idx, int dsidx, int uncnt){
		if(idx >= N){
			return 0;
		}
		if(dp[idx][dsidx][uncnt] >= 0){
			return dp[idx][dsidx][uncnt];
		}
		double ans = 0;
		int t = hd[idx];
		if(dsidx < ds.length){
			double a = 0;
			int nextdsidx = dsidx;
			for(int i = dsidx; i < ds.length; i++){
				if(ds[i] < t){
					nextdsidx = i+1;
					a = 1;
					break;
				}
			}
			a = a + get(idx+1, nextdsidx, uncnt);
			ans = Math.max(ans, a);
		}
		if(uncnt < un.length){
			double a = 0;
			double size = un.length;
			int cnt = 0;
			for(int i = 0; i < un.length; i++){
				if(un[i] < t){
					cnt++;
				}
			}
			a += cnt / size;
			a = a + get(idx+1, dsidx, uncnt+1);
			ans = Math.max(ans, a);
		}
		
		dp[idx][dsidx][uncnt] = ans;
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GreaterGame t = new GreaterGame();
		int[] a1 = {4, 2};
		int[] a2 = {1, 3};
		double a = t.calc(a1, a2);
		out.println(a);
	}

}
