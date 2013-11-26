package done.d167;

import java.io.*;
import java.util.*;





public class A {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	public void solve() throws Exception{

	
		int n = sc.nextInt();
		int[] d = new int[n];
		int sum = 0;
		for(int i = 0; i < n; i++){
			d[i] = sc.nextInt();
			sum += d[i];
		}
		
		int ans = 0;
		for(int i = 1; i <= 5; i++){
			int s = sum + i;
			s %= (n + 1);
			if(s != 1){
				ans++;
			}
		}
		System.out.println(ans);
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
		A t = new A();
		t.solve();
	}
}
