package done.d174;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class B174 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	
	public void solve() throws Exception{
		n = sc.nextInt();
		int a = 0;
		int in = 0;
		int f = 0;
		String s = sc.next();
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == 'A') a++;
			else if(s.charAt(i) == 'I') in++;
			else f++;
		}
		if(in >= 2){
			out.println("0");
		}
		else if(in == 1){
			out.println("1");
		}
		else{
			out.println(a);
		}
		
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
	
		sc =  new Scanner(System.in);
		B174 t = new B174();
		t.solve();

	}

}
