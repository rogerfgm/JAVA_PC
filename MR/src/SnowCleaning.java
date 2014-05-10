import java.io.*;
import java.util.*;


public class SnowCleaning {

	boolean[][] b = null;
	int Sal = 0;
	int Fin = 0;
	int N = 0;
	int cnt = 0;
	
	Worker[] workers = null;
	int[][] workerIdx = null;
	int nextWorkerID = 0; 

	public int init(int boardSize, int salary, int snowFine){
		N = boardSize;
		nextWorkerID = 0;
		Sal = salary;
		Fin = snowFine;
		b = new boolean[boardSize][boardSize];
		cnt = 0;
		workers = new Worker[100];
		for(int i = 0; i < 100; i++){
			workers[i] = new Worker();
		}
		workerIdx = new int[N][N];
		int L = N / 10;
		if(L < 2){
			L = 2;
		}
		int ama = N % L;
		
		int ny = 0;
		int yama = ama;
		int widx = 0;
		while(ny < N){
			int ey = ny + L - 1;
			if(yama > 0){
				ey++;
				yama--;
			}
			assert(ey < N);
			int nx = 0;
			int xama = ama;
			while(nx < N){
				int ex = nx + L -1;
				if(xama > 0){
					xama--;
					ex++;
				}
		
				assert(ex < N);
				
				workers[widx].by = ny;
				workers[widx].ey = ey;
				workers[widx].bx = nx;
				workers[widx].ex = ex;
				widx++;
				nx = ex+1;
				
			}
			
			ny = ey + 1;
		}
		
		for(int i = 0; i < 100; i++){
			Worker w = workers[i];
			for(int j = w.by; j <= w.ey; j++){
				for(int k = w.bx; k <= w.ex; k++){
					workerIdx[j][k] = i;
				}
			}
		}
		
		return 0;
	}
	
	public String[] nextDay(int[] sf){
		for(int i = 0; i < 100; i++){
			workers[i].canmove = true;
		}
		List<String> cmd = new ArrayList<String>();
		for(int i = 0; i < sf.length / 2; i++){
			int idx = i * 2;
			int idx2 = idx+1;
			int y = sf[idx];
			int x = sf[idx2];
			if(!b[y][x]){
				cnt++;
				b[y][x] = true;
			}
		}
//		if(cnt > 0){
//			for(int i = 0; i < N; i++){
//				for(int j = 0; j < N; j++){
//					if(b[i][j]){
//						int widx = workerIdx[i][j];
//						if(!workers[widx].canmove){
//							continue;
//						}
//						if(!workers[widx].w){
//							workers[widx].w = true;
//							workers[widx].workerID = nextWorkerID++;
//							workers[widx].y = i;
//							workers[widx].x = j;
//							workers[widx].canmove = false;
//							b[i][j] = false;
//							cmd.add("H " + i + " " + j);
//						}
//						else{
//							int wy = workers[widx].y;
//							int wx = workers[widx].x;
//							if(b[wy][wx]){
//								// not move
//								b[wy][wx] = false;
//								workers[widx].canmove = false;
//							}
//							else{
//								int dy = i - wy;
//								int dx = j - wx;
//								if(dy != 0 && dx != 0){
//									int nwy = wy + dy;
//									int nwx = wx;
//									if(b[nwy][nwx]){
//										b[nwy][nwx] = false;
//										workers[widx].canmove = false;
//										cmd.add("M " + workers[widx].workerID + " " + getDir(dy, 0));
//										continue;
//									}
//									nwy = wy;
//									nwx = wx + dx;
//									b[nwy][nwx] = false; // true‚Å‚Í‚È‚¢‚©‚à‚µ‚ê‚È‚¢‚ª‚±‚Á‚¿‚É“®‚©‚·‚µ‚©‚È‚¢
//									workers[widx].canmove = false;
//									cmd.add("M " + workers[widx].workerID + " " + getDir(0, dx));
//									continue;
//								}
//								else if(dy != 0){
//									int nwy = wy + dy;
//									int nwx = wx;
//									b[nwy][nwx] = false;
//									workers[widx].canmove = false;
//									cmd.add("M " + workers[widx].workerID + " " + getDir(dy, 0));
//								}
//								else if(dx != 0){
//									int nwy = wy;
//									int nwx = wx + dx;
//									b[nwy][nwx] = false;
//									workers[widx].canmove = false;
//									cmd.add("M " + workers[widx].workerID + " " + getDir(0, dx));
//								}
//							}
//						}
//					}
//				}
//			}
//		}

		
		
		
		String[] ret = new String[cmd.size()];
		for(int i = 0; i < cmd.size(); i++){
			ret[i] = cmd.get(i);
		}
		return ret;
	}
	
	private String getDir(int y, int x){
		if(y < 0 ){
			return "U";
		}
		else if(y > 0){
			return "D";
		}
		else if(x < 0){
			return "L";
		}
		else{
			return "R";
		}
	}
	
	class Worker{
		boolean w = false;
		int by = 0;
		int bx = 0;
		int ey = 0;
		int ex = 0;
		int workerID = 0;
		boolean canmove = false;
	}
	
	
	public static void main(String[] args) throws Exception{
		SnowCleaning s = new SnowCleaning();
		s.init(54, 10, 19);
		for(int i = 0; i < 100; i++){
			System.out.println(s.workers[i].by + " " + s.workers[i].ey + " " + s.workers[i].bx + " "
					+ " " + s.workers[i].ex + " "	);
		}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int boardSize = Integer.parseInt(br.readLine());
//	    int salary = Integer.parseInt(br.readLine());
//	    int snowFine = Integer.parseInt(br.readLine());
//
//	    s.init(boardSize, salary, snowFine);
//
//	    for (int t=0; t < 2000; t++){
//	        int snowCnt = Integer.parseInt(br.readLine());
//	        int[] snowFalls = new int[snowCnt*2];
//	        for (int i=0; i < 2*snowCnt; i++){
//	            snowFalls[i] = Integer.parseInt(br.readLine());
//	        }
//	        String[] ret = s.nextDay(snowFalls);
//	        bw.write(ret.length + "\n");
//	        for (int i=0; i < ret.length; i++){
//	        	bw.write(ret[i] + "\n");
//	        }
//
//	        bw.flush();
//	    }
//
//	    bw.close();
//	    br.close();
	}

}
