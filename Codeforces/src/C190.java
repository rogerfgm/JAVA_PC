


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA on 13
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
		int x = 0;
		int y = 0;
		List<int[]> list = new ArrayList<int[]>();
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
			int[] l = new int[2];
			l[0] = x;
			l[1] = y;
			list.add(l);
		}
		boolean f = false;
		for(int i = 0;i < list.size(); i++){
			int[] l = list.get(i);
			int cx = a - l[0];
			int cy = b - l[1];
			if(x == 0 && y == 0){
				if(cx == 0 && cy == 0){
					f = true;
				}
			}
			else if(x == 0){
				if(cy % y == 0){
					f = true;
				}
			}
			else if(y == 0){
				if(cx % x == 0){
					f = true;
				}
			}
			else{
				if(cx % x == 0 && cy % y == 0){
					if(cx / x == cy / y){
						f = true;
					}
				}
			}
		}
		if(f){
			out.println("Yes");
		}
		else{
			out.println("No");
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
		bw = new BufferedWriter(new PrintWriter(out));
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		C190 t = new C190();
		t.solve();
		bw.close();
	}

}
