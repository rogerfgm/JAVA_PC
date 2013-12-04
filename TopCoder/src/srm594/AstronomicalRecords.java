package srm594;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class AstronomicalRecords {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;

	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[][] dp = null;
	int[] A = null;
	int[] B = null;
	public int minimalPlanets(int[] A, int[] B){
		
		this.A = A;
		this.B = B;
		
		int ans = A.length + B.length;
		
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < B.length; j++){
				long a = (long)A[i] * B[j] / A[i];
				long b = (long)B[j] * A[i] / B[j];
				
				dp = new int[A.length][B.length];
				ans = min(ans, get(i, j, a, b) + i + j);
			}
		}
		
		
		return ans;
	}
	
	int get(int ai, int bi, long a, long b){
		int sum = Integer.MAX_VALUE;
		if(ai >= A.length){
			sum = B.length - bi;
			return sum;
		}
		else if(bi >= B.length){
			sum = A.length - ai;
			return sum;
		}
		
		if(dp[ai][bi] > 0){
			return dp[ai][bi];
		}

		if(A[ai] * a == B[bi] * b){
			sum = min(get(ai+1, bi+1, a, b) + 1, sum);
			sum = min(sum, get(ai, bi+1, a, b) + 1);
			sum = min(sum, get(ai+1, bi, a, b) + 1);
		}
		else{
			sum = min(sum, get(ai, bi+1, a, b) + 1);
			sum = min(sum, get(ai+1, bi, a, b) + 1);
		}
		dp[ai][bi] = sum;
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AstronomicalRecords t = new AstronomicalRecords();
		int[] A = {900, 564, 684, 696, 588, 780, 816, 516, 492, 540, 648, 948, 612, 456, 828, 480, 756, 720, 420, 444, 936, 792, 852, 744, 960, 768, 876, 528, 396, 432, 600, 636, 912, 504, 384, 672, 624};
		int[] B = {520, 360, 624, 336};
		int r = t.minimalPlanets(A, B);
		out.println(r);
	}

}
