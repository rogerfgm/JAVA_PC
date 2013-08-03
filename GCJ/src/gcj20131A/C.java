package gcj20131A;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class C {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	static int N = 0;
	static int M = 0;
	static int K = 0;
	public void solve() throws Exception{

		int n5 = 0;
		int n3 = 0;
		int n4 = 0;
		int n2 = 0;
		
		for(int i = 0; i < K; i++){
			long c = sc.nextLong();
			if(c % 125 == 0) n5 = 3;
			if(c % 25 == 0){
				n5 = max(n5, 2);
			}
			if(c % 5 == 0){
				n5 = max(n5, 1);
			}
			if(c % 27 == 0){
				n3 = 3;
			}
			if(c % 9 == 0){
				n3 = max(n3, 2);
			}
			if(c % 3 == 0){
				n3 = max(n3, 1);
			}
			if(c % 64 == 0){
				n4 = 3;
			}
			if(c % 16 == 0){
				n4 = max(n4, 2);
			}
			if(c % 4 == 0){
				n4 = max(n4, 1);
			}
			if(c % 2 == 0 && c % 4 != 0){
				n2 = 1;
			}
		}
		String ans = "";
		for(int i = 0; i < n5; i++){
			ans += "5";
		}
		for(int i = 0; i < n3; i++){
			ans += "3";
		}
		for(int i = 0; i < n2; i++){
			ans += "2";
		}
		for(int i = 0; i < n4; i++){
			if(ans.length() < 3)ans += "4";
		}
		while(ans.length() < 3){
			Random rnd = new Random();
			int b = rnd.nextInt(4) + 2;
			ans += Integer.toString(b);
		}
		out.println(ans);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-1-attempt1.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		C b = new C();
		out.println("Case #1:");
		int T = sc.nextInt();
		int R = sc.nextInt();
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		int t = 1;
		while(t <= R){
			b.solve();
			t++;
		}
		out.close();
	}
}
