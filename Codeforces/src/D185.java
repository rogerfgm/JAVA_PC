


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA
public class D185 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		String s = br.readLine();
		String[] sp = s.split(" ");
		
		int n = parseInt(sp[0]);
		int m = parseInt(sp[1]);
		int p = parseInt(sp[2]);
		int[] d = new int[n];
		s = br.readLine();
		sp = s.split( " ");
		int dist = 0;
		for(int i = 1; i < n; i++){
			dist += parseInt(sp[i-1]);
			d[i] = dist;
		}
		int[] ts = new int[m];
		for(int i = 0; i < m; i++){
			s = br.readLine();
			sp = s.split(" ");
			int td = parseInt(sp[0]);
			int tt = parseInt(sp[1]);
			int dst = d[td-1];
			ts[i] = tt - dst;
		}
		Arrays.sort(ts);
		long ans = 0;
		for(int i = 0; i < n-1; i++){
			ans += ts[ts.length-1] - ts[i];
		}
		p--;
		
		
		//out.println(ans);
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
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		D185 t = new D185();
		t.solve();
		bw.close();
	}

}
