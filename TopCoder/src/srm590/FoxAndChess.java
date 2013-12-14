package srm590;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class FoxAndChess {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	String pos = "Possible";
	String imp = "Impossible";
	
	public String ableToMove(String b, String t){
		
		int bidx = 0;
		int tidx = 0;
		while(tidx < t.length()){
			if(t.charAt(tidx) == '.'){
			}
			else if(t.charAt(tidx) == 'L'){
				boolean found = false;
				while(bidx < tidx){
					if(b.charAt(bidx) != '.'){
						return imp;
					}
					bidx++;
				}
				
				while(bidx < b.length()){
					if(b.charAt(bidx) == 'L'){
						bidx++;
						found = true;
						break;
					}
					else if(b.charAt(bidx) == 'R'){
						break;
					}
					bidx++;
				}
				if(!found){
					return imp;
				}
			}
			else if(t.charAt(tidx) == 'R'){
				boolean found = false;
				while(bidx <= tidx){
					if(b.charAt(bidx) == 'L'){
						return imp;
					}
					else if(b.charAt(bidx) == 'R'){
						found = true;
						bidx++;
						break;
					}
					bidx++;
				}
				if(!found){
					return imp;
				}
			}
			
			tidx++;
		}
		while(bidx < b.length()){
			if(b.charAt(bidx) != '.'){
				return imp;
			}
			bidx++;
		}
		
		return pos;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAndChess t = new FoxAndChess();
		String b = "L";
		String ta = ".";
		String r = t.ableToMove(b, ta);
		out.println(r);
	}

}
