package srm609;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class MagicalStringDiv1 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	char a = '>';
	char b = '<';
	
	public  int getLongest(String S){
		int ans = 0;
		N = S.length();
		char[] cs = S.toCharArray();
		for(int i = 0; i < N; i++){
			
		}
		for(int i = 0; i < N; i++){
			int cnta = 0;
			int cntb = 0;
			for(int j = 0; j <= i; j++){
				if(cs[j] == a){
					cnta++;
				}
			}
			for(int j = i+1; j < N; j++){
				if(cs[j] == b){
					cntb++;
				}
			}
			int cnt = min(cnta, cntb) * 2;
			ans = max(ans, cnt);
		}
		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicalStringDiv1 t = new MagicalStringDiv1();
		String S = "<<<<><>>><>>><>><>><>>><<<<>><>>>>><<>>>>><><<<<>>";
		int r = t.getLongest(S);
		out.println(r);
	}

}
