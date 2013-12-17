package done.d219;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class D219 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] tm = nextInts();
		int n = tm[0];
		int m = tm[1];
		int Q = tm[2];
	
		boolean[][] d = new boolean[n][m];
		for(int i = 0; i < n; i++){
			String s = nextS();
			for(int j = 0; j < m; j++){
				char c = s.charAt(j);
				if(c == '0'){
					d[i][j] = true;
				}
			}
		}
		boolean[][][][] f = new boolean[n][m][n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(d[i][j]){
					int r = i;
					for(int k = j; k < m; k++){
						if(d[i][k]){
							r = k;
							f[i][j][i][k] = true;
						}
						else{
							break;
						}
					}
					for(int k = i; k < n; k++){
						if(!d[k][j]){
							break;
						}
						for(int p = j; p <= r; p++){
							if(d[k][p]){
								f[i][j][k][p] = true;
							}
							else{
								r = p-1;
								break;
							}
						}
					}
				}
			}
		}
		int[][][][] fwd = new int[n][m][n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				for(int k = i; k < n; k++){
					for(int p = j; p < m; p++){
						if(f[i][j][k][p]){
							fwd[i][j][k][p]++;
						}
						if(k > i && p > j){
							fwd[i][j][k][p] += fwd[i][j][k-1][p] + fwd[i][j][k][p-1] - fwd[i][j][k-1][p-1];
						}
						else if(k > i){
							fwd[i][j][k][p] += fwd[i][j][k-1][p];
						}
						else if(p > j){
							fwd[i][j][k][p] += fwd[i][j][k][p-1];
						}
					}
				}
			}
		}
		int[][][][] dp = new int[n][m][n][m];
		for(int i = n-1; i >= 0; i--){
			for(int j = m-1; j >= 0; j--){
				for(int k = i; k >= 0; k--){
					for(int p = j; p >= 0; p--){
						dp[k][p][i][j] += fwd[k][p][i][j];
						if(k < i && p < j ){
							dp[k][p][i][j] += dp[k+1][p][i][j] + dp[k][p+1][i][j] - dp[k+1][p+1][i][j];
						}
						else if(k < i){
							dp[k][p][i][j] += dp[k+1][p][i][j];
						}
						else if(p < j){
							dp[k][p][i][j] += dp[k][p+1][i][j];
						}
					}
				}
			}
		}
		for(int q = 0; q < Q; q++){
			tm = nextInts();
			int i = tm[0]-1;
			int j = tm[1]-1;
			int k = tm[2]-1;
			int p = tm[3]-1;
			out.println(dp[i][j][k][p]);
			
		}
		
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
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
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		D219 t = new D219();
		t.solve();
		bw.close();
	}

}
