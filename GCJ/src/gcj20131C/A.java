package gcj20131C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class A {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int M = 0;
	List<Integer> pos = null;
	public void solve() throws Exception{
		String S = sc.next();
		int n = sc.nextInt();
		
		N = S.length();
		boolean[] f = new boolean[N];
		for(int i = 0; i < N; i++){
			char c = S.charAt(i);
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
				f[i] = true;
			}
		}
		
		
		pos = new ArrayList<Integer>();
		int num = 0;
		for(int i = N-1; i >= 0; i--){
			if(!f[i]){
				num++;
			}
			else{
				num = 0;
			}
			if(num >= n){
				pos.add(0, i + n - 1);
			}
		}
		if(pos.size() == 0){
			out.println("0");
			return;
		}
		
		long ans = 0;
		for(int i = 0; i < S.length(); i++){
			int p = find(i + n -1);
			if(p >= 0){
				ans += N - pos.get(p);
			}
		}
		out.println(ans);
			
	}
	
	int find(int idx){
		int l = 0;
		int r = pos.size()-1;
		if(pos.get(0).intValue() >= idx){
			return l;
		}
		else if(pos.get(r).intValue() < idx){
			return -1;
		}
		
		while(l + 1 < r){
			int mid = (l + r) /2;
			if(pos.get(mid).intValue() == idx){
				return mid;
			}
			else if(pos.get(mid).intValue() < idx){
				l = mid;
			}
			else{
				r = mid;
			}
		}
		
		return r;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		sysout.println(new Date());
		A b = new A();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			sysout.println(t);
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
		sysout.println(new Date());
	}
}
