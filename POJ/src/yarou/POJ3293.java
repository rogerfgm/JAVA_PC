package yarou;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class POJ3293 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	Map<Integer, Map<Integer, Data>> map = null;
	public void solve() throws Exception{
		int[][] ps = new int[N][2];
		for(int i = 0; i < N; i++){
			ps[i][0] = sc.nextInt();
			ps[i][0] = sc.nextInt();
		}
		Arrays.sort(ps, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]){
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}

		});
		
	
		long ans = -1;
		int idx = 0;
		map = new HashMap<Integer, Map<Integer, Data>>();
		Set<Integer> set = new HashSet<Integer>();
		while(idx < N){
			int x = ps[idx][0];
			List<Integer> ys = new ArrayList<Integer>();
			ys.add(ps[idx][1]);
			while(idx < N-1 && ps[idx][0] == ps[idx+1][0]){
				ys.add(ps[idx+1][0]);
				idx++;
			}
			if(ys.size() >= 2){
				for(int i = 0; i < ys.size()-1; i++){
					for(int j = i+1; j < ys.size(); j++){
						int y1 = ys.get(i);
						int y2 = ys.get(j);
						try{
							Data d = map.get(y1).get(y2);
							ans = max(ans, d.len + 2 * d.x + y2 - y1);
						}
						catch(Exception e){
							if(set.contains(y1) || set.contains(y2)){
								if(set.contains(y1)){
									add(y1, y2, x);
								}
								else{
									add(y2, y1, x);
								}
							}

							Data nd = new Data(x, y2-y1);
							if(!map.containsKey(y1)){
								map.put(y1, new HashMap<Integer, Data>());
							}
							map.get(y1).put(y2, nd);
						};
						
						set.add(y1);
						set.add(y2);
					}
				}
			}
			
			idx++;
		}
		out.println(ans);
	}
	
	void add(int oy, int ny, int x){
		if(map.containsKey(oy)){
			for(int ny2 : map.get(oy).keySet()){
				if(ny > ny2){
					continue;
				}
				Data d = map.get(oy).get(ny2);
				long len = d.len + (x - d.x) * 2 + abs(ny-oy);
				if(!map.containsKey(ny)){
					map.put(ny, new HashMap<Integer, Data>());
				}
				if(map.get(ny).containsKey(ny2)){
					Data od = map.get(ny).get(ny2);
					long olen = od.len + (x - od.x)* 2; 
					if(olen >= len){
						continue;
					}
				}
				Data nd = new Data(x, len);
				map.get(ny).put(ny2, nd);
			}
		}
		else{
			for(int ny2 : map.keySet()){
				if(map.get(ny2).containsKey(oy)){
					if(ny < ny2){
						continue;
					}
					//ny2 < ny
					Data d = map.get(ny2).get(oy);
					long len = d.len + (x - d.x) * 2 + abs(ny-oy);
					if(map.get(ny2).containsKey(ny)){
						Data od = map.get(ny2).get(ny);
						long olen = od.len + (x - od.x)* 2; 
						if(olen >= len){
							continue;
						}
					}
					Data nd = new Data(x, len);
					map.get(ny2).put(ny, nd);
				}
			}
		}
	}
	
	class Data{
		public Data(long x, long len){
			this.x = x;
			this.len = len;
		}
		long x = 0;
		long len = 0;
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
		POJ3293 p = new POJ3293();
		int T = sc.nextInt();
		int t = 1;
		while(t++ <= T){
			try{
				N = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if( N == 0) break;
			p.solve();
		}
	}

}
