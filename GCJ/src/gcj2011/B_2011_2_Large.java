package gcj2011;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B_2011_2_Large {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.00000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		String[] sp = br.readLine().split(" ");
		int R = parseInt(sp[0]);
		int C = parseInt(sp[1]);
		//sysout.print(R + " " + C + " ");
		int[][] d= new int[R][C];
		for(int i = 0; i < R; i++){
			String s = br.readLine();
			for(int j = 0; j < C; j++){
				d[i][j] = s.charAt(j) - '0';
			}
		}
		
		int ans = 0;
		
		double[][][] t = new double[R][C][Math.max(R, C)+1];
		double[][][] y = new double[R][C][Math.max(R, C)+1];
		
		
		
		
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				{
					// i, j中心で奇数
					long st = 0;
					long sy = 0;
					
					for(int k = 1; ; k++){
						int lx = j - k;
						int rx = j + k;
						if(lx  < 0 || rx >= C){
							break;
						}
						sy +=  (d[i][rx] - d[i][lx]) * k; 
						y[i][j-k][2*k+1] = sy;
					}
					for(int k = 1; ; k++){
						int uy = i - k;
						int by = i + k;
						if(uy  < 0 || by >= R){
							break;
						}
						st +=  (d[by][j] - d[uy][j]) * k; 
						t[i-k][j][2*k+1] = st;
					}
				}
				{
					// i, j とi+1, j+1の間
					double st = 0;
					double sy = 0;
					
					for(int k = 1; ; k++){
						int lx = j - k + 1;
						int rx = j + k;
						if(lx  < 0 || rx >= C){
							break;
						}
						sy +=  (d[i][rx] - d[i][lx]) * (k-0.5); 
						y[i][j-k + 1][2*k] = sy;
					}
					for(int k = 1; ; k++){
						int uy = i - k + 1;
						int by = i + k;
						if(uy  < 0 || by >= R){
							break;
						}
						st +=  (d[by][j] - d[uy][j]) * (k-0.5); 
						t[i-k + 1][j][2*k] = st;
					}
				}
			}
		}
		
		
		
		for(int i = 3; i <= Math.min(R, C); i++){
			double[][][] a = new double[R][C][2];
			for(int j = 0; j < R -i + 1; j++){
				double sm = 0;
				for(int k = 0; k < i; k++){
					sm += t[j][k][i];
				}
				a[j][0][0] += sm;
				for(int k = i; k < C; k++){
					sm += t[j][k][i];
					sm -= t[j][k-i][i];
					a[j][k-i+1][0] += sm;
					
				}
			}
			for(int j = 0; j < C -i + 1; j++){
				double sm = 0;
				for(int k = 0; k < i; k++){
					sm += y[k][j][i];
				}
				a[0][j][1] += sm;
			
				for(int k = i; k < R; k++){
					sm += y[k][j][i];
					sm -= y[k-i][j][i];

					a[k-i+1][j][1] += sm;
				}
			}
			
			
			for(int j = 0; j < R; j++){
				if(j + i > R) break;
				for(int k = 0; k < C; k++){
					if(k + i > C){
						break;
					}
	
					double tate = a[j][k][0];
					double yoko = a[j][k][1];
					double dist = i / 2;;
					if(i % 2 == 0){
						dist -= 0.5;
					}
					tate += dist * (-d[j+i-1][k] - d[j+i-1][k+i-1] + d[j][k+i-1] +  d[j][k]); // 逆をやる
					yoko += dist * (-d[j+i-1][k+i-1] - d[j][k+i-1] +  d[j][k] + d[j+i-1][k]); // 逆をやる
					
					if(abs(tate) < DF && abs(yoko) < DF){
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
		sysout.println(new Date());
		File file = new File("B-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		B_2011_2_Large b = new B_2011_2_Large();
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
			sysout.println(t);
			t++;
		}
		out.close();
		fw.close();
		sysout.println(new Date());
	}
}
