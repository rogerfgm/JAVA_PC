package gcj2011;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B_2011_2 {
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
		String[] sp = br.readLine().split(" ");
		int R = parseInt(sp[0]);
		int C = parseInt(sp[1]);
		
		
		long[][] d= new long[R][C];
		for(int i = 0; i < R; i++){
			String s = br.readLine();
			for(int j = 0; j < C; j++){
				d[i][j] = s.charAt(j) - '0';
			}
		}
		
		int ans = 0;
		
		for(int i = 3; i <= min(R, C); i++){
			for(int j = 0; j < min(R, R-i + 1); j++){
				for(int k = 0; k < min(C, C-i + 1); k++){
					double yoko = 0;
					double tate = 0;
					double mid = 0;
					if(i % 2 == 0){
						mid = i / 2 - 0.5;
					}
					else{
						mid = i / 2;
					}

					for(int t = 0; t < i; t++){
						for(int yo = 0; yo < i; yo++){
							if(t == 0 || t == i - 1){
								if(yo == 0 || yo == i - 1){
									continue;
								}
							}
							
							int y = j + t;
							int x = k + yo;
							yoko += (yo - mid) * d[y][x];
							tate += (t - mid) * d[y][x];
						}
					}
					
					if(abs(yoko) < DF && abs(tate) < DF){
						ans = i;
					}
				}
			}
		}
		if(ans == 0){
			out.println("IMPOSSIBLE");
		}
		else{
			out.println(ans);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		System.out.println(new Date());
		B_2011_2 b = new B_2011_2();
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
			System.out.println(t);
			t++;
		}
		out.close();
		fw.close();
		System.out.println(new Date());
	}
}
