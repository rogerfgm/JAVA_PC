package done.d184;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class B {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	long[] a = null;
	public void solve() throws Exception{

		long p = sc.nextLong();
		long q = sc.nextLong();
		n = sc.nextInt();
		a = new long[n];
		for(int i = 0; i < n; i++){
			a[i] = sc.nextLong();
		}
		
		BigInteger[] ret = get(0);
		BigInteger pb = BigInteger.valueOf(p);
		BigInteger qb = BigInteger.valueOf(q);
		if(ret[0].multiply(qb).equals(ret[1].multiply(pb))){
			out.println("YES");
		}
		else{
			out.println("NO");
		}
	}
	
	BigInteger[] get(int idx){
		BigInteger[] ret = new BigInteger[2];
		if(idx == n-1){
			ret[0] = BigInteger.valueOf(a[idx]);
			ret[1] = BigInteger.valueOf(1);
			return ret;
		}
		BigInteger[] pre = get(idx+1);
		BigInteger si = BigInteger.valueOf(a[idx]);
		si = pre[0].multiply(si).add(pre[1]);
		ret[0] = si;
		ret[1] = pre[0];
		return ret;
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
		B t = new B();
		t.solve();

	}

}
