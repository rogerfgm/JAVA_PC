package old;
import java.io.*;
import java.util.*;


public class SnowCleaning01 {

	boolean[][] b = null;
	int Sal = 0;
	int Fin = 0;
	int N = 0;
	int cnt = 0;
	
	Worker[] workers = null;
	int[][] workerIdx = null;
	int nextWorkerID = 0; 
	static PrintStream err = null;
	static long progtime = 0;
	public int init(int boardSize, int salary, int snowFine){
		progtime = 0;
		long start = System.currentTimeMillis();
		//err.println(boardSize + " " + salary + " " + snowFine);
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
		int ama = N - (L * 10);
		
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
			if(w.by < 0){
				continue;
			}
			for(int j = w.by; j <= w.ey; j++){
				for(int k = w.bx; k <= w.ex; k++){
					workerIdx[j][k] = i;
				}
			}
		}
		progtime += System.currentTimeMillis() - start;
		return 0;
	}
	
	public String[] nextDay(int[] sf){
		long start =  System.currentTimeMillis();
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
		if(cnt > 0){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if(b[i][j]){
						int widx = workerIdx[i][j];
						if(!workers[widx].canmove){
							continue;
						}
						if(!workers[widx].w){
							workers[widx].w = true;
							workers[widx].workerID = nextWorkerID++;
							workers[widx].y = i;
							workers[widx].x = j;
							workers[widx].canmove = false;
							b[i][j] = false;
							workers[widx].lastmove = "";
							cmd.add("H " + i + " " + j);
						}
						else{
							String dir = findNear(workers[widx]);
							workers[widx].lastmove = dir;
							if(dir.length() > 0){
								cmd.add("M " + workers[widx].workerID + " " + dir);
							}
							
						}
					}
				}
			}
		}

		
		
		
		String[] ret = new String[cmd.size()];
		//ret = new String[0];
		for(int i = 0; i < cmd.size(); i++){
			ret[i] = cmd.get(i);
			//err.println(ret[i]);
		}
		progtime += System.currentTimeMillis() - start;
		return ret;
	}
	
	private String findNear(Worker worker){
	
		int y = worker.y;
		int x = worker.x;
		worker.canmove = false;
		if(b[y][x]){
			b[y][x] = false;
			return getDir(0, 0);
		}
		if(y-1 >= worker.by && b[y-1][x]){
			b[y-1][x] = false;
			worker.y -=1;
			return getDir(-1, 0);
		}
		else if(y+1 <= worker.ey && b[y+1][x]){
			b[y+1][x] = false;
			worker.y +=1;
			return getDir(1, 0);
		}
		else if(x-1 >= worker.bx && b[y][x-1]){
			b[y][x-1] = false;
			worker.x -= 1;
			return getDir(0, -1);
		}
		else if(x+1 <= worker.ex && b[y][x+1]){
			b[y][x+1] = false;
			worker.x += 1;
			return getDir(0, 1);
		}
		int u = 0, d = 0, l = 0, r = 0;
		for(int i = worker.by; i <= worker.ey; i++){
			for(int j = worker.bx; j <= worker.ex; j++){
				if(b[i][j]){
					if(i < y){
						u++;
					}
					else if(i > y){
						d++;
					}
					if(j < x){
						l++;
					}
					else if(j > x){
						r++;
					}
				}
			}
		}
		if(u > d && u > l && u > r){
			worker.y-=1;
			return getDir(-1, 0);
		}
		if(l > u && l > d && l > r){
			worker.x-=1;
			return getDir(0, -1);
		}
		if(d > u && d > l && d > r){
			worker.y+=1;
			return getDir(1, 0);
		}
		if(r > u && r > d && r > l){
			worker.x+=1;
			return getDir(0, 1);
		}
		if(u > 0 && worker.lastmove.equals("U")){
			worker.y-=1;
			return getDir(-1, 0);
		}
		if(d > 0 && worker.lastmove.equals("D")){
			worker.y+=1;
			return getDir(1, 0);
		}
		if(l > 0 && worker.lastmove.equals("L")){
			worker.x-=1;
			return getDir(0, -1);
		}
		if(r > 0 && worker.lastmove.equals("R")){
			worker.x+=1;
			return getDir(0, 1);
		}
		if(u > 0){
			worker.y-=1;
			return getDir(-1, 0);
		}
		if(d > 0){
			worker.y+=1;
			return getDir(1, 0);
		}
		if(l > 0 ){
			worker.x-=1;
			return getDir(0, -1);
		}
		if(r > 0){
			worker.x+=1;
			return getDir(0, 1);
		}
		
		
		
		return getDir(0, 0);
	}
	
	private String getDir(int y, int x){
		if(y == 0 && x == 0){
			return "";
		}
		else if(y < 0 ){
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
		int by = -1;
		int bx = -1;
		int ey = -1;
		int ex = -1;
		int y = 0;
		int x = 0;
		int workerID = 0;
		boolean canmove = false;
		String lastmove = null;
	}
	
	
	public static void main(String[] args) throws Exception{
		SnowCleaning01 s = new SnowCleaning01();
		err = System.err;
		
		
//		s.init(47, 54, 85);
//		for(int i = 0; i < 100; i++){
//			System.out.println(s.workers[i].by + " " + s.workers[i].ey + " " + s.workers[i].bx + " "
//					+ " " + s.workers[i].ex + " "	);
//		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int boardSize = Integer.parseInt(br.readLine());
	    int salary = Integer.parseInt(br.readLine());
	    int snowFine = Integer.parseInt(br.readLine());

	    s.init(boardSize, salary, snowFine);

	    for (int t=0; t < 2000; t++){
	        int snowCnt = Integer.parseInt(br.readLine());
	        int[] snowFalls = new int[snowCnt*2];
	        for (int i=0; i < 2*snowCnt; i++){
	            snowFalls[i] = Integer.parseInt(br.readLine());
	        }
	        String[] ret = s.nextDay(snowFalls);
	        bw.write(ret.length + "\n");
	        for (int i=0; i < ret.length; i++){
	        	bw.write(ret[i] + "\n");
	        }

	        bw.flush();
	    }

	    bw.close();
	    br.close();
	    System.err.println("time : " + progtime);
	}

}
