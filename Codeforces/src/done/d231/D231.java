package done.d231;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class D231 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		String s= br.readLine();
		int n = parseInt(s);
		s = br.readLine();
		String[] sp = s.split(" ");
		int[] d = new int[n];
		for(int i = 0;i < n; i++){
			d[i] = parseInt(sp[i]);
		}

	
		Arrays.sort(d);
		
		int kata = -1;
		
		int maxbun = 20000;
		int move = 0;
		for(int i = 0; i <= 20000; i++){
			int upmax = 0;
			int dunmax = 0;
			for(int j = 0; j < n; j++){
				int cur = d[0] + i * j;
				int diff = d[j] - cur;
				if(diff > 0){
					upmax = max(diff, upmax);
				}
				else{
					dunmax = min(dunmax, diff);
				}
			
			}
			int bun = (upmax - dunmax + 1) / 2;
			if(bun < maxbun){
				maxbun = bun;
				kata = i;
				int diff = (upmax + dunmax) / 2;
				move = diff;
			}
		}
		out.println(maxbun);
		int start = d[0] + move;
		out.println(start + " " + kata);
		
		
	
		
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
		D231 t = new D231();
		t.solve();
		bw.close();
	}

}
