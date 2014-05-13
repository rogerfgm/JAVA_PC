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
		int N = Y1.length;
		this.Y2 = Y2;
		if(check(Y1, 0)){
			return pos;
		}
		else{
			return imp;
		}
		
	}
	
	boolean check(int[] Y1, int ind){
		if(ind == N) {
			for(int i = 0; i < N; i++){
				if(Y1[i] != Y2[i]){
					return false;
				}
			}
			return true;
		}
		int t = Y2[ind];
		boolean f = false;
		for(int i = 0; i < N; i++){
			if(Y1[i] == t){
				if(i == ind){
					f |= check(Y1, ind+1);
				}
				else{
					for(int j = ind; j < i; j++){
						if(Y1[j] > t){
							int[] ny = Arrays.copyOf(Y1, N);
							ny[i] = ny[j];
							ny[j] = t;
							f |= check(ny, ind);
						}
					}
				}
				
				break;
			}
		}
		
		
		return f;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovingRooksDiv2 t = new MovingRooksDiv2();
		int[] Y1 = {3,1,2,0};
		int[] Y2 = {0,2,1,3};
		String r = t.move(Y1, Y2);
		out.println(r);
	}

}
