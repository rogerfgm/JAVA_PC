package gcj2011;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class D2011Small {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int M = 0;
	
	
	int cn = 0;
	int th = 0;
	Set<Integer> fn = null;
	List<List<Integer>> G = null;
	Set<Long> dp = null;
	public void solve() throws Exception{
		N = sc.nextInt();
		M = sc.nextInt();
		
		G = new ArrayList<List<Integer>>();
		for(int i = 0; i < N; i++){
			List<Integer> L = new ArrayList<Integer>();
			G.add(L);
		}
		fn = new HashSet<Integer>();
		for(int i = 0; i < M; i++){
			String s = sc.next();
			String[] ss = s.split(",");
			int f = Integer.parseInt(ss[0]);
			int t = Integer.parseInt(ss[1]);
			G.get(f).add(t);
			G.get(t).add(f);
			if(f == 1){
				fn.add(t);
			}
			if(t == 1){
				fn.add(f);
			}
		}
		
		cn = Integer.MAX_VALUE;
		th = 0;
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		dp = new HashSet<Long>();
		check(0, set);
		cn--;
		out.println(cn + " " + th);
	}
	
	void check(int p, Set<Integer> set){
		if(fn.contains(p)){
			if(cn > set.size()){
				cn = set.size();
				th = getThre(set);
			}
			else if(cn == set.size()){
				th = max(th, getThre(set));
			}
			return;
		}
		
		if(set.size() > cn){
			return;
		}
		
		{
			long d = 0;
			for(int t : set){
				d |= 1 << t;
			}
			if(dp.contains(d)){
				return;
			}
		}
		
		
		for(int t : G.get(p)){
			if(set.contains(t)){
				continue;
			}
			Set<Integer> nset = new HashSet<Integer>();
			nset.addAll(set);
			nset.add(t);
			check(t, nset);
		}
	}
	
	int getThre(Set<Integer> set){
		Set<Integer> ans = new HashSet<Integer>();
		for(int p : set){
			for(int t : G.get(p)){
				ans.add(t);
			}
		}
		for(int p : set){
			if(ans.contains(p)){
				ans.remove(p);
			}
		}
		
		return ans.size();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("D-2011small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		D2011Small b = new D2011Small();
		int T = sc.nextInt();
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
