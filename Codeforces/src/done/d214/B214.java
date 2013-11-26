package done.d214;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B214 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int n = t[0];
		int k = t[1];
		int[] d = nextInts();
		int ans = Integer.MAX_VALUE;
		
		int[] s = new int[k];
		for(int i = 0; i < n; i++){
			int idx = i % k;
			s[idx] += d[i];
		}
		
		int ama = n % k;
		int aidx = -1;
		if(ama == 0) ama = k;
		for(int i = 0; i < k; i++){
			if(i < ama){
				if(s[i] < ans){
					ans = s[i];
					aidx = i;
				}
		
			}
			else{
				int pu = i - ama;
				if(s[i] + d[pu] < ans){
					ans = s[i] + d[pu] ;
					aidx = i;
				}
			}
		}
		aidx++;
		out.println(aidx);
		
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
		B214 t = new B214();
		t.solve();
		bw.close();
	}

}
