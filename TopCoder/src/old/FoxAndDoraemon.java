package old;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class FoxAndDoraemon {

	int[] wc = null;

	int spC = 0;
	int[][] dp = null;
	public int minTime(int[] workCost, int splitCost){
		spC = splitCost;
		Arrays.sort(workCost);
		wc = new int[workCost.length];
		for(int i = 0; i < wc.length; i++){
			wc[i] = workCost[workCost.length - 1 - i];
		}
		dp = new int[wc.length][wc.length *2 + 10];
		int ret = check(0, 1);
		return ret;
	}
	
	int check(int idx, int num){
		int orgNum = num;
		if(idx >= wc.length){
			return 0;
		}
		if(dp[idx][num] > 0){
			return dp[idx][num];
		}
		
		if(num >= wc.length - idx ){
			return wc[idx];
		}
		
		
		int ret = check(idx, num * 2) + spC;
		
		for(int i = 1; num - i >= 1; i++){
			int time = Math.max(check(idx+i, num-i), wc[idx]);
			ret = Math.min(ret, time);
		}
	

		
		dp[idx][orgNum] = ret;
		
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAndDoraemon f = new FoxAndDoraemon();
		int[] w ={3000,3000,3000,3000,3000,3000,3000,3000,3000,3000};
		int sp = 3000;
		int ret = f.minTime(w, sp);
		System.out.println(ret);

	}

}
