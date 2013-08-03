package d166;
import java.io.*;
import java.util.*;





public class B {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	public void solve() throws Exception{

		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] d = new int[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				d[i][j] = sc.nextInt();
			}
		}
		boolean[] isp = new boolean[100001];
		int last = 0;
		for(int i = 2; i <= 100000; i++){
			isp[i] = is_prime(i);
			if(isp[i]){
				last = i;
			}
		}
		int[] cnt = new int[100001];
		for(int i = last + 1; i < 100001; i++){
			cnt[i] = INF;
		}
		int c = 0;
		for(int i = last; i >= 0; i--){
			if(isp[i]){
				c = 0;
			}
			cnt[i] = c;
			c++;
		}
		int ans = INF;
		for(int i = 0; i < n; i++){
			int sum = 0;
			for(int j = 0; j < m; j++){
				sum += cnt[d[i][j]];
			}
			ans = Math.min(ans, sum);
		}
		for(int i = 0; i < m; i++){
			int sum = 0;
			for(int j = 0; j < n; j++){
				sum += cnt[d[j][i]];
			}
			ans = Math.min(ans, sum);
		}
		System.out.println(ans);
	}
	
	boolean is_prime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return n != 1; // 1の場合は除外
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		B t = new B();
		t.solve();

	}
	
	
}
