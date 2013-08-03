


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA on 5
public class D189 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	int[] d = null;
	public void solve() throws Exception{
		
		int n = parseInt(br.readLine());
		String s = br.readLine();
		String[] sp = s.split(" ");
		d = new int[n];
		for(int i = 0; i < n; i++){
			d[i] = parseInt(sp[i]);
		}
		int l = 0;
		int idx = 0;
		int ans = 0 ;
		while(true){
			if(idx >= n){
				break;
			}
			while(true){
				if(idx == n-1 || d[idx+1] >= d[l]){
					ans = max(ans, check(l, idx));
					idx++;
					l = idx;
					break;
				}
				idx++;
			}
		}
		
		out.println(ans);
	}
	
	int check(int l, int r){
		if(l == r){
			return 0;
		}
		int ans = 0;
		l++;
		int n = r - l + 1;
		int[] dp = new int[n];
		
		for(int i = 0; i < n; i++){
			dp[i] = 1;
			for(int j = 0; j < i; j++){
				if(d[l+i] >= d[l+j]){
					dp[i] = max(dp[i], dp[j] + 1);
				}
			}
		}
		for(int i = 0; i < n; i++){
			ans = max(dp[i], ans);
		}
		
		
		return ans;
	}

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		D189 t = new D189();
		t.solve();
		bw.close();
	}

}
