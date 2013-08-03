package d172;

import java.io.*;
import java.util.*;





public class A {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int[] xd = {1, 0, -1, 0};
	int[] yd = {0, 1, 0, -1};
	public void solve() throws Exception{

	
		String s = sc.nextLine();
		String f = s.substring(0, 1);
		String c = s.substring(1);
		String ans = f.toUpperCase() + c;
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
