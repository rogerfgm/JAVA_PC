

import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	static PrintStream out = null;
	int N = 0;
	long b = 1;
	
	public void solve() throws Exception{

		String[] ss = br.readLine().split(" ");
		N = parseInt(ss[0]);
		int K = parseInt(ss[1]);
		List<Integer> ls = new ArrayList<Integer>();
		List<Integer> as = new ArrayList<Integer>();
		ss = br.readLine().split(" ");
		for(int i = 0; i < ss.length; i++){
			ls.add(parseInt(ss[i]));
			as.add(i+1);
		}
		

		long n = ls.size();
		long ansum = 0;
		int del = 0;
		for(int i = 0; i < ls.size(); i++){
			long an = b * ls.get(i) * i;
			long am = b * (n - i - 1 + del) * ls.get(i) * (i-del);
			if(ansum -  am < K){
				n--;
				del++;
				bw.write(as.get(i) + "\n");
				//out.println(as.get(i));
				
			}
			else{
				ansum += an;
			}
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
		C t = new C();
		t.solve();
		bw.close();
	}

}
