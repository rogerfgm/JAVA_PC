package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Csmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static Set<String> dic = new HashSet<String>();
	static List<String> dics = new ArrayList<String>(); 
	String S = null;
	int[][] dp = null;
	public void solve() throws Exception{
		S = sc.next();
		dp = new int[S.length()][5];
		for(int i = 0; i < S.length(); i++){
			for(int j = 0; j < 5; j++){
				dp[i][j] = -1;
			}
		}
		int ret = check(0, 0);
		out.println(ret);
		
		
	}
	
	int  check(int ind, int rem){
		
		if(ind == S.length()){
			return 0;
		}
		if(dp[ind][rem] >= 0){
			return dp[ind][rem];
		}
		
		int ret = INF;
		int slen = S.length() - ind;
		for(String s : dics){
			if(s.length() > slen){
				continue;
			}
			String t = S.substring(ind, ind + s.length());
			if(t.equals(s)){
				int nrem = rem - s.length();
				if(nrem < 0){
					nrem = 0;
				}
				ret = min(ret, check(ind + s.length(), nrem));
			}
			else{
				int ch = 0;
				int nrem = rem;
				boolean flag = true;
				for(int i = 0; i < s.length(); i++){
					if(s.charAt(i) != t.charAt(i)){
						if(nrem == 0){
							nrem = 4;
							ch++;
						}
						else{
							flag = false;
							break;
						}
					}
					else{
						if(nrem > 0){
							nrem--;
						}
					}
				}
				if(flag){
					ret = min(ret, check(ind + s.length(), nrem) + ch);
				}
			}
		}
		dp[ind][rem] = ret;
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-attempt4.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
		String line = null;
		while( (line = br.readLine()) != null){
			dic.add(line);
			dics.add(line);
		}
		br.close();
		
		
		Csmall b = new Csmall();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
