package done.d167;

import java.io.*;
import java.util.*;







public class C {
	static Scanner sc = null;
	
	
	
	
	public void solve() throws Exception{

		int n = sc.nextInt();
		int[] s = new int[n + 1];
		for(int i = 0; i < n; i++){
			s[i + 1] = sc.nextInt();
		}
		int m = sc.nextInt();
		int[][] o = new int[m][2];
		for(int i = 0; i < m; i++){
			o[i][0] = sc.nextInt();
			o[i][1] = sc.nextInt();
		}
		long height = s[1];
		for(int i = 0; i < o.length; i++){
			int w = o[i][0];
			long base = Math.max(height, s[w]);
			System.out.println(base);
			height = base;
			height += o[i][1];
		}
		
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
		C t = new C();
		t.solve();

	}
	
	
}
