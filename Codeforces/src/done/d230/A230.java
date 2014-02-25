package done.d230;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class A230 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		String s = br.readLine();
		int n = 0;
		int e = 0;
		int i = 0;
		int t = 0;
		for(int j = 0; j < s.length(); j++){
			char c = s.charAt(j);
			if(c == 'n') n++;
			if(c == 'i') i++;
			if(c == 'e') e++;
			if(c == 't') t++;
		}
		int ans = 0;
		for(int j = 0; j < 200; j++){
			if(j == 0){
				n -= 3;
			}
			else{
				n -= 2;
			}
			i--;
			t--;
			e-=3;
			if(n < 0 || i < 0 || t < 0 || e < 0){
				out.println(ans);
				return;
			}
			else{
				ans++;
			}
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
		A230 t = new A230();
		t.solve();
		bw.close();
	}

}
