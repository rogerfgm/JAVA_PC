


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B274 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{
		int[] tp = nextInts();
		int n = tp[0];
		int k = tp[1];
		int[] d = nextInts();
		D[] ds = new D[n];
		for(int i = 0; i < d.length; i++){
			ds[i] = new D();
			ds[i].d = d[i];
			ds[i].idx = i + 1;
		}
		Arrays.sort(ds, new Comparator<D>() {

			@Override
			public int compare(D o1, D o2) {
				// TODO Auto-generated method stub
				return o1.d - o2.d;
			}
		});
		if(n == 1){
			System.out.println("0 0");
			return;
		}
		List<String> as = new ArrayList<String>();
		List<String> suba = new ArrayList<String>();
		
		
		while(k > 0){
			int bdiff = ds[n-1].d - ds[0].d;
			if(bdiff <= 1){
				break;
			}

			ds[0].d +=1;
			ds[n-1].d --;
			suba.add(ds[n-1].idx + " " + ds[0].idx);
			Arrays.sort(ds, new Comparator<D>() {

				@Override
				public int compare(D o1, D o2) {
					// TODO Auto-generated method stub
					return o1.d - o2.d;
				}
			});
	
			
			k--;
			int adiff = ds[n-1].d - ds[0].d;
			if(adiff < bdiff){
				as.addAll(suba);
				suba.clear();
			}
		}
		int cdiff = ds[n-1].d - ds[0].d;
		int op = as.size();
		
		
		System.out.println(cdiff + " " + op);
		for(int i = 0; i < as.size(); i++){
			System.out.println(as.get(i));
		}
	}
	
	class D{
		int idx = 0;
		int d = 0;
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
		B274 t = new B274();
		t.solve();
		bw.close();
	}

}
