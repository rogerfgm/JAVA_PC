package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class CTLE {
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
	static List<String> dic = new ArrayList<String>();
	int[][] dp = null;
	String S = null;
	Object[][] targets = null;
	public void solve() throws Exception{
	
		
		String s = br.readLine();
		N = s.length();
		dp = new int[N][6];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 6; j++){
				dp[i][j] = INF;
			}
		}
		targets = new Object[N][5];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 5; j++){
				List<Integer> l = new ArrayList<Integer>();
				targets[i][j] = l;
			}
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < dic.size(); j++){
				String d = dic.get(j);

				if(d.length() + i <= N){
					List<Integer> dif = new ArrayList<Integer>();
					for(int k = 0; k < d.length(); k++){
						if(d.charAt(k) != s.charAt(i + k)){
							dif.add(k);
						}
					}
					boolean f = true;
					for(int k = 1; k < dif.size(); k++){
						if(dif.get(k).intValue() - dif.get(k-1).intValue() < 5){
							f = false;
						}
					}
					if(f){
						int pos = 4;
						if(dif.size() > 0 && dif.get(0) < 4){
							pos = dif.get(0);
						}
						for(int k = 0; k <= pos; k++){
							((List<Integer>)targets[i][k]).add(j);
						}
					}
				}
			}
		}
		
		
		
		S = s;
		check(0, 0, 0);
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < 5; i++){
			ans  = min(ans, dp[N-1][i]);
		}
		out.println(ans);
		
	}
	
	void check(int idx, int n, int e){
		if(idx == N){
			return;
		}
		for(int i = 0; i < ((List<Integer>)targets[idx][e]).size(); i++){
			int didx = ((List<Integer>)targets[idx][e]).get(i);
			String s = dic.get(didx);
	owari: if(s.length() + idx <= N){
				int cnt = 0;
				int E = e;
				for(int j = 0; j < s.length(); j++){
					if(s.charAt(j) != S.charAt(idx + j)){
						if(E > 0){
							break owari;
						}
						E = 4;
						cnt++;
					}
					else{
						if(E > 0) E--;
					}
				}
			
				if(dp[idx+s.length()-1][E] > n + cnt){
					dp[idx + s.length()-1][E] = n + cnt;
					check(idx + s.length(), n + cnt, E);
				}
				
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		String s = null;
		BufferedReader dr = new BufferedReader(new FileReader("dic.txt"));
		while((s = dr.readLine()) != null){
			dic.add(s);
		}
		dr.close();
		
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		CTLE b = new CTLE();
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
