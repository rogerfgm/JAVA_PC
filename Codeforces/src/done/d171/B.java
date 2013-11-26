package done.d171;
import java.io.*;
import java.util.*;


public class B {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	public void solve() throws Exception{

		int n = sc.nextInt();
		int t = sc.nextInt();
		int[] d = readIntArray(n);
		
		int idx = 0;
		int sum = 0;
		int pidx = 0;
		int ans = 0;
		while(true){
			while(sum + d[idx] <= t){
				sum += d[idx];
				ans = Math.max(ans, idx - pidx + 1);
				idx++;
				if(idx == n){
					System.out.println(ans);
					return;
				}
			}
			sum-=d[pidx];
			pidx++;
		}
		
		
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
		sc =  new Scanner(System.in);
		B t = new B();
		t.solve();

	}
	
	
}
