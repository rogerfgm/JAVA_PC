package srm586;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class NOT_History {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public String verifyClaims(String[] dy, String[] ba, String[] qs){
		
	
//		StringBuilder sb = new StringBuilder();
//		N = dy.length;
//		for(int i = 0; i < ba.length; i++){
//			sb.append(ba[i]);
//		}
//		int[][] d = new int[N][11];
//		for(int i = 0; i < N; i++){
//			String[] t = dy[i].split(" ");
//			int sum = 0;
//			for(int j = 0; j < t.length-1; j++){
//				d[i][j] = sum;
//				sum += Integer.parseInt(t[j+1]) - Integer.parseInt(t[j]);
//			}
//			d[i][t.length-1] = sum;
//		}
//		
//		
//		
//		String[] sp = sb.toString().split(" ");
//		for(int i = 0; i < qs.length; i++){
//			int[] off = new int[N];
//			String[] t = qs[i].split("-");
//			{
//				int D = getD(t[0]);
//				int E = getE(t[0]);
//				off[D] = d[D][E];
//			}
//			{
//				int D = getD(t[1]);
//				int E = getE(t[1]);
//				off[D] = d[D][E];
//			}
//		
//		}
		String ans = "";
		return ans;
	}
	int getD(String s){
		char c = s.charAt(0);
		return c - 'A';
	}
	int getE(String s){
		char c = s.charAt(1);
		return c - '0';
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NOT_History t = new NOT_History();
		String[] dy = {"1 2 4",
		 "1 2 3"};
		String[] ba = {"A1-B0"};
		String[] qs = {"A0-B0",
				 "A0-B1",
				 "A1-B0",
				 "A1-B1"};
		String r = t.verifyClaims(dy, ba, qs);
		out.println(r);
	}

}
