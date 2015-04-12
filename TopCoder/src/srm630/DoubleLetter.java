package srm630;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class DoubleLetter {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	String yes = "Possible";
	String no = "Impossible";
	
	public String ableToSolve(String S){
		while(true){
			boolean f = false;
			for(int i = 0; i < S.length() - 1; i++){
				if(S.charAt(i) == S.charAt(i+1)){
					f = true;
			
					
					S = S.substring(0, i) + S.substring(i+2);
					
					break;
				}
			}
			if(S.length() == 0){
				return yes;
			}
			if(!f){
				break;
			}
		}
	
		return no;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DoubleLetter t = new DoubleLetter();
		String r = t.ableToSolve("abcdee");
		out.println(r);
	}

}
