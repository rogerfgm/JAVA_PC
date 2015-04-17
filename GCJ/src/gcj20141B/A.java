package gcj20141B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class A {
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
		String s = br.readLine();
		N = Integer.parseInt(s);
		
		String[] sp = new String[N];
		for(int i = 0; i < N; i++){
			sp[i] = br.readLine();
		}
		int ans = 0;
		while(true){
			Set<String> nextSet = new HashSet<String>();
			boolean empty = false;
			String target = null;
			for(int i = 0; i < N; i++){
				if(sp[i].length() == 0){
					empty = true;
					nextSet.add("");
				}
				else{
					target = sp[i].substring(0, 1);
					nextSet.add(target);
				}
			}
			if(nextSet.size() > 1){
				println("Fegla Won");
				return;
			}
			if(empty) break;
			int[] cnts = new int[N];
			
			for(int i = 0; i < N; i++){
				int cnt = 0;
				while(sp[i].startsWith(target)){
					cnt++;
					sp[i] = sp[i].substring(1);
				}
				cnts[i] = cnt;
				
			}
			int sum = Integer.MAX_VALUE;
			for(int i = 1; i <= 100; i++){
				int num = 0;
				for(int j = 0; j < N; j++){
					num += Math.abs(i - cnts[j]);
				}
				sum = Math.min(sum, num);
			}
			ans += sum;
		}
		println(ans);
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
		
		A b = new A();
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
