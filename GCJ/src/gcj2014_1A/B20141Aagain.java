package gcj2014_1A;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class B20141Aagain {
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
	
	List<List<Integer>> g = null;
	int[][] dp = null;
	int[][] cntdp = null;
	public void solve() throws Exception{
		String s = br.readLine();
		N = Integer.parseInt(s);
		g = new ArrayList<List<Integer>>();
		for(int i = 0; i < N; i++){
			g.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < N-1; i++){
			s = br.readLine();
			String[] sp = s.split(" ");
			int f = Integer.parseInt(sp[0]) - 1;
			int t = Integer.parseInt(sp[1]) - 1;
			g.get(f).add(t);
			g.get(t).add(f);
		}
		int ans = INF;
		dp = new int[N][N];
		cntdp = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				dp[i][j] = -1;
				cntdp[i][j] = -1;
			}
		}
		for(int i = 0; i < N; i++){
			ans = Math.min(ans, check(i, i));
		}
		println(ans);

	}
	
	int check(int idx, int from){
		if(from != -1 && dp[idx][from] >= 0){
			return dp[idx][from];
		}
		int cnt = 0;
		for(int t : g.get(idx)){
			if(t != from){
				cnt++;
			}
		}
		if(cnt == 0){
			dp[idx][from] = 0;
			return dp[idx][from];
		}
		else if(cnt == 1){
			int to = -1;
			for(int t : g.get(idx)){
				if(t != from){
					to = t;
				}
			}
			int ans = getCnt(to, from);
			dp[idx][from] = ans;
			return ans;
		}
		else if(cnt == 2){
			int ans = 0;
			for(int t : g.get(idx)){
				if(t != from){
					ans += check(t, idx);
				}
			}
			dp[idx][from] = ans;
			return ans;
		}
		else{
			int ans = INF;
			List<Integer> tos = new ArrayList<Integer>();
			for(int t : g.get(idx)){
				if(t != from){
					tos.add(t);
				}
			}
			int[] checks = new int[tos.size()];
			int[] cnts = new int[tos.size()];
			for(int i = 0; i < tos.size(); i++){
				checks[i] = check(tos.get(i), idx);
				cnts[i] = getCnt(tos.get(i), idx);
			}
			for(int i = 0; i < tos.size()-1; i++){
				for(int j = i+1; j < tos.size(); j++){
					int base = checks[i] + checks[j];
					for(int k = 0; k < tos.size(); k++){
						if(k == i || k == j) continue;
						base += cnts[k];
					}
					ans = Math.min(ans, base);
				}
			}
			dp[idx][from] = ans;
			return ans;
		}
		
		
	
		
		
	}
	
	int getCnt(int idx, int from){
		if(from >= 0 && cntdp[idx][from] >= 0){
			return cntdp[idx][from];
		}
		int ans = 1;
		for(int t : g.get(idx)){
			if(t != from){
				ans += getCnt(t, idx);
			}
		}
		
		cntdp[idx][from] = ans;
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-large-practice.in");
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
		
		B20141Aagain b = new B20141Aagain();
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
