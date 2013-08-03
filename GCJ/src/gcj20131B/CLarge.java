package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class CLarge {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	static int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static String[] dics = new String[521196]; 
	String S = null;
	int[][] dp = null;
	static int[] alstart = new int[26];
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
		int start = 0;
		int end =  dics.length-1;
		if(rem != 0){
			int c = S.charAt(ind) - 'a';
			start = alstart[c];
			if(c != 25){
				if(alstart[c+1] == INF){
					end = alstart[c+2] -1;
				}
				else{
					end = alstart[c+1] -1;	
				}
				
				
			}
			
		}
		for(int i = start; i <= end; i++){
			String s = dics[i];
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
				for(int j = 0; j < s.length(); j++){
					if(s.charAt(j) != t.charAt(j)){
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
	
	int getstart(String ck){
		int l = 0;
		int r = dics.length-1;

		if(ck.compareTo(dics[l]) <= 0){
			return l;
		}

		while(l + 1 < r){
			int m = (l + r) / 2;
			if(ck.compareTo(dics[m]) < 0){
				l = m;
			}
			else{
				r = m;
			}
		}
		return l+1;
	}
	int getend(String ck){
		int l = 0;
		int r = dics.length-1;

		if(ck.compareTo(dics[r]) >= 0){
			return r;
		}
		
		while(l + 1 < r){
			int m = (l + r) / 2;
			if(ck.compareTo(dics[m]) > 0){
				r = m;
			}
			else{
				l = m;
			}
		}
		return l;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		System.out.println(new Date());
		File file = new File("C-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		for(int i = 0; i < 26; i++){
			alstart[i] = INF;
		}
		
		BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
		String line = null;
		int idx = 0;
		int alp = -1;
		
		while( (line = br.readLine()) != null){
			dics[idx++] = line;
			int a = line.charAt(0) - 'a';
			if(a != alp){
				alstart[a] = idx-1;
				alp = a;
			}
		}
		br.close();
		
		
		CLarge b = new CLarge();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			System.out.println(t);
			t++;
		}
		out.close();
		System.out.println(new Date());
	}
}
