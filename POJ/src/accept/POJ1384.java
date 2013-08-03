package accept;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ1384 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int E = 0;
	static int F = 0;
	static int N = 0;

	int[][] d = null;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		d = new int[N][2];
		for(int i = 0; i < N; i++){
			d[i][0] = sc.nextInt();
			d[i][1] = sc.nextInt();
		}
		int W = F-E;
		int[] dp = new int[W+1];
		for(int i = 1; i <= W; i++){
			dp[i] = INF;
		}
		for(int i = 0; i < N; i++){
			for(int j = 0; j <= W; j++){
				if(j - d[i][1] >= 0){
					dp[j] = min(dp[j], dp[j-d[i][1]] + d[i][0]);
				}
			}
		}
		if(dp[W] == INF){
			out.println("This is impossible.");
		}
		else{
			out.println("The minimum amount of money in the piggy-bank is " + dp[W] + ".");
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1384 p = new POJ1384();
		int T = sc.nextInt();
		int t = 1;
		while(t++ <= T){
			E = sc.nextInt();
			F = sc.nextInt();
			p.solve();
		}
	}

}
