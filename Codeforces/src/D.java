

import java.io.*;
import java.util.*;
import java.math.*;


public class D {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	
	public void solve() throws Exception{
		int b = sc.nextInt();
		int d = sc.nextInt();
		String s = sc.next();
		String t = sc.next();
		int n = s.length();
		int m = t.length();
		
		int[] l = new int[m];
		int p = 0;
		for(int i = 0; i < m; i++){
			p = i;
			l[i] = 0;
			for(int j = 0; j < n; j++){
				if(s.charAt(j) == t.charAt(p)){
					l[i]++;
					p = (p+1)%m;	
				}
			}
		}
		long c = 0;
		p = 0;
		for(int i = 0; i < b; i++){
			c += l[p];
			p = (p + l[p] )% m;
		}
		long ans = c / m / d;
		out.println(ans);

			
			
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
	
		sc =  new Scanner(System.in);
		D t = new D();
		t.solve();

	}

}
