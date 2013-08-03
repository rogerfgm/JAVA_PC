


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// WA
public class D191 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	int n = 0;
	int m = 0;
	boolean[][] f = null;
	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	public void solve() throws Exception{
		n = sc.nextInt();
		m = sc.nextInt();
		f = new boolean[n][m];
		for(int i = 0; i < n; i++){
			String s = sc.next();
			for(int j = 0; j < m; j++){
				char c = s.charAt(j);
				if(c != '#'){
					f[i][j] = true;
				}
			}
		}
	
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(f[i][j]){
					check(i, j);
				}
			}
		}
	}
	
	void check(int y, int x){

		boolean has1 = false;
		boolean hasother = false;
		int ax = 0;
		int ay = 0;
		for(int i = 0; i < dx.length; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny == n || nx == m){
				continue;
			}
			if(!f[ny][nx]){
				continue;
			}
			if(one(ny, nx)){
				has1 = true;
				ay = y + 1;
				ax = x + 1;
				out.println("B " + ay + " " + ax);
				ay = ny+1;
				ax = nx + 1;
				out.println("R " + ay + " " + ax);
				ay = y + 1;
				ax = x + 1;
				out.println("D " + ay + " " + ax);
				f[ny][nx] = false;
			}
			else{
				hasother = true;
			}
		}
		if(!hasother){
			ay = y + 1;
			ax = x + 1;
			out.println("B " + ay + " " + ax);
			f[y][x] = false;
			return;
		}
		
		boolean done = false;
		for(int i = 0; i < dx.length; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny == n || nx == m){
				continue;
			}
			if(f[ny][nx]){
				if(!done){
					done = true;
					ay = ny + 1;
					ax = nx + 1;
					out.println("B " + ay + " " + ax);
					ay = y + 1;
					ax = x + 1;
					out.println("R " + ay + " " + ax);
					ay = ny + 1;
					ax = nx + 1;
					out.println("D " + ay + " " + ax);
					f[y][x] = false;
				}
				check(ny, nx);
			}
		}
	}
	
	
	
	boolean one(int y, int x){
		if(!f[y][x]){
			return false;
		}
		int cnt = 0;
		for(int i = 0; i < dx.length; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny == n || nx == m){
				continue;
			}
			if(f[ny][nx]){
				cnt++;
			}
		}
		if(cnt == 1){
			return true;
		}
		return false;
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
		D191 t = new D191();
		t.solve();
		bw.close();
	}

}
