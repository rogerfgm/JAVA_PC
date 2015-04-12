


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class D274 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{

		int[] ts = nextInts();
		int n = ts[0];
		int l = ts[1];
		int x = ts[2];
		int y = ts[3];
	
		int[] ls = nextInts();
		List<Integer> lss = new ArrayList<Integer>();
		Set<Integer> lset = new HashSet<Integer>();
		for(int i = 0; i < ls.length; i++){
			lset.add(ls[i]);
		}
		
		
		boolean xf = check(x, ls);
		boolean yf = check(y, ls);
		if(xf && yf){
			System.out.println("0");
		}
		else if(xf){
			System.out.println("1");
			System.out.println(y);
		}
		else if(yf){
			System.out.println("1");
			System.out.println(x);
		}
		else{
			for(int i = 0; i < n; i++){
				int np = ls[i] + x;
				if(np < l){
					if(lset.contains(np + y)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
					if(lset.contains(np - y)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
				}
				np = ls[i] - x;
				if(np > 0){
					if(lset.contains(np + y)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
					if(lset.contains(np - y)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
				}
				
				np = ls[i] + y;
				if(np < l){
					if(lset.contains(np + x)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
					if(lset.contains(np - x)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
				}
				np = ls[i] - y;
				if(np > 0){
					if(lset.contains(np + x)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
					if(lset.contains(np - x)){
						System.out.println("1");
						System.out.println(np);
						return;
					}
				}
				
			}
			System.out.println("2");
			System.out.println(x + " " + y);
			
		}
		
	}
	
	boolean check(int t, int[] ls){
		int n = ls.length;
		if(ls[n-1] == t){
			return true;
		}
		int nxt = 1;
		for(int i = 0; i < n-1; i++){
			while(ls[nxt] - ls[i] < t && nxt < n-1){
				nxt++;
			}
			if(ls[nxt] - ls[i] == t){
				return true;
			}
		}
		
		return false;
	}
	void add(Integer t, List<Integer> lst){
		for(int i = 0; i < lst.size(); i++){
			if(lst.get(i).intValue() >= t.intValue()){
				lst.add(i, t);
				return;
			}
		}
		lst.add(t);
	}
	boolean check(Integer t, List<Integer> ls){
		int n = ls.size();
		if(ls.get(n-1).intValue() == t){
			return true;
		}
		int nxt = 1;
		for(int i = 0; i < n-1; i++){
			while(ls.get(nxt) - ls.get(i) < t && nxt < n-1){
				nxt++;
			}
			if(ls.get(nxt) - ls.get(i) == t){
				return true;
			}
		}
		
		return false;
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
		D274 t = new D274();
		t.solve();
		bw.close();
	}

}
