


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class A231 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		String s = br.readLine();
		int A = 0;
		int B = 0;
		int C = 0;
		int idx = 0;

			while(s.charAt(idx) != '+'){
				A++;
				idx++;
			}
			idx++;
			while(s.charAt(idx) != '='){
				B++;
				idx++;
			}
			idx++;
			C = s.length() - idx;
			if(A + B == C){
				bw.write(s);
				return;
			}
			else if(A + B + 1== C -1 && C > 1){
				A++;
				C--;
			}
			else if(A + B - 1 == C + 1 && (A > 1 || B > 1)){
				C++;
				if(A > B){
					A--;
				}
				else{
					B--;
				}
			}
			else{
				bw.write("Impossible");
				return;
			}
			for(int i = 0; i < A; i++){
				bw.write("|");
			}
			bw.write("+");
			for(int i = 0; i < B; i++){
				bw.write("|");
			}
			bw.write("=");
			for(int i = 0; i < C; i++){
				bw.write("|");
			}
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
		A231 t = new A231();
		t.solve();
		bw.close();
	}

}
