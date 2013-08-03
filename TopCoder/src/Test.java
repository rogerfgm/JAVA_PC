import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class Test {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public long countCells(int A, int B, int r, int c, int k){
		

		int a = max(A, B);
		int b = min(A, B);
		
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				int cnt = 0;
				{
					int y = i + a;
					int x = j + b;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i - a;
					int x = j + b;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i + a;
					int x = j - b;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i - a;
					int x = j - b;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				
				{
					int y = i + b;
					int x = j + a;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i - b;
					int x = j + a;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i + b;
					int x = j - a;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				{
					int y = i - b;
					int x = j - a;
					if(y >= 0 && y < r && x >= 0 && x < c){
						cnt++;
					}
				}
				
				out.print(cnt);
			}
			out.println();
		}
		
		
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t = new Test();
		long r = t.countCells(7, 2, 40, 35, 2);
		//long r = t.countCells(2, 3, 1000000000, 1000000000, 8);
		/*
		 * 
		 {3, 2, 8, 8, 2}

Expected:
16

Received:
32
		 */
		out.println(r);
	}

}
