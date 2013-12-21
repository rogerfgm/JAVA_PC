import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class JumpFurther {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int furthest(int n, int bad){
		int ans = 0;
		for(int i = 1; i<= n; i++){
			ans += i;
			if(ans == bad){
				ans--;
			}
		}
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JumpFurther t = new JumpFurther();
		int n = 1313;
		int bad = 5858;
		int r = t.furthest(n, bad);
		out.println(r);
	}

}
