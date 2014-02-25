package done.d231;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class C231 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{

		String s = br.readLine();
		String[] sp = s.split(" ");
		int n = parseInt(sp[0]);
		int m = parseInt(sp[1]);
		int fl = 0;
		int hf = 0;
		int zr = 0;
		for(int i = 0;i < n; i++){
			s = br.readLine();
			sp = s.split(" ");
			for(int j = 0; j < sp.length; j++){
				if(sp[j].equals("11")){
					fl++;
				}
				else if(sp[j].equals("00")){
					zr++;
				}
				else{
					hf++;
				}
			}
		}
		int[][] ans = new int[n][m];
		int fla = fl % m;
		int row = 0;
		int col = 0;
		if(fl > 0){
			for(row = 0; row < n; row++){
				for(col = 0; col < m; col++){
					ans[row][col] = 1;
					fl--;
					if(fl == 0){
						break;
					}
				}
				if(fl == 0){
					break;
				}
			}
			col++;
		}
	
		if(col == m){
			row++;
			col = 0;
		}
		if(hf > 0){
			for(int j = m -1; j >= col; j--){
				ans[row][j] = 2;
				hf--;
				if(hf == 0){
					break;
				}
			}
		}
		boolean flag = true;
		for(int i = n-1; i >= 0; i--){
			if(hf == 0) break;
			for(int j = m-1; j >= 0; j--){
				if(flag){
					ans[i][j] = 3;
				}
				else{
					ans[i][j] = 2;
				}
				hf--;
				if(hf == 0) break;
			}
			flag = !flag;
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(j != 0){
					bw.write(" ");
				}
				if(ans[i][j] == 1){
					bw.write("11");
				}
				else if(ans[i][j] == 2){
					bw.write("01");
				}
				else if(ans[i][j] == 3){
					bw.write("10");
				}
				else{
					bw.write("00");
				}
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
		C231 t = new C231();
		t.solve();
		bw.close();
	}

}
