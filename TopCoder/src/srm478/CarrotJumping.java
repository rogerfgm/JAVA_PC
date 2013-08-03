package srm478;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;
import java.math.BigInteger;

public class CarrotJumping {

	long INF = Long.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	public int theJump(int init){
	
		long b1 = (long)init * 4 + 3;
		long b2 = (long)init * 8 + 7;
		if(b1 % MOD == 0 || b2 % MOD == 0){
			return 1;
		}
		long a1 = b1 * 4 + 3;
		long a2 = b1 * 8 + 7;
		long a3 = b2 * 8 + 7;
		if(a1 % MOD == 0 || a2 % MOD == 0 || a3 % MOD == 0){
			return 2;
		}
		
		
		for(int i = 3; i <= 100000; i++){
			a1 = a1 * 8 + 7;
			a2 = a2 * 8 + 7;
			a3 = a3 * 8 + 7;
			a1 %= MOD;
			a2 %= MOD;
			a3 %= MOD;
			if(a1 == 0 || a2 == 0 || a3 == 0){
				return i;
			}
		}
		
		
		return -1;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarrotJumping t = new CarrotJumping();
		int r = t.theJump(852808441);
		out.println(r);
	}

}
