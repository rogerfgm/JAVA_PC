package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ2251 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int L = 0;
	static int R = 0;
	static int C = 0;
	public void solve() throws Exception{
		int sl = 0;
		int sx = 0;
		int sy = 0;
		
		int el = 0;
		int ex = 0;
		int ey = 0;
		
		boolean[][][] f = new boolean[L][R][C];
		int[][][] d = new int[L][R][C];
		for(int i = 0; i < L; i++){
			for(int j = 0; j < R; j++){
				String s = sc.next();
				for(int k = 0; k < s.length(); k++){
					d[i][j][k] = INF;
					char c = s.charAt(k);
					if(c != '#'){
						f[i][j][k] = true;
					}
					if(c == 'S'){
						d[i][j][k] = 0;
						sl = i;
						sx = k;
						sy = j;
					}
					if(c == 'E'){
						el = i;
						ex = k;
						ey = j;
					}
				}
			}
		}
		Data start = new Data(0, sl, sy, sx);
		PriorityQueue<Data> que = new PriorityQueue<Data>(10, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		
		que.add(start);
		int[][] mv = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		while(que.size() > 0){
			Data now = que.poll();
			int l = now.l;
			int ny = now.y;
			int nx = now.x;
			if(d[l][ny][nx] < now.d){
				continue;
			}
			
			for(int i = 0; i < mv.length; i++){
				int y = ny + mv[i][0];
				int x = nx + mv[i][1];
				if(y < R && x < C && y >= 0 && x >= 0){
					if(d[l][y][x] > now.d + 1 && f[l][y][x]){
						d[l][y][x] = now.d + 1;
						Data nd = new Data(now.d + 1, l, y, x);
						que.add(nd);
					}
				}
			}
			int ul = l + 1;
			int ll = l - 1;
			if(ul <= L-1){
				if(d[ul][ny][nx] > now.d + 1 && f[ul][ny][nx]){
					d[ul][ny][nx] = now.d + 1;
					Data nd = new Data(now.d + 1, ul, ny, nx);
					que.add(nd);
				}
			}
			if(ll >= 0){
				if(d[ll][ny][nx] > now.d + 1 && f[ll][ny][nx]){
					d[ll][ny][nx] = now.d + 1;
					Data nd = new Data(now.d + 1, ll, ny, nx);
					que.add(nd);
				}
			}
		}
		if(d[el][ey][ex] >= INF){
			out.println("Trapped!");
		}
		else{
			out.println("Escaped in " + d[el][ey][ex] + " minute(s).");
		}
	}
	
	class Data{
		public Data(int d, int l, int y, int x){
			this.d = d;
			this.l = l;
			this.y = y;
			this.x = x;
		}
		int d = 0;
		int l = 0;
		int y = 0;
		int x = 0;
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
		POJ2251 p = new POJ2251();

		while(true){
			try{
				L = sc.nextInt();
				R = sc.nextInt();
				C = sc.nextInt();
				if(L == 0 && R == 0 && C == 0){
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
