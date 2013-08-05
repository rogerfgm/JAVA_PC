


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;


// TLE
public class C191 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	int MOD = 1000000007;
	
	public void solve() throws Exception{

		String s = sc.next();
		int k = sc.nextInt();
		long ans = 0 ;
		
		int a = s.length();
		long B = 1;
		
		if(k == 1){
		
		}
		else{
			int n = k-1;
			BigInteger base = BigInteger.valueOf(1);
			BigInteger b2 = BigInteger.valueOf(2);
			BigInteger bm = BigInteger.valueOf(MOD);
			BigInteger r = b2.pow(a);
			base = r.pow(n+1).subtract(base).divide(r.subtract(base));
			B = base.mod(bm).intValue();
		}
		
		long b2m = 1;
		
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '0' || c == '5'){
				ans += (b2m * B ) % MOD;
				ans %= MOD;
			}
			b2m *= 2;
			b2m %= MOD;
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
		C191 t = new C191();
		t.solve();
		bw.close();
	}

}
