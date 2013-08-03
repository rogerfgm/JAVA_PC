package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// pが素数ではなく、a^p = a (mod p)の時にyesを出力する
public class POJ3641 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int p = 0;
	static int a = 0;
	
	boolean[] f = null;
	public void solve() throws Exception{
		if(is_prime(p)){
			out.println("no");
			return;
		}
		if(get(p, a) == (a % p)){
			out.println("yes");
		}
		else{
			out.println("no");
		}
	}
	
	int get(int b, int c){
		long ret = 1;
		long x = c;
		while(b > 0){
			if((b & 1) > 0){
				ret *= x;
				ret %= p;
			}
			x *= x;
			x %= p;
			b = b >> 1;
		}
		return (int)ret % p;
	}
	
	boolean is_prime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return n != 1; // 1の場合は除外
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
		POJ3641 POJ = new POJ3641();

		while(true){
			try{
				p = sc.nextInt();
				a = sc.nextInt();
				if(a == 0 && p == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			POJ.solve();
		}
	}

}
