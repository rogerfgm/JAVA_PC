package srm586;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PiecewiseLinearFunction {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int maximumSolutions(int[] Y){
		N = Y.length;
		int ans = 0;
		for(int i = 0; i < N; i++){
			if(i != 0 && Y[i] == Y[i-1]){
				return -1;
			}
			{
				double p = Y[i] - 0.5;
				int cnt = 0;
				for(int j = 0; j < N-1; j++){
					if(p > Y[j] && p < Y[j+1] || p > Y[j+1] && p < Y[j]){
						cnt++;
					}
				}
				ans = max(ans, cnt);
			}
			{
				double p = Y[i] + 0.5;
				int cnt = 0;
				for(int j = 0; j < N-1; j++){
					if(p > Y[j] && p < Y[j+1] || p > Y[j+1] && p < Y[j]){
						cnt++;
					}
				}
				ans = max(ans, cnt);
			}
			{
				int p = Y[i];
				int cnt = 0;
				for(int j = 0; j < N-1; j++){
					if(p >= Y[j] && p < Y[j+1] || p > Y[j+1] && p <= Y[j]){
						cnt++;
					}
				}
				if(p == Y[N-1])cnt++;
				ans = max(ans, cnt);
			}
		}
		
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PiecewiseLinearFunction t = new PiecewiseLinearFunction();
		int[] Y = {3, 2};
		int r = t.maximumSolutions(Y);
		out.println(r);
	}

}
