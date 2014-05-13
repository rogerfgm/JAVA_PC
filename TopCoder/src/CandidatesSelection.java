import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class CandidatesSelection {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	String pos = "Possible";
	String imp =  "Impossible";
	int[][] s = null;
	int[] r = null;
	public String possible(String[] S, int[] r){
		
		this.r = r;
		
		N = r.length;
		s = new int[N][N];
		for(int i = 0; i < N; i++){
			String sc = S[i];
			for(int j = 0; j < N; j++){
				s[i][j] = sc.charAt(j) - 'A';
			}
		}
		boolean f = true;
		for(int i = 0; i < N; i++){
			if(i != r[i]){
				f = false;
			}
		}
		if(f){
			return pos;
		}
		if(check(0, 0)){
			return pos;
		}
		return imp;
	}
	
	boolean check(long stp, long usd){
		
		
		return false;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CandidatesSelection t = new CandidatesSelection();

		out.println();
	}

}
