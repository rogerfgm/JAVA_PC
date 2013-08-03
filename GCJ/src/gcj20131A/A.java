package gcj20131A;


import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class A {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		long r = sc.nextLong();
		long t = sc.nextLong();

		if(4*r + 6 > t){
			out.println("1");
			return;
		}
		
		BigInteger min = new BigInteger("0");
		BigInteger max = new BigInteger(Long.toString(Long.MAX_VALUE));
		BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		BigInteger four = new BigInteger("4");
		BigInteger t2 = new BigInteger(Long.toString(t * 2));
		BigInteger br = new BigInteger(Long.toString(r));
		while(min.compareTo(max.subtract(one)) < 0){
			BigInteger mid = min.add(max).divide(two);
			
			if(mid.multiply(mid).multiply(four).subtract(mid.multiply(two)).add(mid.multiply(four).multiply(br)).compareTo(t2) > 0){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		out.println(min);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		A b = new A();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
	
			b.solve();
			t++;
		}
		out.close();
	}
}
