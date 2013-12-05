import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class LittleElephantAndBalls {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<String, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	String S = null;
	char[] sc = null;
	
	char R = 'R';
	char G = 'G';
	char B = 'B';
	public int getNumber(String S){
		int ans = 0;
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for(int i = 0; i < S.length(); i++){
			ans += set1.size() + set2.size();
			int t = 0;
			if(S.charAt(i) == R){
				t = 0;
			}
			else if(S.charAt(i) == G){
				t = 1;
			}
			else{
				t = 2;
			}
			if(!set1.contains(t)){
				set1.add(t);
			}
			else{
				set2.add(t);
			}
		}
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LittleElephantAndBalls t = new LittleElephantAndBalls();
		
		String S = "GGRRRGR";
		int r = t.getNumber(S);
		out.println(r);
	}

}
