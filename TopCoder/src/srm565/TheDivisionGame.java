package srm565;
import java.util.*;


public class TheDivisionGame {

	public long countWinningIntervals(int L, int R){
		int[] d = new int[R - L + 1];
		int[] LR = new int[R-L+1];
		for(int i = 0; i <= R - L; i++){
			LR[i] = L + i;
		}
		int rootR = (int)Math.sqrt(R);
		boolean[] furui = new boolean[rootR+1];
		for(int i = 2; i <= rootR; i++){
			if(!furui[i]){
				int tj = (i - L % i) % i;
				for(int j = tj; j <= R-L; j += i){
					while(LR[j] % i == 0){
						LR[j] /= i;
						d[j]++;
					}
				}
				for(int j = 1; (long)i * j <= rootR; j++){
					furui[i*j] = true;
				}
			}
			
		}
		for(int i = 0; i < LR.length; i++){
			if(LR[i] != 1){
				d[i]++;
			}
		}
		
		int max = 0;
		for(int i = 0; i < d.length; i++){
			max = Math.max(max, d[i]);
		}
		
		int num = 1;
		while(num <= max){
			num *= 2;
		}
		
		long[] dp = new long[num];
		dp[0] = 1;
		long ans = 0;
		for(int i = 0; i < d.length; i++){
			long[] ndp = new long[num];
			ndp[0] = 1;
			
			for(int j = 0; j < num; j++){
				
				int nidx = j ^ d[i];
				ndp[nidx] += dp[j];
				if(nidx > 0){
					ans  += dp[j];
				}
				
			}
			

			dp = ndp;
		}
		
		return ans;
	}
	
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TheDivisionGame t = new TheDivisionGame();
//		
		//long ret = t.countWinningIntervals(12566125, 12567777);
		//long ret = t.countWinningIntervals(304949729, 305059410);  // 5795337874
		
		long ret = t.countWinningIntervals(322182648, 322366988); // 16458568846
		/*
		 * 
		 {322182648, 322366988}

Expected:
16458568846
		 */
		//long ret = t.countWinningIntervals(12566125, 12567777); // 1313432
		System.out.println(ret);
	}

}
