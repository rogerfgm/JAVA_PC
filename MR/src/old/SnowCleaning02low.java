package old;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class SnowCleaning02low {

	boolean[][] b = null;
	int Sal = 0;
	int Fin = 0;
	int N = 0;

	
	Worker[] workers = null;
	int[][] workerIdx = null;
	int nextWorkerID = 0; 
	static PrintStream err = null;
	static long progtime = 0;
	List<Worker> activeWs = null;
	
	int NewWorkerDistThre = 8;
	
	int findNearThre = 10;
	
	String L = "L";
	String R = "R";
	String D = "D";
	String U = "U";
	
	Random rnd = new Random();
	
	public int init(int boardSize, int salary, int snowFine){
		
		progtime = 0;
		long start = System.currentTimeMillis();
		//err.println(boardSize + " " + salary + " " + snowFine);
		N = boardSize;
		nextWorkerID = 0;
		Sal = salary;
		Fin = snowFine;
		b = new boolean[boardSize][boardSize];
	
		workers = new Worker[100];
		for(int i = 0; i < 100; i++){
			workers[i] = new Worker();
			workers[i].workerID = i;
		}
		activeWs = new ArrayList<Worker>();
		
		progtime += System.currentTimeMillis() - start;
		return 0;
	}
	
	public String[] nextDay(int[] sf){
		long start =  System.currentTimeMillis();
		List<String> cmd = new ArrayList<String>();
		try{
			
			
			for(Worker w : activeWs){
				w.canmove = true;
				
			}
			
			int cnt = 0;
			for(int i = 0; i < sf.length / 2; i++){
				int idx = i * 2;
				int idx2 = idx+1;
				int y = sf[idx];
				int x = sf[idx2];
				if(!b[y][x]){
					b[y][x] = true;
				}
			}
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if(b[i][j]){
						cnt++;
					}
				}
			}
			
			// もとのworkerの位置にあったらその場に待機してそこを消す
			for(Worker w : activeWs){
				int y = w.y;
				int x = w.x;
				if(b[y][x]){
					b[y][x] = false;
					cnt--;
					w.canmove = false;
					w.t = null;
				}
			}
			
			
			// 近くにいない場合、その位置にworkerを追加
			if(cnt > 0){
				List<Pointd> bs = new ArrayList<Pointd>();
				for(int i = 0; i < N; i++){
					for(int j = 0; j < N; j++){
						if(b[i][j]){
							Pointd np = new Pointd(i, j, rnd.nextInt(10000000));
							bs.add(np);
						}
					}
				}
				Collections.sort(bs, new Comparator<Pointd>() {
					@Override
					public int compare(Pointd o1, Pointd o2) {
						return o1.d - o2.d;
					}
				});
				for(Pointd p : bs){
					if(!b[p.y][p.x]) throw new Exception("fukuda 1");
					boolean f = false;
					for(Worker w : activeWs){
						int dist = abs(w.y - p.y) + abs(w.x - p.x);
						if(dist <= NewWorkerDistThre){
							f = true;
							break;
						}
					}
					if(!f && nextWorkerID < 100){
						Worker w = workers[nextWorkerID++];
						w.canmove = false;
						w.y = p.y;
						w.x = p.x;
						w.t = null;
						b[p.y][p.x] = false;
						cnt--;
						cmd.add("H " + p.y + " " + p.x);
						activeWs.add(w);
					}
				}
			}
			
		
			
	
			// workerを目的地に近づける
			if(cnt > 0){
				for(Worker w : activeWs){
					if(cnt == 0) break;
					if(!w.canmove) continue;
					int y = w.y;
					int x = w.x;
					
					Point p = findNear(y, x, findNearThre);
					if(p == null){
						continue;
					}
					if(!b[p.y][p.x])throw new Exception("fukuda 2");
					
					
					if(w.t == null){
						w.t = p;
					}
					else if(!b[w.t.y][w.t.x]){
						w.t = p;
					}
					
					int neardist = dist(p, y, x);
					int targetdist = dist(w.t, y, x);
					if(neardist == 0 || targetdist == 0) throw new Exception("fukuda 3");
					if(targetdist <= neardist){
						if(w.t.y == y && w.t.x == x)  throw new Exception("fukuda 4");
						String dir = getDir(w.t, y, x);
						cmd.add("M " + w.workerID + " " + dir);
						w.canmove = false;
						w.y += moveY(dir);
						w.x += moveX(dir);
						
						if(targetdist == 1){
							b[w.t.y][w.t.x] = false;
							w.t = null;
							cnt--;
						}
					}
					else{
						if(p.y == y && p.x == x)  throw new Exception("fukuda 5");
						String dir = getDir(p, y, x);
						w.t = p;
						cmd.add("M " + w.workerID + " " + dir);
						w.canmove = false;
						w.y += moveY(dir);
						w.x += moveX(dir);
						
						if(neardist == 1){
							b[w.t.y][w.t.x] = false;
							w.t = null;
							cnt--;
						}
					}	
				}
			}
			
			for(Worker w : activeWs){
				if(!w.canmove) continue;
				w.canmove = false;
				w.t = null;
				if(w.y < 4){
					w.y++;
					cmd.add("M " + w.workerID + " " + D);
				}
				else if(w.y >= N - 4){
					w.y--;
					cmd.add("M " + w.workerID + " " + U);
				}
				else if(w.x < 4){
					w.x++;
					cmd.add("M " + w.workerID + " " + R);
				}
				else if(w.x >= N - 4){
					w.x--;
					cmd.add("M " + w.workerID + " " + L);
				}
				else{
					String dir = getRndDir();
					cmd.add("M " + w.workerID + " " + dir);
					w.canmove = false;
					w.y += moveY(dir);
					w.x += moveX(dir);
				}
			}
			
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
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
	
	
	
	int moveY(String s){
		if(s.equals(U)){
			return -1;
		}
		else if(s.equals(D)){
			return 1;
		}
		return 0;
	}
	int moveX(String s){
		if(s.equals(L)){
			return -1;
		}
		else if(s.equals(R)){
			return 1;
		}
		return 0;
	}
	
	
	int dist(Point p, int y, int x){
		return Math.abs(p.y - y) + Math.abs(p.x - x);
	}
	
	Point findNear(int y, int x, int d){
		boolean[][] used = new boolean[N][N];
		List<Pointd> que = new ArrayList<Pointd>();
		Pointd p = new Pointd(y, x, d);
		que.add(p);
		while(que.size() > 0){
			p = que.remove(0);
			y = p.y;
			x = p.x;
			d = p.d -1;
			if(used[y][x]) continue;
			used[y][x] = true;
			if(b[y][x]){
				return new Point(y, x);
			}
			{
				y = p.y -1;
				x = p.x;
				if(y >= 0 && d >= 0 && !used[y][x]){
					p = new Pointd(y, x, d);
					que.add(p);
				}
			}
			{
				y = p.y;
				x = p.x-1;
				if(x >= 0 && d >= 0 && !used[y][x]){
					p = new Pointd(y, x, d);
					que.add(p);
				}
			}
			{
				y = p.y +1;
				x = p.x;
				if(y < N && d >= 0 && !used[y][x]){
					p = new Pointd(y, x, d);
					que.add(p);
				}
			}
			{
				y = p.y;
				x = p.x+1;
				if(x < N && d >= 0 && !used[y][x]){
					p = new Pointd(y, x, d);
					que.add(p);
				}
			}
		}
		
		
		return null;
	}
	private String getDir(Point t, int y, int x){
		int Y = t.y - y;
		int X = t.x - x;
		int aY = abs(Y);
		int aX = abs(X);
		if(aY < aX){
			if(X < 0){
				return L;
			}
			else{
				return R;
			}
		}
		else if(aY > aX){
			if(Y < 0){
				return U;
			}
			else{
				return D;
			}
		}
		
		int r = rnd.nextInt(2);
		if(r == 1){
			if(X < 0){
				return L;
			}
			else{
				return R;
			}
		}
		else{
			if(Y < 0){
				return U;
			}
			else{
				return D;
			}
		}
	}
	
	private String getRndDir(){
		int r = rnd.nextInt(4);
		
		if(r == 0){
			return "U";
		}
		else if(r == 1){
			return "D";
		}
		else if(r == 2){
			return "L";
		}
		else{
			return "R";
		}
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
	
	class Point{
		public Point(int y, int x){
			this.y = y;
			this.x = x;
		}
		int y = 0;
		int x = 0;
	}
	
	class Pointd{
		public Pointd(int y, int x, int d){
			this.y = y;
			this.x = x;
			this.d = d;
		}
		int y = 0;
		int x = 0;
		int d = 0;
	}
	
	class Worker{
		
		int y = 0;
		int x = 0;
		Point t = null;
		int workerID = 0;
		boolean canmove = false;
	
	}
	
	
	public static void main(String[] args) throws Exception{
		SnowCleaning02low s = new SnowCleaning02low();
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
