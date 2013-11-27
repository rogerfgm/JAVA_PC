import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class LittleElephantAndString {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getNumber(String A, String B){
		int ans = 0;
		int[] a = new int[27];
		int[] b = new int[27];
		for(int i = 0; i < A.length(); i++){
			int idx = A.charAt(i) - 'A';
			a[idx]++;
		}
		for(int i = 0; i < B.length(); i++){
			int idx = B.charAt(i) - 'A';
			b[idx]++;
		}
		for(int i = 0; i < a.length; i++){
			if(a[i] != b[i]) return -1;
		}
		for(int i = 0; i < B.length(); i++){
			char cb = B.charAt(B.length() - i - 1);
			while(A.length() > 0){
				if(A.charAt(A.length() - 1) == cb){
					A = A.substring(0, A.length() - 1);
					break;
				}
				else{
					ans++;
				}
				A = A.substring(0, A.length() - 1);
			}
			if(A.length() == 0){
				break;
			}
		}
		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LittleElephantAndString t = new LittleElephantAndString();
		String a = "DCABA";
		String B = "DACBA";
		int r = t.getNumber(a, B);
		out.println(r);
	}

}
