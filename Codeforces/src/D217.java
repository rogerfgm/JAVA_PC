


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class D217 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int[] t = nextInts();
		int n = t[0];
		int m = t[1];
		
		boolean[][] w = new boolean[n][m];
		boolean[][] p = new boolean[n][m];
		int lx = INF;
		int rx = -1;
		int uy = INF;
		int by = -1;
		int cnt = 0;
		for(int i = 0; i < n; i++){
			String s = nextS();
			for(int j = 0; j < m; j++){
				if(s.charAt(j) != '.'){
					w[i][j] = true;
					lx = min(lx, j);
					rx = max(rx, j);
					uy = min(uy, i);
					by = max(by, i);
					cnt++;
				}
			}
		}
		if(cnt == 1){
			write(w, p);
		}
		else{
			int width = max(rx - lx, by - uy);
			if(rx - lx >= by - uy){
				for(int i = 0; i + width < n; i++){
					int b = i + width;
					int c = 0;
					for(int j = lx; j <= rx; j++){
						if(w[i][j]) c++;
						if(w[b][j]) c++;
					}
					for(int k = i+1; k < i + width; k++){
						if(w[k][lx]) c++;
						if(w[k][rx]) c++;
					}
					if(c == cnt){
						for(int j = lx; j <= rx; j++){
							if(!w[i][j]) p[i][j] = true;
							if(!w[b][j]) p[b][j] = true;
						}
						for(int k = i+1; k < i + width; k++){
							if(!w[k][lx]) p[k][lx] = true;
							if(!w[k][rx]) p[k][rx] = true;
						}
						write(w, p);
						return;
					}
				}
			}
			if(by - uy >= rx - lx){
				for(int i = 0; i + width < m; i++){
					int r = i + width;
					int c = 0;
					for(int j = uy; j <= by; j++){
						if(w[j][i]) c++;
						if(w[j][r]) c++;
					}
					for(int j = i+1; j < i + width; j++){
						if(w[uy][j]) c++;
						if(w[by][j]) c++;
					}
					if(c == cnt){
						for(int j = uy; j <= by; j++){
							if(!w[j][i]) p[j][i] = true;
							if(!w[j][r]) p[j][r] = true;
						}
						for(int j = i+1; j < i + width; j++){
							if(!w[uy][j]) p[uy][j] = true;
							if(!w[by][j]) p[by][j] = true;
						}
						write(w, p);
						return;
					}
				}
			}
		}
		out.println("-1");
	}
	
	void write(boolean[][] w, boolean[][] p){
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < w[0].length; j++){
				if(w[i][j]){
					out.print("w");;
				}
				else if(p[i][j]){
					out.print("+");
				}
				else{
					out.print(".");
				}
			}
			out.println();
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
		D217 t = new D217();
		t.solve();
		bw.close();
	}

}
