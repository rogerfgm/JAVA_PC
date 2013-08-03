package d190;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;


public class B190 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int r = sc.nextInt();
		int g = sc.nextInt();
		int b = sc.nextInt();
		
		int ans = 0;
		for(int i = 0; i <= 3; i++){
			if(r < i || g < i || b < i){
				continue;
			}
			int R = r-i;
			int G = g -i;
			int B = b - i;
			int min = R;
			min = min(G, min);
			min = min(B, min);
			int a = min;
			R-=min;
			G-=min;
			B-=min;
			R+=i;
			G+=i;
			B+=i;
			a += R/3;
			a += G/3;
			a += B/3;
			ans = max(ans, a);
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
		B190 t = new B190();
		t.solve();
		bw.close();
	}

}
