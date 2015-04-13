package done.d276;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class C276 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{

		int n = nextInt();
		for(int i = 0; i < n; i++){
			long[] t = nextLongs();
			long l = t[0];
			long r = t[1];
			String s = Long.toBinaryString(r);
			String sl = Long.toBinaryString(l);
			
			N = s.length();
			int cnt0 = 0;
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == '0'){
					cnt0++;
				}
			}
			if(cnt0 == 0){
				System.out.println(r);
				continue;
			}

			if(sl.length() < s.length()){
				String ans = "";
				for(int j = 0; j < s.length()-1; j++){
					ans += "1";
				}
				long a = Long.parseLong(ans, 2);
				System.out.println(a);
				continue;
				
			}
			else{
				char[] cs = new char[s.length()];
				for(int j = 0; j < N; j++){
					cs[j] = sl.charAt(j);
				}
				String ans = sl;
				for(int j = N-1; j >= 0; j--){
					if(cs[j] == '0'){
						cs[j] = '1';
						String ns = get(cs);
						long val = Long.parseLong(ns, 2);
						if(val <= r){
							ans = ns;
						}
					}
				}
				long a = Long.parseLong(ans, 2);
				System.out.println(a);
			}
		}
	
		
	}
	
	String get(char[] cs){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cs.length; i++){
			sb.append(cs[i]);
		}
		return sb.toString();
	}

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
	}
	
	void pl(String s){
		try{
			bw.write(s);
		}
		catch(Exception ex){
		}
	}
	
	void pln(String s){
		try{
			bw.write(s);
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
	}
	void pln(){
		try{
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		C276 t = new C276();
		t.solve();
		bw.close();
	}

}
