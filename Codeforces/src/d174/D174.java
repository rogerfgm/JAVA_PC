package d174;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class D174 {
	long INF = Long.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	int[] d = null;
	long[] dp = null;
	public void solve() throws Exception{
		n = sc.nextInt();
		d = new int[n+1];
		dp = new long[n+1];
		for(int i = 2; i <= n; i++){
			d[i] = sc.nextInt();
			dp[i] = INF;
		}
		
		for(int i = 2; i <= n; i++){
			if(dp[i] != INF) continue;
			
			Map<Integer, Long> map = new HashMap<Integer, Long>();
			map.put(1, (long)-1);
			int x = i;
			long y = 0;
			boolean fail = false;
			while(true){
				map.put(x, y);
				if(dp[x] != INF && dp[x] == -1){
					fail = true;
					break;
				}
				else if(dp[x] != INF){
					y+=dp[x];
					break;
				}
				y += d[x];
				x -= d[x];
				if(x <= 0){
					break;
				}
				
				y += d[x];
				x += d[x];

				if(x > n){
					break;
				}
				if(map.containsKey(x)){
					fail = true;
					break;
				}
				
			}
			if(fail){
				for(int key : map.keySet()){
					dp[key] = -1;
				}
			}
			else{
				for(int key : map.keySet()){
					dp[key] = y - map.get(key);
				}
			}
		}
		
		
		
		
		for(int i = 2; i <= n; i++){
			
			if(dp[i] == -1){
				System.out.println("-1");
			}
			else{
				long y = dp[i] + i -1;
				System.out.println(y);
			}
			
		}
	}

	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		D174 t = new D174();
		t.solve();

	}

}
