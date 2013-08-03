package d174;
import java.io.*;
import java.util.*;
import java.math.*;

public class E174 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	
	final int MOD = 1000000007;
	
	int[] dp = null;
	long t = 0;
	int[] d = null;
	List<Integer> l = null;
	public void solve() throws Exception{

		n = sc.nextInt();
		int q = sc.nextInt();
		t = sc.nextInt();
		d = new int[n+1];
		for(int i = 0; i < n; i++){
			d[i+1] = sc.nextInt();
		}
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		//Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		for(int i = 0; i < q; i++){
			int b = sc.nextInt();
			int c = sc.nextInt();
			boolean fb = false;
			boolean fc = false;
			for(int j = 0; j < list.size(); j++){
				List<Integer> l = list.get(j);
				if(l.get(l.size()-1).intValue() == c){
					l.add(b);
					fb = true;
				}
				if(l.get(0).intValue() == b){
					l.add(0, c);
					fc = true;
				}
			}
			if(fb && fc){
				System.out.println("0");
				return;
			}
			if(!fb && !fc){
				List<Integer> l = new ArrayList<Integer>();
				l.add(c);
				l.add(b);
				list.add(l);
			}
		}
		//map = null;
		
		
		for(int i = 0; i < list.size(); i++){
			long tax = 0;
			List<Integer> l = list.get(i);
			for(int j = 0; j < l.size(); j++){
				int idx = l.get(j);
				tax += (long)d[idx] * j;
			}
			t -= tax;
			int sum = 0;
			for(int j = l.size() -1; j >= 0; j--){
				int idx = l.get(j);
				int tmp = sum;
				sum += d[idx];
				d[idx] += tmp;
			}
		}
		list = null;
		if(t < 0){
			System.out.println("0");
			return;
		}

		int[] dp = new int[(int)t+1];
		dp[0] = 1;
		for(int i = 1; i <= n; i++){			
			int[] ndp = new int[(int)t+1];
			
			for(int j = 0; j <= t; j++){
				ndp[j] = dp[j];
				int prevIdx = j - d[i];
				if(prevIdx >= 0) ndp[j] += ndp[prevIdx];	
					
				if(ndp[j] < 0){
					ndp[j] += MOD;
				}
				ndp[j] %= MOD;
				
				
				/*
				for(int k = 0; j + k * d[i] <= t; k++){
					int idx = j+k*d[i];
					ndp[idx] += dp[j];
					ndp[idx] %= MOD;
				}
				*/
				
			}

			dp = ndp;
		}
		
		
		int ans = dp[(int)t];
		System.out.println(ans);
	}
	


	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
	
		sc =  new Scanner(System.in);
		E174 t = new E174();
		t.solve();

	}

}