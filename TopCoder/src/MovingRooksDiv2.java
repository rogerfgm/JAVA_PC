import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class MovingRooksDiv2 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	String pos = "Possible";
	String imp = "Impossible";
	int[] Y2 = null;
	public String move(int[] Y1, int[] Y2){
		N = Y1.length;
		this.Y2 = Y2;
		if(check(Y1)){
			return pos;
		}
		else{
			return imp;
		}
		
	}
	
	boolean check(int[] Y1){
		{
			boolean f = true;
			for(int i = 0; i < N; i++){
				if(Y1[i] != Y2[i]){
					f = false;
					break;
				}
			}
			if(f){
				return true;
			}
		}
		for(int i = N-1; i > 0; i--){
			for(int j = i-1; j >= 0; j--){
				if(Y1[j] > Y1[i]){
					int t = Y1[j];
					Y1[j] = Y1[i];
					Y1[i] = t;
					if(check(Y1)){
						return true;
					}
					t = Y1[j];
					Y1[j] = Y1[i];
					Y1[i] = t;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovingRooksDiv2 t = new MovingRooksDiv2();
		int[] Y1 = {5, 2, 4, 3, 1, 0};
		int[] Y2 = {0, 5, 2, 3, 4, 1};
		String r = t.move(Y1, Y2);
		out.println(r);
	}

}
