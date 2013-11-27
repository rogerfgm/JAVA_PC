import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class LittleElephantAndRGB {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public long getNumber(String[] list, int mg){
		long a = 0;
		String s = "";
		for(int i = 0; i < list.length; i++){
			s += list[i];
		}
		int[][] dpf = new int[251][251];
		if(s.charAt(0) == 'G'){
			dpf[0][1] = 1;
		}
		else{
			dpf[0][0] = 1;
		}
		for(int i = 1; i < s.length(); i++){
			if(s.charAt(i) == 'G'){
				dpf[i][0] = dpf[i-1][0];
				for(int j = 1; j <= i+1; j++){
					dpf[i][j] = dpf[i-1][j-1] + dpf[i-1][j] * 2;
				}
			}
			else{
				dpf[i][0] = dpf[i-1][0] * 2;
				for(int j = 1; j <= i+1; j++){
					dpf[i][j] = dpf[i-1][j];
				}
			}	
		}
		
		int[][] dpb = new int[251][251];
		int len = s.length();
		if(s.charAt(s.length()-1) == 'G'){
			dpb[0][len-1] = 1;
		}
		else{
			dpb[0][len-1] = 1;
		}
		for(int i = len-2; i >= 0; i--){
			if(s.charAt(i) == 'G'){
				dpb[i][0] = dpb[i+1][0];
				for(int j = 1; j <= i+1; j++){
					dpb[i][j] = dpb[i+1][j-1] + dpb[i+1][j] * 2;
				}
			}
			else{
				dpb[i][0] = dpb[i-1][0] * 2;
				for(int j = 1; j <= i+1; j++){
					dpb[i][j] = dpb[i+1][j];
				}
			}	
		}
		
		return a;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LittleElephantAndRGB t = new LittleElephantAndRGB();

		out.println();
	}

}
