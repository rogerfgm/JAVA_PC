package srm579;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class UndoHistory {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	
	public int minPresses(String[] ls){
		int ans = ls[0].length()+1;
		for(int i = 1; i < ls.length; i++){
			String s = ls[i];
			int p = 0;
			if(s.startsWith(ls[i-1])){
				p = s.length() - ls[i-1].length();
			}
			else{
				p = s.length() + 2;
			}
			
			for(int j = 0; j < i; j++){
				String t = ls[j];
				int sm = 0;
				for(int k = 0; k < min(t.length(), s.length()); k++){
					if(t.charAt(k) == s.charAt(k)){
						sm++;
					}
					else{
						break;
					}
				}
				p = min(p, 2 + s.length()-sm);
			}
			
			ans += p + 1;
		}
		
		
		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UndoHistory t = new UndoHistory();
		String[] s = {"ba","a","a","b","ba"};
		int ret = t.minPresses(s);
		out.println(ret);
	}

}
