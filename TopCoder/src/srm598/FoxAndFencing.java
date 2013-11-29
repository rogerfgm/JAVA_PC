package srm598;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class FoxAndFencing {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	String C = "Ciel";
	String L = "Liss";
	String D =  "Draw";
	public String WhoCanWin(int m1, int m2, int r1, int r2, int d){
		if(m1 + r1 >= d){
			return C;
		}
		if(m2 + r2 >= d + m1){
			return L;
		}
		if(m1 > m2){
			if((long)m2 * 2 + r2 + 1 <= m1 + r1){
				return C;
			}
		}
		if(m2 > m1){
			if((long)m1 * 2 + r1 + 1 <= m2 + r2){
				return L;
			}
		}
		return D;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxAndFencing t = new FoxAndFencing();

		out.println();
	}

}
