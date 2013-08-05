


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA on 13

//-1 -1
//UR
//Output
//Yes
//Answer
//No
public class C190 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int a = sc.nextInt();
		int b = sc.nextInt();
		String S = sc.next();
		if(a == 0 && b == 0){
			out.println("Yes");
			return;
		}
		int X = 0;
		int Y = 0;
	
		for(int i = 0; i < S.length(); i++){
			char c = S.charAt(i);
			if(c == 'U'){
				Y++;
			}
			else if(c == 'D'){
				Y--;
			}
			else if(c == 'L'){
				X--;
			}
			else{
				X++;
			}
		}
		
		int x = 0;
		int y = 0;
		boolean f = false;
		
		if(a == 0 && b == 0){
			f = true;
		}
		for(int i = 0; i < S.length(); i++){
			char c = S.charAt(i);
			if(c == 'U'){
				y++;
			}
			else if(c == 'D'){
				y--;
			}
			else if(c == 'L'){
				x--;
			}
			else{
				x++;
			}
			int ca = a - x;
			int cb = b - y;
			if(check(ca, cb, X, Y)){
				f = true;
				break;
			}
		}

		
		if(f){
			out.println("Yes");
		}
		else{
			out.println("No");
		}
	
		
	}
	
	boolean check(int a, int b, int x, int y){
		
		if(x == 0 && y == 0){
			if(a == 0 && b == 0){
				return true;
			}
		}
		else if(x == 0){
			if(a == 0){
				if(b % y == 0){
					return true;
				}
			}
		}
		else if(y == 0){
			if(b == 0){
				if(a % x == 0){
					return true;
				}
			}
		}
		else{
			if(a % x == 0 && b % y == 0 && a / x == b / y){
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
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		C190 t = new C190();
		t.solve();
		bw.close();
	}

}
