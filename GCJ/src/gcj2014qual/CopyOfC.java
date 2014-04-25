package gcj2014qual;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class CopyOfC {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int M = 0;
	char[][] c = null;
	boolean[][] b = null;
	char B = '*';
	public void solve() throws Exception{
		int R, C, M;
		String s = br.readLine();
		String[] sp = s.split(" ");
		R = parseInt(sp[0]);
		C = parseInt(sp[1]);
		M = parseInt(sp[2]);
		c = new char[R][C];
		b = new boolean[R][C];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				c[i][j] = '.';
				b[i][j] = true;
			}
		}
		if(R < C){
			int m = M;
			for(int j = 0; j < C; j++){
				for(int i = 0; i < R; i++){
					if(m > 0){
						m--;
						b[i][j] = false;
						c[i][j] = '*';
					}
				}
			}
		}
		else{
			int m = M;
			for(int i = 0; i < R; i++){
				for(int j = 0; j < C; j++){
					if(m > 0){
						m--;
						b[i][j] = false;
						c[i][j] = '*';
					}
				}
			}
		}
		if(M == 0 || M == R * C - 1){
			boolean f = false;
			for(int i = 0; i < R; i++){
				for(int j = 0; j < C; j++){
					if(c[i][j] != '*'){
						c[i][j] = 'c';
						f = true;
						break;
					}
				}
				if(f) break;
			}
		}
		else{
			for(int i = 0; i < R; i++){
				for(int j = 0; j < C; j++){
					try{
						if(c[i-1][j+1] == B || c[i-1][j] == B || c[i-1][j-1] == B || c[i][j-1] == B 
								|| c[i][j+1] == B || c[i+1][j-1] == B || c[i+1][j+1] == B || c[i+1][j] == B){
							b[i][j] = false;
						}
					}
					catch(Exception ex){
						
					}
				}
			}		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		CopyOfC b = new CopyOfC();
		int T = 0;
		if(sc != null){
			T = sc.nextInt();
		}
		else{
			T = parseInt(br.readLine());
		}
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
