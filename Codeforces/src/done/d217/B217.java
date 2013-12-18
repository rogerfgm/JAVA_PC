package done.d217;



import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B217 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = nextInt();
		int[][] d = new int[N][100];
		for(int i = 0; i < N; i++){
			int[] t = nextInts();
			for(int j = 0; j < t[0]; j++){
				d[i][j] = t[j+1];
			}
		}
		for(int i = 0; i < N; i++){
			boolean f = true;
			for(int j = 0; j < N; j++){
				if(j == i) continue;
				Set<Integer> set = new HashSet<Integer>();
				for(int k = 0; k < 100; k++){
					if(d[j][k] == 0) break;
					set.add(d[j][k]);
				}
				for(int k = 0; k < 100; k++){
					if(d[i][k] == 0) break;
					if(set.contains(d[i][k])){
						set.remove(d[i][k]);
					}
				}
				if(set.size() == 0){
					f = false;
					break;
				}
			}
			if(f){
				out.println("YES");
			}
			else{
				out.println("NO");
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
		B217 t = new B217();
		t.solve();
		bw.close();
	}

}
