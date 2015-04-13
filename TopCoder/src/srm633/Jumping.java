package srm633;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class Jumping {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	String A = "Able";
	String NA = "Not able";
	
	public String ableToGet(int x, int y, int[] js){
		double len = Math.sqrt(x * x + y * y);
		
		int mx = 0;
		int midx = -1;
		for(int i = 0; i < js.length; i++){
			int val = Math.abs(js[i]);
			if(val > mx){
				mx = val;
				midx = i;
			}
		}
		if(len >= mx){
			int sum = 0;
			for(int i = 0;i < js.length; i++){
				sum += Math.abs(js[i]);
			}
			if(sum + EPS >= len){
				return A;
			}
			else{
				return NA;
			}
		}
		else{
			double sum = len;
			for(int i = 0;i < js.length; i++){
				if(i == midx){
					continue;
				}
				sum += Math.abs(js[i]);
			}
			if(sum + EPS>= mx){
				return A;
			}
			else{
				return NA;
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jumping t = new Jumping();
		
		int[] js = {6};
		String r = t.ableToGet(3, 4, js);
		out.println(r);
	}

}
