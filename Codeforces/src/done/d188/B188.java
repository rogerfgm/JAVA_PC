package done.d188;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA on 8
public class B188 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		String s = sc.next();
		
		String hv = "heavy";
		String mt = "metal";
		long hnum = 0;
		long ans = 0;
		for(int i = 0; i < s.length()-4; i++){
			String tp = s.substring(i, i+5);
			if(tp.equals(mt)){
				ans += hnum;
			}
			if(tp.equals(hv)){
				hnum++;
			}
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
		B188 t = new B188();
		t.solve();
		bw.close();
	}

}
