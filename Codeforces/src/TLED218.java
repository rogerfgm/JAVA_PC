


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class TLED218 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int n = nextInt();
		int[] cap = nextInts();
		int[] d = new int[n];
		int m = nextInt();
		int[] next = new int[n];
		int[] prev = new int[n];
		next[n-1] = -1;
		for(int i = 0; i < n-1; i++){
			next[i] = i+1;
		}
		for(int i = 1; i < n; i++){
			prev[i] = i-1;
		}
		prev[0] = -1;
		
		for(int t = 0; t < m; t++){
			int[] in = nextInts();
			if(in.length == 3){
				int num = in[2];
				int idx = in[1] - 1;
				if(d[idx] + num <= cap[idx]){
					d[idx] += num;
					num = 0;
				}
				else{
					num -= cap[idx] - d[idx];
					d[idx] = cap[idx];
					while(num > 0 && idx >= 0){
				
						if(d[idx] + num < cap[idx]){
							d[idx] += num;
							num = 0;
						}
						else{
							num -= cap[idx] - d[idx];
							d[idx] = cap[idx];
							int pidx = prev[idx];
							if(pidx >= 0){
								next[pidx] = next[idx];
							}
							
							int nextidx = next[idx];
							if(nextidx != -1){
								prev[nextidx] = pidx;
							}

							idx = next[idx];
						}
					}
				}
			}
			else{
				out.println(d[in[1]-1]);
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

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
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
		TLED218 t = new TLED218();
		t.solve();
		bw.close();
	}

}
