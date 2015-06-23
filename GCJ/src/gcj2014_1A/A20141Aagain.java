package gcj2014_1A;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A20141Aagain {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 1000;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	int L = 0;
	String imp = "NOT POSSIBLE";
	String[] gs = null;
	
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split(" ");
		N = Integer.parseInt(sp[0]);
		L = Integer.parseInt(sp[1]);
		s = br.readLine();
		String[] st = s.split(" ");
		s = br.readLine();
		gs = s.split(" ");
		int ans = check(st, 0);
		if(ans >= INF){
			println(imp);
		}
		else{
			println(ans);
		}
	}
	
	//Comparator<String> cmp = 
	
	int check(String[] st, int idx){
		if(idx == L){
			return 0;
		}
		int ans = INF;
		List<String> orgs = new ArrayList<String>();
		for(int i = 0; i < gs.length; i++){
			String sub = gs[i].substring(0, idx+1);
			orgs.add(sub);
		}
		Collections.sort(orgs);
		
		{
			List<String> nows = new ArrayList<String>();
			for(int i = 0; i < st.length; i++){
				String sub = st[i].substring(0, idx+1);
				nows.add(sub);
			}
			Collections.sort(nows);
			boolean flag = true;
			for(int i = 0; i < nows.size(); i++){
				if(!orgs.get(i).equals(nows.get(i))){
					flag = false;
				}
			}
			if(flag){
				ans = check(st, idx+1);
			}
		}
		{
			List<String> nows = new ArrayList<String>();
			for(int i = 0; i < st.length; i++){
				String sub = st[i].substring(0, idx);
				if(st[i].charAt(idx) == '0'){
					sub += "1";
				}
				else{
					sub += "0";
				}
				nows.add(sub);
			}
			Collections.sort(nows);
			boolean flag = true;
			for(int i = 0; i < nows.size(); i++){
				if(!orgs.get(i).equals(nows.get(i))){
					flag = false;
				}
			}
			if(flag){
				String[] nst = new String[st.length];
				for(int i = 0; i < N; i++){
					nst[i] = st[i].substring(0, idx);
					if(st[i].charAt(idx) == '0'){
						nst[i] += "1";
					}
					else{
						nst[i] += "0";
					}
					nst[i] += st[i].substring(idx+1);
				}
				ans = Math.min(ans, 1 + check(nst, idx+1));
			}
		}
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large-practice.in");
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
		
		A20141Aagain b = new A20141Aagain();
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
