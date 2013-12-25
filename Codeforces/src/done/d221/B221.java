package done.d221;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B221 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int N = t[0];
		int m = t[1];
		int[][] d = new int[N][N];
		for(int i = 0; i < m; i++){
			t = nextInts();
			d[t[0]-1][t[1]-1] = t[2];
		}
		while(true){
			boolean f = false;
			for(int i = 0; i < N; i++){
				int l = 0;
				int r = 0;
				while(true){
					if(d[l][i] == 0){
						l++;
					}
					if(d[i][r] == 0){
						r++;
					}
					if(l >= N || r >= N){
						break;
					}
					
					int num = min(d[l][i], d[i][r]);
					if(num == 0){
						continue;
					}
					
					d[l][i] -= num;
					d[i][r] -= num;
					d[l][r] += num;
					
					f = true;
				}
			}
			if(!f){
				break;
			}
		}
		int ans = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				ans += d[i][j];
			}
		}
		out.println(ans);
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
		B221 t = new B221();
		t.solve();
		bw.close();
	}

}
