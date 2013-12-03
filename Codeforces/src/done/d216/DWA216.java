package done.d216;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class DWA216 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	int[] pct = null;

	boolean[] righthas = null;
	boolean[] righthas100 = null;
	
	boolean has0 = false;
	boolean[][] dp = null;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		N = t[0];
		int k = t[1];
		pct = nextInts();
		
		righthas = new boolean[N];
		righthas100 = new boolean[N];
		boolean flag = false;
		boolean flag100 = false;
		for(int i = N-1; i >= 0; i--){
			righthas[i] = flag;
			righthas100[i] = flag100;
			if(pct[i] > 0){
				flag = true;
			}
			if(pct[i] == 100){
				flag100  = true;
			}
		}
		dp = new boolean[N+1][N+2];
		
		int ans = get(0, 1, k);
		if(has0) ans++;
		out.println(ans);
		
	}
	
	int get(int l, int r, int k){
		if(l == N && r == N+1){
			has0 = true;
			return 0;
		}
		if(l >= N){
			return 0;
		}
		if(r > N){
			return 0;
		}
		
		if(dp[l][r]) return 0;
		dp[l][r] = true;
		
		if(r == N){
			return 1;
		}

		
		int a = 1;

		if(k == 0){
			return a;
		}

		if(pct[l] == 100){
			if(righthas100[r-1]){
				a += get(r+1, r+2, k-1);
			}
			else if(righthas[r-1]){
				a += get(r+1, r+2, k-1);
				a += get(l, r+1, k-1);
			}
			else{
				a += get(l, r+1, k-1);
			}
		}
		else if(pct[l] > 0){
			if(righthas100[r-1]){
				a += get(r+1, r+2, k-1);
				a += get(r, r+1, k-1);
			}
			else if(righthas[r-1]){
				a += get(l, r+1, k-1);
				a += get(r+1, r+2, k-1);
				a += get(r, r+1, k-1);
			}
			else{
				a += get(l, r+1, k-1);
			}
		}
		else{
			if(righthas[r-1]){
				a += get(r, r+1, k-1);
			}
		}
		
		
		return a;
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
		DWA216 t = new DWA216();
		t.solve();
		bw.close();
	}

}
