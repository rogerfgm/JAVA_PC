package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// ただ実際に繰り返し２乗法で計算するだけ。
public class POJ1995 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int M = 0;
	static int N = 0;

	public void solve() throws Exception{
		long sum = 0;
		for(int i = 0; i < N; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			sum += mypow(a, b);
			sum %= M;
		}
		out.println(sum);
	}
	
	int mypow(int a, int b){
		long x = a;
		long ret = 1;
		while(b > 0){
			if( (b & 1) > 0){
				ret *= x;
				ret %= M;
			}
			x *= x;
			x %= M;
			b = b >> 1;
		}

		return (int)ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1995 p = new POJ1995();

		int T = sc.nextInt();
		int t = 1;
		while(t++ <= T){
			try{
				M = sc.nextInt();
				N = sc.nextInt();
				if(N == 0 && M == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}
}
