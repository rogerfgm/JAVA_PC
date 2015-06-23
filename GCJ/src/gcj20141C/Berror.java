package gcj20141C;


import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class Berror {
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

	String[] ds = null;
	
	public void solve() throws Exception{
		String s = br.readLine();
		N = Integer.parseInt(s);
		s = br.readLine();
		String[] sp = s.split(" ");
		ds = new String[N];

		boolean hasError = false;
		for(int i = 0; i < N; i++){
			ds[i] = sp[i];
			StringBuilder sb = new StringBuilder();
			String prev = "";
			Set<String> set = new HashSet<String>();
			for(int j = 0; j < ds[i].length(); j++){
				s = ds[i].substring(j, j+1);
				if(!s.equals(prev)){
					sb.append(s);
				}
				if(set.contains(s) && !s.equals(prev)){
					hasError = true;
				}
				set.add(s);
				prev = s;
			}
			ds[i] = sb.toString();
		}
		BigInteger ans = BigInteger.valueOf(1);
		
		if(hasError){
			println("0");
			return;
		}
		
		boolean[] fs = new boolean[N];
		long cnt = 0;

		for(int i = 0; i < N; i++){
			if(!fs[i]){
				cnt++;
				List<String> set = new ArrayList<String>();
				Set<String> ckset = new HashSet<String>();
				set.add(ds[i]);
				for(int j = 0; j < ds[i].length(); j++){
					ckset.add(ds[i].substring(j, j+1));
				}
				while(true){
					boolean largeFlag = false;
					for(int j = i+1; j < N; j++){
						if(fs[j]) continue;
						boolean f = false;
						for(int k = 0; k < ds[j].length(); k++){
							if(ckset.contains(ds[j].substring(k, k+1))){
								f = true;
								break;
							}
						}
						if(f){
							largeFlag = true;
							set.add(ds[j]);
							fs[j] = true;
							for(int k = 0; k < ds[j].length(); k++){
								ckset.add(ds[j].substring(k, k+1));
							}
						}
					}
					if(!largeFlag){
						break;
					}
				}
				ans = ans.multiply(check(set));
			}
		}
		while(cnt > 0){
			ans = ans.multiply(BigInteger.valueOf(cnt));
			cnt--;
		}
		
		println(ans.toString());
	}
	
	boolean checkValid(List<String> list){
		while(true){
			boolean found = false;
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).length() == 1){
					
					String s = list.get(i);
					for(int j = 0; j < list.size(); j++){
						String t = list.get(j);
						if(t.length() > 1 && ( t.endsWith(s) || t.startsWith(s))){
							list.remove(t);
							list.remove(s);
							if(t.endsWith(s)){
								t = t + s;
							}
							else{
								t = s + t;
							}
							list.add(t);
							found = true;
							break;
						}
					}
				}
				if(found){
					break;
				}
			}
			
			
			if(!found){
				break;
			}
		}
		
		
		String s = list.remove(0);
		while(list.size() > 0){
			boolean found = false;
			for(int i = 0; i < list.size(); i++){
				String t = list.get(i);
				if(t.startsWith(s.substring(s.length()-1, s.length()))){
					list.remove(i);
					s += t;
					found = true;
					break;
				}
				else if(t.endsWith(s.substring(0, 1))){
					list.remove(i);
					s = t + s;
					found = true;
					break;
				}
			}
			if(!found){
				break;
			}
		}
		if(list.size() > 0){
			return false;
		}
		Set<String> set = new HashSet<String>();
		String prev = "";
		for(int i = 0; i < s.length(); i++){
			String t = s.substring(i, i+1);
			if(set.contains(t) && !prev.equals(t)){
				return false;
			}
			prev = t;
			set.add(t);
		}
		
		
		return true;
	}
	
	BigInteger check(List<String> set){
		BigInteger ans = BigInteger.valueOf(1);;
		
		if(!checkValid(new ArrayList<String>(set))){
			 return BigInteger.valueOf(0);
		}
		while(true){
			boolean f = false;
			for(String s : set){
				if(s.length() == 1){
					f = true;
					long cnt = 0;
					for(int i = set.size()-1; i >= 0; i--){
						if(set.get(i).equals(s)){
							set.remove(i);
							cnt++;
						}
					}
					while(cnt > 0){
						ans = ans.multiply(BigInteger.valueOf(cnt));
						cnt--;
					}
					break;
				}
			}
			if(!f)break;
		}
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
		
		Berror b = new Berror();
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
