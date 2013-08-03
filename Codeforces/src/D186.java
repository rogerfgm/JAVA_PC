


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;



// TLE on 10
public class D186 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	long[][] dp = null;
	int MC = 0;
	long ans = 0;
	int n = 0;
	Data[] ds = null;
	public void solve() throws Exception{
		String s = br.readLine();
		String[] sp = s.split(" ");
		n = parseInt(sp[0]);
		int m = parseInt(sp[1]);
		int k = parseInt(sp[2]);
		

		
		long lc = Long.MAX_VALUE / 1000;

		ds = new Data[m];
		for(int i = 0; i < m; i++){
			s = br.readLine();
			sp = s.split(" ");
			Data d = new Data();
			d.f = parseInt(sp[0]);
			d.t =parseInt(sp[1]);
			d.c =parseInt(sp[2]);
			ds[i] = d;
		}
		Arrays.sort(ds, new Comparator<Data>() {
			@Override
			public int compare(Data d0, Data d1) {
				return d0.f - d1.f;
			}
		});
		dp = new long[n+1][n+1];
		for(int i = 0; i <= n; i++){
			for(int j = 0; j <= n; j++){
				dp[i][j] = lc;
			}
		}
		MC = n - k;
		ans = lc;
		check(0, 0, 0);
		if(ans == lc){
			out.println("-1");
		}
		else{
			out.println(ans);
		}
	}
	
	void check(int idx, int cnt, long cst){
		if(cnt > MC){
			return;
		}
		if(idx == n){
			ans = min(ans, cst);
			return;
		}
		
		if(dp[idx][cnt] <= cst){
			return;
		}
		for(int i = 0; i < cnt; i++){
			if(dp[idx][i] <= cst){
				return;
			}
		}
		
		for(int i = 0; i < ds.length; i++){
			Data d = ds[i];
			if(d.f <= idx + 1 && idx < d.t){
				check(d.t, cnt, cst + d.c);
			}
		}
		check(idx+1, cnt+1, cst);
		
	}
	
	class Data{
		int f = 0;
		int t = 0;
		long c = 0;
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
		D186 t = new D186();
		t.solve();
		bw.close();
	}



}
