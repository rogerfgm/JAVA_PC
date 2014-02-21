


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B231 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{

		String s = br.readLine();
		String[] sp = s.split(" ");
		int p = parseInt(sp[0]);
		int Y = parseInt(sp[1]);
		
		
		BigInteger b10 = BigInteger.valueOf(10);
		BigInteger by = BigInteger.valueOf(Y);
		BigInteger bp = BigInteger.valueOf(p);
		BigInteger b10p = b10.pow(p-1);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger zero = BigInteger.valueOf(0);
	
		
		BigInteger sub = b10p.subtract(by);
		BigInteger right = by.multiply(b10).subtract(one);
		BigInteger ans = null;
		for(int i = 1; i <= 9; i++){
			BigInteger A = BigInteger.valueOf(i);
			BigInteger left = A.multiply(sub);
			BigInteger[] div = left.divideAndRemainder(right);
		
			
			if(div[0].toString().length() == p-1 && div[0].compareTo(zero) > 0 && div[1].equals(zero)){
				BigInteger a = div[0].multiply(b10).add(A);
				if(ans == null){
					ans = a;
				}
				else{
					if(a.compareTo(ans) < 0){
						ans = a;
					}
				}
				
			}
			
		}
		if(ans != null){
			bw.write(ans.toString());
		}
		else{
			bw.write("Impossible");
		}
		return;
		
//		
//		
//		int Ya = Y % 10;
//		BigInteger bya = BigInteger.valueOf(Ya);
//		BigInteger one = BigInteger.valueOf(1);
//		BigInteger right = by.multiply(b10).subtract(one);
//		
//		BigInteger left = bya.multiply(b10p);
//		
//		BigInteger b0 = BigInteger.valueOf(0);
//		if(right.equals(b0)){
//			bw.write("Impossible");
//			return;
//		}
//		if(left.remainder(right).equals(b0)){
//			BigInteger ans = left.divide(right);
//			bw.write(ans.toString());
//			return;
//		}
//		else{
//			bw.write("Impossible");
//			return;
//		}
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
	}
	
	void pl(String s){
		try{
			bw.write(s);
		}
		catch(Exception ex){
		}
	}
	
	void pln(String s){
		try{
			bw.write(s);
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
	}
	void pln(){
		try{
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		B231 t = new B231();
		t.solve();
		bw.close();
	}

}
