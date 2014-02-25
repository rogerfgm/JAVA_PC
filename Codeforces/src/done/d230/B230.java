package done.d230;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B230 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		N = parseInt(br.readLine());
		int[][] w = new int[N][N];
		double[][] a = new double[N][N];
		double[][] b = new double[N][N];
		
		for(int i = 0; i < N; i++){
			String s = br.readLine();
			String[] sp = s.split(" ");
			for(int j = 0; j < N; j++){
				w[i][j] = parseInt(sp[j]);
			}
			a[i][i] = w[i][i];
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < i; j++){
				int W = w[i][j] + w[j][i];
				a[i][j] = a[j][i] = W / 2.0;
				b[i][j] = (w[i][j] - w[j][i]) / 2.0;
				b[j][i] = -b[i][j];
			}
		}
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(j != 0) bw.write(" ");
				bw.write(Double.toString(a[i][j]));
			}
			bw.write("\n");
		}
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(j != 0) bw.write(" ");
				bw.write(Double.toString(b[i][j]));
			}
			bw.write("\n");
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
		B230 t = new B230();
		t.solve();
		bw.close();
	}

}
