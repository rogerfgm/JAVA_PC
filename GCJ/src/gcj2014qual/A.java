package gcj2014qual;


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
	String Bad = "Bad magician!";
	String cheat = "Volunteer cheated!";
	public void solve() throws Exception{
		int fst = parseInt(br.readLine());
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < 4; i++){
			String s = br.readLine();
			if(i != fst - 1) continue;
			String[] sp = s.split(" ");
			for(int j = 0; j < 4; j++){
				set.add(parseInt(sp[j]));
			}
		}
		int scd =  parseInt(br.readLine());
		int[][] d2 = new int[4][4];
		Set<Integer> found = new HashSet<Integer>();
		for(int i = 0; i < 4; i++){
			String s = br.readLine();
			if(scd -1 != i) continue;
			String[] sp = s.split(" ");
			for(int j = 0; j < 4; j++){
				if(set.contains(parseInt(sp[j]))){
					found.add(parseInt(sp[j]));
				}
			}
		}
		if(found.size() == 1){
			out.println(found.iterator().next());
		}
		else if(found.size() == 0){
			out.println(cheat);
		}
		else{
			out.println(Bad);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-small-attempt0.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
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
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
