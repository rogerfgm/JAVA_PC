package srm144;
import java.util.*;
import static java.lang.Math.*;

public class BinaryCode {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	String m = null;
	public String[] decode(String m){
		this.m = m;
		String[] ret = new String[2];
		StringBuilder s0 = new StringBuilder();
		StringBuilder s1 = new StringBuilder();
		check(0, 0, 0, s0);
		check(0, 0, 1, s1);
		ret[0] = s0.toString();
		ret[1] = s1.toString();
		
		return ret;
	}
	
	void check(int idx, int p, int n, StringBuilder sb){
		if(idx == m.length()){
			return;
		}
		int a = m.charAt(idx) - '0';
		int nxt = a - p - n;
		if(nxt > 1 || nxt < 0){
			sb.delete(0, sb.length());
			sb.append("NONE");
			return;
		}
		if(idx == m.length()-1){
			if(nxt != 0){
				sb.delete(0, sb.length());
				sb.append("NONE");
				return;
			}
		}
		sb.append("" + n);
		check(idx+1, n, nxt, sb);
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryCode t = new BinaryCode();
		String s = "1";
		String[] ret = t.decode(s);
		
		System.out.println(ret[0] + " " + ret[1]);
	}

}
