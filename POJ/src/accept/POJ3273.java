package accept;


import java.util.*;
import java.io.*;


public class POJ3273 {
	static Scanner sc = null;
	static PrintStream out = null;
	static int n = 0;
	static int m = 0;
	
	int[] d = null;
	public void solve() throws Exception{
		d = new int[n];
		for(int i = 0; i < n; i++){
			d[i] = sc.nextInt();
		}
		int min = 0;
		int max = Integer.MAX_VALUE;
		int ans = 0;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			int tmp = check(mid);
			if(tmp >= 0){
				ans = tmp;
				max = mid;
			}
			else{
				min = mid;
			}
		}
		out.println(ans);
	}
	
	int check(int th){
		int ans = 0;
		int num = 0;
		int sum = 0;
		for(int i = 0; i < n; i++){
			if(sum + d[i] > th){
				num++;
				ans = Math.max(ans, sum);
				sum = d[i];
			}
			else{
				sum += d[i];
			}
		}
		num++;
		ans = Math.max(ans, sum);
		if(num <= m){
			return ans;
		}
		else{
			return -1;
		}
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3273 t = new POJ3273();

		while(true){
			try{
			n = sc.nextInt();
			m = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(n == 0 && m == 0) break;
			t.solve();
		}
	}
	

}
