package srm600;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;
import java.io.*;

public class ORSolitaire {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;

	public int getMinimum(int[] ns, int goal) {
	
		String gs = Integer.toBinaryString(goal);
		int[] cnt = new int[gs.length()];
		for(int i = 0; i < ns.length; i++){
			String ts = Integer.toBinaryString(ns[i]);
			if(ts.length() > gs.length()){
				continue;
			}
			boolean fail = false;
			for(int j = 0; j < min(gs.length(), ts.length()); j++){
				if(gs.charAt(gs.length() - 1-j) == '0' && ts.charAt(ts.length() - 1-j) == '1'){
					fail = true;
				}
			}
			if(fail){
				continue;
			}
			for(int j = 0; j < ts.length(); j++){
				if(ts.charAt(ts.length() - 1 -j) == '1'){
					cnt[ j]++;
				}
			}
		}
		int ans = INF;
		for(int i = 0; i < gs.length(); i++){
			if(gs.charAt(gs.length()-1-i) == '1'){
				ans = min(ans, cnt[i]);
			}
		}
		
		
		return ans;
	}

	public static void main(String[] args) {
		ORSolitaire t = new ORSolitaire();
		int[] in = {12571295, 2174218, 2015120};
		int tar = 1;
		int r = t.getMinimum(in, tar);

		out.println(r);
	}

}
