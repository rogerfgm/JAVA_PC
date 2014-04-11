package srm616;
public class WakingUp {
	  static int gcd(int a, int b){
		    return b == 0 ? a : gcd(b, a % b);
		  }

	
		  static int lcm(int a, int b){
		    return a * b / gcd(a, b);
		  }
	
	public int maxSleepiness(int[] p, int[] s, int[] v, int D){
		int ans = 0;
		int N = p.length;
		int del = 0;
		int add = 0;
		int LCM = p[0];
		for(int i = 1; i < N; i++){
			LCM = lcm(LCM, p[i]);
		}
		for(int i = 0; i < N; i++){
			int cnt = LCM / p[i];
			del += v[i] * cnt;
	
		}
		if(del > D * LCM){
			return -1;
		}
		
		int amt = 0;
		for(int i = 1; ; i++){
			if(i == LCM * 3) break;
			
			amt+= D;
			for(int j = 0; j < N; j++){
				if(i >= s[j]){
					int t = i - s[j];
					if(t % p[j] == 0){
						amt-= v[j];
					}
				}
			}
			if(amt < 0){
				ans += Math.abs(amt);
				amt = 0;
			}
			
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WakingUp t = new WakingUp();
		int[] p = {5, 6, 5, 3, 8, 3, 4};
		int[] s = {1, 1, 3, 2, 6, 3, 3};
		int[] v = {42, 85, 10, 86, 21, 78, 38};
		int D = 88;
		int r = t.maxSleepiness(p, s, v, D);
		System.out.println(r);
	}

}
