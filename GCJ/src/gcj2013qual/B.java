package gcj2013qual;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class B {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		String yes = "YES";
		String no = "NO";
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] d = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				d[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(d[i][j] == 100) continue;
				boolean fv = true;
				boolean fh = true;
				int target = d[i][j];
				for(int k = 0; k < N; k++){
					if(d[k][j] > target){
						fv = false;
					}
				}
				for(int k = 0; k < M; k++){
					if(d[i][k] > target){
						fh = false;
					}
				}
				if(!fv && !fh){
					out.println(no);
					return;
				}
			}
		}
		out.println(yes);
		return;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-large.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		B b = new B();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
