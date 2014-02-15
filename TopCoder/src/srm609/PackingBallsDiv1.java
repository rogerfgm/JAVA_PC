package srm609;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PackingBallsDiv1 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minPacks(int K, int A, int B, int C, int D){
		int ans = 0;
		int[] X = new int[K];
		X[0] = A;
		for(int i = 1; i < K; i++){
			long t = X[i-1];
			long n = (t * B + C) % D + 1;
			X[i] = (int)n;
		}
		
		for(int i = 0; i < K; i++){
			int mul = X[i] / K;
			ans += mul;
			X[i] -= mul * K;
			
		}
		Arrays.sort(X);
		int mx = INF;
		for(int i = 0; i < K; i++){
		
			mx = min(mx, X[i] + K-i -1);
		}
		if(mx != INF){
			ans += mx;
		}
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PackingBallsDiv1 t = new PackingBallsDiv1();
		int r = t.minPacks(3, 4, 2, 5, 6);
		out.println(r);
	}

}
