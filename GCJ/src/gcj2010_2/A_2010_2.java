package gcj2010_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A_2010_2 {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		N = parseInt(br.readLine());
		int[][] d = new int[2*N-1][2*N-1];
		for(int i = 0; i < 2*N-1; i++){
			for(int j = 0; j < 2*N-1; j++){
				d[i][j] = -1;
			}
		}
		for(int i = 0; i < 2*N-1; i++){
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++){
				char c = s.charAt(j);
				if(c == ' '){
					d[i][j] = -1;
				}
				else{
					d[i][j] = c - '0';
				}
			}
		}
		int ans = INF;
		
		for(int i = 0; i < 2*N -1; i++){
			for(int j = 0; j < 2*N-1; j++){
				boolean f = true;
				for(int k = 0; k < 2*N-1; k++){
					for(int l = 0; l < 2*N-1; l++){
						{
							int y = k;
							int x = j + j - l;
							if(x >= 0 && x < 2*N-1){
								if(d[y][l] != -1 && d[y][x] != -1){
									if(d[y][l] != d[y][x]){
										f = false;
									}
								}
							}
						}
						{
							int y = i + i - k;
							int x = l;
							if(y >= 0 && y < 2*N-1){
								if(d[y][x] != -1 && d[k][x] != -1){
									if(d[y][x] != d[k][x]){
										f = false;
									}
								}
							}
						}
					}
				}
				if(f){
					int y = N-1-i;
					if(y < 0){
						y*= -1;
					}
					int x = N-1 - j;
					if(x < 0){
						x *= -1;
					}
					ans = min(ans, cnt(N + y + x));
				}
				
				
			}
		}
		
		ans -= cnt(N);
		out.println(ans);
		return;
	}
	
	int cnt(int n){
		int a = 0;
		for(int i = 1; i < n; i++){
			a += 2 * i;
		}
		a += n;
		return a;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//File file = new File("input.txt");
		File file = new File("A-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		A_2010_2 b = new A_2010_2();
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
