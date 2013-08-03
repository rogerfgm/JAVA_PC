package yarou;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;


// とりあえずtleで書いてみる　1 ^ 9 だから
// 分類みるも（行列累乗）、パターンがわからずのまま
// Runtime Error
public class POJ3420 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int ans = 0;
	public void solve() throws Exception{
		
		ans = 0;
		check(1, 0, 0, 0);
		out.println(ans);
	}
	
	void check(int row, int col, int now, int next){
		if(col == 4){
			if(now != (1 << 4)-1){
				return;
			}
			check(row + 1, 0, next, 0);
			return;
		}
		if(row == N+1){
			if(now == 0){
				ans++;
				ans %= M;
			}
			return;
		}

		boolean fnow = ((1 << col) & now) == 0;
		boolean fncol = ((1 << (col+1)) & now) == 0;
		
		
		if(fnow){
			if(col < 3 && fncol){
				int nnow = 1 << col | now;
				nnow = 1 << (col+1) | nnow;
				check(row, col+2, nnow, next);
			}
			int nnow = 1 << col | now;
			int nnext = 1 << col | next;
			check(row, col+1, nnow, nnext);
		}
		else{
			check(row, col+1, now, next);
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3420 p = new POJ3420();

		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}

}
