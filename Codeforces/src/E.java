

import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class E {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	long b = 1;
	int MOD = 1000000007;
	public void solve() throws Exception{
		N = parseInt(br.readLine());
		String[] sp = br.readLine().split(" ");
		int[] d = new int[N];
		int m = 0;
		for(int i = 0; i < N; i++){
			d[i] = parseInt(sp[i]);
			m = max(m, d[i]);
		}
		Arrays.sort(d);
		BIT bit = new BIT(m);

		for(int i = 0; i < N; i++){
			int c = d[i];
			long sum = bit.sum(c);
			
			long a = sum * c + c;
			int add = (int)((a - bit.sum(c) + bit.sum(c-1)) % MOD);

			bit.add(c, add);
			
		}
		long ans = bit.sum(m) % MOD;;

		bw.write(ans + "\n");
		
		
		
		
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
		E t = new E();
		t.solve();
		bw.close();
	}
	public class BIT {

	    int N = 0;
	    long bit[] = null; 
		public BIT(int n){
			this.N = n;
			bit = new long[N+2];
		}
		


	    

	    long sum(int i){
	    	long s = 0;
	    	while(i > 0){
	    		s += bit[i];
	    		i-=i&-i;
	    	}
	    	return s;
	    }
	    

	    void add(int i, int x){
	    	while(i <= N){
	    		bit[i] = bit[i] + x;
	    		bit[i] %= MOD;
	    		i += i & -i;
	    	}
	    }
	}
}
