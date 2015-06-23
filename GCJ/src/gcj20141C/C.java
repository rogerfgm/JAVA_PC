package gcj20141C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class C {
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
	int K = 0;

	
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split(" ");
		N = Integer.parseInt(sp[0]);
		M = Integer.parseInt(sp[1]);
		int min = Math.min(N, M);
		int max = Math.max(N, M);
		N = min;
		M = max;
		K = Integer.parseInt(sp[2]);
		int ans = K;
		for(int i = 1; i <= (N-2) * (M - 2); i++){
			ans = Math.min(ans, check(i));
		}
		println(ans);
	}
	
	int check(int c){
	
		if(N == 3){
			boolean[][] d = new boolean[N][M];
			for(int i = 1; i < c + 1; i++){
				d[1][i] = true;
			}
			
			int cnt = draw(d);
			if(cnt  + c < K){
				cnt += K - (cnt + c);
			}
			return cnt;
		}
		int ans = Integer.MAX_VALUE;
		if(N == 4 && c == 3){
			boolean[][] d = new boolean[N][M];
			for(int i = 1; i < c  + 1; i++){
				d[1][i] = true;
			}
			
			int cnt = draw(d);
			if(cnt  + c < K){
				cnt += K - (cnt + c);
			}
			ans = Math.min(cnt, ans);
		}
		if(N == 4){
			boolean[][] d = new boolean[N][M];
			for(int i = 1; i < c /2 + 1; i++){
				d[1][i] = true;
				d[2][i] = true;
			}
			if(c % 2 == 1){
				int x = 1 + c / 2;
				d[1][x] = true;
			}
			
			int cnt = draw(d);
			if(cnt  + c < K){
				cnt += K - (cnt + c);
			}
			ans = Math.min(ans, cnt);
		}
		
		return ans;
	}
	
	int draw(boolean[][] d){
		boolean[][] ck = new boolean[d.length][d[0].length];
		for(int i = 0; i < d.length; i++){
			for(int j = 0; j < d[0].length; j++){
				if(d[i][j]){
					{
						int y = i - 1;
						int x = j;
						if(y >= 0 && y < N && x >= 0 && x < M && !d[y][x]){
							ck[y][x] = true;
						}
					}
					{
						int y = i + 1;
						int x = j;
						if(y >= 0 && y < N && x >= 0 && x < M && !d[y][x]){
							ck[y][x] = true;
						}
					}
					{
						int y = i;
						int x = j-1;
						if(y >= 0 && y < N && x >= 0 && x < M && !d[y][x]){
							ck[y][x] = true;
						}
					}
					{
						int y = i;
						int x = j+1;
						if(y >= 0 && y < N && x >= 0 && x < M && !d[y][x]){
							ck[y][x] = true;
						}
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < d.length; i++){
			for(int j = 0; j < d[0].length; j++){
				if(ck[i][j]) cnt++;
			}
		}
		return cnt;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		else{
			throw new Exception("can't find a input file : " + file.getAbsolutePath());
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		C b = new C();
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
			System.out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
	
	void print(int i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(int i){
		out.println(i + "");
		System.out.println(i);
	}
	void print(String s){
		out.print(s);
		System.out.print(s);
	}
	void println(String s){
		out.println(s);
		System.out.println(s);
	}
	void print(long i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(long i){
		out.println(i + "");
		System.out.println(i);
	}
}
