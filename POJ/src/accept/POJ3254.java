package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ3254 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int MOD = 100000000;
	int[][] dp = null;
	boolean[][] f = null;
	public void solve() throws Exception{
		f = new boolean[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				int a = sc.nextInt();
				if(a == 1){
					f[i][j] = true;
				}
			}
		}
		dp = new int[N][1<<M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 1 << M; j++){
				dp[i][j] = -1;
			}
		}
		int ret = check(0, 0, 0, 0);
		out.println(ret);
		
	}
	
	int check(int in, int im, int pp, int p){
		if(in == N){
			return 1;
		}
		if(im == M){
			return check(in+1, 0, p, 0);
		}
		if(im == 0 && dp[in][pp] >= 0){
			return dp[in][pp];
		}
		long ret = 0;
	
		boolean flag = f[in][im];
		if(im > 0 &&  (p & (1 << (im-1))) > 0 ){
			flag = false;
		}
		if( (pp & 1 << im) > 0 ){
			flag = false;
		}
		if(flag){
			int np = p | 1 << im;
			ret += check(in, im+1, pp, np);
			ret %= MOD;
		}
		ret += check(in, im+1, pp, p);
		
		
		ret %= MOD;
		if(im == 0){
			dp[in][pp] = (int)ret;
		}
		return (int)ret;
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
		POJ3254 p = new POJ3254();
		while(true){
			
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}

}
