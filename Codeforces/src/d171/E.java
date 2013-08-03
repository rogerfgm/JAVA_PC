package d171;
import java.util.*;
import java.io.*;

public class E {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int n = 0;
	String s = null;
	int[] z = null;
	public void solve() throws Exception{

		String s = sc.next();
		int ans = INF;
		int[] dp = new int[2];
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i = 0; i < s.length()-1; i++){
			int[] ndp = new int[2];
			ndp[0] = ndp[1] = INF;
			if(s.charAt(i) == '0'){
				ndp[0] = Math.min(ndp[0], dp[0]);
				ndp[0] = Math.min(ndp[0], dp[1] + 1);
				ndp[1] = Math.min(ndp[1], dp[0] + 1);
				ndp[1] = Math.min(ndp[1], dp[1] + 1);
			
			}
			else{
				ndp[0] = Math.min(ndp[0], dp[0]+1);
				ndp[0] = Math.min(ndp[0], dp[1] + 1);
				ndp[1] = Math.min(ndp[1], dp[1]);
				ndp[1] = Math.min(ndp[1], dp[0] + 1);

			}
			dp = ndp;
		}
		if(s.charAt(s.length()-1) == '0'){
			ans = Math.min(ans, dp[0]);
			ans = Math.min(ans, dp[1]+1);
		}
		else{
			ans = Math.min(ans, dp[1] + 1);
			ans = Math.min(ans, dp[0] + 1);
		}
		
		
		System.out.println(ans);
		
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
		sc =  new Scanner(System.in);
		E t = new E();
		t.solve();
	}


}