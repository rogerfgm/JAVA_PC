package srm626;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class FixedDiceGameDiv2 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public 	double getExpectation(int a, int b){
		double sum = 0;
		double cnt = 0;
		if(a == 1){
			return 0;
		}
		for(int i = a; i >= 2; i--){
			
			int c = Math.min(i-1, b);
			cnt += c;
			sum += i * (c);
		}
		
		
		
		
		return sum / cnt;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixedDiceGameDiv2 t = new FixedDiceGameDiv2();
		double r = t.getExpectation(11,13);
		out.println(r);
	}

}
