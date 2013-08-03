import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class DrawingPointsDivOne {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	int B = 70;
	int L = 281;
	
	
	public int maxSteps(int[] x, int[] y){
		boolean[][] st = new boolean[L][L];
	
		for(int i = 0; i < x.length; i++){
			st[y[i]+B][x[i]+B] = true;
		}
		
		int l = 0;
		int r = 140;
		if(check(r, st)){
			return -1;
		}
		while(l + 1 < r){
			int m = (l + r) / 2;
			if(check(m, st)){
				l = m;
			}
			else{
				r = m;
			}
		}

		return l;
	}
	
	boolean check(int cnt, boolean[][] st){
		boolean[][] ns = st;
		for(int i = 0; i < cnt; i++){
			ns = rev(ns);
		}
		for(int i = 0; i < cnt; i++){
			ns = fwd(ns);
		}
		
		for(int j = 0; j < L; j++){
			for(int k = 0; k < L; k++){
				if(ns[j][k] != st[j][k]){
					return false;
				}
			}
		}
		
		return true;
	}
	
	boolean[][] rev(boolean[][] st){
		boolean[][] ns = new  boolean[L][L];
		for(int i = 0; i < L-1; i++){
			for(int j = 0; j < L-1; j++){
				if(st[i][j]){
					ns[i][j] = true;
					ns[i+1][j] = true;
					ns[i][j+1] = true;
					ns[i+1][j+1] = true;
				}
			}
		}
		return ns;
	}
	
	boolean[][] fwd(boolean[][] st){
		boolean[][] ns = new  boolean[L][L];
		for(int i = 0; i < L-1; i++){
			for(int j = 0; j < L-1; j++){
				if(st[i][j] && st[i+1][j] &&st[i][j+1] &&st[i+1][j+1]){
					ns[i][j] = true;
				}
			}
		}
		return ns;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawingPointsDivOne t = new DrawingPointsDivOne();
		int[] x = {15, -21, -58, 1, 52};
		int[] y = {57, -68, -31, 66, 25};
		
		
//		int[] x = {-41,-40,1,-11,-32,-7,24,-11,49,-15,-22,20,-8,54,54,69,16,-30,36,-6,-30,40,64,20,-66,
//				 -37,-33,-18,-35,36,9,61,-43,45,5,60,-8,-58,65,-66,41,12,34,-11,-57,-38,46,63,-55,3};
//		int[] y = {5,-24,-2,-4,23,14,1,70,-26,45,15,48,32,-41,54,-47,-67,-46,-9,-53,54,28,-61,11,53,68,
//				 -33,62,37,-8,-17,-17,48,19,-49,56,-41,16,17,-50,28,59,10,50,23,-16,56,31,-70,-44};
		
//		int[] x = {0, 3};
//		int[] y = {0, 0};
		
		/*
		 {{15, -21, -58, 1, 52}, {57, -68, -31, 66, 25}}

Expected:
95

Received:
-1
		 */
		
		
		int r = t.maxSteps(x, y);
		out.println(r);
	}

}
