

import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;

// WA on 4
//20
//1 1208 1583
//1 -258 729
//1 -409 1201
//1 194 1938
//1 -958 1575
//1 -1466 1752
//2 1 2
//2 1 2
//2 6 5
//1 -1870 1881
//1 -2002 2749
//1 -2002 2984
//1 -2002 3293
//2 2 4
//2 8 10
//2 9 6
//1 -2002 3572
//1 -2002 4175
//1 -2002 4452
//1 -2002 4605
//Output
//YES
//YES
//YES
//YES
//NO
//NO
//Answer
//YES
//YES
//YES
//YES
//YES
//NO
//Checker Log
//wrong answer 5th lines differ - expected: 'YES', found: 'NO'

public class B189 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	boolean[][] f = null;
	boolean[] used = null;
	public void solve() throws Exception{
		int n = sc.nextInt();
		f = new boolean[n][n];

		List<int[]> list = new ArrayList<int[]>();
		for(int i = 0; i < n; i++){
			int b = sc.nextInt();
			if(b == 1){
				int l = sc.nextInt();
				int r = sc.nextInt();
				for(int j = 0; j < list.size(); j++){
					int[] t = list.get(j);
					if(l < t[0] && t[0] < r ||
							l < t[1] && t[1] < r){
						f[j][i] = true;
					}
					if(t[0] < l && l < t[1] || t[0] < r && r < t[1]){
						f[i][j] = true;
					}
				}
				int[] nt = new int[2];
				nt[0] = l;
				nt[1] = r;
				list.add(nt);
			}
			else{
				int from = sc.nextInt() - 1;
				int to = sc.nextInt() - 1;
				used = new boolean[n];
				if(check(from, to)){
					out.println("YES");
				}
				else{
					out.println("NO");
				}
			}
		}
	}
	
	boolean check(int from, int to){
		if(from == to){
			return true;
		}
		used[from] = true;
		for(int i = 0; i < f[from].length; i++){
			if(f[from][i] && !used[i]){
				if(check(i, to)){
					return true;
				}
			}
		}
		
		return false;
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
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		B189 t = new B189();
		t.solve();
		bw.close();
	}

}
