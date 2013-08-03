


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA on 8
public class C188 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		long a = sc.nextLong();
		long b = sc.nextLong();
		long m = sc.nextLong();
		
		if(a > b){
			long t = a;
			a = b;
			b = t;
		}
		long ans = 0;
		if(b >= m){
			out.println(ans);
			return;
		}
		if(a == 0 && b == 0){
			out.println("-1");
			return;
		}
		if(a < 0){
			if(b <= 0 || b <= abs(a)){
				out.println("-1");
				return;
			}
			long tmp = a + b;
			a = tmp;
			ans++;
		}
		
		while(b < m){
			long t = a + b;
			a = b;
			b = t;
			ans++;
		}
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
		bw = new BufferedWriter(new PrintWriter(out));
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		C188 t = new C188();
		t.solve();
		bw.close();
	}

}
