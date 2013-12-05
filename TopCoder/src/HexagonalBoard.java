import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class HexagonalBoard {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int minColors(String[] bd){
		int ans = 0;
		N = bd.length;
	
		int[][] dt = new int[N][N];
		for(int i = 0; i < bd.length; i++){
			String s = bd[i];
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == 'X'){
					Set<Integer> set = new HashSet<Integer>();
					if(j > 0){
						set.add(dt[i][j-1]);
					}
					if(j < N-1){
						set.add(dt[i][j+1]);
					}
					if(i > 0){
						set.add( dt[i-1][j]);
						if(j < N-1){
							set.add( dt[i-1][j+1]);
						}
					}
					if(i < N-1){
						set.add( dt[i+1][j]);
						if(j > 0){
							set.add( dt[i+1][j-1]);
						}
					}
					for(int k = 1; ; k++){
						if(!set.contains(k)){
							dt[i][j] = k;
							break;
						}
					}
				}
			}
		}

		for(int i = 0; i < N; i++){
			for(int j = 0 ; j < N; j++){
				ans = max(ans, dt[i][j]);
			}
		}

		
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HexagonalBoard t = new HexagonalBoard();
		String[] bd = {"---",
				 "---",
		 "---"}
;
		int r = t.minColors(bd);
		out.println(r);
	}

}
