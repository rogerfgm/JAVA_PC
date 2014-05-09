package srm619;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class SplitStoneGame {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	String win = "WIN";
	String lose = "LOSE";
	public 	String winOrLose(int[] n){
		int num = 0;
		for(int i = 0; i < n.length; i++){
			if(n[i] >= 2){
				num++;
			}
		}
		if(num == 0){
			return lose;
		}
		if(n.length == 1 || n.length == 2){
			return lose;
		}
		if(n.length % 2 == 1){
			return win;
		}
		else{
			return lose;
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplitStoneGame t = new SplitStoneGame();
		int[] number = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 1, 9, 1, 3, 1, 1, 1, 1, 1};
		String r = t.winOrLose(number);
		
		out.println(r);
	}

}
