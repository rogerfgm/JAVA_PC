



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class TLED219 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] z = nextInts();
		int n = z[0];
		int m = z[1];
		int Q = z[2];
		boolean[][] d = new boolean[n][m];
		for(int i = 0; i < n; i++){
			String s = nextS();
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == '0'){
					d[i][j] = true;
				}
			}
		}
		int[][][][] dp = new int[n][n][m][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int k = 0; k < m; k++){
					for(int p = 0; p < m; p++){
						dp[i][j][k][p] = -1;
					}
				}
			}
		}
		
		
		
		
		for(int q = 0; q < Q; q++){
			z = nextInts();
			int u = z[0] - 1;
			int b = z[2] - 1;
			int l = z[1] - 1;
			int r = z[3] - 1;
			if(dp[u][b][l][r] != -1){
				out.println(dp[u][b][l][r]);
				continue;
			}
			int cnt = 0;
			for(int i = 1; i <= n; i++){
				for(int j = 1; j <= m; j++){
					
					for(int y = u; y+i <= b+1; y++){
						for(int x = l; x + j <= r+1; x++){

							boolean f = true;
							for(int Y = 0; Y < i; Y++){
								for(int X = 0; X < j; X++){
									if(!d[y+Y][x+X]){
										f = false;
									}
								}
							}
							if(f){
								cnt++;
							}
						}
					}
				}
			}
			dp[u][b][l][r] = cnt;
			out.println(cnt);
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
		TLED219 t = new TLED219();
		t.solve();
		bw.close();
	}

}
