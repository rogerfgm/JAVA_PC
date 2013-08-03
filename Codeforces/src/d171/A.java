package d171;

import java.io.*;
import java.util.*;





public class A {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int[] xd = {1, 0, -1, 0};
	int[] yd = {0, 1, 0, -1};
	public void solve() throws Exception{

	
		int X = sc.nextInt();
		int Y = sc.nextInt();
		int idx = 0;
		int x = 0;
		int y = 0;
		if(X == 0 && Y == 0){
			System.out.println("0");
			return;
		}
		
		while(true){
			int base = (idx / 2) + 1;
			int db = idx % 4;
			int prevx = x;
			int prevy = y;
			x = x + base * xd[db];
			y = y + base * yd[db];
			int minx = Math.min(prevx, x);
			int maxx = Math.max(prevx, x);
			int miny = Math.min(prevy, y);
			int maxy = Math.max(prevy, y);
			
			if( (x == X && miny <= Y && Y <= maxy) || (y == Y && minx <= X && X <= maxx)){
				System.out.println(idx);
				return;
			}
			idx++;
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
		A t = new A();
		t.solve();
	}
}
