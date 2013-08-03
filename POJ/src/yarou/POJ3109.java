package yarou;



import java.util.*;
import java.io.*;
import static java.lang.Math.*;


// とりあえずTLEでもで書く
// MLE。。。
public class POJ3109 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{
		P[] ps  = new P[N];
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for(int i = 0; i < N; i++){
			P p = new P();
			p.x = sc.nextInt();
			p.y = sc.nextInt();
			ps[i] = p;
			if(!map.containsKey(p.x)){
				Set<Integer> set = new HashSet<Integer>();
				map.put(p.x, set);
			}
			map.get(p.x).add(p.y);
		}
		Arrays.sort(ps, new Comparator<P>() {
			@Override
			public int compare(P p1, P p2) {
				if(p1.x != p2.x){
					return p1.x - p2.x;
				}
				return p1.y - p2.y;
			}
		});
		
		
		List<Pair> yp = new ArrayList<Pair>();
		for(int i = 0; i < ps.length-1; i++){
			if(ps[i].x != ps[i+1].x)continue;
			int lidx = i+1;
			while(lidx + 1 < ps.length && ps[i].x == ps[lidx+1].x){
				lidx++;
			}
			Pair pair = new Pair();
			pair.s = ps[i].y;
			pair.l = ps[lidx].y;
			pair.base = ps[i].x;
			yp.add(pair);
			i = lidx;
		}
		
		Arrays.sort(ps, new Comparator<P>() {
			@Override
			public int compare(P p1, P p2) {
				if(p1.y != p2.y){
					return p1.y - p2.y;
				}
				return p1.x - p2.x;
			}
		});
		List<Pair> xp = new ArrayList<Pair>();
		for(int i = 0; i < ps.length-1; i++){
			if(ps[i].y != ps[i+1].y)continue;
			int lidx = i+1;
			while(lidx + 1 < ps.length && ps[i].y == ps[lidx+1].y){
				lidx++;
			}
			Pair pair = new Pair();
			pair.s = ps[i].x;
			pair.l = ps[lidx].x;
			pair.base = ps[i].y;
			xp.add(pair);
			i = lidx;
		}
		
		int ans = 0;
		for(int i = 0; i < yp.size(); i++){
			int xnow = yp.get(i).base;
			int sy = yp.get(i).s;
			int ly = yp.get(i).l;
			for(int j = 0; j < xp.size(); j++){
				int y = xp.get(j).base;
				if(y < sy || ly < y) continue;
				if(xp.get(j).s > xnow || xp.get(j).l < xnow) continue;
				try{
					if(!map.get(xnow).contains(y)){
						ans++;
						map.get(xnow).add(y);
					}
				}
				catch(Exception ex){
					
				}
			}
		}
		ans += ps.length;
		out.println(ans);
	}
	
	class Pair{
		int base = 0;
		int s = 0;
		int l = 0;
	}

	class P{
		int x = 0;
		int y = 0;
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
		POJ3109 p = new POJ3109();

		while(true){
			try{
				N = sc.nextInt();
				if(N == 0){
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
