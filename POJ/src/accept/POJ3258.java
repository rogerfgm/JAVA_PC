package accept;


import java.util.*;
import java.io.*;


public class POJ3258 {
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	static PrintStream out = null;
	static int n = 0;
	static int m = 0;
	static int L = 0;
	
	int[] d = null;
	public void solve() throws Exception{
		d = new int[n+2];
		
		for(int i = 1; i <= n; i++){
			d[i] = sc.nextInt();
		}
		d[n+1] = L;
		Arrays.sort(d);
	
		int min = 0;
		int max = Integer.MAX_VALUE;
	
		while(min + 1 < max){
			int mid = (min + max) / 2;
			if(check(mid)){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		out.println(min);
		
	}
	
	boolean check(int mid){
		long sum = 0;
		int cnt = 0;
		for(int i = 1; i < d.length; i++){
			sum += d[i] - d[i-1];
			if(sum < mid){
				cnt++;
			}
			else{
				sum = 0;
			}
		}

		if(cnt <= m){
			return true;
		}
		return false;
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
		POJ3258 t = new POJ3258();

		while(true){
			try{
				L = sc.nextInt();
				n = sc.nextInt();
				m = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(n == 0 && m == 0 && L == 0) break;
			t.solve();
		}
	}
	

}
