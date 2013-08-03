package d189;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C189 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	final int MOD = 1000000007;
	
	public void solve() throws Exception{

		String s = sc.next();
		int n = s.length();
		int st = 2 * n - 2;
		BigInteger mb = BigInteger.valueOf(MOD);
		BigInteger b2 = BigInteger.valueOf(2);
		long ans = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '1'){
				int kai = st - i;
				ans += b2.pow(kai).mod(mb).intValue();
				ans %= MOD;
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
		C189 t = new C189();
		t.solve();
		bw.close();
	}

}
