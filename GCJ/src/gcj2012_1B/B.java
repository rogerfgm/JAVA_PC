package gcj2012_1B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class B {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	boolean[][] used = null;
	int T = 0;
	int[][] C = null;
	int[][] F = null;
	int N = 0;
	int M = 0;
	int H = 0;
	int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public void solve() throws Exception{
		H = sc.nextInt();
		N = sc.nextInt();
		M = sc.nextInt();
		C = new int[N][M];
		F = new int[N][M];
		for(int i =0; i < N; i++){
			for(int j = 0; j < M; j++){
				C[i][j] = sc.nextInt();
			}
		}
		for(int i =0; i < N; i++){
			for(int j = 0; j < M; j++){
				F[i][j] = sc.nextInt();
			}
		}
		used = new boolean[N][M];
		if(check(0, 0)){
			out.println("0.0");
		}
		else{
			PriorityQueue<Data> que = new PriorityQueue<Data>(100, new Comparator<Data>() {
				@Override
				public int compare(Data o1, Data o2) {
					if(o1.time < o2.time){
						return -1;
					}
					else if(o1.time == o2.time){
						return 0;
					}
					else{
						return 1;
					}
				}
			});
			
			double[][] d = new double[N][M];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++){
					d[i][j] = INF;
				}
			}
		
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++){
					if(used[i][j]){
						d[i][j] = 0;
						Data tdt = new Data(0, i, j);
						que.add(tdt);
					}
				}
			}
			while(que.size() > 0){
				Data dt = que.poll();
				int y = dt.y;
				int x = dt.x;
				if(d[dt.y][dt.x] < dt.time){
					continue;
				}
				double c = C[y][x];
				double f = max(hei(dt.time), F[y][x]);
				boolean more20 = hei(dt.time) - F[y][x] + DF>= 20;
				for(int i = 0; i < dir.length; i++){
					int ny = y + dir[i][0];
					int nx = x + dir[i][1];
					if(ny < 0 || ny >= N || nx < 0 || nx >= M){
						continue;
					}
					if(C[ny][nx] - F[ny][nx] < 50 || C[ny][nx] - F[y][x] < 50 ||
						C[y][x] - F[ny][nx]	< 50){
						continue;
					}
					double nc = C[ny][nx];
					double nf = max(hei(dt.time), F[ny][nx]);
					if(c - nf + DF >= 50 && nc - f + DF >= 50){
						double ntime = 0;
						if(more20){
							ntime = dt.time + 1;
						}
						else{
							ntime = dt.time + 10;
						}
						if(d[ny][nx] > ntime){
							d[ny][nx] = ntime;
							Data ndt = new Data(ntime, ny, nx);
							que.add(ndt);
						}
					}
					else{
						double rem = 50 - min(c-nf, nc - f);
						double wait = rem / 10;
						boolean nowmore20 = hei(dt.time + wait) - F[y][x] + DF >= 20;
						double ntime = 0;
						if(nowmore20){
							ntime = dt.time + wait + 1;
						}
						else{
							ntime = dt.time + wait + 10;
						}
						if(d[ny][nx] > ntime){
							d[ny][nx] = ntime;
							Data ndt = new Data(ntime, ny, nx);
							que.add(ndt);
						}
					}
				}
			}
			out.println(d[N-1][M-1]);
		}
		
		
	}
	
	double hei(double time){
		double ret = H - 10 * time;
		if(ret < 0){
			ret = 0;
		}
		return ret;
	}
	
	class Data{
		public Data(double time, int y, int x){
			this.time = time;
			this.y = y;
			this.x = x;
		}
		double time = 0;
		int y = 0;
		int x = 0;
	}
	
	boolean check(int y, int x){
		if(y == N-1 && x == M-1){
			return true;
		}
		used[y][x] = true;
		int c = C[y][x];
		int f = max(H, F[y][x]);
		for(int i = 0; i < dir.length; i++){
			int ny = y + dir[i][0];
			int nx = x + dir[i][1];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M){
				continue;
			}
			if(used[ny][nx]) continue;
			int nc = C[ny][nx];
			int nf = max(H, F[ny][nx]);
			if(c - nf < 50 || nc - f < 50 || nc - nf < 50) continue;
			if(check(ny, nx)) return true;
		}
		
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		B b = new B();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
